package donator.persistence;


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

    public  long save(Donator entity){
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

    public void delete(Donator donator) {
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx=session.beginTransaction();
                //session.delete("Zbor",zbor);
                session.createQuery("delete from donator.entities.Donator where cnp  = "+donator.getCnp()).executeUpdate();

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

        List<Donator> donators = new ArrayList<>();
        Transaction tx = null;
        try
        {
            Session session = sessionFactory.openSession();


            tx = session.beginTransaction();
            Query query = session.createQuery("FROM donator.entities.Donator");
            donators = (ArrayList<Donator>) query.getResultList();

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

    public List<Donator> findOne(String string,String pre) {
        Session session =sessionFactory.openSession();
        Donator z=null;
        ArrayList<Donator> donators = null;
        Transaction tx=null;
        try{
            tx=session.beginTransaction();

             donators = (ArrayList<Donator>) session.createQuery("select D from Donator D where D.nume= :num and D.prenume= :pre").setParameter("num",string).setParameter("pre",pre).getResultList();
            //z=donators.get(0);
            tx.commit();
        }catch (RuntimeException ex){
            if(tx!=null)
                tx.rollback();
        }finally {
            session.close();
        }

        return donators;
    }

    public Donator findMail(String string) {
        Session session =sessionFactory.openSession();
        Donator z=null;
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            Query query = session.createQuery("FROM donator.entities.Donator");
            ArrayList<Donator> donators = (ArrayList<Donator>) query.getResultList();
            for(Donator d:donators){
                if(d.getEmail().equals(string))
                    z=d;
            }
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
    public Donator findId(long id) {
        Session session =sessionFactory.openSession();
        Donator z=null;
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
             z= (Donator) session.get(Donator.class,id);
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
}
