package com.glory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glory.service.PointConversionService;
import com.glory.service.model.ConversionRequest;
import com.glory.service.model.ConversionResponse;

@RestController
@RequestMapping(path = "/convertToMiles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PointConversionController {

	@Autowired
	PointConversionService pointConversionService;

	@CrossOrigin(origins="*")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ConversionResponse convertToMiles(@RequestBody ConversionRequest conversionRequest) {
		return pointConversionService.convert(conversionRequest);
	}
}
