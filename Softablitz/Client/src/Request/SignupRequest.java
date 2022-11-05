package Request;
import java.io.Serializable;

//public enum RequestType{
//    LOGIN_REQUEST,
//    SIGNUP_REQUEST;
//}


//public abstract class AppRequest implements Serializable {
//    public abstract RequestType getRequestType();
//}

public class SignupRequest implements Serializable {

    private String email;
    private String username;
    private String password;
    private String phone;
    private String name;

    public SignupRequest() {
    }

    public SignupRequest(String name, String username, String email, String password, String phone) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }


//    @Override
//    public RequestType getRequestType() {
//        return RequestType.SIGNUP_REQUEST;
//    }
}
