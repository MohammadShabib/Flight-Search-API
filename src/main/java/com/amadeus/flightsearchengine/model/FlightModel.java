package com.amadeus.flightsearchengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightModel
{
    private String id;

    private String departureAirport;

    private String arrivalAirport;

    private Long departureDateTime;

    private Long returnDateTime;

    private Double price;
}
