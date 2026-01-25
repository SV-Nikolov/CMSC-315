import java.util.*;

public class NLPUtility {

    /**
     * Splits the given text into word tokens using one or more whitespace
     * or punctuation characters as delimiters.
     *
     * @param text the input string to be tokenized
     * @return an array of word tokens, excluding punctuation and whitespace
     */
    public static String[] splitTextIntoTokens(String text) {
        return text.split("[\\s\\p{P}]+");
    }

    /**
     * Counts the frequency of words in the given array, excluding those present in
     * the specified set of stop words.
     * The comparison is case-insensitive, and results are stored in a
     * {@link TreeMap} sorted alphabetically by word.
     *
     * @param words     An array of tokenized words to analyze.
     * @param stopWords A set of words to exclude from the frequency count (e.g.,
     *                  common stop words like "the", "and").
     * @return A {@link TreeMap} mapping each non-stop word to its frequency, sorted
     *         alphabetically.
     */
    public static TreeMap<String, Integer> countFilteredWords(String[] words, Set<String> stopWords) {
        TreeMap<String, Integer> wordCount = new TreeMap<>();
        
        for (String word : words) {
            String lowerWord = word.toLowerCase();
            
            // Skip if it's a stop word
            if (!stopWords.contains(lowerWord)) {
                wordCount.put(lowerWord, wordCount.getOrDefault(lowerWord, 0) + 1);
            }
        }
        
        return wordCount;
    }

    /**
     * Sorts the entries of a map by their values in descending order.
     * The result is returned as a {@link LinkedHashMap} to preserve the order of
     * sorted entries.
     *
     * @param map A map containing keys and integer values to be sorted by value.
     * @return A {@link LinkedHashMap} containing the same entries as the input map,
     *         sorted in descending order by value.
     */
    public static LinkedHashMap<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }

    /**
     * Performs sentiment analysis by scanning the word-frequency map.
     * Adds up the total frequency of all words found in the predefined
     * positive and negative word sets.
     *
     * @param wordMap A map of words and their frequencies.
     * @return A summary string in the format: "Positive: X, Negative: Y"
     *         where X and Y are the total counts of positive and negative words.
     */
    public static String getSentiment(Map<String, Integer> wordMap, Set<String> positiveWords,
            Set<String> negativeWords) {
        int positiveCount = 0;
        int negativeCount = 0;
        
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey().toLowerCase();
            int frequency = entry.getValue();
            
            if (positiveWords.contains(word)) {
                positiveCount += frequency;
            }
            if (negativeWords.contains(word)) {
                negativeCount += frequency;
            }
        }
        
        return "Positive: " + positiveCount + ", Negative: " + negativeCount;
    }

    /**
     * Finds the words with the highest frequency in the given map and returns a map
     * containing a sorted word list along with the maximum frequency value.
     *
     * @param wordMap A map of words and their corresponding frequencies.
     * @return A map containing:
     *         - "words": A list of words with the highest frequency, sorted
     *         alphabetically.
     *         - "frequency": The highest frequency value.
     */
    public static Map<String, Object> getWordsWithMaxFrequency(Map<String, Integer> wordMap) {
        Map<String, Object> result = new HashMap<>();
        
        // Find maximum frequency
        int maxFrequency = 0;
        for (int frequency : wordMap.values()) {
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }
        
        // Collect all words with maximum frequency
        List<String> maxWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                maxWords.add(entry.getKey());
            }
        }
        
        // Sort words alphabetically
        Collections.sort(maxWords);
        
        result.put("words", maxWords);
        result.put("frequency", maxFrequency);
        
        return result;
    }

}
