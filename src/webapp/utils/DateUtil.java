package webapp.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    final static String[] MONTH={"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};

    public static LocalDate of(int year, Month month){
        return LocalDate.of(year,month,1);
    }

    public static LocalDate Parse(String date){
        if (HtmlUtil.isEmpty(date)){
             return LocalDate.now();
        } else {
            return LocalDate.parse(date);
        }
    }

    public static String formatDateOutput(LocalDate date){
       if (date!=null) {
           return MONTH[date.getMonthValue() - 1] + " " + date.getYear();
       }else {return "";}
    }
}
