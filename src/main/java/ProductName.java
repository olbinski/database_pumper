import com.github.javafaker.Faker;

public class ProductName {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            Faker faker = new Faker();
            System.out.println(faker.commerce().department());
//            System.out.println(faker.commerce().productName());
        }
    }
}
