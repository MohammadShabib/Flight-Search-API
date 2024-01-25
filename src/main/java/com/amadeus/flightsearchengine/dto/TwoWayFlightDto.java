package com.amadeus.flightsearchengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoWayFlightDto
{
    List<FlightDto> departureFlight;

    List<FlightDto> returnFlight;
}
