package com.caboooom;

public class StringCalculator {

    public int add(String str) {

        if(str == null || str.isBlank()) {
            return 0;
        }

        String[] numbers = splitByDelimiter(str);

        int sum = 0;
        for(String n : numbers) {
            sum += stringToPositiveInt(n);
        }
        return sum;
    }

    private String[] splitByDelimiter(String str) {

        str = str.replaceAll("//.*\\n", "c");
        str = str.replaceAll("\\s", "");

        return str.split("[,:c]");
    }

    private int stringToPositiveInt(String strNum) {

        int intNum;

        try {
            intNum = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 형식");
        }

        if(intNum < 0) {
            throw new IllegalArgumentException("음수를 계산할 수 없습니다");
        }

        return intNum;
    }

}
