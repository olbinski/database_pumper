import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Random;

public class App {

    public static void main(String args[]) {
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:ORCLCDB", "dawid", "dawid");

//step3 create the statement object
            Faker faker = new Faker();

            faker.commerce().productName();

            Statement stmt = con.createStatement();

//step4 execute query
            Random random = new Random();
            for (int i = 1000; i < 300000; i++) {
                stmt.executeQuery("INSERT INTO \"DAWID\".\"PRODUCTS\" (PRODUCT_ID, NAME, NET_PRICE_CURRENT, VAT, BARCODE) VALUES (" + i + ", 'bike', "+ random.nextInt(1000)+", '23', '1123321312')\n");

            }
            ResultSet rs = stmt.executeQuery("select * from products");


            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

//step5 close the connection object
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
