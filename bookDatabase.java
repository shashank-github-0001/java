import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class bookDatabase {
  public static ArrayList<Book> bl = new ArrayList<>();
  public static void main(String[] args) {

    addBook("t1", "a1", "p1", 1);
    addBook("t2", "a2", "p2", 2);
    addBook("t3", "a3", "p3", 3);
    addBook("t4", "a4", "p4", 4);
    addBook("t5", "a5", "p5", 5);

    Collections.sort(bl);
    Scanner s = new Scanner(System.in);
    ArrayList<Book> nbl = new ArrayList<>(bl);
    System.out.print("give the author name: ");
    String auth = s.nextLine().trim();
    printAuthor(auth);
  }

  public static void addBook(String title, String author, String publisher,
                             int price) {
    Book bo = new Book(title, author, publisher, price);
    bl.add(bo);
  }

  public static void printAuthor(String author) {
    for (Book o : bl) {
      if (o.author.equals(author)) {
        System.out.println("title: " + o.title);
        System.out.println("author: " + o.author);
        System.out.println("publisher: " + o.publisher);
        System.out.println("price: " + o.price);
      }
    }
  }
}

class Book implements Comparable<Book> {
  String title, author, publisher;
  int price;

  Book(String title, String author, String publisher, int price) {
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.price = price;
  }

  @Override
  public int compareTo(Book o) {
    return Integer.compare(this.price, o.price);
  }
}
