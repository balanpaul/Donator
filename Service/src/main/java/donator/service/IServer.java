package donator.service;


import donator.entities.*;

import javax.sql.rowset.Predicate;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IServer {
    //DateSange[] getSange() throws DonatorException;
    //  DateSange[] cautareSange() throws DonatorException;

    void adaugaDonator(Donator donator, Programari programari) throws DonatorException,RemoteException;
    void adaugaChestionar(Chestionar chestionar) throws DonatorException,RemoteException;
    void adaugaObservatie(Observatie observatie) throws DonatorException, RemoteException;
    void planificare(Donator d,Programari donator)throws DonatorException,RemoteException;
    void cerereSange(String tipSange, int numar)throws  DonatorException,RemoteException;
    Programari cautaPlanifica(int id)throws DonatorException,RemoteException;
    Donator cautareDonator(String mail)throws DonatorException,RemoteException;
    Personal cautarePersonal(String pass)throws DonatorException,RemoteException;
    Chestionar cautareChestionar(String email) throws DonatorException, RemoteException;
    List<String> getDonatori()throws DonatorException,RemoteException;
    void recoltare(Donator donator, DateSange dateSange)throws DonatorException,RemoteException;
    List<DateSange> getSange()throws DonatorException,RemoteException;
    void trimitereMail(String to) throws DonatorException,RemoteException;
    List<Observatie> listaObservatii(int idSange) throws DonatorException, RemoteException;
    //boolean verificareDonator(Donator donator) throws DonatorException,RemoteException;
    List<String> filtrareDonatorDupaNume(String nume, String prenume)throws DonatorException,RemoteException;

}
