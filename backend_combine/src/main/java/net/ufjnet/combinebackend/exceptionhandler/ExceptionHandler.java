package net.ufjnet.combinebackend.exceptionhandler;

import net.ufjnet.combinebackend.services.exceptions.BusinessException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final MessageSource message;

	public ExceptionHandler(MessageSource message) {
		this.message = message;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request ) {

		List<StandardError.Fields> error_fields = new ArrayList<>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String msg = message.getMessage(error, LocaleContextHolder.getLocale());
			
			error_fields.add(new StandardError.Fields(name, msg));
		}
		
		StandardError err = new StandardError(
			status.value(),
			LocalDateTime.now(),
			"Alguns campos estão vazios, favor verifique!",
			error_fields
		);
		
		return this.handleExceptionInternal(ex, err, headers, status, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> dataIntegrity (BusinessException ex) {
		StandardError error = new StandardError(
			HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),
			ex.getMessage(),
			null
		);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
