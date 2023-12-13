package nonFrameworkTests;

import java.util.*;

public class JavaCoding {
    public static void main(String[] args) {
        JavaCoding.reverseString("application");
        JavaCoding.getLengthOfString("java");
        JavaCoding.checkPrime(8);
        JavaCoding.printPrimeNumbers(40);
        JavaCoding.formatString("aabbccssbbaddd");
        JavaCoding.removeDuplicates("aabdsksdlasnmakd");
        JavaCoding.powerOfNumber(3, 4);
        JavaCoding.removeDuplicateWords("a boy is a boy not a girl");
        JavaCoding.perfectNumber(29);
        JavaCoding.fibonacci(10);
        JavaCoding.recursiveMessage(4);
        JavaCoding.combination(7, 5);
        JavaCoding.permutation(7, 5);
        JavaCoding.extractCharacters("Email of John is john007@mail.com");
        JavaCoding.sumOfNumbersInString("1 boy has 100 rupees in his 1 pocket and 22 rupees in 1 pocket");
        JavaCoding.anagram("listen", "silent");
        JavaCoding.pyramidOfNumbers(4);
        JavaCoding.pyramidOfStars(6);
        JavaCoding.inversePyramid(5);
        JavaCoding.swapNumbers(8, 9);
        JavaCoding.armstrong(158);
        JavaCoding.secondLargest();
        JavaCoding.checkVowels("Why");
        JavaCoding.toggleCase("Any casE");
        JavaCoding.printDuplicateCharacters("ritish");

    }

