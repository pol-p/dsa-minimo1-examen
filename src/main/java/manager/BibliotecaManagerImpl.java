package manager;


import dto.PrestamosDelLectorDTO;
import exceptions.LectorNotFoundException;
import exceptions.LlibreNotFoundException;

import java.util.*;

import exceptions.NoQuedanLibrosParaCatalogarException;
import models.Lector;
import models.Llibre;
import models.LlibreCatalogat;
import models.PrestecLlibre;
import org.apache.log4j.Logger;

public class BibliotecaManagerImpl implements BibliotecaManager {
    private static BibliotecaManager fm;
    private static final Logger LOGGER = Logger.getLogger(BibliotecaManagerImpl.class);
    //BASES DE DATOS
    private Map<String, Lector> mapLectors;
    private Map<String, LlibreCatalogat> mapLlibresCatalogats;
    private Queue<Stack<Llibre>> magatzemLlibres;
    private ArrayList<PrestecLlibre> listPrestecLlibres;

    private BibliotecaManagerImpl(){
        this.mapLectors = new HashMap<>();
        this.mapLlibresCatalogats = new HashMap<>();
        this.magatzemLlibres = new LinkedList<>();
        this.magatzemLlibres.add(new Stack<Llibre>());
        this.listPrestecLlibres = new ArrayList<>();
        LOGGER.info("manager.(Singleton) creado e inicializado.");
    }

    public static BibliotecaManager getInstance(){
        if(fm == null){
            LOGGER.info("Creando nueva instancia de manager");
            fm = new BibliotecaManagerImpl();
        }
        return fm;
    }



    // Helpers

    private Lector getLectorById(String idLector) throws LectorNotFoundException {
        LOGGER.info("INICIO getAvioById: id=" + idLector);

        Lector a = this.mapLectors.get(idLector);
        if(a == null){
            // Log de error antes de lanzar la excepción
            LOGGER.error("ERROR en getLectorById: Lector no encontrado con ID: " + idLector);
            throw new LectorNotFoundException(idLector);
        }

        LOGGER.info("FIN getLectorById: Lector encontrado.");
        return a;
    }

    private LlibreCatalogat getLlibreCatalogatByISBN(String idLlibre) throws LlibreNotFoundException {
        LOGGER.info("INICIO getVolById: id=" + idLlibre);

        LlibreCatalogat v = this.mapLlibresCatalogats.get(idLlibre);
        if (v == null){
            // Log de error antes de lanzar la excepción
            LOGGER.error("ERROR en getVolById: Vuelo no encontrado con ID: " + idLlibre);
            throw new LlibreNotFoundException(idLlibre);
        }

        LOGGER.info("FIN getVolById: Vuelo encontrado.");
        return v;
    }
    //

    //Funcioens interface

    @Override
    public void añadirLector(Lector l) {
        LOGGER.info("INICIO añadirLector: " + l);
        Lector lector = new Lector(l.getIdLector(), l.getNomLector(), l.getCognomLector(), l.getDni(), l.getDataNaixement(), l.getAdrecça());
        this.mapLectors.put(lector.getIdLector(), lector);
        LOGGER.info("Lector añadido: " + lector);
    }

    @Override
    public void enmagatzemarLlibre(Llibre llibre) {
        LOGGER.info("INICIO enmagatzemarLlibre: " + llibre);
        Llibre llibreNew = new Llibre(llibre.getIdLlibre(), llibre.getIsbn(), llibre.getTitol(), llibre.getEdiorial(), llibre.getAnyPublicacio(), llibre.getNumEdicio(), llibre.getAutor(), llibre.getTematica());
        Stack<Llibre> ultimaPila = obtenerUltimaPila();
        if(ultimaPila.size() < 10){
            ultimaPila.add(llibreNew);
        }
        else{
            LOGGER.info("Pila plena, creando nueva pila para el libro: " + llibreNew);
            Stack<Llibre> newPila = new Stack<>();
            this.magatzemLlibres.add(newPila);
            newPila.push(llibreNew);
            LOGGER.info("Llibre emmagatzemat a nova pila: " + llibreNew);
            return;
        }
        LOGGER.info("Llibre emmagatzemat: " + llibreNew);
    }

