package br.com.fiap.resource;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.to.PacienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.exception.BusinessRuleException;
import br.com.fiap.exception.DAOException;

import java.util.List;



@Path("/pacientes")
public class PacienteResource {
    private PacienteBO pacienteBO = new PacienteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            List<PacienteTO> resultado = pacienteBO.findAll();
            Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
            return response.entity(resultado).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500
                    .entity("Erro ao consultar dados: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            PacienteTO resultado = pacienteBO.findById(id);
            return Response.ok(resultado).build(); // 200 OK
        } catch (IdNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND) // 404
                    .entity(e.getMessage())
                    .build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500
                    .entity("Erro ao buscar no banco de dados: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid PacienteTO paciente) {
        try {
            PacienteTO resultado = pacienteBO.save(paciente);
            return Response.status(Response.Status.CREATED) // 201 Created
                    .entity(resultado)
                    .build();

        } catch (BusinessRuleException e) {
            return Response.status(Response.Status.BAD_REQUEST) // 400 Bad Request
                    .entity(e.getMessage())
                    .build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500
                    .entity("Erro no banco de dados: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = pacienteBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid PacienteTO paciente, @PathParam("id") Long id) {
        paciente.setIdPaciente(id);
        PacienteTO resultado = pacienteBO.update(paciente);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}