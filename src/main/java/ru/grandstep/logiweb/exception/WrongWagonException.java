package ru.grandstep.logiweb.exception;

public class WrongWagonException extends Exception{
    public WrongWagonException() {
        super("Фура не подходит");
    }
}
