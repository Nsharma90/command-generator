package com.org.atm.client;


import com.org.atm.exceptions.IllegalDigitInPinException;
import com.org.atm.exceptions.PinCanNotBeBlankException;
import com.org.atm.generator.CommandGenerator;
import com.org.atm.generator.impl.CommandGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class CommandGeneratorController {

    @Autowired
    CommandGenerator commandGenerator;
    @GetMapping(value="/getCommand/{pin}")
    public ResponseEntity<String> getCommand(@PathVariable("pin") String pin) throws PinCanNotBeBlankException, IllegalDigitInPinException {
        int[] pinArr = Arrays.stream(pin.split(",")).mapToInt(digit->Integer.parseInt(digit)).toArray();

        String command = commandGenerator.generateCommandSequence(pinArr);
        return new ResponseEntity(command, HttpStatus.OK);
    }

}
