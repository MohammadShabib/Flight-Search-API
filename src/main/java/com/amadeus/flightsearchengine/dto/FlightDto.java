package com.amadeus.flightsearchengine.dto;

import com.amadeus.flightsearchengine.model.FlightModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto
{
    private String id;

    private String departureAirport;

    private String arrivalAirport;

    private LocalDateTime departureDateTime;

    private Double price;

    public static FlightDto convertModelToDTO(FlightDto aInFlightDto, FlightModel aInFlightModel)
    {
        FlightDto lFlightDto = new FlightDto();
        lFlightDto.setId(aInFlightModel.getId());
        lFlightDto.setPrice(aInFlightModel.getPrice());
        lFlightDto.setDepartureAirport(aInFlightDto.getDepartureAirport());
        lFlightDto.setArrivalAirport(aInFlightDto.getArrivalAirport());
        /*
        Ank -> ist
        ist -> ank
        ank -> ank
         */
        if (!aInFlightDto.getDepartureAirport().equals(aInFlightModel.getArrivalAirport()))
        {
            lFlightDto.setDepartureDateTime(aInFlightModel.getDepartureDateTime());
        }
        else if (!aInFlightDto.getDepartureAirport().equals(aInFlightModel.getDepartureAirport()))
        {
            lFlightDto.setDepartureDateTime(aInFlightModel.getReturnDateTime());
        }
        else if (!aInFlightDto.getArrivalAirport().equals(aInFlightModel.getArrivalAirport()))
        {
            return null;
        }
        return lFlightDto;
    }

    public static List<FlightDto> convertBulkModelToDTO(FlightDto aInFlightDto, List<FlightModel> aInFlightModel)
    {
        return aInFlightModel.stream()
                .map(lFlightModel -> convertModelToDTO(aInFlightDto, lFlightModel))
                .collect(Collectors.toList());
    }

    public static FlightDto convertModelToDTO(FlightModel aInFlightModel, boolean aInIsDepartureTime)
    {
        FlightDto lFlightDto = new FlightDto();
        lFlightDto.setId(aInFlightModel.getId());
        lFlightDto.setPrice(aInFlightModel.getPrice());
        if (aInIsDepartureTime)
        {
            lFlightDto.setDepartureAirport(aInFlightModel.getDepartureAirport());
            lFlightDto.setArrivalAirport(aInFlightModel.getArrivalAirport());
            lFlightDto.setDepartureDateTime(aInFlightModel.getDepartureDateTime());
        }
        else
        {
            lFlightDto.setDepartureAirport(aInFlightModel.getArrivalAirport());
            lFlightDto.setArrivalAirport(aInFlightModel.getDepartureAirport());
            lFlightDto.setDepartureDateTime(aInFlightModel.getReturnDateTime());
        }
        return lFlightDto;
    }
}
