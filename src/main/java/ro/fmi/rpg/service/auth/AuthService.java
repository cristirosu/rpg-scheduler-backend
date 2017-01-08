package ro.fmi.rpg.service.auth;

import com.auth0.jwt.JWTSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.Character;
import ro.fmi.rpg.dao.repository.CharacterRepository;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.to.login.LoginRequest;
import ro.fmi.rpg.to.login.LoginResponse;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.service.email.EmailService;
import ro.fmi.rpg.service.email.EmailType;

import java.util.HashMap;

/**
 * Created by User on 11/13/2016.
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CharacterRepository characterRepository;

    public LoginResponse login(LoginRequest loginRequest) throws RPGException {

        User user = userRepository.findUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if(user == null){
            throw new RPGException(404,"Incorrect username or password");
        }

        if(!user.getConfirmed()){
            throw new RPGException(403,"Please confirm your email before logging in");
        }

        final String issuer = "https://cristi.red/";
        final String secret = "ABC784*3125*123";

        final long iat = System.currentTimeMillis() / 1000L; // issued at claim
        final long exp = iat + 600000000L; // expires claim. In this case the token expires in 60 seconds
        final int userId = user.getId();

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("exp", exp);
        claims.put("iat", iat);
        claims.put("userId", userId);

        String jwt = signer.sign(claims);
        System.out.println("RETURNING JWT +++ " + jwt);
        return new LoginResponse(jwt);
    }

    public User register(User user) throws RPGException {
        if(userRepository.findUserByEmail(user.getEmail()) != null){
            throw new RPGException("Email already in use");
        }

        userRepository.save(user);

        Character character = new Character();
        character.setExperience(0);
        character.setLevel(1);
        character.setUser(user);
        character.setPicture("");
        character.setHealth(100);

        characterRepository.save(character);
        emailService.send(EmailType.ACTIVATION_EMAIL, user);
        return user;
    }

    public void activate(Integer id) throws RPGException {
        User user = userRepository.getOne(id);
        if(user == null){
            throw new RPGException("User with id " + id + " does not exist");
        }
        user.setConfirmed(true);
        userRepository.save(user);
    }

}
