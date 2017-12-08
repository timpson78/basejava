package webapp.tmp;

import java.util.*;

public class CollectionStorage {


    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException {
        ArrayList<String> list = new ArrayList<String>(10);
        list.add("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.add(3,"Hundred");

        for (String lst : list) {
            System.out.println(lst);
        }
      Iterator itr= list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("__________________________________");

       LinkedList <String> linkedLst=new LinkedList <String>();
        linkedLst.add("1");
        linkedLst.add("2");
        linkedLst.add("3");

        for (String lst:linkedLst) {
            System.out.println(lst);
        }
        System.out.println("__________________________________");

       HashMap<String, String> hashMp= new HashMap(4);
       hashMp.put("UUID1","Первый");

       hashMp.put("UUID2","Второй");
       hashMp.put("UUID3","Третий");
       hashMp.put("UUID4","Четвертый");
       hashMp.put("UUID5","Пятый");
       hashMp.put("UUID6","Шестой");

       //Set entrySt= hashMp.entrySet();


        for (HashMap.Entry<String,String> pair:hashMp.entrySet()) {
            System.out.println(pair.getKey()+"-" +pair.getValue());
        }



        System.out.println("__________________________________");
       LinkedHashMap linkHsMap=new LinkedHashMap();

        linkHsMap.put("UUID1","Второй");
        linkHsMap.put("UUID2","Третий");
        linkHsMap.put("UUID3","Четвертый");
        linkHsMap.put("UUID4","Пятый");
        linkHsMap.put("UUID5","Шестой");

        System.out.println(linkHsMap.toString());

        System.out.println("__________________________________");



        HashSet nSet=new HashSet();
        nSet.add("First");
        nSet.add("First");
        nSet.add("Second");
        nSet.add("Third");
        nSet.add("Fourth");
        System.out.println(nSet.toString());

    }




}
