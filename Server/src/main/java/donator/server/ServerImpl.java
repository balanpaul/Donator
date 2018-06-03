package donator.server;

import donator.persistence.DonatorRepository;
import donator.entities.Donator;
import donator.service.DonatorException;
import donator.service.IServer;

import java.rmi.RemoteException;

public class ServerImpl implements IServer {

    private DonatorRepository donatorRepository;

    public ServerImpl(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    @Override
    public void adaugaDonator(Donator donator) throws DonatorException, RemoteException {
        System.out.println("Sunt in server " + donator.getNume());
        donatorRepository.save(donator);
    }

    @Override
    public void updateDonator(Donator donator) throws DonatorException, RemoteException {
        donatorRepository.update(donator);
    }

    @Override
    public void cerereSange(String tipSange, int numar) throws DonatorException, RemoteException {

    }

    @Override
    public Donator cautareDonator(String mail) throws DonatorException, RemoteException {
        Donator donator=null;
        donator=donatorRepository.findOne(mail);
        if(donator==null)
            throw new DonatorException("Nu exista acest donator");
        return donator;
    }
}
