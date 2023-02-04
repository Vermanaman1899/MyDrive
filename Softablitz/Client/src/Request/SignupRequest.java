package Request;
import java.io.Serializable;


public class SignupRequest extends AppRequest implements Serializable {

    private String email;
    private String username;
    private String password;
    private String phone;
    private String name;


    public SignupRequest(String name, String email, String username, String password, String phone) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public RequestType getRequestType() {
        return RequestType.SIGNUP_REQUEST;
    }

}

