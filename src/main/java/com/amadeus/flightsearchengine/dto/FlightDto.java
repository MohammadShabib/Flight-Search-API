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

    /**
     * maps {@link FlightModel} to {@link FlightDto} based on the provided aInFlightDto
     * @param aInFlightDto flight which will be the referencing for mapping
     * @param aInFlightModel flight to be mapped
     * @return mapped {@link FlightDto
     */
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

    /**
     * Converts {@link FlightModel} to {@link FlightDto}
     * @param aInFlightModel     the flight to be converted
     * @param aInIsDepartureTime if true take the departure flight, if it false takes the return flight
     * @return converted {@link FlightDto}
     */
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

    /**
     * Converts {@link FlightDto} to {@link FlightModel}
     *
     * @param aInFlightDto Flight to be converted
     * @return {@link FlightModel}
     */
    public FlightModel convertDtoToModel()
    {
       FlightModel lOutFlightModel = new FlightModel();
        lOutFlightModel.setId(id);
        lOutFlightModel.setDepartureAirport(departureAirport);
        lOutFlightModel.setArrivalAirport(arrivalAirport);
        lOutFlightModel.setDepartureDateTime(departureDateTime);

        //Assume Return Date is after 1 day
        LocalDateTime lReturnDateTime  = departureDateTime.plusDays(1);
        lOutFlightModel.setReturnDateTime(lReturnDateTime);

        lOutFlightModel.setPrice(price);

        return lOutFlightModel;
    }
}
