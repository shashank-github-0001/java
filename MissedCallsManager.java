import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class MissedCalls {
  int ph;
  String name;
  LocalDateTime time;

  MissedCalls(int ph, String name, LocalDateTime time) {
    this.ph = ph;
    this.name = name;
    this.time = time;
  }
}

public class MissedCallsManager {

  static ArrayList<MissedCalls> missedCalls = new ArrayList<>();

  public static void main(String[] args) {
    addMissedCall(12345, "god");
    addMissedCall(67890);
    addMissedCall(13579, "god");
    addMissedCall(24680, "devil");

    Iterator<MissedCalls> it = missedCalls.iterator();
    Scanner s = new Scanner(System.in);

    /*
     * we iterate till the end
     * if we want we will delete the particular missedCall
     * else we can also display the details
     */

    /*
     * actually the thing is that we should
     * be able to delete it at start when displaying
     * also be able to delete it at end based on number
     * so give a choice to display the next delete the current
     */

    while (it.hasNext()) {
      MissedCalls obj = it.next();
      System.out.println("you have missed call from" + obj.ph);
      System.out.println("select 1 for displaying the details");
      System.out.println("select 2 for deleting the missedcall");
      int res = s.nextInt();
      if (res == 1) {
        display(obj);
      } else if (res == 2) {
        it.remove();
        System.out.println("missed call deleted");
      } else {
        System.out.println("please select from only the above 2 option");
      }
    }

    s.close();
  }

  public static void addMissedCall(int ph, String name) {
    MissedCalls ms = new MissedCalls(ph, name, LocalDateTime.now());
    missedCalls.add(ms);
  }

  public static void addMissedCall(int ph) {
    MissedCalls ms = new MissedCalls(ph, "private caller", LocalDateTime.now());
    missedCalls.add(ms);
  }

  public static void printMissedCalls() {
    for (MissedCalls ms : missedCalls) {
      System.out.println("ph no is: " + ms.ph);
      System.out.println("name is: " + ms.name);
      System.out.println("time is: " + ms.time);
    }
  }

  public static void display(MissedCalls obj) {
    System.out.println("ph no is: " + obj.ph);
    System.out.println("name is: " + obj.name);
    System.out.println("time is: " + obj.time);
  }
}
