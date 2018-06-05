package donator.service;


import donator.entities.*;
import donator.validatori.ValidationException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IServer {
    //DateSange[] getSange() throws DonatorException;
    //  DateSange[] cautareSange() throws DonatorException;

    void adaugaDonator(Donator donator, Programari programari) throws DonatorException, RemoteException, ValidationException;
    void adaugaChestionar(Chestionar chestionar) throws DonatorException,RemoteException;
    void adaugaObservatie(Observatii observatie) throws DonatorException, RemoteException;
    void planificare(Donator d,Programari donator)throws DonatorException,RemoteException;
    void cerereSange(String tipSange, int numar)throws  DonatorException,RemoteException;
    Programari cautaPlanifica(int id)throws DonatorException,RemoteException;
    Donator cautareDonator(String mail)throws DonatorException,RemoteException;
    Personal cautarePersonal(String pass)throws DonatorException,RemoteException;
    Chestionar cautareChestionar(String email) throws DonatorException, RemoteException;
    List<Donator> getAll()throws DonatorException,RemoteException;
    List<DateSange> getNeverificati() throws DonatorException, RemoteException;
    void recoltare(Donator donator, DateSange dateSange)throws DonatorException,RemoteException;
    List<DateSange> getSange()throws DonatorException,RemoteException;
    DateSange getDateSangeDonator(int id) throws  DonatorException, RemoteException;
    void trimitereMail(String to) throws DonatorException,RemoteException;
    List<Observatii> listaObservatii(int idSange) throws DonatorException, RemoteException;
   String getCordinates(String address) throws IOException, ParserConfigurationException, SAXException, DonatorException, XPathExpressionException;
    List<DateSange> sangeNerverificat()throws DonatorException,RemoteException;
    List<DateSange> recent(Date date)throws DonatorException,RemoteException;
    void verificare(Donator d, DateSange dateSange)throws DonatorException,RemoteException;

    //boolean verificareDonator(Donator donator) throws DonatorException,RemoteException;
    List<Donator> filtrareDonatorDupaNume(String nume, String prenume)throws DonatorException,RemoteException;
    List<Programari> getProgramari(int id) throws DonatorException, RemoteException;
    public List<Donator> filtrareDonatorDupaNumeSiData(String nume, String prenume, Date date)throws DonatorException, RemoteException;
    void exportPDF(String mail) throws DonatorException, RemoteException;

    List<Centru> listaCentre() throws DonatorException, RemoteException;
    List<Centru> cautaCentreUrgenta(String grupaSange, int unitatiSanguine, boolean trombocite, boolean plasma, boolean globuleRosii) throws RemoteException, DonatorException;
    List<Centru> cautaCentreNormala(String grupaSange, int unitatiSanguine, boolean trombocite, boolean plasma, boolean globuleRosii) throws RemoteException, DonatorException;
    List<String> grupeCompatibile(String grupaSanguina) throws DonatorException, RemoteException;
    void adaugareCentruRecoltare(DatesangeCentre datesangeCentre)throws DonatorException, RemoteException;
    List<DateSange> getByDonator(int id);
}
