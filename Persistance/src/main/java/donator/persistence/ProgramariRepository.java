package donator.persistence;

import donator.entities.Donator;
import donator.entities.Programari;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProgramariRepository {
    private SessionFactory sessionFactory;

    public ProgramariRepository() {
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

    public  void save(Programari entity){
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

    public Programari findProg(int id) {
        Session session =sessionFactory.openSession();
        Programari z=null;
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
           /*

            SQLString = "select A from " + Ad.class.getSimpleName() +" A JOIN A.category B where B.__Id =:category_id ";
           return centityManager.createQuery(SQLString, Ad.class).setParameter("category_id", Long.valueOf(8)).setMaxResults(10)
                    .getResultList();
*/
           z = (Programari) session.createQuery("SELECT A FROM Programari A join fetch A.donator B where B.IdDonator = :donator").setParameter("donator",id).
                   setMaxResults(1).getResultList().get(0);
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
}
