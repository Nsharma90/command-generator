package com.org.atm.generator.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.org.atm.domain.DirectionEnum;
import com.org.atm.exceptions.IllegalDigitInPinException;
import com.org.atm.exceptions.PinCanNotBeBlankException;
import com.org.atm.generator.CommandGenerator;
import com.org.atm.pojos.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class CommandGeneratorImpl implements CommandGenerator {
	
	private static Map<Integer,Coordinates> keypad = new HashMap<>();
	
	public CommandGeneratorImpl() {
		initializeKeyPad();
	}
	
	public String generateCommandSequence(int[] pin) throws PinCanNotBeBlankException, IllegalDigitInPinException{
		sanitize(pin);
		String command = "";

		command+=getDirection(1, pin[0]);
		//if pin is of length one, assuming starting point will be from key 1
		if(pin.length==1) {
			return command;
		}
		
		
		for(int index=1; index<pin.length;index++) {
			command+=getDirection(pin[index-1], pin[index]);
		}
		
		return command;
	}
	
	private String getDirection(int src, int dest) {
		Coordinates steps = getDistanceBetweenDigits(src, dest);
		return generateDirectionByCoordinates(steps);
	}

	
	/**
	 * @param source
	 * @param destination
	 * @return
	 * 
	 */
	private Coordinates getDistanceBetweenDigits(int source,int destination) {
		
		//lookup coordinates
		Coordinates sourceCoordinates=keypad.get(source);
		Coordinates destiNationCoordinates  = keypad.get(destination);
		
		int stepsToMoveHorizontally = sourceCoordinates.getY() - destiNationCoordinates.getY();
		int stepsToMoveVertically = sourceCoordinates.getX() - destiNationCoordinates.getX();
		
		
		return new Coordinates(stepsToMoveVertically,stepsToMoveHorizontally); 
	}
	
	/**
	 * @param distance
	 * @return
	 * 
	 * Y negative : move right
	 * Y positive : move left
	 * 
	 * X negative move DOWN
	 * X positive: move UP
	 */
	private String generateDirectionByCoordinates(Coordinates distance) {
		
		String direction = "";
		int horizontalSteps = distance.getY();
		int verticalSteps = distance.getX();
		//distance is 0, then no need to move into any direction, just press
		if(horizontalSteps==0 && verticalSteps==0) {
			return DirectionEnum.PRESS.value;
			//simply return from here
		}
		
		if(horizontalSteps>0) {
			direction+=addMovesToSequence(DirectionEnum.LEFT,horizontalSteps);
		}else if(horizontalSteps<0) {
			direction+=addMovesToSequence(DirectionEnum.RIGHT,horizontalSteps);
		}
		
		if(verticalSteps>0) {
			direction+=addMovesToSequence(DirectionEnum.UP,verticalSteps);
		}else if(verticalSteps<0) {
			direction+=addMovesToSequence(DirectionEnum.DOWN,verticalSteps);
		}
		
		direction=direction.concat(DirectionEnum.PRESS.value);
		
		return direction;
	}
	
	
	private String addMovesToSequence(DirectionEnum directionVal, int numberOfMoves) {
		String directionSequence="";
		numberOfMoves=Math.abs(numberOfMoves);
		for(int nom=0;nom<numberOfMoves;nom++) {
			directionSequence+=directionVal.value;
		}
		return directionSequence;
	}
	/**
	 * 2D matrix to store keys on atm keypad
	 * 123 00 01 02
	 * 456 10 11 12
	 * 789 20 21 22
	 */
	private void initializeKeyPad() {
		int key=1;
		for(int x=0;x<=2;x++) {
			for(int y=0;y<=2;y++) {
				keypad.put(key++, new Coordinates(x, y));
			}
		}
		
	}
	
	
	private void sanitize(int[] pin) throws PinCanNotBeBlankException, IllegalDigitInPinException {
		if(pin.length==0) {
			throw new PinCanNotBeBlankException("Pin can not be blank");
		}
		boolean isContainZero = Arrays.stream(pin).anyMatch(digit->digit==0);
		
		if(isContainZero) {
			throw new IllegalDigitInPinException("Pin contains illegal digit 0");
		}

		Boolean isPinContainDigitGreaterThanZero=Arrays.stream(pin).anyMatch(digit->digit>9 || digit<0);
		if(isPinContainDigitGreaterThanZero) {
			throw new IllegalDigitInPinException("Pin contains  illegal digit less than 0 or greater than 9");
		}
	}
}
