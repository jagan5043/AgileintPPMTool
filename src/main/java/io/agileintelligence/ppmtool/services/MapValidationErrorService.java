package io.agileintelligence.ppmtool.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.agileintelligence.ppmtool.domain.Project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class MapValidationErrorService {
	
	public ResponseEntity<?> mapValidationService(BindingResult result){
		Map<String,String> errorMap = new HashMap<>();
		for(FieldError error:result.getFieldErrors()) {
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	}