package io.github.stranger777.qa.task2;

import java.io.IOException;

public class MainPunctuationAndWordsCounter {
    public static void main(String[] args) throws IOException {

        String first = ResourceExtractor.getJsonStringFromFileByStringName("strings.json", "first");
        String second = ResourceExtractor.getJsonStringFromFileByStringName("strings.json", "second");
        String third = ResourceExtractor.getJsonStringFromFileByStringName("strings.json", "third");
        String anyWord = new OneStringOperations(third).getAnyWord();
        int matchesInSecondString = new OneStringOperations(second).countWords(anyWord);

        System.out.println("Punctuation chars in first string: " + new OneStringOperations(first).countPunctuationChars());
        System.out.println("Word " + anyWord + " matches in second string " + matchesInSecondString + " times");

    }
}
