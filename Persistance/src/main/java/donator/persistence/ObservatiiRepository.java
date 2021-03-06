package donator.persistence;

import donator.entities.Observatie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ObservatiiRepository {
    private SessionFactory sessionFactory;

    public ObservatiiRepository() {
        try {
            initialize();
        }catch (Exception e){
            System.out.println("\n\neroare?\n\n"+e.getMessage());
            e.printStackTrace();
            close();
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

    private void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public  long save(Observatie entity){
        long id= 0;
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx=session.beginTransaction();
                session.save(entity);

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

        return 1;
    }

    public List<Observatie> listaObservatii(int idS) {
        System.out.println("asdasd");

        List<Observatie> obs = new ArrayList<>();
        Transaction tx = null;
        try
        {
            Session session = sessionFactory.openSession();


            tx = session.beginTransaction();


            Query query = session.createQuery("SELECT A FROM Observatie A join fetch A.idSange B where B.IdDonator = :idS").setParameter("idS",idS);
            obs = (ArrayList<Observatie>) query.getResultList();

            tx.commit();
        }
        catch (Exception ex)
        {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        }
        return obs;
    }
}
