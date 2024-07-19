package com.caboooom;

public class StringCalculator {

    public int calculate(String str) {

        if(str == null || str.isBlank()) {
            return 0;
        }

        str = str.replaceAll("//.*\\n", "c");
        String[] numbers = str.split("[,:c]");

        int sum = 0;
        for(String n : numbers) {
            sum += stringToInteger(n);
        }
        return sum;
    }

    private int stringToInteger(String strNum) {
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
