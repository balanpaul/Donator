package donator.server;

import com.itextpdf.text.Paragraph;
import donator.entities.*;
import donator.persistence.*;
import donator.service.DonatorException;
import donator.service.IServer;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


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
    public void adaugaDonator(Donator donator, Programari programari) throws DonatorException, RemoteException {
        int i = programariRepository.nrProg(programari);
        if (i == 5)
            throw new DonatorException("Nu mai sunt locuri disponibile in aceasta perioada");
        long id = donatorRepository.save(donator);
        Donator d = donatorRepository.findMail(donator.getEmail());
        programari.setDonator(d);
        programariRepository.save(programari);
        System.out.println("Sunt in server " + donator.getNume());
        donatorRepository.save(donator);
    }

    @Override
    public void adaugaChestionar(Chestionar chestionar) throws DonatorException, RemoteException {
        chestionarRepository.save(chestionar);
        System.out.println("Sunt in server " + chestionar.getIdDonator().getNume());
    }

    @Override
    public void planificare(Donator d, Programari programari) throws DonatorException, RemoteException {
        int i = programariRepository.nrProg(programari);
        if (i >= 5)
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
        Programari programari = null;
        programari = programariRepository.findProg(id);
        if (programari == null)
            throw new DonatorException("Nu existaplanificare pentru acest id");
        return programari;
    }

    @Override
    public Donator cautareDonator(String mail) throws DonatorException, RemoteException {
        Donator donator = null;
        donator = donatorRepository.findMail(mail);
        if (donator == null)
            throw new DonatorException("Nu exista acest donator");
        return donator;
    }

    @Override
    public Personal cautarePersonal(String pass) throws DonatorException, RemoteException {
        Personal personal = personalRepository.find(pass);
        if (personal == null)
            throw new DonatorException("Parola invalida");
        return personal;
    }

    @Override
    public List<String> getDonatori() throws DonatorException, RemoteException {
        List<Donator> donators = donatorRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Donator donator : donators) {
            String s;
            if (donator.getCnp() == null) {
                s = "-";
            } else
                s = donator.getCnp();
            String line = donator.getNume() + "  " + donator.getPrenume() + "  " + s + "  " + donator.getNrTelefon();
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
    public void trimitereMail(String mailto) throws DonatorException, RemoteException {
        String to = mailto;
        String subject = "subject";
        String msg = "email text....";
        final String from = "balanpaul16@gmail.com";
        final String password = "Manchester1918";


        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        //session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("maplabs@scs.ubbcluj.ro"));
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText("sadsa");

            System.out.println("Sending");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
}
