package algos;

public class Misc {
    public static double pi(int digits) {
        double pi = .0d, base = 4.0d, divisor = 1.0d;
        boolean sign = false;
        for (int j = 0; j < digits; j++) {
            pi = pi + (sign ? (-1.0d) : 1.0d) * (base / divisor);
            sign = !sign;
            divisor += 2.0d;
        }
        return pi;
    }
}
