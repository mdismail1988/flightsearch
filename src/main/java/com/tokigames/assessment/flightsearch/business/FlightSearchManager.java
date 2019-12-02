package com.tokigames.assessment.flightsearch.business;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokigames.assessment.flightsearch.exception.FlightSearchException;
import com.tokigames.assessment.flightsearch.model.BusinessClass;
import com.tokigames.assessment.flightsearch.model.BusinessFlights;
import com.tokigames.assessment.flightsearch.model.EconomyClass;
import com.tokigames.assessment.flightsearch.model.EconomyFlights;
import com.tokigames.assessment.flightsearch.model.FlightSearchResult;
import com.tokigames.assessment.flightsearch.model.FlightTimings;
import com.tokigames.assessment.flightsearch.service.FlightsInfoProviderService;
import com.tokigames.assessment.flightsearch.util.DateUtil;
import com.tokigames.assessment.flightsearch.util.Sorting;

/**
 * 
 * @author Mohamed_Ismail
 * 
 * FlightSearchManager Business logic class to get the data from provider and filter the data 
 */
@Component
public class FlightSearchManager {
	
	@Autowired
	private FlightsInfoProviderService flightsInfoProviderService;
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param sort
	 * @param page
	 * @param size
	 * @param returnSerach
	 * @return
	 */
	public List<EconomyClass> getAvailableFlights(String source,String destination,String sort,int page,int size,boolean returnSerach) {
		try {
			EconomyFlights economyFlights =  flightsInfoProviderService.getEconomyFlights();
			
			List<EconomyClass> availableFlights = economyFlights.getData().stream()
					 								.filter(economyFlight -> source.equals(economyFlight.getSource()) &&
					 										destination.equals(economyFlight.getDestination()))
					 								.collect(Collectors.toList());
			int totalResults = availableFlights.size();
			int totalPage = (int) Math.ceil(totalResults / (float) size);
			
			if(!returnSerach) {
				if(page != 1 && totalPage < page) {  // 
					throw new FlightSearchException("Given Page number exceeded total pages available");
				}
			}
			
			if (sort.equals(Sorting.ASCENDING.getDirectionCode())) {
				availableFlights.sort((EconomyClass e1, EconomyClass e2)->e1.getDeparture().compareTo(e2.getDeparture()));
			}else if(sort.equals(Sorting.DESCENDING.getDirectionCode())) {
				availableFlights.sort((EconomyClass e1, EconomyClass e2)->e2.getDeparture().compareTo(e1.getDeparture()));
			}
			return availableFlights;
		}catch(Exception e) {
			throw new FlightSearchException(e.getMessage());
		}
		 
	} 
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	public FlightSearchResult searchEconomyFlights(String source,String destination,String sort,int page,int size) {
		try {
			List<EconomyClass> onwardFlights = getAvailableFlights(source, destination, sort, page, size,false);
			List<EconomyClass> retrunFlights = new ArrayList<EconomyClass>();
			
			if(onwardFlights.size() > 0) {
				retrunFlights = getAvailableFlights(destination, source, sort, page, size,true);
			}
			
			FlightSearchResult result = new FlightSearchResult();
			result.setSource(source);
			result.setDestination(destination);
			
			List<FlightTimings> onwardFlightTimingList   =  new ArrayList<FlightTimings>();
			ListIterator<EconomyClass> onwardFlightsItr = onwardFlights.listIterator();
			while(onwardFlightsItr.hasNext()){
				EconomyClass economyClass = onwardFlightsItr.next();
				FlightTimings flightTimings = new FlightTimings();
				flightTimings.setDepartureTime(DateUtil.timestampToDateString(economyClass.getDeparture().split("\\.")[0].trim()));
				flightTimings.setArrivalTime(DateUtil.timestampToDateString(economyClass.getArrival().split("\\.")[0].trim()));
				onwardFlightTimingList.add(flightTimings);
			}
			result.setOnwardFlights(onwardFlightTimingList);
			
			
			List<FlightTimings> returnFlightTimingList   =  new ArrayList<FlightTimings>();
			ListIterator<EconomyClass> retrunFlightsItr = retrunFlights.listIterator();
			while(retrunFlightsItr.hasNext()){
				EconomyClass economyClass = retrunFlightsItr.next();
				FlightTimings flightTimings = new FlightTimings();
				flightTimings.setDepartureTime(DateUtil.timestampToDateString(economyClass.getDeparture().split("\\.")[0].trim()));
				flightTimings.setArrivalTime(DateUtil.timestampToDateString(economyClass.getArrival().split("\\.")[0].trim()));
				returnFlightTimingList.add(flightTimings);
			}
			result.setReturnFlights(returnFlightTimingList);
			
			return result;
		}catch(Exception e) {
			throw new FlightSearchException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	public FlightSearchResult searchBusinessFlights(String source,String destination,String sort,int page,int size) {
		try {
			List<BusinessClass> onwardFlights = getAvailableBusinessClass(source, destination, sort, page, size, false);
			List<BusinessClass> retrunFlights =  new ArrayList<BusinessClass>();
			
			if(onwardFlights.size() > 0) {
				retrunFlights = getAvailableBusinessClass(destination, source, sort, page, size,true);
			}
			
			FlightSearchResult result = new FlightSearchResult();
			result.setSource(source);
			result.setDestination(destination);
			
			List<FlightTimings> onwardFlightTimingList   =  new ArrayList<FlightTimings>();
			ListIterator<BusinessClass> onwardFlightsItr = onwardFlights.listIterator();
			while(onwardFlightsItr.hasNext()){
				BusinessClass businessClass = onwardFlightsItr.next();
				FlightTimings flightTimings = new FlightTimings();
				flightTimings.setDepartureTime(DateUtil.timestampToDateString(businessClass.getDepartureTime().split("\\.")[0].trim()));
				flightTimings.setArrivalTime(DateUtil.timestampToDateString(businessClass.getArrivalTime().split("\\.")[0].trim()));
				onwardFlightTimingList.add(flightTimings);
			}
			result.setOnwardFlights(onwardFlightTimingList);
			
			List<FlightTimings> returnFlightTimingList   =  new ArrayList<FlightTimings>();
			ListIterator<BusinessClass> retrunFlightsItr = retrunFlights.listIterator();
			while(retrunFlightsItr.hasNext()){
				BusinessClass businessClass = retrunFlightsItr.next();
				FlightTimings flightTimings = new FlightTimings();
				flightTimings.setDepartureTime(DateUtil.timestampToDateString(businessClass.getDepartureTime().split("\\.")[0].trim()));
				flightTimings.setArrivalTime(DateUtil.timestampToDateString(businessClass.getArrivalTime().split("\\.")[0].trim()));
				returnFlightTimingList.add(flightTimings);
			}
			result.setReturnFlights(returnFlightTimingList);
			return result;
		}catch(Exception e) {
			throw new FlightSearchException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param sort
	 * @param page
	 * @param size
	 * @param returnSerach
	 * @return
	 */
	public List<BusinessClass> getAvailableBusinessClass(String source,String destination,String sort,int page,int size,boolean returnSerach) {
		try {
			BusinessFlights businessFlights = flightsInfoProviderService.getBusinessFlights();
			
			ListIterator<BusinessClass> litr = null;
			litr=businessFlights.getData().listIterator();

			List<BusinessClass> availableFlights = businessFlights.getData().stream().filter(businessClass -> source.equals(businessClass.getDeparture()) &&
																							 destination.equals(businessClass.getArrival()))
																					 .collect(Collectors.toList());
			
			int totalResults = availableFlights.size();
			int totalPage = (int) Math.ceil(totalResults / (float) size);
			
			if(!returnSerach) {
				if(page != 1 && totalPage < page) {  // 
					throw new FlightSearchException("Given Page number exceeded total pages available");
				}
			}
			
			if (sort.equals(Sorting.ASCENDING.getDirectionCode())) {
				availableFlights.sort((BusinessClass b1, BusinessClass b2)->b1.getDeparture().compareTo(b2.getDeparture()));
			}else if(sort.equals(Sorting.DESCENDING.getDirectionCode())) {
				availableFlights.sort((BusinessClass b1, BusinessClass b2)->b2.getDeparture().compareTo(b1.getDeparture()));
			}
			return availableFlights;
		}catch(Exception e) {
			throw new FlightSearchException(e.getMessage());
		}
	}
	 

}
