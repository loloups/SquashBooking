package fr.squash.booking;

import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    private final static String JWT_SALT = "12345678901234567890123456789012";

    public static void main(String[] args) {
        Spark.get("/token", (req, res) -> {
            String user = req.queryParams("user");
            String password = req.queryParams("password");
            Map<String, String> claims = new HashMap<>();
            claims.put("sub", user);
            claims.put("password", password);
            JwtGenerator generator = new JwtGenerator(new SecretSignatureConfiguration(JWT_SALT));
            return generator.generate(claims);
        });
    }
}
