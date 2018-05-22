package donator.service;

import donator.entities.DateSange;

public interface IServer {
    DateSange[] getSange() throws DonatorException;
    DateSange[] cautareSange();
}
