package com.amadeus.flightsearchengine.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.mapper.tools.AeroMapper;
import com.aerospike.mapper.tools.AeroMapper.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig
{
    @Bean(destroyMethod = "close")
    public AerospikeClient asClient(@Value("${aerospike.url}") String aInDbUrl,
            @Value("${aerospike.port}") Integer aInPort) throws AerospikeException
    {
        try
        {
            return new AerospikeClient(aInDbUrl, aInPort);
        }
        catch (AerospikeException aInE)
        {
            throw aInE;
        }
    }

    @Bean()
    public AeroMapper model(AerospikeClient aInAerospikeClient) throws AerospikeException
    {
        AeroMapper lOutAeroMapper = new Builder(aInAerospikeClient).build();
        return lOutAeroMapper;
    }
}
