package algos.constraint;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConstraintTests {

    @Test
    public void mapColoringTest() {
        List<String> variables = List.of(
                "Western Australia",
                "Northern Territory",
                "South Australia",
                "Queensland",
                "New South Wales",
                "Victoria",
                "Tasmania");
        Map<String, List<String>> domains = new HashMap<>();
        for (String variable : variables)
            domains.put(variable, List.of("red", "green", "blue"));
        CSP<String, String> csp = new CSP<>(variables, domains);
        csp.addConstraint(new MapColoringConstraint("Western Australia", "Northern Territory"));
        csp.addConstraint(new MapColoringConstraint("Western Australia", "South Australia"));
        csp.addConstraint(new MapColoringConstraint("South Australia", "Northern Territory"));
        csp.addConstraint(new MapColoringConstraint("Queensland", "Northern Territory"));
        csp.addConstraint(new MapColoringConstraint("South Australia", "Queensland"));
        csp.addConstraint(new MapColoringConstraint("Queensland", "New South Wales"));
        csp.addConstraint(new MapColoringConstraint("New South Wales", "South Australia"));
        csp.addConstraint(new MapColoringConstraint("Victoria", "South Australia"));
        csp.addConstraint(new MapColoringConstraint("Victoria", "New South Wales"));
        csp.addConstraint(new MapColoringConstraint("Tasmania", "Victoria"));
        Map<String, String> solution = csp.backtrackingSearch(new HashMap<>());
        if (solution == null)
            System.out.println("No solution found!");
        else
            System.out.println(solution);
    }

    @Test
    public void queensTest() {
        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        Map<Integer, List<Integer>> rows = new HashMap<>();
        for (int column : columns) rows.put(column, List.of(1, 2, 3, 4, 5, 6, 7, 8));
        CSP<Integer, Integer> csp = new CSP<>(columns, rows);
        csp.addConstraint(new QueensConstraint(columns));
        Map<Integer, Integer> solution = csp.backtrackingSearch(new HashMap<>());
        if (solution == null)
            System.out.println("No solution found!");
        else
            System.out.println(solution);
        HashMap<Integer, Integer> preSettingMap = new HashMap<>();
        preSettingMap.put(2, 2);
        solution = csp.backtrackingSearch(preSettingMap);
        if (solution == null)
            System.out.println("No solution found!");
        else
            System.out.println(solution);
    }

    @Test
    public void wordSearchConstraintTest() {
        WordGrid grid = new WordGrid(9, 9);
        List<String> words = List.of("MATTHEW", "JOE", "MARY", "SARAH", "SALLY");
        // generate domains for all words
        Map<String, List<List<WordGrid.GridLocation>>> domains = new HashMap<>();
        for (String word : words)
            domains.put(word, grid.generateDomain(word));
        CSP<String, List<WordGrid.GridLocation>> csp = new CSP<>(words, domains);
        csp.addConstraint(new WordSearchConstraint(words));
        Map<String, List<WordGrid.GridLocation>> solution = csp.backtrackingSearch(new HashMap<>());
        if (solution == null)
            System.out.println("No solution found!");
        else {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (Map.Entry<String, List<WordGrid.GridLocation>> item : solution.entrySet()) {
                String word = item.getKey();
                List<WordGrid.GridLocation> locations = item.getValue();
                // random reverse half the time
                if (random.nextBoolean())
                    Collections.reverse(locations);
                grid.mark(word, locations);
            }
            System.out.println(grid);
        }
    }

    @Test
    public void sendMoreMoneyTest() {
        List<Character> letters = List.of('S', 'E', 'N', 'D', 'M', 'O', 'R', 'Y');
        Map<Character, List<Integer>> possibleDigits = new HashMap<>();
        for (Character letter : letters)
            possibleDigits.put(letter, List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        // so we don't get answers starting with a 0
        possibleDigits.replace('M', List.of(1));
        CSP<Character, Integer> csp = new CSP<>(letters, possibleDigits);
        csp.addConstraint(new SendMoreMoneyConstraint(letters));
        Map<Character, Integer> solution = csp.backtrackingSearch(new HashMap<>());
        if (solution == null)
            System.out.println("No solution found!");
        else
            System.out.println(solution);
    }

    @Test
    public void makeUpLinearEquationTest() {
        ThreadLocalRandom rndm = ThreadLocalRandom.current();
        List<LinearEquationCoefficient> coefficients = new LinkedList<>();
        for (int j = 0; j < 6; j++) coefficients.add(new LinearEquationCoefficient("X" + String.valueOf(j), rndm.nextLong(1L, 7L)));
        Map<LinearEquationCoefficient, List<Long>> domainsOfCoefficients = new HashMap<>();
        for (LinearEquationCoefficient cf : coefficients) {
            domainsOfCoefficients.put(cf, new LinkedList<Long>());
            for (long d = -17L; d <= 5L; d += 1L) domainsOfCoefficients.get(cf).add(d);
        }
        CSP<LinearEquationCoefficient, Long> csp = new CSP<>(coefficients, domainsOfCoefficients);
        csp.addConstraint(new LinearEquationSystemConstraint(coefficients));
        Map<LinearEquationCoefficient, Long> solution = csp.backtrackingSearch(new HashMap<>());
        if (solution == null)
            System.out.println("No solution found!");
        else {
            StringBuilder sb = new StringBuilder();
            Map.Entry<LinearEquationCoefficient, Long>[] entries = solution.entrySet().toArray(Map.Entry[]::new);
            for (int e = 0; e < entries.length; e++) {
                sb.append(entries[e].getKey().toString());
                if (e <= entries.length - 2) sb.append(" + ");
                if (e == entries.length - 1)  sb.append(" = 0;\n");
            }
            for (int e = 0; e < entries.length; e++)
                sb.append(entries[e].getKey().l).append(" = ").append(solution.get(entries[e].getKey())).append(";\n");
            System.out.println(sb.toString());
        }
    }
}