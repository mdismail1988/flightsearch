package com.tokigames.assessment.flightsearch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokigames.assessment.flightsearch.business.FlightSearchManager;
import com.tokigames.assessment.flightsearch.constants.FlightSearchAppConstant;
import com.tokigames.assessment.flightsearch.exception.FilteringException;
import com.tokigames.assessment.flightsearch.exception.FlightSearchException;
import com.tokigames.assessment.flightsearch.model.FlightSearchResult;
import com.tokigames.assessment.flightsearch.util.Sorting;


/**
 * @author Mohamed_Ismail
 *
 */
@RestController
@RequestMapping("/flights")
public class FlightsSearchController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(FlightsSearchController.class);
	 
	 @Autowired
	 private FlightSearchManager flightSearchManager;
	 
	 @RequestMapping(method=RequestMethod.GET, value = "/{source}/{destination}/{flightClass}", 
			 		 params = { "sort", "page","size" }, produces = MediaType.APPLICATION_JSON_VALUE) 
	 @ResponseBody
	 public FlightSearchResult serchFlight(@RequestParam("sort") String sort, @RequestParam("page") int page, @RequestParam("size") int size,
			 				 @PathVariable("source") String source, @PathVariable("destination") String destination, 
			 				 @PathVariable("flightClass") String flightClass) {
		 
		 LOGGER.info("PARAMS : sort{"+sort+"} page{"+page+"} size{"+size+"} source{"+source+"} destination{"+destination+"} flightClass{"+flightClass+"}");
		 
		 if (!(sort.equals(Sorting.ASCENDING.getDirectionCode()) || sort.equals(Sorting.DESCENDING.getDirectionCode()))) {
			 throw new FilteringException("Invalid sort direction");
		 }
		 
		 if(source.equals("") || destination.equals("") || flightClass.equals("")) {
			 LOGGER.error("missing Filtring values");
			 throw new FilteringException("Invalid filtring values");
		 }
		 
		 if(page <= 0 || size<= 0) {
			 LOGGER.error("missing page/size values");
			 throw new FilteringException("Invalid pagination parameters");
		 }
		 
		 FlightSearchResult flightSearchResult = new FlightSearchResult();
		 if(flightClass.equals(FlightSearchAppConstant.FLIGHT_CLASS_ECONOMY)) {
			 flightSearchResult = flightSearchManager.searchEconomyFlights(source,destination,sort,page,size);
		 }else if(flightClass.equals(FlightSearchAppConstant.FLIGHT_CLASS_BUSINESS)) {
			 flightSearchResult = flightSearchManager.searchBusinessFlights(source,destination,sort,page,size);
		 }
		 return flightSearchResult;
	}

}
