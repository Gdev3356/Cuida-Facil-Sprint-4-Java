package br.com.fiap.resource;

import br.com.fiap.bo.MedicoBO;
import br.com.fiap.to.MedicoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/medicos")
public class MedicoResource {
    private MedicoBO medicoBO = new MedicoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<MedicoTO> resultado = medicoBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        MedicoTO resultado = medicoBO.findById(id);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid MedicoTO medico) {
        MedicoTO resultado = medicoBO.save(medico);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = medicoBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid MedicoTO medico, @PathParam("id") Long id) {
        medico.setIdMedico(id);
        MedicoTO resultado = medicoBO.update(medico);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}