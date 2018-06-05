package donator.persistence;

import donator.entities.Centru;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CentreRepository {
    private SessionFactory sessionFactory;

    public CentreRepository() {
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

    public  void save(Centru entity){
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

    public Centru findCentru(int id) {
        Session session =sessionFactory.openSession();
        Centru z=null;
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            ArrayList<Centru> a = (ArrayList<Centru>) session.createQuery("SELECT A FROM Centru A join fetch A.idDonator B where B.IdDonator = :donator").setParameter("donator",id).getResultList();
            z=a.get(a.size()-1);
            tx.commit();
            return z;
        }catch (RuntimeException ex){
            if(tx!=null)
                tx.rollback();
        }finally {
            session.close();
        }

        return z;
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

    public List<Centru> findAll() {
        System.out.println("asdasd");

        List<Centru> donators = new ArrayList<>();
        Transaction tx = null;
        try
        {
            Session session = sessionFactory.openSession();


            tx = session.beginTransaction();
            Query query = session.createQuery("FROM donator.entities.Centru");
            donators = (ArrayList<Centru>) query.getResultList();

            tx.commit();
        }
        catch (Exception ex)
        {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        }
        return donators;
    }
}
