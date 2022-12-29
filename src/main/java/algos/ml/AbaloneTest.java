/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.ml;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AbaloneTest {
	private List<double[]> abaloneParameters = new ArrayList<>();
	private List<double[]> abaloneClassifications = new ArrayList<>();
	private List<Double> abaloneAges = new ArrayList<>(); // this is an array of correct ages for each row of the dataset
	private LinkedHashSet<Double> agesSet;
	private HashMap<Double, Integer> agesValuesOnIndices = new HashMap<>();
	private HashMap<Integer, Double> agesIndicesOnValues = new HashMap<>();

	public AbaloneTest() {
		List<String[]> abaloneDataset = Util.loadCSV("src/main/java/algos/ml/data/abalone.data"); // you may download it from here https://archive.ics.uci.edu/ml/machine-learning-databases/abalone/
		Collections.shuffle(abaloneDataset);
		for (String[] abalone : abaloneDataset) {
			double[] params = new double[abalone.length-1];
			double[] parameters = Arrays.stream(abalone)
					.skip(1)
					.limit(7)
					.mapToDouble(Double::parseDouble)
					.toArray();
			System.arraycopy(parameters, 0, params, 1, parameters.length);
			char sex = abalone[0].charAt(0);
			switch (sex) {
				case 'M' -> params[0] = .1d;
				case 'F' -> params[0] = .5d;
				default -> params[0] = .9d;
			}
			abaloneParameters.add(params);
			int rings = Integer.parseInt(abalone[8]);
			abaloneAges.add(rings * 1.5);
		}
		final AtomicInteger counter = new AtomicInteger(0);
		agesSet = new HashSet<>(abaloneAges).stream().sorted(Double::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
		for (Double age : agesSet) {
			agesValuesOnIndices.put(age, counter.get());
			agesIndicesOnValues.put(counter.getAndIncrement(), age);
		}
		for (int i = 0; i < abaloneDataset.size(); i++) {
			abaloneClassifications.add(i, new double[agesSet.size()]);
			abaloneClassifications.get(i)[agesValuesOnIndices.get(abaloneAges.get(i))] = 1.0d;
		}
		Util.normalizeByFeatureScaling(abaloneParameters);
	}

	/**
	 * This method interprets the outcome of the neural network.
	 *
	 * @param output an array of probabilities (0 to 1) of a particular classification result.
	 * @return the age (the answer)
	 */
	public Double abaloneInterpretOutput(double[] output) {
		double max = Util.max(output);
		double age = Double.MIN_VALUE;
		for (int d = 0; d < output.length; d++) {
			if (output[d] == max) {
				age = agesIndicesOnValues.get(d); // the highest probability in array of the neural outcome signifies the answer (age)
				break;
			}
		}
		return age;
	}

	public Network<Double>.Results classify() {
		Network<Double> abaloneNetwork = new Network<>(new int[] {8, 12, agesSet.size()}, 0.9, Util::sigmoid,
				Util::derivativeSigmoid);
		List<double[]> abaloneTrainers = abaloneParameters.subList(0, 3133);
		List<double[]> abaloneTrainersCorrects = abaloneClassifications.subList(0, 3133);
		int trainingIterations = 41;
		for (int i = 0; i < trainingIterations; i++)
			abaloneNetwork.train(abaloneTrainers, abaloneTrainersCorrects);
		List<double[]> abaloneTesters = abaloneParameters.subList(3133, 4177);
		List<Double> abaloneTestersCorrects = abaloneAges.subList(3133, 4177);
		return abaloneNetwork.validate(abaloneTesters, abaloneTestersCorrects, this::abaloneInterpretOutput);
	}

	public static void main(String[] args) {
		AbaloneTest abaloneTest = new AbaloneTest();
		Network<Double>.Results results = abaloneTest.classify();
		System.out.println(results.correct + " correct of " + results.trials + " = " +
				results.percentage * 100 + "%");
	}
}