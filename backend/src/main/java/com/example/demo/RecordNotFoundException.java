package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class RecordNotFoundException extends Exception {

	public RecordNotFoundException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1732405826954263020L;
}