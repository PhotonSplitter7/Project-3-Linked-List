package Project3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// Watch friday video: List of rentals are saved in sorted fasion, games first, sorted by date
        //AVOID GUI!!!! comment out update screen everywhere!
        //if you get it working
        //LEARN!!!! : selection sort is O(n*n)
        //insertion sort: O(n)
        //merge sort O()




        MyDoubleWithOutTailLinkedList Llist = new MyDoubleWithOutTailLinkedList();

        /**THIS GENERATES LIST WITH NO EQUAL DATES*/
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();
        GregorianCalendar g7 = new GregorianCalendar();
        GregorianCalendar g8 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2020");
            g1.setTime(d1);
            Date d2 = df.parse("12/22/2020");
            g2.setTime(d2);
            Date d3 = df.parse("12/20/2019");
            g3.setTime(d3);
            Date d4 = df.parse("7/02/2020");
            g4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            g5.setTime(d5);
            Date d6 = df.parse("9/29/2020");
            g6.setTime(d6);
            Date d7 = df.parse("7/25/2020");
            g7.setTime(d7);
            Date d8 = df.parse("7/29/2020");
            g8.setTime(d8);




        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }

        Game game1 = new Game("Person1", g3, g2, null, "title1",
                new Console("Person1", g3, g2, null, ConsoleTypes.PlayStation4));
        Game game2 = new Game("Person1", g3, g1, null, "title2",
                new Console("Person1", g3, g1, null, ConsoleTypes.PlayStation4));
        Game game3 = new Game("Person1", g5, g3, null, "title2",
                new Console("Person1", g5, g3, null, ConsoleTypes.SegaGenesisMini));
        Game game4 = new Game("Person7", g4, g8, null, "title2", null);
        Game game5 = new Game("Person3", g3, g1, g1, "title2",
                new Console("Person3", g3, g1, g1, ConsoleTypes.XBoxOneS));
        Game game6 = new Game("Person6", g4, g7, null, "title1",
                new Console("Person6", g4, g7, null, ConsoleTypes.NintendoSwich));
        Game game7 = new Game("Person5", g4, g8, null, "title1",
                new Console("Person5", g4, g8, null, ConsoleTypes.NintendoSwich));

        Console console1 = new Console("Person1", g4, g6, null, ConsoleTypes.PlayStation4);
        Console console2 = new Console("Person2", g5, g3, null, ConsoleTypes.PlayStation4);
        Console console3 = new Console("Person5", g4, g8, null, ConsoleTypes.SegaGenesisMini);
        Console console4 = new Console("Person6", g4, g7, null, ConsoleTypes.SegaGenesisMini);
        Console console5 = new Console("Person1", g5, g4, g3, ConsoleTypes.XBoxOneS);

/****************************/





        Llist.add(game1);
        Llist.add(game4);
        Llist.add(game5);


        System.out.println(Llist.size() + " = Length");

        Llist.display();






    }
}
