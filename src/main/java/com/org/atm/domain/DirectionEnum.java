package com.org.atm.domain;

public enum DirectionEnum {
LEFT("L"),
RIGHT("R"),
UP("U"),
DOWN("D"),
PRESS("P");

public final String value;

private DirectionEnum(String value) {
    this.value = value;
}
}
