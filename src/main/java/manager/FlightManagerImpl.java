package manager;


import exceptions.AvioNotFoundException;
import exceptions.VolNotFoundException;
import models.*;
import java.util.*;

import org.apache.log4j.Logger;

public class FlightManagerImpl implements FlightManager {
    private static FlightManager fm;
    private static final Logger LOGGER = Logger.getLogger(FlightManagerImpl.class);
    //BASES DE DATOS
    private Map<Integer, Avio> listAviones;
    private Map<Integer, Vol> listVols;
    private Integer contadorMalet;

    private FlightManagerImpl(){
        this.listAviones = new HashMap<>();
        this.listVols = new HashMap<>();
        this.contadorMalet = 0;
        LOGGER.info("manager.(Singleton) creado e inicializado.");
    }

    public static FlightManager getInstance(){
        if(fm == null){
            LOGGER.info("Creando nueva instancia de manager");
            fm = new FlightManagerImpl();
        }
        return fm;
    }



    // (Helpers)

    private Class getXById(String idAvio) throws AvioNotFoundException {
        LOGGER.info("INICIO getAvioById: id=" + idAvio);

        Avio a = this.listAviones.get(idAvio);
        if(a == null){
            // Log de error antes de lanzar la excepción
            LOGGER.error("ERROR en getAvioById: Avión no encontrado con ID: " + idAvio);
            throw new AvioNotFoundException(idAvio);
        }

        LOGGER.info("FIN getAvioById: Avión encontrado.");
        return a;
    }

    private Vol getVolById(String idVol) throws VolNotFoundException {
        LOGGER.info("INICIO getVolById: id=" + idVol);

        Vol v = this.listVols.get(idVol);
        if (v == null){
            // Log de error antes de lanzar la excepción
            LOGGER.error("ERROR en getVolById: Vuelo no encontrado con ID: " + idVol);
            throw new VolNotFoundException(idVol);
        }

        LOGGER.info("FIN getVolById: Vuelo encontrado.");
        return v;
    }
}