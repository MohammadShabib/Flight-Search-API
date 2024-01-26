package com.amadeus.flightsearchengine.config;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Host;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.TlsPolicy;
import com.aerospike.client.proxy.AerospikeClientProxy;
import com.aerospike.mapper.tools.AeroMapper;
import com.aerospike.mapper.tools.AeroMapper.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig
{
    @Bean(destroyMethod = "close")
    public IAerospikeClient  asClient(@Value("${aerospike.host.name}") String aInDbName,
            @Value("${aerospike.apiKeyId}") String aInDbKeyId,
            @Value("${aerospike.apiKeySecret}") String aInDbKeySecret) throws AerospikeException
    {
        TlsPolicy tlsPolicy = new TlsPolicy();
        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.user = aInDbKeyId;
        clientPolicy.password = aInDbKeySecret;
        clientPolicy.tlsPolicy = tlsPolicy;
        IAerospikeClient client =  new AerospikeClientProxy(clientPolicy, new Host(aInDbName, 4000));
        client.getWritePolicyDefault().totalTimeout= 50000;
        return client;
    }

    @Bean()
    public AeroMapper model(IAerospikeClient aInAerospikeClient) throws AerospikeException
    {
        AeroMapper lOutAeroMapper = new Builder(aInAerospikeClient).build();
        return lOutAeroMapper;
    }
}
