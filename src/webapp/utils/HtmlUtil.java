package webapp.utils;

import webapp.model.*;

import java.util.List;

public class HtmlUtil {

    public static String TitletoHtml(String title){
        return "<h3>"+title+"</h3>";
    }

    public static String ResumeSectiontoHtml(SectionType sectionType,Resume r,boolean html) {
        String outPut="";
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                TextSection textSection=(TextSection) r.getSection(sectionType);
                if (textSection!=null) {
                    if (html == true) {
                        outPut = "<ul>" + textSection.getText() + "</ul>";
                    } else {
                        outPut = textSection.getText();
                    }
                }
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                ListSection listSection=(ListSection) r.getSection(sectionType);
                if (listSection!=null) {
                    String outPut2 = "";
                    outPut = "<ul>";
                    for (String str : listSection.getListSection()) {
                        outPut += "<li>" + str + "</li>";
                        outPut2 += str+"\n";
                    }
                    outPut += "</ul>";

                    if (html == false) {
                        outPut = outPut2;
                    }
                }
                break;
        }
        return outPut;
    }

    public static String ResumeOrganizationtoHtml(SectionType sectionType,Resume r ) {
         String outPut="";
         return outPut;
    }



    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
