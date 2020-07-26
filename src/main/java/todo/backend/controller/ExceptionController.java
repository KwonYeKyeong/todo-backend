package todo.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import todo.backend.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = {NotFoundException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequestException(NotFoundException e) {
		return e.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidException(MethodArgumentNotValidException e) {
		return e.getMessage();
	}

}
