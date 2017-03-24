package com.glory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glory.service.MilesCheckService;
import com.glory.service.model.MileCheckRequest;
import com.glory.service.model.MileCheckResponse;

@RestController
@RequestMapping(path = "/checkMiles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MilesCheckController {

	@Autowired
	MilesCheckService milesCheckService;

	@CrossOrigin(origins="*")
	@RequestMapping(method = RequestMethod.POST) 
	public @ResponseBody MileCheckResponse checkMiles(@RequestBody MileCheckRequest request) {
		return milesCheckService.checkMileAvailability(request);
	}

}
