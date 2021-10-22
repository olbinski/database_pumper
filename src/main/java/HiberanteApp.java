import entity.Company;
import org.hibernate.Session;
import pumpers.AddressPumpers;
import pumpers.ProductPumper;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class HiberanteApp {

    public static void main(String[] args) throws SQLException {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        //Add new Employee object
//
//
//        Company company = Company.builder().id(1L).creationDate(Timestamp.from(Instant.now())).build();
//        session.save(company);
//
//        session.getTransaction().commit();
//        HibernateUtil.shutdown();

        var addPumper = new ProductPumper() ;

        addPumper.run();
    }
}
