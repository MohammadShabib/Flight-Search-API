package com.amadeus.flightsearchengine.par;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AerospikeRecord(namespace="distributionrecords", set="Flight")
public class FlightPar
{
    @AerospikeKey
    private String id;

    @AerospikeBin(name="depAirport")
    private String departureAirport;

    @AerospikeBin(name="arrAirport")
    private String arrivalAirport;

    @AerospikeBin(name="depTime")
    private Long departureDateTime;

    @AerospikeBin(name="retTime")
    private Long returnDateTime;

    private Double price;
}
