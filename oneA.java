public class test {
  public static void main(String[] args) {
    String mainString = args[0];
    String subString = args[1];
    int result = frequency(mainString, subString);
    System.out.println("the frequency is: " + result);
  }

  public static int frequency(String main, String subs) {
    int frequency = 0;
    int index = 0;
    while ((index = main.indexOf(subs, index)) != -1) {
      frequency++;
      index += subs.length();
    }
    return frequency;
  }
}
