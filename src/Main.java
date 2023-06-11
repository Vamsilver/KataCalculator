import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(calc(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) throws Exception
    {
        boolean isArabNumeral = false;

        String answer = "";
        String[] strings = input.split(" ");

        if(strings.length < 3)
            throw new Exception("throws Exception //т.к. строка не является математической операцией");
        else if (strings.length > 3) {
            throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию " +
                    "- два операнда и один оператор (+, -, /, *)");
        }

        if(isNumericInteger(strings[0]) && isNumericInteger(strings[2]))
            isArabNumeral = true;
        else if(!isNumericInteger(strings[0]) && !isNumericInteger(strings[2]))
        {
            strings[0] = String.valueOf(toArabNumeral(strings[0]));
            strings[2] = String.valueOf(toArabNumeral(strings[2]));
        }
        else
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");

        switch (strings[1]) {
            case "+" -> answer = String.valueOf(Integer.parseInt(strings[0]) + Integer.parseInt(strings[2]));
            case "-" -> answer = String.valueOf(Integer.parseInt(strings[0]) - Integer.parseInt(strings[2]));
            case "/" -> answer = String.valueOf(Integer.parseInt(strings[0]) / Integer.parseInt(strings[2]));
            case "*" -> answer = String.valueOf(Integer.parseInt(strings[0]) * Integer.parseInt(strings[2]));
        }

        if(!isArabNumeral)
            answer = toRomanNumeral(Integer.parseInt(answer));

        return answer;
    }

    public static boolean isNumericInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int toArabNumeral(String romanNumeric) throws Exception {
        Map<String,Integer> romanArab = new HashMap<>();

        romanArab.put("I", 1);
        romanArab.put("II", 2);
        romanArab.put("III", 3);
        romanArab.put("IV", 4);
        romanArab.put("V", 5);
        romanArab.put("VI", 6);
        romanArab.put("VII", 7);
        romanArab.put("VIII", 8);
        romanArab.put("IX", 9);
        romanArab.put("X", 10);

        if(romanArab.get(romanNumeric) == null)
            throw new Exception("throws Exception");

        return romanArab.get(romanNumeric);
    }

    public static String toRomanNumeral(int arabNumeric) throws Exception {
        Map<Integer,String> arabRoman = new HashMap<>();

        arabRoman.put(1, "I");
        arabRoman.put(2, "II");
        arabRoman.put(3, "III");
        arabRoman.put(4, "IV");
        arabRoman.put(5, "V");
        arabRoman.put(6, "VI");
        arabRoman.put(7, "VII");
        arabRoman.put(8, "VIII");
        arabRoman.put(9, "IX");
        arabRoman.put(10, "X");

        if(arabNumeric < 1)
            throw new Exception("throws Exception //т.к. в римской системе нет неположительных чисел");

        if(arabRoman.get(arabNumeric) == null)
            throw new Exception("throws Exception");

        return arabRoman.get(arabNumeric);
    }
}