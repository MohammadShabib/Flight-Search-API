package com.amadeus.flightsearchengine.par;

import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AerospikeRecord(namespace="aerospike_cloud", set="Airport")
public class AirportPar
{
    @AerospikeKey
    private String id;

    private String city;
}
