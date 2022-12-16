// MODIFIED BY VIACHESLAV MIKHAILOV
// From Classic Computer Science Problems in Java
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
package algos.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SendMoreMoney extends Chromosome<SendMoreMoney> {
    private ArrayList<Character> letters;
    private ThreadLocalRandom random;

    public SendMoreMoney(ArrayList<Character> letters) {
        this.letters = letters;
        this.random = ThreadLocalRandom.current();
    }

    public static SendMoreMoney randomInstance() {
        ArrayList<Character> letters = new ArrayList<>(List.of('S', 'E', 'N', 'D', 'M', 'O', 'R', 'Y', ' ', ' '));
        Collections.shuffle(letters);
        return new SendMoreMoney(letters);
    }

    @Override
    public double fitness() {
        int s = letters.indexOf('S');
        int e = letters.indexOf('E');
        int n = letters.indexOf('N');
        int d = letters.indexOf('D');
        int m = letters.indexOf('M');
        int o = letters.indexOf('O');
        int r = letters.indexOf('R');
        int y = letters.indexOf('Y');
        int send = s * 1000 + e * 100 + n * 10 + d;
        int more = m * 1000 + o * 100 + r * 10 + e;
        int money = m * 10000 + o * 1000 + n * 100 + e * 10 + y;
        int difference = Math.abs(money - (send + more));
        return 1.0d / (difference + 1.0d);
    }

    @Override
    public void mutate() {
        int indx1 = random.nextInt(letters.size());
        int indx2 = random.nextInt(letters.size());
        Collections.swap(letters, indx1, indx2);
    }

    @Override
    public List<SendMoreMoney> crossover(SendMoreMoney other) {
        SendMoreMoney child1 = new SendMoreMoney(new ArrayList<>(letters));
        SendMoreMoney child2 = new SendMoreMoney(new ArrayList<>(other.letters));
        int indx1 = random.nextInt(letters.size());
        int indx2 = random.nextInt(other.letters.size());
        Character ch1 = letters.get(indx1);
        Character ch2 = other.letters.get(indx2);
        int indx3 = letters.indexOf(ch2);
        int indx4 = other.letters.indexOf(ch1);
        Collections.swap(child1.letters, indx1, indx3);
        Collections.swap(child2.letters, indx2, indx4);
        return List.of(child1, child2);
    }

    @Override
    public SendMoreMoney copy() {
        return new SendMoreMoney(new ArrayList<>(letters));
    }

    @Override
    public String toString() {
        int s = letters.indexOf('S');
        int e = letters.indexOf('E');
        int n = letters.indexOf('N');
        int d = letters.indexOf('D');
        int m = letters.indexOf('M');
        int o = letters.indexOf('O');
        int r = letters.indexOf('R');
        int y = letters.indexOf('Y');
        int send = s * 1000 + e * 100 + n * 10 + d;
        int more = m * 1000 + o * 100 + r * 10 + e;
        int money = m * 10000 + o * 1000 + n * 100 + e * 10 + y;
        int difference = Math.abs(money - (send + more));
        return (send + " + " + more + " = " + money + " Difference: " + difference);
    }
}