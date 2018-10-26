package io.github.stranger777.qa.task2;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class OneStringOperations {
    private String str;

    public OneStringOperations(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public String[] getAllWordsArray() {
        String[] split = getStr().replaceAll("\\p{Punct}+", "").toLowerCase().split("\\s+");
        return split;
    }

    public String getAnyWord() {
        return getAllWordsArray()[new Random().nextInt(getAllWordsArray().length)];
    }

    public int countPunctuationChars() {
        return getStr().length() - getStr().replaceAll("\\p{Punct}+", "").length();
    }

    public int countWords(String word) {
        return StringUtils.countMatches(getStr(), word);
    }
}
