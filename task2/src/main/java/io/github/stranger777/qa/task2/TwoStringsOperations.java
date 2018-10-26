package io.github.stranger777.qa.task2;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoStringsOperations {
    private String firstString;
    private String secondString;

    public TwoStringsOperations(String firstString, String secondString) {
        this.firstString = firstString;
        this.secondString = secondString;
    }

    public String getFirstString() {
        return firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public String getUniqueWordsFromFirst() {
        String[] firstArray = new OneStringOperations(getFirstString()).getAllWordsArray();
        String[] secondArray = new OneStringOperations(getSecondString()).getAllWordsArray();
        //
        List<String> firstList = new ArrayList<String>(Arrays.asList(firstArray));
        firstList.removeAll(Arrays.asList(secondArray));
        //
        String[] strings = firstList.toArray(new String[firstList.size()]);
        return StringUtils.join(strings, ", ");

    }

}
