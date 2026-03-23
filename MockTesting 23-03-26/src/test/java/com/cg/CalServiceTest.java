package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalServiceTest {
    private CalculatorService service;

    @BeforeEach
    public void init() {
        ICalculator calculator = mock(ICalculator.class);
        service = new CalculatorService(calculator);

        when(calculator.calculate(20, 5)).thenReturn(25);
        when(calculator.calculate(10, 5)).thenReturn(15);
    }

    @Test
    void addServiceTest() {
        assertEquals(15, service.addService(10, 5));
    }
}
