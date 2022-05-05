package com.org.atm.generator;

import com.org.atm.exceptions.IllegalDigitInPinException;
import com.org.atm.exceptions.PinCanNotBeBlankException;

public interface CommandGenerator {

    String generateCommandSequence(int[] pin) throws PinCanNotBeBlankException, IllegalDigitInPinException;

}
