package br.cefet.sistema_estagios.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<InputDataErrorDTO> handle(MethodArgumentNotValidException exception)
	
	{
		List<InputDataErrorDTO> dtoList = new ArrayList<>();
		List<FieldError> fieldErrors =  exception.getBindingResult().getFieldErrors();
		
		for (FieldError fieldError : fieldErrors) {
			//LocaleContextHolder.getLocale(): internacionalização das mensagens
			//Se no Heaers da Request vc usar Accept-Language en-US (fica em Inglês a mensagem)
			String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			dtoList.add(new InputDataErrorDTO(fieldError.getField(), errorMessage));
		}
		
		return dtoList;
		
	}
}
