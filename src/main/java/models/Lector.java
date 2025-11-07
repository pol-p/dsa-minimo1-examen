package models;

public class Lector {
    String idLector;
    String nomLector;
    String cognomLector;
    String dni;
    String dataNaixement;
    String adrecça;

    public Lector() {
    }

    public Lector(String idLector, String nomLector, String cognomLector, String dni, String dataNaixement, String adrecça) {
        this.idLector = idLector;
        this.nomLector = nomLector;
        this.cognomLector = cognomLector;
        this.dni = dni;
        this.dataNaixement = dataNaixement;
        this.adrecça = adrecça;
    }

    public String getIdLector() {
        return idLector;
    }

    public void setIdLector(String idLector) {
        this.idLector = idLector;
    }

    public String getNomLector() {
        return nomLector;
    }

    public void setNomLector(String nomLector) {
        this.nomLector = nomLector;
    }

    public String getCognomLector() {
        return cognomLector;
    }

    public void setCognomLector(String cognomLector) {
        this.cognomLector = cognomLector;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getAdrecça() {
        return adrecça;
    }

    public void setAdrecça(String adrecça) {
        this.adrecça = adrecça;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "idLector='" + idLector + '\'' +
                ", nomLector='" + nomLector + '\'' +
                ", cognomLector='" + cognomLector + '\'' +
                ", dni='" + dni + '\'' +
                ", dataNaixement='" + dataNaixement + '\'' +
                ", adrecça='" + adrecça + '\'' +
                '}';
    }
}
