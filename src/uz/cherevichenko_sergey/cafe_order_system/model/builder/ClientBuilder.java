package uz.cherevichenko_sergey.cafe_order_system.model.builder;

import org.apache.commons.codec.digest.DigestUtils;
import uz.cherevichenko_sergey.cafe_order_system.model.client.Client;

public class ClientBuilder {
    private final String emailAdmin;
    private final  String passwordAdmin;
    public ClientBuilder(){
        emailAdmin = "cherevichenkosn@gmail.com";
        passwordAdmin = DigestUtils.sha256Hex("Bradley-10121981");
    }
    public Client build(String name, String phoneNumber, String email, String password) {
        Client client =  new Client(name, phoneNumber,email,password);
        if (email.equals(emailAdmin) && DigestUtils.sha256Hex(password).equals(passwordAdmin)) {
            client.setAdmin(true);
        }
        return client;

    }


}

