package Response;
import java.io.Serializable;

public class LoginResponse  implements Serializable {
    private final String email,username,phone,name,password;
    public LoginResponse(String email, String username, String phone, String name, String password) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

}