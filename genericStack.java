import java.util.ArrayList;

class Stack<T> {
  ArrayList<T> stack = new ArrayList<>();

  void push(T arg) { this.stack.add(arg); }

  T pop() {
    T last = this.stack.get(this.stack.size() - 1);
    this.stack.remove(this.stack.size() - 1);
    return last;
  }

  void clear() { this.stack.clear(); }

  boolean isEmpty() { return this.stack.size() == 0; }

  void display() {
    for (int a = this.stack.size() - 1; a >= 0; --a) {
      System.out.println(this.stack.get(a));
    }
  }
}

public class genericStack {
  public static void main(String[] args) {

    // for string stack
    Stack<String> stringStack = new Stack<>();
    stringStack.push("what");
    stringStack.push("is");
    stringStack.display();
    String poppedString = stringStack.pop();
    System.out.println("popped is: " + poppedString);
    stringStack.clear();
    boolean resultString = stringStack.isEmpty();
    System.out.println("is empty: " + resultString);

    // for Integer class
    Stack<Integer> integerStack = new Stack<>();
    integerStack.push(1);
    integerStack.push(2);
    integerStack.display();
    Integer poppedInt = integerStack.pop();
    System.out.println("popped is: " + poppedInt);
    integerStack.clear();
    boolean resultInt = integerStack.isEmpty();
    System.out.println("is empty: " + resultInt);
  }
}
