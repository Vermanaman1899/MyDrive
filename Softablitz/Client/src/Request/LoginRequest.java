package Request;
import java.io.Serializable;


public class LoginRequest extends AppRequest implements Serializable {

    private String email;
    private String password;


    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.LOGIN_REQUEST;
    }
}
