package ro.fmi.rpg.service.helper;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 12/11/2016.
 */
@Service
public class ConverterHelper {

    //static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //2016-12-21T21:07:00.000Z
    public Date getDateFromString(String stringDate) throws ParseException {
        stringDate = stringDate.substring(0, stringDate.length() - 8);
        stringDate = stringDate.replace("T", " ");
        Date date = simpleDateFormat.parse(stringDate);
        System.out.println("date : " + simpleDateFormat.parse(stringDate));
        return date;
    }

}
