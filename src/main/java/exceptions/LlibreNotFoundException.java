package exceptions;

public class LlibreNotFoundException extends RuntimeException {
    public LlibreNotFoundException(String idVol) {
        super("El Llibre con ISBN --> " + idVol + "No existe" );
    }
}
