package com.amadeus.flightsearchengine.scheduler;

import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.resttemplatecalls.AmadeusApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Scheduler that is responsible for fetching flights from 3rd party APIs
 */
@Component
public class FetchAmadeusFlightsScheduler
{
    private final AmadeusApiService amadeusApiService;

    private final FlightDao flightDao;

    public FetchAmadeusFlightsScheduler(AmadeusApiService aInAmadeusApiService,
            FlightDao aInFlightDao)
    {
        amadeusApiService = aInAmadeusApiService;
        flightDao = aInFlightDao;
    }

    @Scheduled(cron = "${scheduler.fetchFlight.cron}")
    public void scheduleFlightDataFetching()
    {
        FlightDto lFlightDto = new FlightDto();
        lFlightDto.setDepartureAirport("ESB");
        lFlightDto.setArrivalAirport("SAW");
        lFlightDto.setDepartureDateTime(LocalDate.now().atStartOfDay());
        List<FlightDto> lFlightDtoList = amadeusApiService.getFlightOffers(lFlightDto, 2);
        List<FlightModel> lFlightModelList =
                lFlightDtoList.stream().map(FlightDto::convertDtoToModel).toList();
        flightDao.createBulk(lFlightModelList);
    }
}
