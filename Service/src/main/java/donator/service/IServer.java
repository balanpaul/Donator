package donator.service;

import donator.entities.DateSange;
import donator.entities.Donator;

import java.rmi.RemoteException;

public interface IServer {
    DateSange[] getSange() throws DonatorException;
    DateSange[] cautareSange() throws DonatorException;
    void adaugaDonator(Donator donator) throws DonatorException,RemoteException;
    void cerereSange(String tipSange, int numar)throws  DonatorException,RemoteException;
    boolean verificareDonator(Donator donator) throws DonatorException,RemoteException;
}
