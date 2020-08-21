package todo.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import todo.backend.exception.NotFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequestException(MethodArgumentNotValidException e) {
		return e.getMessage();
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidException(NotFoundException e) {
		return e.getMessage();
	}
}
