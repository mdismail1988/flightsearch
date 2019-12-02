package com.tokigames.assessment.flightsearch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tokigames.assessment.flightsearch.constants.FlightSearchAppConstant;
import com.tokigames.assessment.flightsearch.exception.FlightInfoProviderException;
import com.tokigames.assessment.flightsearch.model.BusinessFlights;
import com.tokigames.assessment.flightsearch.model.EconomyFlights;

/**
 * 
 * @author Mohamed_Ismail
 *
 */
@Service
public class FlightsInfoProviderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightsInfoProviderService.class);
	
	@Value("${flight.info.provider.url}")
	String flightInfoProviderUrl;
	
	private final RestTemplate restTemplate;
	
	public FlightsInfoProviderService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	/**
	 * 
	 * @return
	 * @throws FlightInfoProviderException
	 */
	public EconomyFlights getEconomyFlights() throws FlightInfoProviderException   {
		LOGGER.info("::getEconomyFlights::");
		EconomyFlights economyFlights = this.restTemplate.getForObject(flightInfoProviderUrl+"/"+FlightSearchAppConstant.FLIGHT_CLASS_CHEAP, EconomyFlights.class);
		return economyFlights;
	}
	
	/**
	 * 
	 * @return
	 * @throws FlightInfoProviderException
	 */
	public BusinessFlights getBusinessFlights() throws FlightInfoProviderException {
		LOGGER.info("::getBusinessFlights::");
		BusinessFlights businessFlights = this.restTemplate.getForObject(flightInfoProviderUrl+"/"+FlightSearchAppConstant.FLIGHT_CLASS_BUSINESS, BusinessFlights.class);
		return businessFlights;
	}
	
}