package dto;

public class PrestamosDelLectorDTO {
    private String idLector;

    public PrestamosDelLectorDTO() {
    }

    public PrestamosDelLectorDTO(String idLector) {
        this.idLector = idLector;
    }

    public String getIdLector() {
        return idLector;
    }

    public void setIdLector(String idLector) {
        this.idLector = idLector;
    }

    @Override
    public String toString() {
        return "DTOPrestamoDelLector{" +
                "idLector='" + idLector + "}";
    };
}
