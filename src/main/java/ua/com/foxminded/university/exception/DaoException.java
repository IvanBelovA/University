package ua.com.foxminded.university.exception;

public class DaoException extends RuntimeException {

    public DaoException(String message, Throwable err) {
        super(message, err);
    }

    public DaoException(String message) {
        super(message);
    }

}
