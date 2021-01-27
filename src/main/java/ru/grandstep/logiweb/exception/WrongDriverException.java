package ru.grandstep.logiweb.exception;

public class WrongDriverException extends Exception{
    public WrongDriverException(String identityNumber) {
        super("Водитель "+ identityNumber + " не подходит");
    }
}
