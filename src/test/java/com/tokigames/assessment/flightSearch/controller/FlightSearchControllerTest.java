package com.tokigames.assessment.flightSearch.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tokigames.assessment.flightsearch.business.FlightSearchManager;
import com.tokigames.assessment.flightsearch.controller.FlightsSearchController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightsSearchController.class, secure = false)
public class FlightSearchControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FlightSearchManager flightSearchManager;
	
	@Test
	public void serchCheapFlight() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:8081/flights/Singapore/Chennai/economy?sort=ASC&page=1&size=1").accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		int status = result.getResponse().getStatus();
		String expectedStatus = "200";
		
		String expected = "{\r\n" + 
				"\"source\": \"Singapore\",\r\n" + 
				"\"destination\": \"Chennai\",\r\n" + 
				"\"onwardFlights\": [],\r\n" + 
				"\"returnFlights\": [],\r\n" + 
				"}";
		JSONAssert.assertEquals(expectedStatus, String.valueOf(status), false);
	}
	
	@Test
	public void serchBusinessFlight() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:8081/flights/Singapore/Chennai/business?sort=ASC&page=1&size=1").accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		int status = result.getResponse().getStatus();
		String expectedStatus = "200";
		
		/*String expected = "{\r\n" + 
				"\"source\": \"Singapore\",\r\n" + 
				"\"destination\": \"Chennai\",\r\n" + 
				"\"onwardFlights\": [],\r\n" + 
				"\"returnFlights\": [],\r\n" + 
				"}";*/
		JSONAssert.assertEquals(expectedStatus, String.valueOf(status), false);
	}
}
