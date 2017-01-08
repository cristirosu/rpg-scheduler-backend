package ro.fmi.rpg.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 11/13/2016.
 */
@Component
public class LoggerInterceptor  extends HandlerInterceptorAdapter {

    private Logger LOG = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Autowired
    private ServletContext context;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOG.info("-----------------------------------------------------------------------------------------------------------------------------");
        LOG.info("Request path " + request.getRequestURI());
        LOG.info("Request method " + request.getMethod());

        return true;
    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOG.info("-----------------------------------------------------------------------------------------------------------------------------");
    }
}
