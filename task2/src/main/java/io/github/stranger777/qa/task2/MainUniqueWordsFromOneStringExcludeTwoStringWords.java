package io.github.stranger777.qa.task2;

import java.io.IOException;

public class MainUniqueWordsFromOneStringExcludeTwoStringWords {
    public static void main(String[] args) throws IOException {
        String first = ResourceExtractor.getJsonStringFromFileByStringName("strings.json", "first");
        String second = ResourceExtractor.getJsonStringFromFileByStringName("strings.json", "second");
        System.out.println("Unique words in first string, exclude words in second string: " + new TwoStringsOperations(first, second).getUniqueWordsFromFirst());
    }
}
