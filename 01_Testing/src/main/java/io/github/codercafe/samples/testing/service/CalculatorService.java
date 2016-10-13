package io.github.codercafe.samples.testing.service;

import io.github.codercafe.samples.testing.NumberCalculator;
import io.github.codercafe.samples.testing.WordCalculator;

import java.text.ParseException;

public class CalculatorService {

    private NumberCalculator numberCalculator;
    private WordCalculator wordCalculator;

    public CalculatorService(WordCalculator wordCalculator) {
        this.numberCalculator = new NumberCalculator();
        this.wordCalculator = wordCalculator;
    }

    public String mixedAdd(int a, String b) {
        try {
            return numberCalculator.add(a, wordCalculator.parseInt(b));
        } catch (ParseException ex) {
            throw new CalculatorServiceException("Something went wrong", ex);
        }
    }

    public int mixedSubstract(int a, String b) {
        try {
            return wordCalculator.subtract(numberCalculator.format(a), b);
        } catch (ParseException ex) {
            throw new CalculatorServiceException("Something went wrong", ex);
        }
    }
}
