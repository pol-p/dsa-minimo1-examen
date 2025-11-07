package services;

import dto.PrestamosDelLectorDTO;
import exceptions.LectorNotFoundException;
import exceptions.LlibreNotFoundException;
import exceptions.NoQuedanLibrosParaCatalogarException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import manager.BibliotecaManager;
import manager.BibliotecaManagerImpl;
import models.Lector;
import models.Llibre;
import models.PrestecLlibre;

import java.util.List;

@Api(value = "/biblioteca", description = "Endpoint to Biblioteca Services")
@Path("/biblioteca")
public class BibliotecaManagerService {
    private BibliotecaManager bm;

    public BibliotecaManagerService(){
        this.bm = BibliotecaManagerImpl.getInstance();
    }

    @POST
    @Path("/lectors")
    @ApiOperation(value = "Añadir o actualizar un lector")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lector añadido/actualizado", response = Lector.class)
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLector(Lector lector) {
        bm.añadirLector(lector);
        return Response.status(201).entity(lector).build();
    }

    @POST
    @Path("/llibres")
    @ApiOperation(value = "Almacenar un llibre")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Llibre Almacenado", response = Llibre.class)
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enmagatzemarLlibre(Llibre llibre) {
        bm.enmagatzemarLlibre(llibre);
        return Response.status(201).entity(llibre).build();
    }

    @POST
    @Path("/catalogar")
    @ApiOperation(value = "Catalogar un llibre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llibre catalogat correctament"),
            @ApiResponse(code = 404, message = "No hay libros para catalogar", response = String.class)
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogarLlibre() {
        try {
            bm.catalogarLlibre();
            return Response.status(200).entity("Llibre catalogat correctament").build();
        } catch (NoQuedanLibrosParaCatalogarException e) {
            return Response.status(404).entity("Error: " + e.getMessage()).build();
        }

    }

    @POST
    @Path("/prestecs")
    @ApiOperation(value = "Prestar un llibre")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Llibre prestat", response = PrestecLlibre.class),
            @ApiResponse(code = 404, message = "Lector o llibre no encontrado", response = String.class)
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response prestarLlibre(PrestecLlibre prestec) {
        try {
            bm.prestarLlibre(prestec);
            return Response.status(201).entity(prestec).build();
        } catch (LectorNotFoundException | LlibreNotFoundException e) {
            return Response.status(404).entity("Error: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/prestecs/{idLector}")
    @ApiOperation(value = "Consultar prestamos de un lector")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PrestecLlibre.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Lector no encontrado", response = String.class)
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrestecsByLector(@PathParam("idLector") PrestamosDelLectorDTO dto) {
        try {
            List<PrestecLlibre> prestecs = bm.getLlibresPrestacsOfLector(dto);
            GenericEntity<List<PrestecLlibre>> entity = new GenericEntity<List<PrestecLlibre>>(prestecs) {};
            return Response.status(200).entity(entity).build();
        } catch (LectorNotFoundException e) {
            return Response.status(404).entity("Error: " + e.getMessage()).build();
        }
    }
}
