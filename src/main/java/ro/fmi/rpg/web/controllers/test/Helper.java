package ro.fmi.rpg.web.controllers.test;

import org.springframework.stereotype.Service;

/**
 * Created by User on 1/27/2017.
 */
@Service
public class Helper {

    public void test(Test t){
        t.setColor("BLUEEE");
    }

}