    public static void reverseString(String text) {
        String reverse = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reverse += text.charAt(i);
        }
        System.out.println("Reverse of the string is: " + reverse);
    }

    public static void getLengthOfString(String text) {
        int count = 0;
        for (char character : text.toCharArray()) {
            count++;
        }
        System.out.println("Length of given string is: " + count);
    }

    public static void checkPrime(int number) {
        int counter = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                counter += 1;
            }
        }
        if (counter == 2) {
            System.out.println("Given number " + number + " is prime");
        } else {
            System.out.println("Given number " + number + " is not prime");
        }
    }

    public static void printPrimeNumbers(int count) {
        System.out.println("Prime numbers from 1 to " + count + " are: ");
        for (int i = 2; i <= count; i++) {
            int counter = 0;
            for (int j = 1; j < count; j++) {
                if (i % j == 0) {
                    counter += 1;
                }
            }
            if (counter == 2) {
                System.out.printf(i + "\t");
            }
        }
    }

    public static void formatString(String text) {
        String[] array = text.split("");
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : array) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        System.out.println("\nAfter formatting string: ");
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }

    public static void removeDuplicates(String text) {
        String[] array = text.split("");
        ArrayList<String> list = new ArrayList<>();
        for (String s : array) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        System.out.println("After removing duplicates: " + list);
    }

    public static void powerOfNumber(int number, int exponent) {
        double power = 1;
        int initial = exponent;
        while (exponent > 0) {
            power *= number;
            exponent--;
        }
        System.out.println(number + " power of " + initial + " is " + power);
    }

    public static void removeDuplicateWords(String text) {
        String[] words = text.split(" ");
        List<String> unique = new ArrayList<>();
        for (String s : words) {
            if (!unique.contains(s)) {
                unique.add(s);
            }
        }
        String s = "";
        for (String a : unique) {
            s += a + " ";
        }
        System.out.println("After removing duplicate words: " + s.trim());
    }

    public static void perfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        if (number == sum) {
            System.out.println("Given number " + number + " is perfect number");
        } else System.out.println("Given number " + number + " is not perfect number");
    }

    public static void fibonacci(int size) {
        int first = 0;
        int next = 1;
        int sum = 0;
        for (int i = 1; i <= size; i++) {
            System.out.print(first + " ");
            sum = first + next;
            first = next;
            next = sum;
        }
        System.out.println();
    }

    public static void recursiveMessage(int count) {
        if (count > 0) {
            System.out.println("Print Message by recursive method " + count + " time");
            count--;
            recursiveMessage(count);
        }
        if (count == 0) return;
    }

    public static void combination(int n, int r) {
        if (r == 0 || n == 1) {
            System.out.println("NCR is: 1");
        }
        if (r == 1) {
            System.out.println("NCR is: " + n);
        }
        if (n < r) {
            System.out.println("NCR is: 0");
        } else {
            int result = factorial(n) / (factorial(r) * factorial(n - r));
            System.out.println("NCR is: " + result);
        }
    }

    public static int factorial(int number) {
        if (number > 0) {
            return number * factorial(number - 1);
        } else {
            return 1;
        }
    }

    public static void permutation(int n, int r) {
        System.out.println("NPR is: " + factorial(n) / factorial(n - r));
    }

    public static void extractCharacters(String text) {
        text = text.replaceAll("[^A-Za-z]", " ");
        text = text.replaceAll(" +", " ");
        System.out.println("Text after removing numbers and special characters: " + text);
    }

    public static void sumOfNumbersInString(String text) {
        text = text.replaceAll("[A-Za-z]", " ");
        text = text.replaceAll(" +", " ");
        int sum = 0;
        for (String array : text.split(" ")) {
            sum += Integer.parseInt(array);
        }
        System.out.println("Sum of numbers in string is: " + sum);
    }

    public static void anagram(String text1, String text2) {
        String[] array1 = text1.split("");
        String[] array2 = text2.split("");
        Arrays.sort(array1);
        Arrays.sort(array2);
        if (array2.length == array1.length) {
            if (Arrays.equals(array1, array2)) {
                System.out.println(text1 + " is anagram of " + text2);
            }
        } else System.out.println("Both strings are not anagram to each other");
    }

    public static void pyramidOfNumbers(int size) {
        int spaces = size - 1;
        int stars = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= stars; k++) {
                System.out.print(k + " ");
            }
            spaces--;
            stars++;
            System.out.println();
        }
    }

    public static void pyramidOfStars(int size) {
        int spaces = size - 1;
        int stars = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= stars; k++) {
                System.out.print("* ");
            }
            spaces--;
            stars++;
            System.out.println();
        }
    }

    public static void inversePyramid(int size) {
        int stars = size;
        int spaces = 0;
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= stars; k++) {
                System.out.print("* ");
            }
            System.out.println();
            stars--;
            spaces += 1;
        }
    }

    public static void swapNumbers(int number1, int number2) {
        number1 = number2 + number1;
        number2 = number1 - number2;
        number1 = number1 - number2;
        System.out.println("After swapping the values are " + number1 + " " + number2);
    }

    public static void armstrong(int number) {
        int result = 0;
        int temp;
        int mod = number;
        while (mod > 0) {
            temp = mod % 10;
            mod = mod / 10;
            result = result + (temp * temp * temp);
        }
        if (number == result) {
            System.out.println("Given number is armstrong");
        } else System.out.println("Given number is not armstrong");
    }

    public static void secondLargest() {
        int[] array = {11, 2, 3112, 2323, 11, 2323, 3322};
        int largest = 0, second = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > largest) {
                second = largest;
                largest = array[i];
            } else if (array[i] > second) {
                second = array[i];
            }
        }
        System.out.println("Largest number is: " + largest);
        System.out.println("Second largest number is: " + second);
    }

    public static void checkVowels(String text) {
        if (text.toLowerCase().matches(".*[aeiou].*")) {
            System.out.println("Given string contains vowels");
        } else System.out.println("Given string does not contains vowels");
    }

    public static void toggleCase(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                result = result + Character.toLowerCase(ch);
            } else {
                result = result + Character.toUpperCase(ch);
            }
        }
        System.out.println("After changing the case of text: " + result);
    }

    public static void printDuplicateCharacters(String text) {
        String[] array = text.split("");
        for (int i = 0; i < text.length(); i++) {
            int count = 0;
            for (int j = i + 1; j < text.length(); j++) {
                if (array[i].equals(array[j])) {
                    count++;
                }
            }
            if (count >=1) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();

    }
}