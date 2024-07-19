package com.caboooom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void setup() {
        stringCalculator = new StringCalculator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("파라미터가 빈 문자열이나 null이면 0을 리턴")
    void returnZero(String input) {
        assertEquals(0, stringCalculator.calculate(input));
    }

    private static Stream<Arguments> singleNumber() {
        return Stream.of(
                Arguments.of("12345", 12345),
                Arguments.of("9999", 9999)
        );
    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("파라미터로 숫자 하나만 주어질 경우 해당 숫자를 리턴")
    void singleNumber(String input, int expected) {
        assertEquals(expected, stringCalculator.calculate(input));
    }

    private static Stream<Arguments> defaultDelimiters() {
        return Stream.of(
                Arguments.of("1,2,3:4", 10),
                Arguments.of("  1  :2, 3:10", 16)
        );
    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("기본 구분자 , : 로 구분")
    void defaultDelimiters(String input, int expected) {
        assertEquals(expected, stringCalculator.calculate(input));
    }

    private static Stream<Arguments> customDelimiters() {
        return Stream.of(
                Arguments.of("1//?\n2", 3),
                Arguments.of("3//~\n4", 7),
                Arguments.of("1:2//a\n3,4//?\n5", 15),
                Arguments.of("12//-\n2", 14),
                Arguments.of("1//+\n", 1)
        );
    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("커스텀 구분자로 구분")
    void customDelimiters(String input, int expected) {
        assertEquals(expected, stringCalculator.calculate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1,2", "1:-58", "1,-2//a\n3"})
    @DisplayName("파라미터 음수면 예외처리")
    void negaitveNumber(String input) {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a:1", "1,,2"})
    @DisplayName("잘못된 형식이면 예외 처리")
    void wrongFormat(String input) {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate(input));
    }

}
