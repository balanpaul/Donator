package donator.validatori;

import donator.entities.Donator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alex on 26 oct. 2017.
 */
public class DonatorValidare implements Validator<Donator> {

    @Override
    public void validate(Donator element) throws ValidationException{

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher;

        String errMsg= "" ;

        //nume
        matcher = pattern.matcher(element.getNume());
        if(element.getNume() == null || "".equals(element.getNume()))
            errMsg += "Introduceti un nume!";
        else if(!element.getNume().matches("[0-9]+") || !matcher.matches())
            errMsg += "Numele trebuie sa contina doar litere!";

        //prenume
        matcher = pattern.matcher(element.getPrenume());
        if(element.getPrenume() == null || "".equals(element.getPrenume()))
            errMsg += "Introduceti un prenume!";
        else if(!element.getPrenume().matches("[0-9]+") || !matcher.matches())
            errMsg += "Prenumele trebuie sa contina doar litere!";

        //strada
        matcher = pattern.matcher(element.getStrada());
        if(element.getStrada() == null || "".equals(element.getStrada()))
            errMsg += "Introduceti o strada!";
        else if(!matcher.matches())
            errMsg += "Strada nu poate sa contina simboluri!";

        //numar
        matcher = pattern.matcher(element.getNumar());
        if(element.getNumar() == null || "".equals(element.getNumar()))
            errMsg += "Introduceti un numar!";
        else if(!element.getNumar().matches("[0-9]+") || !matcher.matches())
            errMsg += "Numarul trebuie sa contina doar cifre!";

        //bloc
        matcher = pattern.matcher(element.getBloc());
        if(element.getBloc() == null || "".equals(element.getBloc()))
            errMsg += "Introduceti un bloc!";
        else if(!matcher.matches())
            errMsg += "Blocul nu poate sa contina simboluri!";

        //scara
        matcher = pattern.matcher(element.getScara());
        if(element.getScara() == null || "".equals(element.getScara()))
            errMsg += "Introduceti o scara!";
        else if(!matcher.matches())
            errMsg += "Scara nu poate sa contina simboluri!";

        //apartament
        matcher = pattern.matcher(element.getApartament());
        if(element.getApartament() == null || "".equals(element.getApartament()))
            errMsg += "Introduceti un apartament!";
        else if(!matcher.matches())
            errMsg += "Apartamentul nu poate sa contina simboluri!";

        //oras
        matcher = pattern.matcher(element.getOras());
        if(element.getOras() == null || "".equals(element.getOras()))
            errMsg += "Introduceti un oras!";
        else if(!element.getOras().matches("[0-9]+") || !matcher.matches())
            errMsg += "Orasul trebuie sa contina doar litere!";

        //judet
        matcher = pattern.matcher(element.getJudet());
        if(element.getJudet() == null || "".equals(element.getJudet()))
            errMsg += "Introduceti un judet!";
        else if(!element.getJudet().matches("[0-9]+") || !matcher.matches())
            errMsg += "Judetul trebuie sa contina doar litere!";

        //telefon
        matcher = pattern.matcher(element.getNrTelefon());
        if(element.getNrTelefon() == null || "".equals(element.getNrTelefon()))
            errMsg += "Introduceti un numar de telefon!";
        else if(!element.getNrTelefon().matches("[0-9]+") || !matcher.matches())
            errMsg += "Numarul de telefon trebuie sa contina doar cifre!";

        //email
        pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$");
        matcher = pattern.matcher(element.getEmail());
        if(element.getEmail() == null || "".equals(element.getEmail()))
            errMsg += "Introduceti un email!";
        else if(!matcher.matches())
            errMsg += "Email-ul nu este valid!";

        //cnp
        matcher = pattern.matcher(element.getCnp());
        if(element.getCnp() == null || "".equals(element.getCnp()))
            errMsg += "Introduceti un CNP!";
        else if(!element.getCnp().matches("[0-9]+") || !matcher.matches())
            errMsg += "CNP-ul trebuie sa contina doar cifre!";


        if(errMsg != "")
            throw new ValidationException(errMsg);
    }

}
