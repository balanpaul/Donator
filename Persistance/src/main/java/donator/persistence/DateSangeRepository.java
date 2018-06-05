package donator.persistence;

import donator.entities.DateSange;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DateSangeRepository {
    private SessionFactory sessionFactory;

    public DateSangeRepository() {
        try {
            initialize();
        }catch (Exception e){
            System.out.println("\n\neroare?\n\n"+e.getMessage());
            e.printStackTrace();
            close();
        }
    }
    private void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    private void initialize() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            //StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }

    }

    public  void save(DateSange entity){
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx=session.beginTransaction();
                session.save(entity);
                System.out.println("asd");
                tx.commit();
            }catch (RuntimeException ex){
                if(tx!=null)
                    tx.rollback();
                ex.printStackTrace();
            }
            finally {
                session.close();
            }
        }
    }

    public void update( DateSange entity) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            session.update(entity);
            tx.commit();
        }catch (Exception e){
            if(tx!=null)
                tx.rollback();;
        }finally {
            session.close();
        }
    }
    public DateSange getSangeD(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        DateSange dateSange = null;
        try {
            tx = session.beginTransaction();
            dateSange = (DateSange) session.createQuery("SELECT A FROM  DateSange A join fetch A.donator B where B.idDonator= :donator").setParameter("donator",id).
                    setMaxResults(1).getResultList().get(0);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            ;
        } finally {
            session.close();
        }
        return dateSange;
    }
    public List<DateSange> getAllSange(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<DateSange> dateSange = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            dateSange =  session.createQuery("SELECT A FROM  DateSange A join fetch A.donator B where B.idDonator= :donator").setParameter("donator",id).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            ;
        } finally {
            session.close();
        }
        return dateSange;
    }
    public List<DateSange> getSange() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<DateSange> dateSange = null;
        try {
            tx = session.beginTransaction();
            dateSange = (List<DateSange>) session.createQuery("SELECT A FROM  DateSange A join fetch A.donator B ").getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            ;
        } finally {
            session.close();
        }
        return dateSange;
    }
    public List<DateSange> dateSanges(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<DateSange> dateSange = null;
        try {
            tx = session.beginTransaction();
            dateSange = (List<DateSange>) session.createQuery("SELECT A FROM  DateSange A join fetch A.donator where A.dataRecolta=NULL ").getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            ;
        } finally {
            session.close();
        }
        return dateSange;
    }
    public List<DateSange> recent(Date date){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<DateSange> dateSange = null;
        try {
            tx = session.beginTransaction();
            dateSange = (List<DateSange>) session.createQuery("SELECT A FROM  DateSange A join fetch A.donator where A.dataRecolta>= :d ").setParameter("d",date).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            ;
        } finally {
            session.close();
        }
        return dateSange;
    }
}
