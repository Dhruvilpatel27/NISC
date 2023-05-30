import java.util.ArrayList;
import java.util.List;

public class CountOccurencesTimes {
    public static record Occurrence(char character, int numberOfOccurrences) {}

    public static CountOccurencesTimes.Occurrence[] countOccurrences(String text) {
        int[] occurrences = new int[128]; // Array to store the occurrences of each character
        List<CountOccurencesTimes.Occurrence> result = new ArrayList<>();

        for (char c : text.toCharArray()) {
            occurrences[c]++;
        }

        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] > 0) {
                result.add(new CountOccurencesTimes.Occurrence((char) i, occurrences[i]));
            }
        }

        return result.toArray(new CountOccurencesTimes.Occurrence[0]);
    }

    public static void main(String[] args) {
        String text = "Typical Sentence";
        CountOccurencesTimes.Occurrence[] occurrences = countOccurrences(text);

        for (CountOccurencesTimes.Occurrence occurrence : occurrences) {
            System.out.println(occurrence.character() + ": " + occurrence.numberOfOccurrences());
        }
    }
}
