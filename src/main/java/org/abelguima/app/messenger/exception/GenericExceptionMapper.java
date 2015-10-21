package org.abelguima.app.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.abelguima.app.messenger.model.MensagemDeErro;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		MensagemDeErro erroMessage = new MensagemDeErro(exception.getMessage(), 500, "http://localhost:8080");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(erroMessage)
				.build();
	}

}
