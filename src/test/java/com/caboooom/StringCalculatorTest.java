package com.caboooom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void setup() {
        stringCalculator = new StringCalculator();
    }

    @Test
    @DisplayName("파라미터가 빈 문자열이나 null이면 0을 리턴")
    void returnZero() {
        assertEquals(0, stringCalculator.calc(""));
        assertEquals(0, stringCalculator.calc(null));
    }

    @Test
    @DisplayName("파라미터로 숫자 하나만 주어질 경우 해당 숫자를 리턴")
    void singleNumber() {
        int number = 12345;
        assertEquals(number, stringCalculator.calc(String.valueOf(number)));
    }

    @Test
    @DisplayName("기본 구분자 , : 로 구분")
    void defaultDelimiter() {
        String testStr = "1,2,3:4";
        assertEquals(10, stringCalculator.calc(testStr));
    }

    @Test
    @DisplayName("커스텀 구분자 지정")
    void setCustomDelimiter() {
        stringCalculator.addDelimiter("?");

        String testStr = "1//?\n2";
        assertEquals(3, stringCalculator.calc(testStr));

        stringCalculator.addDelimiter("~");
        String testStr2 = "1//~\n2";
        assertEquals(3, stringCalculator.calc(testStr2));
    }

    @Test
    @DisplayName("파라미터 음수면 예외처리")
    void negaitveNumber() {
        String testStr = "-1,2";
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calc(testStr));
    }

}
