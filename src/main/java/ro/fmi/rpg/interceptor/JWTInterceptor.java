package ro.fmi.rpg.interceptor;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.service.auth.SessionService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 11/12/2016.
 */
@Component
public class JWTInterceptor extends HandlerInterceptorAdapter {

    private final String secret = "ABC784*3125*123";

    @Autowired
    private ServletContext context;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserRepository userRepository;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }


        // JWT VALID ?
        // SESIUNE NOUA ?
        // IAU ID
        // REQ BAZA DE DATA
        // TREC PE SESIUNE USER-UL

        String jwt = request.getHeader("Authorization");
        if (request.getSession().isNew()) {
            if (sessionService.getUser() != null)
                System.out.println("Session user ; " + sessionService.getUser().getEmail());
        }

        System.out.println("JWT ===>> " + jwt);

        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String, Object> claims = verifier.verify(jwt);

                if(request.getSession().isNew()){
                    System.out.println("NEW SESSION SETTING USER");
                    int userId = (int) claims.get("userId");
                    User sessionUser = userRepository.findOne(userId);
                    System.out.println("SET USER ID == " + userId);
                    sessionService.setUser(sessionUser);
                }


        } catch (JWTVerifyException e) {
            // Invalid Token
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
            //return new ServerResponse("Unauthorized", 401, new Headers<Object>());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SignatureException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return false;
        }
        // Proceed normally*/

        return true;
    }

}