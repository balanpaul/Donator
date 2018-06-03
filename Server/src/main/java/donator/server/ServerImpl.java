package donator.server;

import donator.entities.*;
import donator.persistence.*;

import donator.service.DonatorException;
import donator.service.IServer;

import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Properties;


public class ServerImpl implements IServer {

    private DonatorRepository donatorRepository;
    private ProgramariRepository programariRepository;
    private PersonalRepository personalRepository;
    private ChestionarRepository chestionarRepository;
    private DateSangeRepository dateSangeRepository;
    private ObservatiiRepository observatiiRepository;


    public ServerImpl(DonatorRepository donatorRepository, ProgramariRepository programariRepository, PersonalRepository personalRepository, ChestionarRepository chestionarRepository, DateSangeRepository dateSangeRepository, ObservatiiRepository observatiiRepository) {
        this.donatorRepository = donatorRepository;
        this.programariRepository = programariRepository;
        this.personalRepository = personalRepository;
        this.chestionarRepository = chestionarRepository;
        this.dateSangeRepository = dateSangeRepository;
        this.observatiiRepository = observatiiRepository;
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
    public void adaugaObservatie(Observatie observatie) throws DonatorException, RemoteException {
        observatiiRepository.save(observatie);
        System.out.println("Sunt in server " + observatie.getIdObservatie() + " " + observatie.getIdObservatie());
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
    public List<String> getDonatori() throws DonatorException, RemoteException {
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
    public void trimitereMail(String to) throws DonatorException, RemoteException {

    }

    @Override
    public List<Observatie> listaObservatii(int idSange) throws DonatorException, RemoteException {
        return observatiiRepository.listaObservatii(idSange);
    }

    @Override
    public void exportPDF(String mail) throws DonatorException, RemoteException {
       /*Donator donator=donatorRepository.findMail(mail);
       DateSange dateSange=dateSangeRepository.getSangeD(donator.getIdDonator());

        Paragraph paragraph;

        for(DateSange student : dateSangeRepository.getAllSange(donator.getIdDonator())){

                try {
                    paragraph = new Paragraph("  ID: " + student.getId() + ", NAME: " + student.getName() + ", FINAL GRADE: " + res + "\n", font);
                    document.add(paragraph);
                } catch (DocumentException ex)




        }*/
    }


    @Override
    public Chestionar cautareChestionar(String mail) throws DonatorException, RemoteException {
        Donator donator = donatorRepository.findMail(mail);
        Chestionar ch = chestionarRepository.findChestionar(donator.getIdDonator());
        return ch;
    }

    @Override
    public List<String> getDonatori() throws DonatorException, RemoteException {
        return null;
    }

    @Override
    public List<String> filtrareDonatorDupaNume(String nume, String prenume)throws DonatorException, RemoteException{
        List<String> lista = new ArrayList<>();

        for(Donator donator : donatorRepository.findOne(nume, prenume)) {
            if(donator.getCnp() == null)
                lista.add(donator.getNume() + "  " + donator.getPrenume() + "  " + "-" + "  " + donator.getNrTelefon());
            else
                lista.add(donator.getNume() + "  " + donator.getPrenume() + "  " + donator.getCnp() + "  " + donator.getNrTelefon());
        }
        return lista;
    }
}
