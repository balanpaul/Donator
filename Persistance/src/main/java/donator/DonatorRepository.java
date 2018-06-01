package donator;

import donator.entities.Donator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DonatorRepository {
    private SessionFactory sessionFactory;

    public DonatorRepository() {
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

    public  void save(Donator entity){
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx=session.beginTransaction();
                session.save(entity);
                tx.commit();
            }catch (RuntimeException ex){
                if(tx!=null)
                    tx.rollback();
            }
            finally {
                session.close();
            }
        }
    }

    public void delete(Donator donator) {
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx=session.beginTransaction();
                //session.delete("Zbor",zbor);
                session.createQuery("delete from donatori where cnp = "+donator.getCnp()).executeUpdate();

                //session.delete(zbor);
                tx.commit();

            }catch (Exception e){
                if(tx!=null)
                    tx.rollback();
            }
            finally {
                session.close();
            }
        }
    }


    public void update( Donator entity) {
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

    public List<Donator> findAll() {
        System.out.println("asdasd");

        List<Donator> zborList = new ArrayList<>();
        Transaction tx = null;
        try
        {
            Session session = sessionFactory.openSession();


            tx = session.beginTransaction();


            Query query = session.createQuery("FROM donatori");
            zborList = (ArrayList<Donator>) query.getResultList();

            tx.commit();
        }
        catch (Exception ex)
        {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        }
        return zborList;
    }

    public Donator findOne(Integer integer) {
        Session session =sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            Donator z=session.get(Donator.class,integer);
            tx.commit();
            return z;
        }catch (RuntimeException ex){
            if(tx!=null)
                tx.rollback();
        }finally {
            session.close();
        }

        return null;
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
}
