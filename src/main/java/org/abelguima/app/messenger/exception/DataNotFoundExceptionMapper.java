package org.abelguima.app.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.abelguima.app.messenger.model.MensagemDeErro;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		MensagemDeErro erroMessage = new MensagemDeErro(exception.getMessage(), 404, "http://localhost:8080");
		return Response.status(Status.NOT_FOUND)
				.entity(erroMessage)
				.build();
	}

}
