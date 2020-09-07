package com.insta.sns.config.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.insta.sns.config.handler.ex.MyUserIdNotFoundException;
import com.insta.sns.config.handler.ex.MyUsernameNotFoundException;
import com.insta.sns.util.Script;


@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=MyUserIdNotFoundException.class)
	public String myUserIdNotFoundException(Exception e) {
		return Script.back(e.getMessage());
	}

	@ExceptionHandler(value=MyUsernameNotFoundException.class)
	public String myUsernameNotFoundException(Exception e) {
		return Script.alert(e.getMessage());
	}
}