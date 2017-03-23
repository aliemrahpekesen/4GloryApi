package com.glory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glory.service.MilesCheckService;
import com.glory.service.model.MileCheckRequest;
import com.glory.service.model.MileCheckResponse;

@Controller
public class MilesCheckController {

	@Autowired
	MilesCheckService milesCheckService;

	@RequestMapping(path = "/checkMiles", method = RequestMethod.POST)
	public @ResponseBody MileCheckResponse checkMiles(MileCheckRequest request) {

		return milesCheckService.checkMileAvailability(request);
	}

}
