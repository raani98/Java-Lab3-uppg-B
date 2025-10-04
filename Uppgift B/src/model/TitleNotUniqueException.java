package model;

public class TitleNotUniqueException extends RuntimeException {

    public TitleNotUniqueException() {
        super("Project title must be unique.");
    }

    public TitleNotUniqueException(String message) {
        super(message);
    }
}

