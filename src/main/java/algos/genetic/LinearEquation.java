/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.genetic;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LinearEquation extends Chromosome<LinearEquation> {
    int[] coefficients;
    int[] answers;
    ThreadLocalRandom random = ThreadLocalRandom.current();

    public LinearEquation(int members) {
        this.coefficients = new int[members];
        this.answers = new int[members];
        for (int c = 0; c < coefficients.length; c++) {
            coefficients[c] = random.nextInt(-10, 11);
            answers[c] = random.nextInt(-10, 11);
        }
    }

    public LinearEquation(int[] copyCoefficients, int[] copyAnswers) {
        this.coefficients = copyCoefficients;
        this.answers = copyAnswers;
    }

    @Override
    public double fitness() {
        int sum = 0;
        for (int c = 0; c < coefficients.length; c++) sum = sum + coefficients[c] * answers[c];
        return 1.0d / (1.0d + Math.abs(sum));
    }

    @Override
    public void mutate() {
        answers[random.nextInt(coefficients.length)] = random.nextInt(-10, 11);
    }

    @Override
    public List<LinearEquation> crossover(LinearEquation other) {
        int idx1 = random.nextInt(answers.length);
        int idx2 = random.nextInt(other.answers.length);
        int coeff1 = answers[idx1];
        int coeff2 = other.answers[idx2];
        answers[idx1] = coeff2;
        other.answers[idx2] = coeff1;
        return List.of(this, other);
    }

    @Override
    public LinearEquation copy() {
        int[] copyCoefficients = new int[coefficients.length];
        int[] copyAnswers = new int[answers.length];
        System.arraycopy(coefficients, 0, copyCoefficients, 0, coefficients.length);
        System.arraycopy(answers, 0, copyAnswers, 0, answers.length);
        return new LinearEquation(copyCoefficients, copyAnswers);
    }

    @Override
    public String toString() {
        char letter = 65;
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < coefficients.length; c++) {
            sb.append(coefficients[c] + Character.valueOf(letter++).toString());
            if (c < coefficients.length - 1) sb.append(" + ");
            else sb.append(" = 0\n");
        }
        letter = 65;
        for (int c = 0; c < coefficients.length; c++) {
            sb.append(Character.valueOf(letter++).toString() + " = " + answers[c] + "\n");
        }
        return sb.toString();
    }
}