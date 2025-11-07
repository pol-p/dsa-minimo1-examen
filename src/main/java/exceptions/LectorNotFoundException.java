package exceptions;

public class LectorNotFoundException extends RuntimeException{
    public LectorNotFoundException(String idAvio){
        super("[!] Lector no encontrado con id --> " + idAvio + " [!]");
    }
}
