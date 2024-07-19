package com.caboooom;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class StringCalculator {

    private Set<String> delimiters;

    public StringCalculator() {
        this.delimiters = new HashSet<>();
        delimiters.add(",");
        delimiters.add(":");
    }

    public void addDelimiter(String delimiter) {
        if (delimiter.equals("-")) {
            throw new IllegalArgumentException("구분자로 사용할 수 없는 문자입니다.");
        }
        delimiters.add(delimiter);
    }

    public int calc(String str) {

        if(str == null || str.isBlank()) {
            return 0;
        }

        str = str.replaceAll("//", "");
        str = str.replaceAll("\\n", "");
        for(String delimiter : delimiters) {
            try {
                str = str.replaceAll(delimiter, " ");
            } catch (PatternSyntaxException ignore) {
                str = str.replaceAll(String.format("\\%s", delimiter), " ");
            }
        }

        String[] numbers = str.split(" ");
        int sum = 0;
        for(String n : numbers) {
            if (n.contains("-")) {
                throw new IllegalArgumentException("음수를 넣을 수 없습니다");
            }
            try {
                sum += Integer.parseInt(n);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("잘못된 형식");
            }
        }
        return sum;
    }

}
