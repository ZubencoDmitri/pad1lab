package xPlatform;
import java.net.Socket;

public class Letter {
    private String name;
    private String message;
    private boolean sent;
    public Letter(String name, String message) {
        this.name = name;
        this.message = message;
        this.sent=false;
    }
    public String getName() {
        return name;
    }
    public String getMessage() {
        return message;
    }
    public boolean isSent() {
        return sent;
    }
    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
