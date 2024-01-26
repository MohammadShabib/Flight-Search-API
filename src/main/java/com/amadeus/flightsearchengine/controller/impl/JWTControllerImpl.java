package com.amadeus.flightsearchengine.controller.impl;

import com.amadeus.flightsearchengine.config.jwt.JwtHelper;
import com.amadeus.flightsearchengine.controller.JWTController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class JWTControllerImpl implements JWTController
{
    private final JwtHelper helper;

    public JWTControllerImpl(JwtHelper aInHelper)
    {
        helper = aInHelper;
    }

    @Override
    public ResponseEntity<String> generateJwtToken() {
        String token = helper.generateToken();
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
