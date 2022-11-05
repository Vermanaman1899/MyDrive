package Request;
import java.io.Serializable;

//public enum RequestType{
//    LOGIN_REQUEST,
//    SIGNUP_REQUEST;
//}


//public abstract class AppRequest implements Serializable {
//    public abstract RequestType getRequestType();
//}


public class LoginRequest implements Serializable {

    private String email;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
//    public RequestType getRequestType() {
//        return RequestType.LOGIN_REQUEST;
//    }
}
