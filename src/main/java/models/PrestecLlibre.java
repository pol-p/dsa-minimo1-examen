package models;

public class PrestecLlibre {
    String idPrestec;
    String idLector;
    String idISBN;
    String idLibreCatalogat;
    String dataPrestec;
    String dataDevolucio;
    String estadoPrestamo;

    public PrestecLlibre() {
    }

    public PrestecLlibre(String idPrestec, String idLector, String idISBN, String dataPrestec, String dataDevolucio) {
        this.idPrestec = idPrestec;
        this.idLector = idLector;
        this.idISBN = idISBN;
        this.dataPrestec = dataPrestec;
        this.dataDevolucio = dataDevolucio;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public String getIdLector() {
        return idLector;
    }

    public void setIdLector(String idLector) {
        this.idLector = idLector;
    }

    public String getIdPrestec() {
        return idPrestec;
    }

    public void setIdPrestec(String idPrestec) {
        this.idPrestec = idPrestec;
    }

    public String getIdISBN() {
        return idISBN;
    }

    public void setIdISBN(String idISBN) {
        this.idISBN = idISBN;
    }

    public String getIdLibreCatalogat() {
        return idLibreCatalogat;
    }

    public void setIdLibreCatalogat(String idLibreCatalogat) {
        this.idLibreCatalogat = idLibreCatalogat;
    }

    public String getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(String dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public String getDataDevolucio() {
        return dataDevolucio;
    }

    public void setDataDevolucio(String dataDevolucio) {
        this.dataDevolucio = dataDevolucio;
    }

    @Override
    public String toString() {
        return "PrestecLlibre{" +
                "idPrestec='" + idPrestec + '\'' +
                ", idLlibre='" + idISBN + '\'' +
                ", idLibreCatalogat='" + idLibreCatalogat + '\'' +
                ", dataPrestec='" + dataPrestec + '\'' +
                ", dataDevolucio='" + dataDevolucio + '\'' +
                '}';
    }
}
