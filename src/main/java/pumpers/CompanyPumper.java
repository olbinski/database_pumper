package pumpers;

import com.github.javafaker.Faker;
import config.PumperConfig;
import entity.CsvSerializable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompanyPumper extends AbstractPumper {


    private static final long EPOCH_FROM = 1420074000; // January 1, 2015 1:00:00 AM
    private static final int DIFFERENCE = 212976000;

    private final Random random = new Random();

    @Override
    protected List<CsvSerializable> pump() {

        var companies = new ArrayList<CsvSerializable>();

        for (int i = 0; i < PumperConfig.COMPANIES_AMOUNT; i++) {
            companies.add(generateFakeCompany(i));
        }
        return companies;
    }

    private entity.Company generateFakeCompany(int id) {

        var faker = new Faker();

        var companyName = faker.company().name();
        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();

        var random_seconds = Integer.valueOf(random.nextInt(DIFFERENCE)).longValue();

        var date = Timestamp.from(Instant.ofEpochSecond(EPOCH_FROM + random_seconds));


        //TODO address_id, nip

        return entity.Company.builder()
                .companyId(id)
                .name(companyName)
                .ownerFirstName(firstName)
                .owenerLastName(lastName)
                .creationDate(date)
                .build();
    }
}
