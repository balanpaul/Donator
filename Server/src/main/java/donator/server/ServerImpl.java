package donator.server;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import donator.entities.*;
import donator.persistence.*;

import donator.service.DonatorException;
import donator.service.IServer;
import org.xml.sax.SAXException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
        exportPDF(mailto);
        String to = mailto;
        String subject = "subject";
        String msg = "email text....";
        final String from = "balanpaul16@gmail.com";
        final String password = "Manchester1918";

        String attachmentPath="E:\\Donator\\Istoric.pdf";
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
                    InternetAddress.parse(mailto));
            message.setSubject(subject);
            message.setText("Istoicul donatii");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            File att = new File(attachmentPath);
            messageBodyPart.attachFile(att);

            DataSource source = new FileDataSource(att);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Istoricul donatiilor");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            System.out.println("Sending");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Observatie> listaObservatii(int idSange) throws DonatorException, RemoteException {
        return null;
    }


    private void exportPDF(String mail)  {
       Donator donator=donatorRepository.findMail(mail);
       DateSange dateSange=dateSangeRepository.getSangeD(donator.getIdDonator());
        Document document = new Document();
        Paragraph paragraph;
        try {
            PdfWriter.getInstance(document, new FileOutputStream("E:\\Donator\\Istoric.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            for(DateSange sange : dateSangeRepository.getAllSange(donator.getIdDonator())) {

                paragraph = new Paragraph(" GRUPA SANGUINA : " + sange.getGrupaSanguina() + ", SANATOS: " + sange.getSanatos() + ",Data prelevari : " + sange.getDataRecolta().toString() + "\n", font);
                document.add(paragraph);
               // paragraph =new Paragraph()
            }
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Chestionar cautareChestionar(String mail) throws DonatorException, RemoteException {
        Donator donator = donatorRepository.findMail(mail);
        Chestionar ch = chestionarRepository.findChestionar(donator.getIdDonator());
        return ch;
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

    @Override
    public List<String> getAll() throws DonatorException, RemoteException {
        return null;
    }
    public static String[] getCordinates(String address) throws IOException, ParserConfigurationException, SAXException, DonatorException, XPathExpressionException {
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
        System.out.println("URL : "+api);
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();
        String status = null;
        if(responseCode == 200)
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
            Document document = (Document) builder.parse(httpConnection.getInputStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = null;
            try {
                expr = xpath.compile("/GeocodeResponse/status");
                status= (String)expr.evaluate(document, XPathConstants.STRING);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

            if(status.equals("OK"))
            {
                expr = xpath.compile("//geometry/location/lat");
                String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
                expr = xpath.compile("//geometry/location/lng");
                String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
                return new String[] {latitude, longitude};
            }
            else
            {
                throw new DonatorException("Error from the API - response status: "+status);
            }
        }
        return null;
    }
}
