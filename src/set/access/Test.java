package set.access;

/**
 * Created by victor on 16.05.15.
 */
public class Test {
    public static void main(String[] args) {
        //launch(args);
        final long startTime = System.currentTimeMillis();
        int numberOfProperties = 4;
        Table table = new Table(numberOfProperties);

        // print alle kaarten
        /*for (int i = 0; i < table.getTotalCards().size(); i++) {
            for (int j = 0; j < numberOfProperties; j++) {
                System.out.print(table.getTotalCards().get(i).getProperties()[j] + " ");
            }
            System.out.println();
        }*/

        // print alle kaarten op tafel
        /*for (int i = 0; i < table.getTableCards().size(); i++) {
            for (int j = 0; j < numberOfProperties; j++) {
                System.out.print(table.getTableCards().get(i).getProperties()[j] + " ");
            }
            System.out.println();
        }*/

        // print gevonden set + enkele details
        System.out.println("Found set: " + table.findSet().toString());
        System.out.println("Number of cards on table: " + table.getTableCards().size());
        System.out.println("Number of cardproperties: " + numberOfProperties);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "milliseconds" );


        // 10.000 samples om snelheid te testen
        /*long maxtime = 0;
        for (int i = 0; i < 10000; i++) {
            long start = System.currentTimeMillis();
            Table t = new Table(numberOfProperties);
            t.findSet();
            long end = System.currentTimeMillis();
            if (maxtime < end-start) {
                maxtime = end-start;
            }
        }
        System.out.println(maxtime);
        */
    }
}
