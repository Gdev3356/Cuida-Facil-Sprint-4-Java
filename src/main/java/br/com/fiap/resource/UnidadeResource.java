package br.com.fiap.resource;

import br.com.fiap.bo.UnidadeBO;
import br.com.fiap.to.UnidadeTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/unidades")
public class UnidadeResource {
    private UnidadeBO unidadeBO = new UnidadeBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<UnidadeTO> resultado = unidadeBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null && !resultado.isEmpty()) {
            response = Response.ok(); // 200 - OK
        }
        else {
            response = Response.status(404);  // 404 - NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        UnidadeTO resultado = unidadeBO.findById(id);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();  // 200 (OK)
        } else {
            response = Response.status(404);  // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid UnidadeTO unidade) {
        UnidadeTO resultado = unidadeBO.save(unidade);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.created(null);  // 201 - CREATED
        } else {
            response = Response.status(400);  // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = null;
        if (unidadeBO.delete(id)){
            response = Response.status(204);  // 204 - NO CONTENT
        } else {
            response = Response.status(404);  // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid UnidadeTO unidade, @PathParam("id") Long id) {
        unidade.setIdUnidade(id);

        UnidadeTO resultado = unidadeBO.update(unidade);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok();  // 200 - OK (Comum para PUT)
        } else {
            response = Response.status(400);  // 400 - BAD REQUEST (ou 404 se o ID não existir)
        }
        response.entity(resultado);
        return response.build();
    }
}