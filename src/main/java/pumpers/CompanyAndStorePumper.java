package pumpers;

import com.github.javafaker.Faker;
import config.PumperConfig;
import entity.*;
import me.tongfei.progressbar.ProgressBar;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

public class CompanyAndStorePumper extends AbstractPumper {


    private static final long EPOCH_FROM = 1420074000; // January 1, 2015 1:00:00 AM
    private static final int DIFFERENCE = 212976000;

    private final Random random = new Random();

    private int nextAddress = 0;

    private final List<Address> addresses = new ArrayList<Address>();

    List<String> voivodeships = Arrays.asList(
            "dolnośląskie",
            "mazowieckie",
            "dolnośląskie",
            "dolnośląskie",
            "mazowieckie",
            "kujawsko-pomorskie",
            "lubelskie",
            "lubuskie",
            "łódzkie",
            "małopolskie",
            "dolnośląskie",
            "mazowieckie",
            "dolnośląskie",
            "dolnośląskie",
            "mazowieckie"
    );

    @Override
    public void pump() {

        ArrayList<Company> companies = new ArrayList<Company>();
        var stores = new ArrayList<Store>();


        final int processors = Runtime.getRuntime().availableProcessors();
        final ExecutorService threads = Executors.newFixedThreadPool(processors);

        ProgressBar pb1 = new ProgressBar("Addresses generator", PumperConfig.ADDRESSES_AMOUNT);
        int batchSize = PumperConfig.ADDRESSES_AMOUNT / processors;

        List<Callable<List<Address>>> tasksAddresses = new ArrayList<Callable<List<Address>>>();

        for (int i = 0; i < processors; i++) {
            int finalI = i * batchSize;
            int lastIndex = i == processors - 1 ? PumperConfig.ADDRESSES_AMOUNT : (i + 1) * batchSize;

            tasksAddresses.add(() -> {
                try (pb1) { // name, initial max
                    // Use ProgressBar("Test", 100, ProgressBarStyle.ASCII) if you want ASCII output style
                    var addresses = new ArrayList<Address>();
                    for (int run = finalI; run < lastIndex; run++) {
                        Address fakeAddress = generateFakeAddress(run);
                        addresses.add(fakeAddress);
                        pb1.step();
                    }
                    return addresses;
                } // progress bar stops automatically after completion of try-with-resource block
            });
        }

        try {
            List<Future<List<Address>>> results = threads.invokeAll(tasksAddresses);
            results.forEach(res -> {
                try {
                    addresses.addAll(res.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        Collections.shuffle(addresses);

        batchSize = PumperConfig.COMPANIES_AMOUNT / processors;

        var tasksCompanies = new ArrayList<Callable<List<Company>>>();
        ProgressBar pbc = new ProgressBar("Companies generator", PumperConfig.COMPANIES_AMOUNT);

        for (int i = 0; i < processors; i++) {
            int finalI = i * batchSize;
            int lastIndex = i == processors - 1 ? PumperConfig.COMPANIES_AMOUNT : (i + 1) * batchSize;

            tasksCompanies.add(() -> {
                try (pbc) {
                    var comps = new ArrayList<Company>();
                    for (int run = finalI; run < lastIndex; run++) {
                        Company fakeCompany = generateFakeCompany(run);
                        int addressId = getNextAddressId();
                        fakeCompany.setAddressId(addressId);
                        comps.add(fakeCompany);
                        pbc.step();
                    }
                    return comps;
                } // progress bar stops automatically after completion of try-with-resource block
            });
        }

        try {
            List<Future<List<Company>>> results = threads.invokeAll(tasksCompanies);
            results.forEach(res -> {
                try {
                    companies.addAll(res.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        Collections.shuffle(companies);

        try (ProgressBar pb = new ProgressBar("Stores generator", PumperConfig.STORES_AMOUNT)) { // name, initial max
            // Use ProgressBar("Test", 100, ProgressBarStyle.ASCII) if you want ASCII output style

            for (int run = 0; run < PumperConfig.STORES_AMOUNT; run++) {
                Store fakeStore = generateFakeStore(run);
                int addressId = getNextAddressId();
                fakeStore.setAddressId(addressId);
                stores.add(fakeStore);
                pb.step();
            }
        } // progress bar stops automatically after completion of try-with-resource block
        Collections.shuffle(stores);

        int firstClass = (int) (PumperConfig.STORE_COMPANIES_AMOUNT * 0.3);
        int secondClass = (int) (PumperConfig.STORE_COMPANIES_AMOUNT * 0.5);

        int storeIndex = 0;

        var storeCompanies = companies.subList(0, PumperConfig.STORE_COMPANIES_AMOUNT);

        while (storeIndex < PumperConfig.STORES_AMOUNT) {
            if (storeIndex < PumperConfig.STORES_AMOUNT * 0.6) {
                for (int comp = 0; comp < firstClass; comp++) {
                    stores.get(storeIndex).setOwnerCompanyId(storeCompanies.get(comp).getCompanyId());
                    storeIndex++;
                }
            } else if (storeIndex < PumperConfig.STORES_AMOUNT * 0.8) {
                for (int comp = firstClass; comp < secondClass; comp++) {
                    stores.get(storeIndex).setOwnerCompanyId(storeCompanies.get(comp).getCompanyId());
                    storeIndex++;
                }
            } else if (storeIndex < PumperConfig.STORES_AMOUNT * 0.95) {
                for (int comp = secondClass; comp < PumperConfig.STORE_COMPANIES_AMOUNT; comp++) {
                    stores.get(storeIndex).setOwnerCompanyId(storeCompanies.get(comp).getCompanyId());
                    storeIndex++;
                }
            } else {
                stores.get(storeIndex).setOwnerCompanyId(storeCompanies.get(random.nextInt(storeCompanies.size())).getCompanyId());
                storeIndex++;
            }
        }

        try {
            writer.write((List<CsvSerializable>) (List<?>) companies);
            writer.write((List<CsvSerializable>) (List<?>) stores);
            writer.write((List<CsvSerializable>) (List<?>) addresses);
        } catch (IOException e) {
            e.printStackTrace();
        }

        threads.shutdown();
    }

    private entity.Company generateFakeCompany(int id) {

        var faker = new Faker();

        var companyName = faker.company().name();
        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();

        var random_seconds = Integer.valueOf(random.nextInt(DIFFERENCE)).longValue();

        var date = Timestamp.from(Instant.ofEpochSecond(EPOCH_FROM + random_seconds));

        var nip = faker.regexify("([0-1]){10}");

        return entity.Company.builder()
                .companyId(id)
                .name(companyName)
                .ownerFirstName(firstName)
                .owenerLastName(lastName)
                .creationDate(date)
                .nip(nip)
                .build();
    }

    private Store generateFakeStore(int id) {

        var faker = new Faker();

        var companyName = faker.company().name();

        var random_seconds = Integer.valueOf(random.nextInt(DIFFERENCE)).longValue();

        var date = Timestamp.from(Instant.ofEpochSecond(EPOCH_FROM + random_seconds));

        return entity.Store.builder()
                .storeId(id)
                .name(companyName)
                .creationDate(date)
                .build();
    }

    private Address generateFakeAddress(int id) {

        var faker = new Faker();

        var voivodeship = voivodeships.get(random.nextInt(voivodeships.size()));

        var address = faker.address();
        var city = address.city();
        var street = address.streetName();
        var streetNumber = address.streetAddressNumber();
        var postal = address.zipCode();

        return entity.Address.builder()
                .addressId(id)
                .voivodeship(voivodeship)
                .city(city)
                .postalCode(postal)
                .street(street)
                .addressNumber(streetNumber)
                .build();
    }

    private synchronized int getNextAddressId() {
        return addresses.get(nextAddress++).getAddressId();
    }

}
