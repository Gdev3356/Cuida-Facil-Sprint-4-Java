package br.com.fiap.resource;

import br.com.fiap.bo.ChatbotBO;
import br.com.fiap.to.ChatbotTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/chatbot")
public class ChatbotResource {
    private ChatbotBO chatbotBO = new ChatbotBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<ChatbotTO> resultado = chatbotBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        ChatbotTO resultado = chatbotBO.findById(id);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ChatbotTO atendimento) {
        ChatbotTO resultado = chatbotBO.save(atendimento);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = chatbotBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid ChatbotTO atendimento, @PathParam("id") Long id) {
        atendimento.setIdAtendimento(id);
        ChatbotTO resultado = chatbotBO.update(atendimento);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}