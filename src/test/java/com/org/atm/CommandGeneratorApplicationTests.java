package com.org.atm;

import com.org.atm.exceptions.IllegalDigitInPinException;
import com.org.atm.exceptions.PinCanNotBeBlankException;
import com.org.atm.generator.CommandGenerator;
import com.org.atm.generator.impl.CommandGeneratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CommandGeneratorApplicationTests {
	CommandGenerator  commandGenerator = new CommandGeneratorImpl(); //initialize command sequence generator

	@Test
	void singleDigitPinTest() throws PinCanNotBeBlankException, IllegalDigitInPinException {
		Assertions.assertEquals("P",commandGenerator.generateCommandSequence(new int[]{1}));
		Assertions.assertEquals("RP",commandGenerator.generateCommandSequence(new int[]{2}));
		Assertions.assertEquals("RRP",commandGenerator.generateCommandSequence(new int[]{3}));
		Assertions.assertEquals("DP",commandGenerator.generateCommandSequence(new int[]{4}));
		Assertions.assertEquals("RDP",commandGenerator.generateCommandSequence(new int[]{5}));
		Assertions.assertEquals("RRDP",commandGenerator.generateCommandSequence(new int[]{6}));
		Assertions.assertEquals("DDP",commandGenerator.generateCommandSequence(new int[]{7}));
		Assertions.assertEquals("RDDP",commandGenerator.generateCommandSequence(new int[]{8}));
		Assertions.assertEquals("RRDDP",commandGenerator.generateCommandSequence(new int[]{9}));
	}

	@Test
	void blankPinTest()  {
		Assertions.assertThrows(PinCanNotBeBlankException.class,()->commandGenerator.generateCommandSequence(new int[]{}),"Blank pin excepted which is wrong!!");
	}

	@Test
	void pinContainsIllegalDigitsTest(){
		//digits <=0 & >9 are illeagal
		Assertions.assertThrows(IllegalDigitInPinException.class,()->commandGenerator.generateCommandSequence(new int[]{0}));
		Assertions.assertThrows(IllegalDigitInPinException.class,()->commandGenerator.generateCommandSequence(new int[]{1,0}));
		Assertions.assertThrows(IllegalDigitInPinException.class,()->commandGenerator.generateCommandSequence(new int[]{2,0,2}));
		Assertions.assertThrows(IllegalDigitInPinException.class,()->commandGenerator.generateCommandSequence(new int[]{2,1,-2}));
		Assertions.assertThrows(IllegalDigitInPinException.class,()->commandGenerator.generateCommandSequence(new int[]{12,2,2}));
	}

	@Test
	void multiDigitPinTest() throws PinCanNotBeBlankException, IllegalDigitInPinException {
		Assertions.assertEquals("PRPRPLDP",commandGenerator.generateCommandSequence(new int[]{1,2,3,5}));
		Assertions.assertEquals("RDPLPDPRRP",commandGenerator.generateCommandSequence(new int[]{5,4,7,9}));
		Assertions.assertEquals("RDPLPDPRRPLPLUUP",commandGenerator.generateCommandSequence(new int[]{5,4,7,9,8,1}));
	}

}