    @Override
    public void catalogarLlibre() {
        LOGGER.info("INICIO catalogarLlibre");

        if (magatzemLlibres.isEmpty()) {
            LOGGER.error("ERROR: No hay pilas en el almacén, no se puede catalogar");
            throw new NoQuedanLibrosParaCatalogarException("No hay libros pendientes de catalogar");
        }

        Stack<Llibre> primeraPila = ((LinkedList<Stack<Llibre>>) magatzemLlibres).getFirst();

        if(primeraPila.isEmpty()){
            LOGGER.error("ERROR: No hay libros pendientes de catalogar");
            throw new NoQuedanLibrosParaCatalogarException("No hay libros pendientes de catalogar");
        }

        Llibre llibreACatalogar = primeraPila.pop();
        LlibreCatalogat llibreExistent = null;
        for (LlibreCatalogat lc : mapLlibresCatalogats.values()) {
            if (lc.getLlibreCatalogat().getIsbn().equals(llibreACatalogar.getIsbn())) {
                llibreExistent = lc;
                break;
            }
        }
        if (llibreExistent != null) {
            llibreExistent.incrementarExemplars();
            LOGGER.info("Llibre ya catalogado, ejemplars incrementats: " + llibreExistent);
        } else {
            LlibreCatalogat newLlibreCatalogat = new LlibreCatalogat(llibreACatalogar);
            this.mapLlibresCatalogats.put(newLlibreCatalogat.getLlibreCatalogat().getIsbn(), newLlibreCatalogat);
            LOGGER.info("Llibre catalogat: " + newLlibreCatalogat);
        }

        if(primeraPila.isEmpty()){
            ((LinkedList<Stack<Llibre>>) magatzemLlibres).removeFirst();
            LOGGER.info("Primera pila vacía eliminada, siguiente pila es ahora la primera");
        }

        LOGGER.info("FIN catalogarLlibre");
    }


    @Override
    public void prestarLlibre(PrestecLlibre pl) {
        LOGGER.info("INICIO prestarLlibre: " + pl);
        LlibreCatalogat llibreCatalogat = getLlibreCatalogatByISBN(pl.getIdISBN());
        Lector lector = getLectorById(pl.getIdLector());
        if (llibreCatalogat.getNumExemplars() > 0) {
            llibreCatalogat.decrementarExemplars();
            pl.setEstadoPrestamo("En tràmit");
            listPrestecLlibres.add(pl);
            LOGGER.info("Llibre prestat: " + pl + " al lector: " + lector);
        } else {
            LOGGER.error("ERROR: No hay ejemplares disponibles para prestar del libro con ISBN: " + pl.getIdISBN());
            throw new LlibreNotFoundException("No hay ejemplares disponibles para prestar del libro con ISBN: " + pl.getIdISBN());
        }
    }

    @Override
    public List<PrestecLlibre> getLlibresPrestacsOfLector(PrestamosDelLectorDTO pdl) {
        LOGGER.info("INICIO getLlibresPrestacsOfLector: " + pdl);
        Lector lector = getLectorById(pdl.getIdLector());
        List<PrestecLlibre> listaADevolver = new ArrayList<>();
        for(PrestecLlibre pl: this.listPrestecLlibres){
            if(pl.getIdLector().equals(pdl.getIdLector())){
                LOGGER.info("Llibre prestat trobat: " + pl + " per al lector: " + lector);
                listaADevolver.add(pl);
            }
        }
        LOGGER.info("FIN getLlibresPrestacsOfLector para el lector: " + lector);
        return listaADevolver;
    }
    //

    //HELPER
    private Stack<Llibre> obtenerUltimaPila() {

        if (magatzemLlibres.isEmpty()) {
            Stack<Llibre> nuevaPila = new Stack<>();
            magatzemLlibres.add(nuevaPila);
            LOGGER.info("No había pilas, creada una nueva pila en el almacén.");
            return nuevaPila;
        }

        Stack<Llibre> ultimaPila = ((LinkedList<Stack<Llibre>>) magatzemLlibres).getLast();
        LOGGER.info("Obtenida la última pila del almacén.");
        return ultimaPila;
    }
    //
}