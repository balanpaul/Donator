package donator.server;

import donator.entities.*;
import donator.persistence.*;

import donator.service.DonatorException;
import donator.service.IServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements IServer {

    private DonatorRepository donatorRepository;
    private ProgramariRepository programariRepository;
    private PersonalRepository personalRepository;
    private ChestionarRepository chestionarRepository;
    private DateSangeRepository dateSangeRepository;


    public ServerImpl(DonatorRepository donatorRepository, ProgramariRepository programariRepository, PersonalRepository personalRepository, ChestionarRepository chestionarRepository, DateSangeRepository dateSangeRepository) {
        this.donatorRepository = donatorRepository;
        this.programariRepository = programariRepository;
        this.personalRepository = personalRepository;
        this.chestionarRepository = chestionarRepository;
        this.dateSangeRepository = dateSangeRepository;
    }

    public ServerImpl(ProgramariRepository programariRepository) {
        this.programariRepository = programariRepository;
    }

    public ServerImpl(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    @Override
    public void adaugaDonator(Donator donator,Programari programari) throws DonatorException, RemoteException {
        int i=programariRepository.nrProg(programari);
        if(i==5)
            throw  new DonatorException("Nu mai sunt locuri disponibile in aceasta perioada");
        long id=donatorRepository.save(donator);
        Donator d=donatorRepository.findMail(donator.getEmail());
        programari.setDonator(d);
        programariRepository.save(programari);
        System.out.println("Sunt in server " + donator.getNume());
        donatorRepository.save(donator);
    }

    @Override
    public void adaugaChestionar(Chestionar chestionar)throws DonatorException, RemoteException{
        chestionarRepository.save(chestionar);
        System.out.println("Sunt in server " + chestionar.getIdDonator().getNume());
    }

    @Override
    public void planificare(Donator d,Programari programari) throws DonatorException, RemoteException {
        int i=programariRepository.nrProg(programari);
        if(i>=5)
            throw new DonatorException("Nu mai sunt locuri disponibile in aceasta perioada");
        programari.setDonator(d);
        programariRepository.save(programari);

    }

    @Override
    public void cerereSange(String tipSange, int numar) throws DonatorException, RemoteException {
        //onatorRepository.findMail()
    }

    @Override
    public Programari cautaPlanifica(int id) throws DonatorException, RemoteException {
        Programari programari=null;
        programari= programariRepository.findProg(id);
        if(programari==null)
            throw new DonatorException("Nu existaplanificare pentru acest id");
        return programari;
    }

    @Override
    public Donator cautareDonator(String mail) throws DonatorException, RemoteException {
        Donator donator=null;
        donator=donatorRepository.findMail(mail);
        if(donator==null)
            throw new DonatorException("Nu exista acest donator");
        return donator;
    }

    @Override
    public Personal cautarePersonal(String pass) throws DonatorException, RemoteException {
        Personal personal=personalRepository.find(pass);
        if(personal==null)
            throw  new DonatorException("Parola invalida");
        return personal;
    }

    @Override
    public List<String> getAll() throws DonatorException, RemoteException {
        List<Donator> donators=donatorRepository.findAll();
        List<String> list=new ArrayList<>();
        for(Donator donator :donators){
            String s;
            if(donator.getCnp()==null) {
                 s="-";
            } else
                s=donator.getCnp();
            String line=donator.getNume()+"  "+donator.getPrenume()+"  "+s+"  "+donator.getNrTelefon();
            list.add(line);
        }
        return list;
    }

    @Override
    public void recoltare(Donator donator, DateSange dateSange) throws DonatorException, RemoteException {
        dateSange.setDonator(donator);
        dateSangeRepository.save(dateSange);
    }

    @Override
    public List<DateSange> getSange() throws DonatorException, RemoteException {
        return dateSangeRepository.getSange();
    }


    @Override
    public Chestionar cautareChestionar(String mail) throws DonatorException, RemoteException{
        Donator donator=donatorRepository.findMail(mail);
         Chestionar ch = chestionarRepository.findChestionar(donator.getIdDonator());
         return ch;
    }
}
