package pumpers;

import config.PumperConfig;
import dto.ProductDto;
import entity.CsvSerializable;
import entity.Invoice;
import entity.InvoiceItem;
import entity.Price;
import me.tongfei.progressbar.ProgressBar;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class InvoicePumper extends AbstractPumper {

    private final Map<Integer, ProductDto> products;
    private final List<Integer> productIds;
    private List<Integer> clientsIds;  // TODO
    private List<Integer> suppliersIds; // TODO

    public InvoicePumper(List<ProductDto> products) {
        this.products = products.stream().collect(Collectors.toMap(ProductDto::getProductId, x -> x));
        this.productIds = this.products.keySet().stream().toList(); // TODO: make more chances to get some products
    }

    private static final long EPOCH_FROM = 1420074000; // January 1, 2015 1:00:00 AM
    private static final int DIFFERENCE = 212976000;
    private static final int TWO_WEEKS = 1209600;

    private static final int BATCH_SIZE = 1;
    private final Random random = new Random();
    private int nextInvoiceItemId = 1;
    private int nextInvoiceId = 1;

    @Override
    public void pump() {

        var invoices = new ArrayList<CsvSerializable>();
        var invoicesItems = new ArrayList<InvoiceItem>();


        try (ProgressBar pb = new ProgressBar("Invoice generator", PumperConfig.INVOICE_AMOUNT)) { // name, initial max
            // Use ProgressBar("Test", 100, ProgressBarStyle.ASCII) if you want ASCII output style

            for (int run = 0; run < PumperConfig.INVOICE_AMOUNT; run++) {
                Invoice fakeInvoice = createFakeInvoice();
                for (int i1 = 0; i1 < random.nextInt(PumperConfig.MAX_ITEMS_PER_INVOICE_AMOUNT); i1++) {
                    var invoiceItem = createFakeInvoiceItem(fakeInvoice.getInvoiceId(), fakeInvoice.getInvoiceDate());
                    invoicesItems.add(invoiceItem);
                }
                invoices.add(fakeInvoice);
                pb.step();
            }
        } // progress bar stops automatically after completion of try-with-resource block
        writer.write(invoices);
    }


    private Invoice createFakeInvoice() {
        long invoiceDateSeconds = random.nextInt(DIFFERENCE) + EPOCH_FROM;
        long supplyDateSeconds = random.nextInt(TWO_WEEKS) + invoiceDateSeconds;

        int supplierId = 1; // TODO
        int clientId = random.nextInt(PumperConfig.COMPANIES_AMOUNT); // TODO


        return Invoice.builder()
                .invoiceId(getNextInvoiceId())
                .invoiceDate(Timestamp.from(Instant.ofEpochSecond(invoiceDateSeconds)))
                .supplyDate(Timestamp.from(Instant.ofEpochSecond(supplyDateSeconds)))
                .supplierId(supplierId)
                .clientId(clientId)
                .build();
    }

    private InvoiceItem createFakeInvoiceItem(int invoiceId, Timestamp timestamp) {


        Integer productId = this.productIds.get(random.nextInt(this.productIds.size()));

        ProductDto productDto = this.products.get(productId);
        Price price = productDto.getPrice(timestamp);

        BigDecimal discount = BigDecimal.ZERO;
        if (PumperConfig.DISCOUNT_CHANCE < random.nextDouble()) {
            discount = PumperConfig.DISCOUNT_POSSIBILITIES.get(random.nextInt(PumperConfig.DISCOUNT_POSSIBILITIES.size()));
        }

        return InvoiceItem.builder()
                .invoiceItemId(getNextInvoiceItemId())
                .invoiceId(invoiceId)
                .itemId(price.getProductId())
                .netPrice(price.getNetPrice())
                .vat(price.getVat())
                .quantity(random.nextInt(10))
                .discount(discount)
                .build();
    }

    private int getNextInvoiceItemId() {
        return nextInvoiceItemId++;
    }

    private int getNextInvoiceId() {
        return nextInvoiceId++;
    }

}
