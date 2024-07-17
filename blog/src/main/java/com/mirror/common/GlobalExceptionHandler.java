package com.mirror.common;


import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	// 实体校验异常捕获
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Result<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		ObjectError objectError = result.getAllErrors().stream().findFirst().get();
		return Result.<String>builder().code(ResultEnum.ERROR.getCode()).message(ResultEnum.ERROR.getMessage()).data(objectError.getDefaultMessage()).build();
	}


}