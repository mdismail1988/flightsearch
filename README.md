# flightsearch application is restful webservice for searching flight

##API

###GET  /flights/{source}/{destination}/{flightclass}?sort={ASC/DSCE}&page={1}&size={1}

source  - Source city (Departure)

destination  - Destination city (Arrival)

flightclass - business or economy 

sort  - sorting direction

page - page number

size - page size (no. of records)


####Example :

http://localhost:8081/flights/Antalya/Istanbul/business?sort=ASC&page=2&size=1


The API will return the json with available flights with return flights for the same route

#####Example response:

{
"source": "Antalya",
"destination": "Istanbul",
"onwardFlights": [{
"departureTime": "20-07-2019 07:30",
"arrivalTime": "21-07-2019 08:30"
}],
"returnFlights": [{
"departureTime": "20-07-2019 07:30",
"arrivalTime": "21-07-2019 08:30"
},{
"departureTime": "27-05-2019 02:00",
"arrivalTime": "27-05-2019 02:00"
},{
"departureTime": "27-05-2019 02:00",
"arrivalTime": "27-05-2019 02:00"
}]
}


####### Development steps

### import maven project into eclipse
### BUILD ###  mvn clean install
### BUILD AND TEST #### mvn clean test
### RUN APPLICATION ### mvn clean spring-boot:run

#### application properties update #######
server.port -- application port number 
flight.info.provider.url  -- source API URL to get the list of available flights
