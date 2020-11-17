package Project3;

import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
	// Watch friday video: List of rentals are saved in sorted fasion, games first, sorted by date
        //AVOID GUI!!!! comment out update screen everywhere!
        //if you get it working
        //LEARN!!!! : selection sort is O(n*n)
        //insertion sort: O(n)
        //merge sort O()




        MyDoubleWithOutTailLinkedList Llist = new MyDoubleWithOutTailLinkedList();

        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();

        Game game1 = new Game("Person1", g3, g2, null, "title1",
                new Console("Person1", g3, g2, null, ConsoleTypes.PlayStation4));

        Game game2 = new Game("Person2", g3, g2, null, "Halo",
                new Console("Person2", g3, g2, null, ConsoleTypes.PlayStation4));



        System.out.println(Llist.size());
        Llist.add(game1);
        Llist.add(game2);
        System.out.println(Llist.size());


    }
}
