package com.caboooom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void setup() {
        stringCalculator = new StringCalculator();
    }

    @Test
    @DisplayName("기본 구분자 , : 로 구분")
    public void defaultDelimeter() {
        String testStr = "1,2,3:4";
        assertEquals(10, stringCalculator.calc(testStr));
    }

    @Test
    @DisplayName("커스텀 구분자 지정")
    void setCustomDelimeter() {
        stringCalculator.addDelimeter("?");

        String testStr = "1//?\n2";
        assertEquals(3, stringCalculator.calc(testStr));

        stringCalculator.addDelimeter("~");
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
