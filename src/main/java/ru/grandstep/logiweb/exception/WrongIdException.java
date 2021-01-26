package ru.grandstep.logiweb.exception;

public class WrongIdException extends RuntimeException{
    public WrongIdException() {
        super("Wrong Id");
    }
}
