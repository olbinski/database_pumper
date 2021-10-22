package pumpers;

import org.hibernate.Session;
import utils.HibernateUtil;

public abstract class AbstractPumper {

    protected Session session;

    public AbstractPumper() {
        session = HibernateUtil.getSessionFactory().openSession();

    }

    public void run(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        pump();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    protected abstract void pump();
}
