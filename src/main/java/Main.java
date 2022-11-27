import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger counter3 = new AtomicInteger(0);
    public static AtomicInteger counter4 = new AtomicInteger(0);
    public static AtomicInteger counter5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isPalindrome(texts[i]) == true && !isRepite(texts[i])) {
                    switch (texts[i].length()) {
                        case 3:
                            counter3.addAndGet(1);
                            break;
                        case 4:
                            counter4.addAndGet(1);
                            break;
                        case 5:
                            counter5.addAndGet(1);
                            break;
                        default:
                            break;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isRepite(texts[i])) {
                    switch (texts[i].length()) {
                        case 3:
                            counter3.addAndGet(1);
                            break;
                        case 4:
                            counter4.addAndGet(1);
                            break;
                        case 5:
                            counter5.addAndGet(1);
                            break;
                        default:
                            break;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isAlphabetically(texts[i]) && !isRepite(texts[i])) {
                    switch (texts[i].length()) {
                        case 3:
                            counter3.addAndGet(1);
                            break;
                        case 4:
                            counter4.addAndGet(1);
                            break;
                        case 5:
                            counter5.addAndGet(1);
                            break;
                        default:
                            break;
                    }
                }
            }
        }).start();
        System.out.println("Красивых слов с длиной 3: " + counter3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + counter4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + counter5 + " шт");


    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        return text.equals(new StringBuilder(text).reverse().toString());
    }

    public static boolean isRepite(String text) {
        return text.contains("a") && !text.contains("b") && !text.contains("c")
                || text.contains("b") && !text.contains("a") && !text.contains("c")
                || text.contains("c") && !text.contains("a") && !text.contains("b")
                ;
    }

    public static boolean isAlphabetically(String text) {
        return text.equals(text.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
    }

}


