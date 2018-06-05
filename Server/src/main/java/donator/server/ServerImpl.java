package donator.server;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import donator.entities.*;
import donator.persistence.*;

import donator.service.DonatorException;
import donator.service.IServer;
import donator.validatori.DonatorValidare;
import donator.validatori.ValidationException;
import org.xml.sax.SAXException;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
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
    private CentreRepository centreRepository;
    private DateSangeCentreRepository dateSangeCentreRepository;
    private DonatorValidare donatorValidare=new DonatorValidare();

    public ServerImpl(DonatorRepository donatorRepository, ProgramariRepository programariRepository, PersonalRepository personalRepository, ChestionarRepository chestionarRepository, DateSangeRepository dateSangeRepository, ObservatiiRepository observatiiRepository, CentreRepository centreRepository, DateSangeCentreRepository dateSangeCentreRepository) {
        this.donatorRepository = donatorRepository;
        this.programariRepository = programariRepository;
        this.personalRepository = personalRepository;
        this.chestionarRepository = chestionarRepository;
        this.dateSangeRepository = dateSangeRepository;
        this.observatiiRepository = observatiiRepository;
        this.centreRepository = centreRepository;
        this.dateSangeCentreRepository = dateSangeCentreRepository;
    }

    @Override
    public void adaugaDonator(Donator donator, Programari programari) throws DonatorException, RemoteException, ValidationException {
        donatorValidare.validate(donator);
        int i = programariRepository.nrProg(programari);
        if (i == 5)
            throw new DonatorException("Nu mai sunt locuri disponibile in aceasta perioada");

        long id = donatorRepository.save(donator);
        Donator d = donatorRepository.findMail(donator.getEmail());
        programari.setDonator(d);
        programariRepository.save(programari);
        System.out.println("Sunt in server " + donator.getNume());

    }

    @Override
    public void adaugaChestionar(Chestionar chestionar) throws DonatorException, RemoteException {
        chestionarRepository.save(chestionar);
        System.out.println("Sunt in server " + chestionar.getIdDonator().getNume());
    }

    @Override
    public void adaugaObservatie(Observatii observatie) throws DonatorException, RemoteException {
        observatiiRepository.save(observatie);
        System.out.println("Sunt in server " + observatie.getIdObservatia() + " " );
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
            throw new DonatorException("Nu exista planificare pentru acest id");
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
    public List<Donator> getAll() throws DonatorException, RemoteException {
        List<Donator> donators=donatorRepository.findAll();
        List<Donator> list=new ArrayList<>();
        for(Donator donator :donators){
            if(donator.getCnp()==null)
                donator.setCnp("-");

            list.add(donator);
        }
        return list;
    }

    @Override
    public List<DateSange> getNeverificati() throws DonatorException, RemoteException {
        List<DateSange> dateSanges = dateSangeRepository.getSange();
        List<DateSange> list = new ArrayList<>();
        for(DateSange ds : dateSanges){
            if(ds.getSanatos() == 0){
                list.add(ds);
            }
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
    public DateSange getDateSangeDonator(int id) throws DonatorException, RemoteException {
        return dateSangeRepository.getSangeD(id);
    }


    @Override
    public ArrayList<Observatii> listaObservatii(int idSange) throws DonatorException, RemoteException {
       ArrayList<Observatii> observaties= (ArrayList<Observatii>) observatiiRepository.listaObservatii(idSange);
        return  observaties;
    }

    @Override
    public void trimitereMail(String mailto) throws DonatorException, RemoteException {
        exportPDF(mailto);
        String to = mailto;
        String subject = "subject";
        String msg = "email text....";
        final String from = "killlerbas@gmail.com";
        final String password = "issproiect";

        String attachmentPath="Istoric.pdf";
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
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(mailto));
            message.setSubject("Istoricul donatiilor");
            message.setText("Istoricul donatii");

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

    /*@Override
    public List<Observatii> listaObservatii(int idSange) throws DonatorException, RemoteException {
        return observatiiRepository.listaObservatii(idSange);
    }*/


    public void exportPDF(String mail) throws DonatorException, RemoteException {
       Donator donator=donatorRepository.findMail(mail);
       DateSange dateSange=dateSangeRepository.getSangeD(donator.getIdDonator());
        Document document = new Document();
        Paragraph paragraph;
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Istoric.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            for(DateSange sange : dateSangeRepository.getAllSange(donator.getIdDonator())) {
                for (Observatii obs : observatiiRepository.listaObservatii(sange.getIdSange())) {
                    paragraph = new Paragraph(" GRUPA SANGUINA : " + sange.getGrupaSanguina() + ", SANATOS: " + sange.getSanatos() + ",Data prelevari : " + sange.getDataRecolta().toString() + "\n", font);
                    document.add(paragraph);
                    // paragraph =new Paragraph()
                }
            }
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
    public List<Donator> filtrareDonatorDupaNume(String nume, String prenume)throws DonatorException, RemoteException{
        List<Donator> lista = new ArrayList<>();

        for(Donator donator : donatorRepository.findOne(nume, prenume)) {
            if(donator.getCnp() == null)
                donator.setCnp("-");

            lista.add(donator);
        }
        return lista;
    }

    @Override
    public List<Programari> getProgramari(int id) throws DonatorException, RemoteException{
        return programariRepository.findAllProg(id);
    }


    @Override
    public List<Donator> filtrareDonatorDupaNumeSiData(String nume, String prenume, Date date)throws DonatorException, RemoteException{
        List<Donator> lista = new ArrayList<>();

        for(Donator donator : donatorRepository.findOne(nume, prenume)) {
            for(Programari programari : programariRepository.findAllProg(donator.getIdDonator()))
                if(programari.getDataD() == date) {
                    if (donator.getCnp() == null)
                        donator.setCnp("-");

                    lista.add(donator);
                }
        }
        return lista;
    }
    public String getCordinates(String address) throws IOException, ParserConfigurationException, SAXException, DonatorException, XPathExpressionException {
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
            org.w3c.dom.Document document =  builder.parse(httpConnection.getInputStream());
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
                String co=latitude + " "+ longitude;
                return  co ;
            }
            else
            {
                throw new DonatorException("Error from the API - response status: "+status);
            }
        }
        return null;
    }


    @Override
    public List<Centru> listaCentre() throws DonatorException, RemoteException{
        ArrayList<Centru> lista = (ArrayList<Centru>) centreRepository.findAll();
        return lista;
    }



    boolean isInList(ArrayList<String> list, String string){
        for(String str:list){
            if(str.compareTo(string) == 0){
                return true;
            }
        }
        return false;
    }

    public List<Centru> cautaCentre(String grupaSange, int unitatiSanguine, boolean trombocite, boolean plasma, boolean globuleRosii) throws RemoteException, DonatorException {
        ArrayList<DatesangeCentre> listaSangeCentre = (ArrayList<DatesangeCentre>) dateSangeCentreRepository.findAll();
        ArrayList<String> listaGrupeCompatibile = (ArrayList<String>)grupeCompatibile(grupaSange);
        ArrayList<Centru> rezultat = new ArrayList<>();
        for(DatesangeCentre dsc:listaSangeCentre){
            DateSange sange = dsc.getIdDateSange();
            if(isInList(listaGrupeCompatibile, sange.getGrupaSanguina())){
                if(sange.getPlasma() == 1 && sange.getGlobuleRosii() == 1 && sange.getTrombocite() == 1){
                    rezultat.add(dsc.getIdCentru());
                }
            }
        }




        return rezultat;
    }

    @Override
    public List<Centru> cautaCentreUrgenta(String grupaSange, int unitatiSanguine, boolean trombocite, boolean plasma, boolean globuleRosii) throws RemoteException, DonatorException {
        return cautaCentre(grupaSange, unitatiSanguine, trombocite, plasma, globuleRosii);
    }

    @Override
    public List<Centru> cautaCentreNormala(String grupaSange, int unitatiSanguine, boolean trombocite, boolean plasma, boolean globuleRosii) throws RemoteException, DonatorException {
        ArrayList<Centru> list = (ArrayList<Centru>) cautaCentre(grupaSange, unitatiSanguine, trombocite, plasma, globuleRosii);
        ArrayList<Centru> rezultat = new ArrayList<>();
        int cont;
        for(int i=0;i<list.size();i++){
            cont=0;
            for(int j=0;j<list.size();j++){
                if(list.get(i).getIdCentru() == list.get(j).getIdCentru() && i != j) {
                    cont++;
                    list.remove(j);
                }
            }
            rezultat.add(list.get(i));
        }
        return rezultat;
    }

    @Override
    public List<String> grupeCompatibile(String grupaSanguina) throws DonatorException, RemoteException{
        ArrayList<String> list = new ArrayList<>();
        if(grupaSanguina.compareTo("0+") == 0){
            list.add("0+");
            list.add("0-");
            return list;
        }
        if (grupaSanguina.compareTo("A+") == 0){
            list.add("A+");
            list.add("A-");
            list.add("0+");
            list.add("0-");
            return list;
        }
        if (grupaSanguina.compareTo("B+") == 0){
            list.add("B+");
            list.add("B-");
            list.add("0+");
            list.add("0-");
            return list;
        }
        if (grupaSanguina.compareTo("AB+") == 0){
            list.add("A+");
            list.add("A-");
            list.add("0+");
            list.add("0-");
            list.add("AB+");
            list.add("AB-");
            list.add("B+");
            list.add("B-");
            return list;
        }
        if(grupaSanguina.compareTo("0-") == 0){
            list.add("0-");
            return list;
        }
        if(grupaSanguina.compareTo("A-") == 0){
            list.add("A-");
            list.add("0-");
            return list;
        }
        if(grupaSanguina.compareTo("B-") == 0){
            list.add("B-");
            list.add("0-");
            return list;
        }
        if(grupaSanguina.compareTo("A-") == 0){
            list.add("A-");
            list.add("B-");
            list.add("AB-");
            list.add("0-");
            return list;
        }
        return null;
    }

    @Override
    public void adaugareCentruRecoltare(DatesangeCentre datesangeCentre) throws DonatorException, RemoteException {
        dateSangeCentreRepository.save(datesangeCentre);
    }

    @Override
    public List<DateSange> sangeNerverificat() throws DonatorException, RemoteException {
        return dateSangeRepository.dateSanges();
    }

    @Override
    public List<DateSange> recent(Date date) throws DonatorException, RemoteException {
        return dateSangeRepository.recent(date);
    }

    @Override
    public void verificare(Donator d, DateSange dateSange) throws DonatorException, RemoteException {
        dateSange.setDonator(d);
        dateSangeRepository.update(dateSange);
    }
}
