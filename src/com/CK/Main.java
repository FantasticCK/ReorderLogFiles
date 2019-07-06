package com.CK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] logs = new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo", "a2 act car"};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.reorderLogFiles(logs)));
    }
}

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<Log> logsList = new ArrayList<>();
        for (String log : logs) {
            logsList.add(new Log(log));
        }
        Collections.sort(logsList);
        String[] returnArr = new String[logs.length];
        for (int i = 0; i < returnArr.length; i++) {
            returnArr[i] = logsList.get(i).getContent();
        }
        return returnArr;
    }
}

class Log implements Comparable<Log> {
    private String content;
    private String type;
    private String[] contentArray;
    private String letterContent;

    Log(String content) {
        this.content = content;
        this.contentArray = content.split(" ");
        if (Character.isDigit(contentArray[1].charAt(0))) this.type = "digit";
        else {
            this.type = "letter";
            this.letterContent = content.substring(contentArray[0].length(), content.length());
        }
    }

    @Override
    public int compareTo(Log anotherLog) {
        if (this.type.equals("digit") && anotherLog.type.equals("digit")) {
            return 0;
        } else if (this.type.equals("digit") && anotherLog.type.equals("letter")) {
            return 1;
        } else if (this.type.equals("letter") && anotherLog.type.equals("digit")) {
            return -1;
        } else {
            int letterComparison = this.letterContent.compareTo(anotherLog.letterContent);
            if (letterComparison == 0) return this.contentArray[0].compareTo(anotherLog.contentArray[0]);
            else return letterComparison;
        }
    }

    String getContent() {
        return content;
    }
}