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
package algos.dna;

import java.util.BitSet;

public class DNABitSet extends BitSet {

	private static final long serialVersionUID = 1L;

	private long totalBits = 0;

	public DNABitSet() {
		super();
	}

	public DNABitSet(int nbits) {
		super(nbits);
	}

	@Override
	public void set(int bitIndex) {
		if (bitIndex >= totalBits) {
			totalBits = bitIndex + 1;
			super.set(bitIndex);
		}
	}
	
	@Override
	public void set (int bitIndex, boolean value) {
		if (value)
            this.set(bitIndex);
        else
            this.clear(bitIndex);
	}

	@Override
	public void set(int fromIndex, int toIndex) {
		if (fromIndex >= toIndex)
			throw new IllegalArgumentException("Watch what you're doing");
		if (toIndex >= totalBits) {
			totalBits = toIndex + 1;
			super.set(fromIndex, toIndex);
		}
	}
	
	@Override
	public void set(int fromIndex, int toIndex, boolean value) {
		if (value)
            this.set(fromIndex, toIndex);
        else
            this.clear(fromIndex, toIndex);
	}
	
	@Override
	public void clear(int fromIndex, int toIndex) {
		if (fromIndex >= toIndex)
			throw new IllegalArgumentException("Watch what you're doing");
		if (toIndex >= totalBits) {
			totalBits = toIndex + 1;
			super.clear(fromIndex, toIndex);
		}
	}
	
	@Override
	public void clear(int bitIndex) {
		if (bitIndex >= totalBits) {
			totalBits = bitIndex + 1;
			super.clear(bitIndex);
		}
	}

	public long getTotalBits() {
		return totalBits;
	}

}