package com.amadeus.flightsearchengine.par;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AerospikeRecord(namespace="aerospike_cloud", set="Flight")
public class FlightPar
{
    @AerospikeKey
    private String id;

    @AerospikeBin(name="depAirport")
    private String departureAirport;

    @AerospikeBin(name="arrAirport")
    private String arrivalAirport;

    @AerospikeBin(name="depTime")
    private LocalDateTime departureDateTime;

    @AerospikeBin(name="retTime")
    private LocalDateTime returnDateTime;

    private Double price;
}
