package exception;
 
public class MetierException extends Exception {
 
    public MetierException(String message) {
        super(message);
    }
 
    public MetierException(String message, Throwable cause) {
        super(message, cause);
    }
}