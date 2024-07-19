package com.caboooom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

public class StringCalculator {

    private final static String DELIMETER_PREFIX = "//";
    private final static String DELIMETER_POSTFIX = "\\n";

    private Set<String> delimeters;

    public StringCalculator() {
        this.delimeters = new HashSet<>();
        delimeters.add(",");
        delimeters.add(":");
    }

    public void addDelimeter(String delimeter) {
        if (delimeter.equals("-")) {
            throw new IllegalArgumentException();
        }

        delimeters.add(delimeter);

    }

    public int calc(String str) {

        if(str == null || str.isBlank()) {
            return 0;
        }

        str = str.replaceAll("//", "");
        str = str.replaceAll("\\n", "");
        for(String delimeter : delimeters) {
            try {
                str = str.replaceAll(delimeter, " ");
            } catch (PatternSyntaxException ignore) {
                str = str.replaceAll(String.format("\\%s", delimeter), " ");
            }
        }

        String[] numbers = str.split(" ");
        int sum = 0;
        try {
            for(String n : numbers) {
                 if(n.contains("-")) {
                     throw new IllegalArgumentException("음수를 넣을 수 없습니다");
                 }
                 sum += Integer.parseInt(n);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 형식");
        }

        return sum;
    }

}
