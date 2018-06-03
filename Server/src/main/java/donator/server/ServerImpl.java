package donator.server;

import donator.entities.Personal;
import donator.entities.Chestionar;
import donator.entities.Programari;

import donator.entities.Donator;

import donator.persistence.ChestionarRepository;
import donator.persistence.DonatorRepository;
import donator.persistence.PersonalRepository;
import donator.persistence.ProgramariRepository;
import donator.service.DonatorException;
import donator.service.IServer;

import java.rmi.RemoteException;

public class ServerImpl implements IServer {

    private DonatorRepository donatorRepository;
    private ProgramariRepository programariRepository;
    private PersonalRepository personalRepository;
    private ChestionarRepository chestionarRepository;



    public ServerImpl(DonatorRepository donatorRepository, ProgramariRepository programariRepository, PersonalRepository personalRepository, ChestionarRepository chestionarRepository) {
        this.donatorRepository = donatorRepository;
        this.programariRepository = programariRepository;
        this.personalRepository = personalRepository;
        this.chestionarRepository = chestionarRepository;
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
        Donator d=donatorRepository.findOne(donator.getNume());
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
    public Chestionar cautareChestionar(String mail) throws DonatorException, RemoteException{
        Donator donator=donatorRepository.findMail(mail);
         Chestionar ch = chestionarRepository.findChestionar(donator.getIdDonator());
         return ch;
    }
}
