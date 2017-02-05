package ro.fmi.rpg.service.auth;

import com.auth0.jwt.JWTSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.Character;
import ro.fmi.rpg.dao.repository.CharacterRepository;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.notifications.NotificationService;
import ro.fmi.rpg.to.login.LoginRequest;
import ro.fmi.rpg.to.login.LoginResponse;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.service.email.EmailService;
import ro.fmi.rpg.service.email.EmailType;
import ro.fmi.rpg.to.user.UserModel;

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

    @Autowired
    private NotificationService notificationService;

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
        sessionService.setUser(user);
        notificationService.notifyLogin(sessionService.getUser());
        return new LoginResponse(jwt);
    }

    public User register(UserModel user) throws RPGException {
        if(userRepository.findUserByEmail(user.getEmail()) != null){
            throw new RPGException("Email already in use");
        }

        User toBeSaved = new User();
        toBeSaved.setConfirmed(false);
        toBeSaved.setEmail(user.getEmail());
        toBeSaved.setFirstName(user.getFirstName());
        toBeSaved.setLastName(user.getLastName());
        toBeSaved.setPassword(user.getPassword());
        toBeSaved.setPhoneNumber("0767895301");
        userRepository.save(toBeSaved);

        Character character = new Character();
        character.setExperience(0);
        character.setLevel(1);
        character.setUser(toBeSaved);
        if(user.getPicture() == null || user.getPicture().isEmpty()){
            character.setPicture("http://vignette1.wikia.nocookie.net/wowwiki/images/2/2b/The_lich_king_Wotlk.png/revision/latest?cb=20150418151044");
        } else {
            character.setPicture(user.getPicture());
        }

        character.setHealth(100);

        characterRepository.save(character);
        emailService.send(EmailType.ACTIVATION_EMAIL, toBeSaved);
        return toBeSaved;
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
