package com.caboooom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    /*
    - 전달하는 문자를 구분자로 분리한 후, 각 숫자의 합을 구해 반환한다.
- 구분자가 `,` `:` 인 경우, 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    - 예시) “” → 0, “1,2” → 3, “1:2,3” → 6
- 그 외 커스텀 구분자를 지정할 수 있다.
    - 커스텀 구분자는 `//` 와 `\n` 으로 감싸 지정한다.
    - 예를들어 커스텀 구분자를 `;` 으로 하고싶다면, “1//;\n2,3”이며 결과는 6이다.
- 문자열 계싼기에 음수를 전달하는 경우 RuntimeException으로 예외 처리해야 한다.
     */
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
