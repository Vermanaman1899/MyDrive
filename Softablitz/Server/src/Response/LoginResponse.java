package Response;
import java.io.Serializable;

public class LoginResponse extends AppResponse implements Serializable {
    private final String email,username,phone,name,password;
    public LoginResponse(String email, String username, String phone, String name, String password) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    @Override
    ResponseType getResponseType() {
        return ResponseType.LOGIN_RESPONSE;
    }
}