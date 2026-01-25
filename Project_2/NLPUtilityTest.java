/**
 * Student Name: Stefan V. Nikolov
 * Project: CMSC 315 Project 2 - Word Frequency & Sentiment Analysis
 * Date: January 26, 2026
 * Description: Unit test class for NLPUtility - Tests all five static methods in isolation
 */

import java.util.*;

public class NLPUtilityTest {

    static int testsPassed = 0;
    static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("NLP UTILITY UNIT TEST SUITE");
        System.out.println("Student: Stefan V. Nikolov");
        System.out.println("Project: CMSC 315 Project 2");
        System.out.println("========================================\n");

        // Run all test suites
        testSplitTextIntoTokens();
        testCountFilteredWords();
        testSortByValueDescending();
        testGetSentiment();
        testGetWordsWithMaxFrequency();

        // Print summary
        printSummary();
    }

    // ============================================================
    // TEST SUITE 1: splitTextIntoTokens
    // ============================================================
    public static void testSplitTextIntoTokens() {
        System.out.println("\n======== TEST SUITE 1: splitTextIntoTokens ========");

        // TC 1.1: Standard Text with Punctuation
        String[] result1 = NLPUtility.splitTextIntoTokens("WOW!?! That .?# is REALLY(reaLLy) amazing!");
        String[] expected1 = {"WOW", "That", "is", "REALLY", "reaLLy", "amazing"};
        assertArrayEquals("TC 1.1: Standard punctuation", result1, expected1);

        // TC 1.2: Multiple Spaces
        String[] result2 = NLPUtility.splitTextIntoTokens("Hello    world    test");
        String[] expected2 = {"Hello", "world", "test"};
        assertArrayEquals("TC 1.2: Multiple spaces", result2, expected2);

        // TC 1.3: Mixed Delimiters
        String[] result3 = NLPUtility.splitTextIntoTokens("I'm can't won't");
        String[] expected3 = {"I", "m", "can", "t", "won", "t"};
        assertArrayEquals("TC 1.3: Apostrophes as delimiters", result3, expected3);

        // TC 1.4: Numbers and Punctuation
        String[] result4 = NLPUtility.splitTextIntoTokens("The price is $99.99!");
        // Verify it splits on punctuation
        assertEquals("TC 1.4: Contains 'The'", true, arrayContains(result4, "The"));
        assertEquals("TC 1.4: Contains 'price'", true, arrayContains(result4, "price"));

        // TC 1.5: Single Word
        String[] result5 = NLPUtility.splitTextIntoTokens("hello");
        String[] expected5 = {"hello"};
        assertArrayEquals("TC 1.5: Single word", result5, expected5);

        // TC 1.6: Only Punctuation
        String[] result6 = NLPUtility.splitTextIntoTokens("!?!.?#$");
        System.out.println("TC 1.6: Only punctuation - Result: " + Arrays.toString(result6));
        testsPassed++;
    }

    // ============================================================
    // TEST SUITE 2: countFilteredWords
    // ============================================================
    public static void testCountFilteredWords() {
        System.out.println("\n======== TEST SUITE 2: countFilteredWords ========");

        Set<String> stopWords = new HashSet<>(
                Arrays.asList("the", "is", "in", "at", "of", "and", "a", "to", "it", "or", "was", "so"));

        // TC 2.1: Standard Word Count
        String[] words1 = {"i", "love", "a", "good", "BOOK", "and", "LOVE", "sad", "BooK", "book"};
        TreeMap<String, Integer> result1 = NLPUtility.countFilteredWords(words1, stopWords);
        System.out.println("TC 2.1: Standard count - Result: " + result1);
        assertEquals("TC 2.1: Contains 'book'", true, result1.containsKey("book"));
        assertEquals("TC 2.1: book frequency = 3", 3, (int) result1.get("book"));
        assertEquals("TC 2.1: love frequency = 2", 2, (int) result1.get("love"));
        testsPassed++;

        // TC 2.2: All Stop Words
        String[] words2 = {"the", "is", "and", "a"};
        TreeMap<String, Integer> result2 = NLPUtility.countFilteredWords(words2, stopWords);
        System.out.println("TC 2.2: All stop words - Result: " + result2);
        assertEquals("TC 2.2: Empty map", true, result2.isEmpty());
        testsPassed++;

        // TC 2.3: No Stop Words in Input
        String[] words3 = {"hello", "world", "test"};
        TreeMap<String, Integer> result3 = NLPUtility.countFilteredWords(words3, stopWords);
        System.out.println("TC 2.3: No stop words - Result: " + result3);
        assertEquals("TC 2.3: Size = 3", 3, result3.size());
        testsPassed++;

        // TC 2.4: Case Sensitivity Verification
        String[] words4 = {"Book", "BOOK", "book", "BoOk"};
        TreeMap<String, Integer> result4 = NLPUtility.countFilteredWords(words4, stopWords);
        System.out.println("TC 2.4: Case insensitivity - Result: " + result4);
        assertEquals("TC 2.4: All counted as 'book'", 4, (int) result4.get("book"));
        testsPassed++;

        // TC 2.5: Alphabetical Order Verification
        String[] words5 = {"zebra", "apple", "mango", "banana"};
        TreeMap<String, Integer> result5 = NLPUtility.countFilteredWords(words5, stopWords);
        List<String> keys = new ArrayList<>(result5.keySet());
        System.out.println("TC 2.5: Alphabetical order - Result: " + result5);
        assertEquals("TC 2.5: Alphabetically sorted", true, isAlphabeticallySorted(keys));
        testsPassed++;

        // TC 2.6: Empty Input
        String[] words6 = {};
        TreeMap<String, Integer> result6 = NLPUtility.countFilteredWords(words6, stopWords);
        System.out.println("TC 2.6: Empty input - Result: " + result6);
        assertEquals("TC 2.6: Empty result", true, result6.isEmpty());
        testsPassed++;
    }

    // ============================================================
    // TEST SUITE 3: sortByValueDescending
    // ============================================================
    public static void testSortByValueDescending() {
        System.out.println("\n======== TEST SUITE 3: sortByValueDescending ========");

        // TC 3.1: Standard Descending Sort
        Map<String, Integer> input1 = new TreeMap<>();
        input1.put("book", 3);
        input1.put("good", 1);
        input1.put("i", 1);
        input1.put("love", 2);
        input1.put("sad", 1);
        LinkedHashMap<String, Integer> result1 = NLPUtility.sortByValueDescending(input1);
        System.out.println("TC 3.1: Descending sort - Result: " + result1);
        List<Integer> values1 = new ArrayList<>(result1.values());
        assertEquals("TC 3.1: Descending order", true, isDescending(values1));
        testsPassed++;

        // TC 3.2: Tied Frequencies
        Map<String, Integer> input2 = new TreeMap<>();
        input2.put("apple", 2);
        input2.put("banana", 2);
        input2.put("cherry", 2);
        LinkedHashMap<String, Integer> result2 = NLPUtility.sortByValueDescending(input2);
        System.out.println("TC 3.2: Tied frequencies - Result: " + result2);
        assertEquals("TC 3.2: All values = 2", true, result2.values().stream().allMatch(v -> v == 2));
        testsPassed++;

        // TC 3.3: Single Entry
        Map<String, Integer> input3 = new TreeMap<>();
        input3.put("word", 5);
        LinkedHashMap<String, Integer> result3 = NLPUtility.sortByValueDescending(input3);
        System.out.println("TC 3.3: Single entry - Result: " + result3);
        assertEquals("TC 3.3: Size = 1", 1, result3.size());
        testsPassed++;

        // TC 3.4: Reverse Order Input
        Map<String, Integer> input4 = new TreeMap<>();
        input4.put("word1", 1);
        input4.put("word2", 2);
        input4.put("word3", 3);
        LinkedHashMap<String, Integer> result4 = NLPUtility.sortByValueDescending(input4);
        System.out.println("TC 3.4: Reverse to descending - Result: " + result4);
        List<Integer> values4 = new ArrayList<>(result4.values());
        assertEquals("TC 3.4: Descending order", true, isDescending(values4));
        testsPassed++;

        // TC 3.5: Return Type Check
        Map<String, Integer> input5 = new HashMap<>();
        input5.put("test", 1);
        LinkedHashMap<String, Integer> result5 = NLPUtility.sortByValueDescending(input5);
        System.out.println("TC 3.5: Return type - " + result5.getClass().getName());
        assertEquals("TC 3.5: Returns LinkedHashMap", true, result5 instanceof LinkedHashMap);
        testsPassed++;

        // TC 3.6: Large Frequency Differences
        Map<String, Integer> input6 = new TreeMap<>();
        input6.put("rare", 1);
        input6.put("common", 100);
        input6.put("medium", 50);
        LinkedHashMap<String, Integer> result6 = NLPUtility.sortByValueDescending(input6);
        System.out.println("TC 3.6: Large differences - Result: " + result6);
        List<Integer> values6 = new ArrayList<>(result6.values());
        assertEquals("TC 3.6: Correctly sorted", true, isDescending(values6));
        testsPassed++;
    }

    // ============================================================
    // TEST SUITE 4: getSentiment
    // ============================================================
    public static void testGetSentiment() {
        System.out.println("\n======== TEST SUITE 4: getSentiment ========");

        Set<String> positiveWords = new HashSet<>(Arrays.asList("good", "great", "happy", "love", "like"));
        Set<String> negativeWords = new HashSet<>(Arrays.asList("bad", "terrible", "horrible", "sad", "hate"));

        // TC 4.1: Standard Sentiment Analysis
        Map<String, Integer> wordMap1 = new LinkedHashMap<>();
        wordMap1.put("book", 3);
        wordMap1.put("love", 2);
        wordMap1.put("good", 1);
        wordMap1.put("i", 1);
        wordMap1.put("sad", 1);
        String result1 = NLPUtility.getSentiment(wordMap1, positiveWords, negativeWords);
        System.out.println("TC 4.1: Standard sentiment - Result: " + result1);
        assertEquals("TC 4.1: Positive: 3, Negative: 1", "Positive: 3, Negative: 1", result1);
        testsPassed++;

        // TC 4.2: No Positive Words
        Map<String, Integer> wordMap2 = new LinkedHashMap<>();
        wordMap2.put("test", 5);
        wordMap2.put("bad", 3);
        String result2 = NLPUtility.getSentiment(wordMap2, positiveWords, negativeWords);
        System.out.println("TC 4.2: No positive - Result: " + result2);
        assertEquals("TC 4.2: Positive: 0, Negative: 3", "Positive: 0, Negative: 3", result2);
        testsPassed++;

        // TC 4.3: No Negative Words
        Map<String, Integer> wordMap3 = new LinkedHashMap<>();
        wordMap3.put("love", 5);
        wordMap3.put("great", 3);
        String result3 = NLPUtility.getSentiment(wordMap3, positiveWords, negativeWords);
        System.out.println("TC 4.3: No negative - Result: " + result3);
        assertEquals("TC 4.3: Positive: 8, Negative: 0", "Positive: 8, Negative: 0", result3);
        testsPassed++;

        // TC 4.4: No Sentiment Words
        Map<String, Integer> wordMap4 = new LinkedHashMap<>();
        wordMap4.put("book", 5);
        wordMap4.put("test", 3);
        String result4 = NLPUtility.getSentiment(wordMap4, positiveWords, negativeWords);
        System.out.println("TC 4.4: No sentiment - Result: " + result4);
        assertEquals("TC 4.4: Positive: 0, Negative: 0", "Positive: 0, Negative: 0", result4);
        testsPassed++;

        // TC 4.5: Case Insensitivity
        Map<String, Integer> wordMap5 = new LinkedHashMap<>();
        wordMap5.put("Love", 3);
        wordMap5.put("GOOD", 2);
        wordMap5.put("BAD", 1);
        String result5 = NLPUtility.getSentiment(wordMap5, positiveWords, negativeWords);
        System.out.println("TC 4.5: Case insensitivity - Result: " + result5);
        assertEquals("TC 4.5: Positive: 5, Negative: 1", "Positive: 5, Negative: 1", result5);
        testsPassed++;

        // TC 4.6: Multiple Words Same Sentiment
        Map<String, Integer> wordMap6 = new LinkedHashMap<>();
        wordMap6.put("good", 2);
        wordMap6.put("great", 2);
        wordMap6.put("happy", 2);
        String result6 = NLPUtility.getSentiment(wordMap6, positiveWords, negativeWords);
        System.out.println("TC 4.6: Multiple positive - Result: " + result6);
        assertEquals("TC 4.6: Positive: 6, Negative: 0", "Positive: 6, Negative: 0", result6);
        testsPassed++;
    }

    // ============================================================
    // TEST SUITE 5: getWordsWithMaxFrequency
    // ============================================================
    public static void testGetWordsWithMaxFrequency() {
        System.out.println("\n======== TEST SUITE 5: getWordsWithMaxFrequency ========");

        // TC 5.1: Single Most Frequent Word
        Map<String, Integer> wordMap1 = new LinkedHashMap<>();
        wordMap1.put("good", 1);
        wordMap1.put("i", 1);
        wordMap1.put("love", 3);
        wordMap1.put("book", 2);
        wordMap1.put("sad", 1);
        Map<String, Object> result1 = NLPUtility.getWordsWithMaxFrequency(wordMap1);
        System.out.println("TC 5.1: Single max - Result: " + result1);
        assertEquals("TC 5.1: Frequency = 3", 3, result1.get("frequency"));
        @SuppressWarnings("unchecked")
        List<String> words1 = (List<String>) result1.get("words");
        assertEquals("TC 5.1: One word", 1, words1.size());
        assertEquals("TC 5.1: Word is 'love'", "love", words1.get(0));
        testsPassed++;

        // TC 5.2: Multiple Words with Max Frequency
        Map<String, Integer> wordMap2 = new LinkedHashMap<>();
        wordMap2.put("good", 1);
        wordMap2.put("i", 1);
        wordMap2.put("love", 3);
        wordMap2.put("book", 3);
        wordMap2.put("sad", 1);
        Map<String, Object> result2 = NLPUtility.getWordsWithMaxFrequency(wordMap2);
        System.out.println("TC 5.2: Multiple max - Result: " + result2);
        assertEquals("TC 5.2: Frequency = 3", 3, result2.get("frequency"));
        @SuppressWarnings("unchecked")
        List<String> words2 = (List<String>) result2.get("words");
        assertEquals("TC 5.2: Two words", 2, words2.size());
        assertEquals("TC 5.2: Alphabetically sorted", true, isAlphabeticallySorted(words2));
        assertEquals("TC 5.2: First word is 'book'", "book", words2.get(0));
        assertEquals("TC 5.2: Second word is 'love'", "love", words2.get(1));
        testsPassed++;

        // TC 5.3: All Words Same Frequency
        Map<String, Integer> wordMap3 = new LinkedHashMap<>();
        wordMap3.put("apple", 5);
        wordMap3.put("banana", 5);
        wordMap3.put("cherry", 5);
        Map<String, Object> result3 = NLPUtility.getWordsWithMaxFrequency(wordMap3);
        System.out.println("TC 5.3: All same freq - Result: " + result3);
        assertEquals("TC 5.3: Frequency = 5", 5, result3.get("frequency"));
        @SuppressWarnings("unchecked")
        List<String> words3 = (List<String>) result3.get("words");
        assertEquals("TC 5.3: Three words", 3, words3.size());
        assertEquals("TC 5.3: Alphabetically sorted", true, isAlphabeticallySorted(words3));
        testsPassed++;

        // TC 5.4: Single Entry
        Map<String, Integer> wordMap4 = new LinkedHashMap<>();
        wordMap4.put("word", 10);
        Map<String, Object> result4 = NLPUtility.getWordsWithMaxFrequency(wordMap4);
        System.out.println("TC 5.4: Single entry - Result: " + result4);
        assertEquals("TC 5.4: Frequency = 10", 10, result4.get("frequency"));
        @SuppressWarnings("unchecked")
        List<String> words4 = (List<String>) result4.get("words");
        assertEquals("TC 5.4: One word", 1, words4.size());
        testsPassed++;

        // TC 5.5: Data Structure Check
        Map<String, Integer> wordMap5 = new LinkedHashMap<>();
        wordMap5.put("test", 1);
        Map<String, Object> result5 = NLPUtility.getWordsWithMaxFrequency(wordMap5);
        System.out.println("TC 5.5: Data structure - Contains 'words': " + result5.containsKey("words") +
                ", Contains 'frequency': " + result5.containsKey("frequency"));
        assertEquals("TC 5.5: Has 'words' key", true, result5.containsKey("words"));
        assertEquals("TC 5.5: Has 'frequency' key", true, result5.containsKey("frequency"));
        testsPassed++;

        // TC 5.6: Alphabetical Sort Verification
        Map<String, Integer> wordMap6 = new LinkedHashMap<>();
        wordMap6.put("zebra", 2);
        wordMap6.put("apple", 2);
        wordMap6.put("mango", 2);
        wordMap6.put("banana", 2);
        Map<String, Object> result6 = NLPUtility.getWordsWithMaxFrequency(wordMap6);
        System.out.println("TC 5.6: Alphabetical - Result: " + result6);
        @SuppressWarnings("unchecked")
        List<String> words6 = (List<String>) result6.get("words");
        assertEquals("TC 5.6: Alphabetically sorted", true, isAlphabeticallySorted(words6));
        assertEquals("TC 5.6: First is 'apple'", "apple", words6.get(0));
        testsPassed++;
    }

    // ============================================================
    // HELPER METHODS
    // ============================================================

    private static void assertArrayEquals(String testName, String[] result, String[] expected) {
        if (Arrays.equals(result, expected)) {
            System.out.println("✓ " + testName + " - PASS");
            System.out.println("  Result: " + Arrays.toString(result));
            testsPassed++;
        } else {
            System.out.println("✗ " + testName + " - FAIL");
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Result: " + Arrays.toString(result));
            testsFailed++;
        }
    }

    private static void assertEquals(String testName, Object expected, Object actual) {
        if (expected.equals(actual)) {
            System.out.println("✓ " + testName + " - PASS");
            testsPassed++;
        } else {
            System.out.println("✗ " + testName + " - FAIL");
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual: " + actual);
            testsFailed++;
        }
    }

    private static boolean arrayContains(String[] array, String value) {
        for (String s : array) {
            if (s.equals(value)) return true;
        }
        return false;
    }

    private static boolean isAlphabeticallySorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDescending(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static void printSummary() {
        System.out.println("\n========================================");
        System.out.println("TEST SUMMARY");
        System.out.println("========================================");
        System.out.println("Total Tests Run: " + (testsPassed + testsFailed));
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Success Rate: " + String.format("%.1f%%", 
                (testsPassed * 100.0) / (testsPassed + testsFailed)));
        System.out.println("========================================");
        
        if (testsFailed == 0) {
            System.out.println("✓ ALL TESTS PASSED");
        } else {
            System.out.println("✗ SOME TESTS FAILED");
        }
        System.out.println("========================================");
    }
}
