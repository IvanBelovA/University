package ua.com.foxminded.university.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String message, Throwable err) {
        super(message, err);
    }
    
    public ServiceException(String message) {
        super(message);
    }
}
