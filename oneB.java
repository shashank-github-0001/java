import java.util.HashMap;

public class oneB {
  public static void main(String[] args) {
    boolean result = isAnagram(args[0], args[1]);
    System.out.println("anagram: " + result);
  }

  public static boolean isAnagram(String s1, String s2) {
    HashMap<Character, Integer> charCount = new HashMap<>();

    for (Character c : s1.toCharArray()) {
      charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }

    for (Character c : s2.toCharArray()) {
      if (!charCount.containsKey(c)) {
        return false;
      }
      charCount.put(c, charCount.get(c) - 1);
      if (charCount.get(c) == 0) {
        charCount.remove(c);
      }
    }

    return charCount.isEmpty();
  }
}
