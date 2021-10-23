package pumpers;

import entity.CsvSerializable;
import org.hibernate.Session;
import utils.CscWriter;

import java.io.IOException;
import java.util.List;

public abstract class AbstractPumper {

    protected Session session;
    CscWriter writer = new CscWriter();


    public AbstractPumper() {
//        session = HibernateUtil.getSessionFactory().openSession();

    }

    public void run() {
//        session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
        List<CsvSerializable> pump = pump();

        try {
            writer.write(pump);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        session.getTransaction().commit();
//        HibernateUtil.shutdown();
    }

    protected abstract List<CsvSerializable> pump();
}
