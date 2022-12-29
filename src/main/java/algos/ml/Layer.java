// Modified by Viacheslav Mikhailov
// Layer.java
// From Classic Computer Science Problems in Java Chapter 7
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package algos.ml;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleUnaryOperator;

public class Layer {
    String layerId;
    public Optional<Layer> previousLayer;
    public List<Neuron> neurons = new ArrayList<>();
    public double[] outputCache; // outputs of neurons of this layer after activation - this field is updated only when we move in forth direction through the neural network

    public Layer(Optional<Layer> previousLayer, int numNeurons, double learningRate, DoubleUnaryOperator activationFunction, DoubleUnaryOperator derivativeActivationFunction) {
        this.previousLayer = previousLayer;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < numNeurons; i++) {
            double[] randomWeights = null;
            if (previousLayer.isPresent()) {
                randomWeights = random.doubles(previousLayer.get().neurons.size()).toArray(); // initially, neuron weights are randomly assigned. Starting with zero weights harms training, yields worse results.
            }
            Neuron neuron = new Neuron(randomWeights, learningRate, activationFunction, derivativeActivationFunction);
            neurons.add(neuron);
        }
        outputCache = new double[numNeurons];
        layerId = UUID.randomUUID().toString();
    }

    /**
     * This method does the actual neural processing of the input signals by applying an activation function
     * to each input signal of this layer's neurons and then passing it further (to next layer).
     * If this layer is the first (input) one, then no real processing (activation) takes place, the raw input signals
     * are simply gotten stored in the input layer.
     *
     * @param inputs an array of input signals to this layer. Their amount equals to amount of neurons of a previous layer (or number of raw signal dimensions if the layer is the first one).
     * @return an array of activated outcomes of neurons of this layer. Their amount equals to amount of neurons of this layer.
     */
    public double[] process(double[] inputs) {
        if (previousLayer.isPresent()) { // if there is a previous layer, then the inputs (signals) have come from neurons who know about how to activate these signals before passing them to next layer
            outputCache = neurons.stream().mapToDouble(n -> n.activate(inputs)).toArray(); // activate the inputs
        } else {
            outputCache = inputs; // if there is no previous layer, inputs are pure signals from outside, they come in as they are
        }
        return outputCache;
    }

    /**
     * There are two types of ∆ in backwards propagation neural networks: those of an output layer, and others of hidden layers.
     * This method should only be called on output layer.
     * This method calculates deltas for neurons of this layer (the outputCache of this layer isn't getting updated)
     */
    public void calculateDeltasForOutputLayer(double[] expected) {
        for (int n = 0; n < neurons.size(); n++) {
            neurons.get(n).delta = neurons.get(n).derivativeActivationFunction.applyAsDouble(neurons.get(n).outputCache) * (expected[n] - outputCache[n]);
        }
    }

    /**
     * There are two types of ∆ in backwards propagation neural networks: those of an output layer, and others of hidden layers.
     * This method should not be called on output layer.
     * This method calculates deltas for neurons of this layer (the outputCache of this layer isn't getting updated)
     */
    public void calculateDeltasForHiddenLayer(Layer nextLayer) {
        double[] nextDeltas = nextLayer.neurons.stream().mapToDouble(n -> n.delta).toArray();
        for (int i = 0; i < neurons.size(); i++) {
            int index = i;
            double[] nextWeights = nextLayer.neurons.stream().mapToDouble(n -> n.weights[index]).toArray();
            double sumWeightsAndDeltas = Util.dotProduct(nextWeights, nextDeltas);
            neurons.get(i).delta = neurons.get(i).derivativeActivationFunction.applyAsDouble(neurons.get(i).outputCache) * sumWeightsAndDeltas;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Layer.class.getSimpleName() + "[" + layerId, "]").toString();
    }
}