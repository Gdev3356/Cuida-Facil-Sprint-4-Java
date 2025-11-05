package br.com.fiap.resource;

import br.com.fiap.bo.ConsultaBO;
import br.com.fiap.to.ConsultaDetalhadaTO;
import br.com.fiap.to.ConsultaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/consultas")
public class ConsultaResource {
    private ConsultaBO consultaBO = new ConsultaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<ConsultaDetalhadaTO> resultado = consultaBO.findAllDetalhadas();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty())
                ? Response.ok()
                : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        ConsultaDetalhadaTO resultado = consultaBO.findByIdDetalhada(id);
        Response.ResponseBuilder response = (resultado != null)
                ? Response.ok()
                : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid ConsultaTO consulta) {
        // LOGS PARA DEBUG
        System.out.println("========= DEBUG CONSULTA =========");
        System.out.println("Data: " + consulta.getDataConsulta());
        System.out.println("Status: " + consulta.getStatus());
        System.out.println("Tipo: " + consulta.getTipoAtendimento());
        System.out.println("ID Paciente: " + consulta.getIdPaciente());
        System.out.println("ID Medico: " + consulta.getIdMedico());
        System.out.println("ID Unidade: " + consulta.getIdUnidade()); // ← VERIFIQUE ESTE
        System.out.println("ID Especialidade: " + consulta.getIdEspecialidade());
        System.out.println("==================================");

        // Validação adicional
        if (consulta.getIdUnidade() == null) {
            return Response.status(400)
                    .entity("{\"error\": \"ID da Unidade é obrigatório\"}")
                    .build();
        }

        try {
            ConsultaTO resultado = consultaBO.save(consulta);
            Response.ResponseBuilder response = (resultado != null)
                    ? Response.created(null)
                    : Response.status(400);
            response.entity(resultado);
            return response.build();
        } catch (Exception e) {
            System.err.println("Erro ao salvar consulta: " + e.getMessage());
            e.printStackTrace();
            return Response.status(500)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = consultaBO.delete(id)
                ? Response.status(204)
                : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid ConsultaTO consulta, @PathParam("id") Long id) {
        consulta.setIdConsulta(id);
        ConsultaTO resultado = consultaBO.update(consulta);
        Response.ResponseBuilder response = (resultado != null)
                ? Response.ok()
                : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}