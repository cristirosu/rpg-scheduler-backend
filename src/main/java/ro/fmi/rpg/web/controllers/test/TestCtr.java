package ro.fmi.rpg.web.controllers.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 1/23/2017.
 */
@RestController
public class TestCtr {

    @Autowired
    private Helper helper;

    @GetMapping("/gett")
    private List<Test> getTest(){
        List<Test> test = new ArrayList<>();
        test.add(new Test("Test", "New TEST", "57 TEST", "person", 88));
        test.add(new Test("Test", "New TEST", "57 TEST", "person", 88));
        test.add(new Test("Test", "New TEST", "57 TEST", "person", 88));
        test.add(new Test("Test", "New TEST", "57 TEST", "person", 88));

        Test t = new Test();
        t.setColor("REEED");
        System.out.println(t.getColor());
        System.out.println(t.hashCode());
        helper.test(t);
        System.out.println(t.getColor());
        System.out.println(t.hashCode());

        return test;
    }

}
