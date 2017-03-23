package com.glory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glory.service.BurnMilesService;
import com.glory.service.model.BurnMilesRequest;
import com.glory.service.model.BurnMilesResponse;

@Controller
public class BurnMilesController {

	@Autowired
	BurnMilesService burnMilesService;

	@RequestMapping(path = "/burnMiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BurnMilesResponse burnMiles(@RequestBody BurnMilesRequest burnMilesRequest) {

		return burnMilesService.burnMiles(burnMilesRequest);
	}

}
