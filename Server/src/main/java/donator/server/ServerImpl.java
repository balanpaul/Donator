package donator.server;

import donator.entities.Programari;
import donator.persistence.DonatorRepository;
import donator.entities.Donator;
import donator.persistence.ProgramariRepository;
import donator.service.DonatorException;
import donator.service.IServer;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

public class ServerImpl implements IServer {

    private DonatorRepository donatorRepository;
    private ProgramariRepository programariRepository;



    public ServerImpl(DonatorRepository donatorRepository, ProgramariRepository programariRepository) {
        this.donatorRepository = donatorRepository;
        this.programariRepository = programariRepository;
    }

    public ServerImpl(ProgramariRepository programariRepository) {
        this.programariRepository = programariRepository;
    }

    public ServerImpl(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    @Override
    public void adaugaDonator(Donator donator,Programari programari) throws DonatorException, RemoteException {
        long id=donatorRepository.save(donator);
        Donator d=donatorRepository.findOne(donator.getNume());
        programari.setDonator(d);
        programariRepository.save(programari);
        System.out.println("Sunt in server " + donator.getNume());

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
