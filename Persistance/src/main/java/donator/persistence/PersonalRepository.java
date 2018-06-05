package donator.persistence;

import donator.entities.Donator;
import donator.entities.Personal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PersonalRepository {
    private SessionFactory sessionFactory;

    public PersonalRepository() {
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
    public Personal find(String par) {
        Session session =sessionFactory.openSession();
        Personal z=null;
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            z= (Personal) session.createQuery("select P from Personal P join fetch P.centru C where P.parola= :par").setParameter("par",par).getSingleResult();
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
