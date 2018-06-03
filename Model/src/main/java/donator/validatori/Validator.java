package donator.validatori;

/**
 * Created by Alex on 26 oct. 2017.
 */
public interface Validator<E> {

    public void validate(E element) throws ValidationException;
}
