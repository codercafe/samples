package io.github.codercafe.samples.testing.service;

import io.github.codercafe.samples.testing.NumberCalculator;
import io.github.codercafe.samples.testing.WordCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    /* Alternatives to @RunWith(MockitoJUnitRunner.class) to enable Mockito annotations
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    */

    @Mock
    private NumberCalculator numberCalculator;

    @Mock
    private WordCalculator wordCalculator;

    @Spy
    private WordCalculator wordCalculatorSpy = new WordCalculator();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void testMixedAdd() throws Exception {
        CalculatorService calculatorService = new CalculatorService(wordCalculator);
        when(wordCalculator.parseInt(anyString())).thenReturn(42);

        String result = calculatorService.mixedAdd(42, "123");
        assertThat(result, is(equalTo("eighty-four")));

        verify(wordCalculator, never()).add(anyString(), anyString());
    }

    @Test
    public void testMixedAddWithSpy() throws Exception {
        CalculatorService calculatorService = new CalculatorService(wordCalculatorSpy);
        String result = calculatorService.mixedAdd(42, "42");
        assertThat(result, is(equalTo("eighty-four")));

        verify(wordCalculatorSpy, times(1)).parseInt(anyString());
        verify(wordCalculatorSpy, never()).add(anyString(), anyString());
    }

    @Test
    public void testMixedAddWithWhiteboxAndCaptor() throws Exception {
        CalculatorService calculatorService = new CalculatorService(wordCalculator);
        Whitebox.setInternalState(calculatorService, "numberCalculator", numberCalculator);

        when(wordCalculator.parseInt(anyString())).thenReturn(42);
        when(numberCalculator.add(captor.capture(), captor.capture())).thenReturn("123");

        calculatorService.mixedAdd(123, "123");
        assertThat(captor.getAllValues(), containsInAnyOrder(123, 42));

        verify(wordCalculator, never()).add(anyString(), anyString());
    }
}
