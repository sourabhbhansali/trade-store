package main;

public class TradeException extends RuntimeException {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TradeException() {
        super();
    }

    public TradeException(String message) {
        super(message);
        this.message = message;
    }
}
