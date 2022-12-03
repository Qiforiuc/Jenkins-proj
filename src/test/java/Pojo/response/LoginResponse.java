package Pojo.response;

public class LoginResponse {
    private String token;
    public LoginResponse(){
        super();
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
