package ru.grandstep.logiweb.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String string, Integer id) {
        super(string + " with id = " + id + " not found");
    }
}
