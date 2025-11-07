package models;

public class LlibreCatalogat {
    Llibre llibre;
    Integer numExemplars;

    public LlibreCatalogat() {
    }

    public LlibreCatalogat(Llibre llibreCatalogat) {
        this.llibre = llibreCatalogat;
        this.numExemplars = 1;
    }

    public Llibre getLlibre() {
        return llibre;
    }

    public void setLlibre(Llibre llibre) {
        this.llibre = llibre;
    }


    public Llibre getLlibreCatalogat() {
        return llibre;
    }

    public void setLlibreCatalogat(Llibre llibreCatalogat) {
        this.llibre = llibreCatalogat;
    }

    public Integer getNumExemplars() {
        return numExemplars;
    }

    public void setNumExemplars(Integer numExemplars) {
        this.numExemplars = numExemplars;
    }

    public void incrementarExemplars() {
        this.numExemplars++;
    }

    public void decrementarExemplars() {
        if (this.numExemplars > 0) {
            this.numExemplars--;
        }
    }

    @Override
    public String toString() {
        return "LlibreCatalogat{" +
                "llibre=" + llibre +
                ", numExemplars=" + numExemplars +
                '}';
    }
}
