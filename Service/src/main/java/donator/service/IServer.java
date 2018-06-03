package donator.service;


import donator.entities.Donator;
import donator.entities.Programari;

import java.rmi.RemoteException;

public interface IServer {
    //DateSange[] getSange() throws DonatorException;
    //  DateSange[] cautareSange() throws DonatorException;

    void adaugaDonator(Donator donator, Programari programari) throws DonatorException,RemoteException;
    void planificare(Donator d,Programari donator)throws DonatorException,RemoteException;
    void cerereSange(String tipSange, int numar)throws  DonatorException,RemoteException;
    Programari cautaPlanifica(int id)throws DonatorException,RemoteException;
    Donator cautareDonator(String mail)throws DonatorException,RemoteException;
    //boolean verificareDonator(Donator donator) throws DonatorException,RemoteException;
}
