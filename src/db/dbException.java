package db;

public class dbException extends RuntimeException {
    
    private static final Long serialVersionUID = 1L;

    public dbException(String msg){
        super(msg);
    }
}
