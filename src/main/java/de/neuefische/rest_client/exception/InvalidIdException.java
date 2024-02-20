package de.neuefische.rest_client.exception;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String message) {
        super("Good job, Morty! You *burp* fucked everything up! " + message + " is not a valid id!");
    }
}
