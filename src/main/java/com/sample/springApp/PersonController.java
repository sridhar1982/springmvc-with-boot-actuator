package com.sample.springApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.springApp.component.IPersonService;

@Controller
@RequestMapping("/data")
public class PersonController {
	@Autowired
	private IPersonService personService;

	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
	public Person getPersonDetail(@RequestParam(value = "id",required = false,
	                                                    defaultValue = "0") Integer id) {
		Person p = personService.getPersonDetail(id);
		return p;
	}


	private ObjectMapper mapper = new ObjectMapper();


	@Autowired
	private MetricsEndpoint metrics;

	@Autowired
    private HealthEndpoint health;

	@RequestMapping(value="/metrics",method = RequestMethod.GET, produces = "application/json" )
	@ResponseBody
	public String getDump() throws JsonProcessingException {
	    return mapper.writeValueAsString(metrics.invoke());
	}

	@RequestMapping(value="/health", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getEnvironment() throws JsonProcessingException {
	    return mapper.writeValueAsString(health.invoke());
	}


}