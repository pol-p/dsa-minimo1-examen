package manager;


import dto.PrestamosDelLectorDTO;
import models.Lector;
import models.Llibre;
import models.PrestecLlibre;

import java.util.List;

public interface BibliotecaManager {
    public void añadirLector(Lector l);
    public void enmagatzemarLlibre(Llibre llibre);
    public void catalogarLlibre();
    public void prestarLlibre(PrestecLlibre pl);
    public List<PrestecLlibre> getLlibresPrestacsOfLector(PrestamosDelLectorDTO pdl);
}
