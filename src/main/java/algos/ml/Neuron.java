// Modified by Viacheslav Mikhailov
// Neuron.java
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

import java.util.function.DoubleUnaryOperator;

public class Neuron {
    public double[] weights;
    public final double learningRate;
    public double outputCache; // dot (scalar) product of this neuron's weight vector and input signals vector before applying activation function.
    // These input signals are themselves activated signals from previous layer neurons (if the previous is not the very first layer only).
    public double delta; // delta gets calculated during training backwards propagation
    public final DoubleUnaryOperator activationFunction;
    public final DoubleUnaryOperator derivativeActivationFunction;

    public Neuron(double[] weights, double learningRate, DoubleUnaryOperator activationFunction, DoubleUnaryOperator derivativeActivationFunction) {
        this.weights = weights;
        this.learningRate = learningRate;
        outputCache = 0.0;
        delta = 0.0;
        this.activationFunction = activationFunction;
        this.derivativeActivationFunction = derivativeActivationFunction;
    }

    /**
     * This method calculates dot product of two vectors of equal coordinates number:
     * of weights vector and of input signals vector. Because there are as many input signal as there are
     * neurons in the previous layer, so are the amount of weights, thus we may calculate the scalar product.
     * After that, the scalar product is getting cached in this neuron.
     * The cached dot product result is getting activated with an activation function (i.e. sigmoid)
     * and is returned from the method call.
     *
     * @param inputs if this neuron is of the second (the first inner) layer,
     *               then these are pure unprocessed signals from the outside.
     *               Otherwise, these are already activated dot products from neurons of the previous layer.
     *               This function must never be called on the first (input) layer.
     * @return an activated dot product result for this particular neuron
     */
    public double activate(double[] inputs) {
        outputCache = Util.dotProduct(inputs, weights);
        return activationFunction.applyAsDouble(outputCache);
    }
}