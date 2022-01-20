package rohsik2.com.carrot.service;

import rohsik2.com.carrot.domain.User;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TokenService {
    private final HashMap<String, User> tokens;

    public TokenService() {
        tokens = new HashMap<>();
    }

    public String get_token(User user){
        String token = randomStringGenerator();
        while(tokens.keySet().contains(token))
            token = randomStringGenerator();
        tokens.put(token, user);
        return token;
    }

    public boolean isValidToken(String token){
        return tokens.keySet().contains(token);
    }

    public User getUserByToken(String token){
        if(isValidToken(token))
            return tokens.get(token);
        return null;
    }

    public String randomStringGenerator() {
        String charset = "abcdefghijklmnopqrstuvwxyz1234567890";
        String generatedString = "";
        for(int i =0; i<20; i++){
            int next_idx = (new Random()).nextInt(charset.length());
            generatedString += charset.substring(next_idx,next_idx+1);
        }
        return generatedString;
    }

}
