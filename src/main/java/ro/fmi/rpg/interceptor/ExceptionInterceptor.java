package ro.fmi.rpg.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.to.ErrorResponse;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 11/13/2016.
 */
@RestControllerAdvice
public class ExceptionInterceptor {

    private Logger LOG = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RPGException.class)
    @ResponseBody
    public ErrorResponse badRequest(HttpServletRequest req, RPGException exception) {
        LOG.error("Exceptione " + exception);
        return new ErrorResponse(exception.getErrorMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ErrorResponse idk(HttpServletRequest req, Exception exception) {
        LOG.error("Exception " + exception);
        return new ErrorResponse(exception.getMessage());
    }

}