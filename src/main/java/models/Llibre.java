package models;

public class Llibre {
    String idLlibre;
    String isbn;
    String titol;
    String ediorial;
    String anyPublicacio;
    Integer numEdicio;
    String autor;
    String tematica;

    public Llibre() {
    }

    public Llibre(String idLlibre, String isbn, String titol, String ediorial, String anyPublicacio, Integer numEdicio, String autor, String tematica) {
        this.idLlibre = idLlibre;
        this.isbn = isbn;
        this.titol = titol;
        this.ediorial = ediorial;
        this.anyPublicacio = anyPublicacio;
        this.numEdicio = numEdicio;
        this.autor = autor;
        this.tematica = tematica;
    }

    public String getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(String idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getEdiorial() {
        return ediorial;
    }

    public void setEdiorial(String ediorial) {
        this.ediorial = ediorial;
    }

    public String getAnyPublicacio() {
        return anyPublicacio;
    }

    public void setAnyPublicacio(String anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public Integer getNumEdicio() {
        return numEdicio;
    }

    public void setNumEdicio(Integer numEdicio) {
        this.numEdicio = numEdicio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    @Override
    public String toString() {
        return "Llibre{" +
                "idLlibre='" + idLlibre + '\'' +
                ", isbn='" + isbn + '\'' +
                ", titol='" + titol + '\'' +
                ", ediorial='" + ediorial + '\'' +
                ", anyPublicacio='" + anyPublicacio + '\'' +
                ", numEdicio=" + numEdicio +
                ", autor='" + autor + '\'' +
                ", tematica='" + tematica + '\'' +
                '}';
    }
}
