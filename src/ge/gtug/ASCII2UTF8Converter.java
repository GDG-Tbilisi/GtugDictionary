package ge.gtug;


import java.util.HashMap;
import java.util.Map;

public class ASCII2UTF8Converter {
  // Georgian unicode alphabet starts at 4304
  private static final int UTF8_CHAR_CODE_START = 4304;

  // Populate array of Georgian ASCII characters
  private static final char[] GEO_ASCII =
          "abgdevzTiklmnopJrstufqRySCcZwWxjh".toCharArray();

  private static final int ALPHABET_LENGTH = GEO_ASCII.length;

  // Populate Georgian ASCII to Unicode characters Map
  private static final Map<Character, Character> GEO_UTF8 =
          new HashMap<Character, Character>() {{

    for (int i = 0; i < ALPHABET_LENGTH; i++) {
      put(GEO_ASCII[i], (char) (UTF8_CHAR_CODE_START + i));
    }
  }};

  public static String toUTF8(char[] chars) {
    int len = chars.length;

    for (int i = 0; i < len; i++) {
      Character c = GEO_UTF8.get(chars[i]);

      if (c != null) {
        chars[i] = c;
      }
    }

    return String.valueOf(chars);
  }

  public static String toUTF8(String input) {
    return toUTF8(input.toCharArray());
  }

  public static String toASCII(char[] chars) {
    int len = chars.length;

    for (int i = 0; i < len; i++) {
      int index = chars[i] - UTF8_CHAR_CODE_START;

      if (index >= 0 && index < ALPHABET_LENGTH) {
        chars[i] = GEO_ASCII[index];
      }
    }

    return String.valueOf(chars);
  }

  public static String toASCII(String input) {
    return toASCII(input.toCharArray());
  }

/*  public static void main(String[] args) {
    // test input
    String input = "ioseb ZmanaSvili";

    // converted input
    String converted = ASCII2UTF8Converter.toUTF8(input);

    System.out.println(converted);
    System.out.println(ASCII2UTF8Converter.toASCII(converted));

  }*/
}
