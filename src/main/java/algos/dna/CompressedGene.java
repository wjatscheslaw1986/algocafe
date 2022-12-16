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

public class CompressedGene {
	
	private DNABitSet bitset;
	private int length;
	
	public CompressedGene(String gene) {
		compress(gene);
	}

	public void compress(String gene) {
		length = gene.length();
		bitset = new DNABitSet(length);
		final String formattedGene = gene.toUpperCase();
		
		for (int i = 0; i < length; i++) {
			final int firstLocation = 2 * i;
			final int secondLocation = firstLocation + 1;
			switch (formattedGene.charAt(i)) {
			case 'A': // 00
				bitset.set(firstLocation, false);
				bitset.set(secondLocation, false);
				break;
			case 'C': // 01
				bitset.set(firstLocation, false);
				bitset.set(secondLocation, true);
				break;
			case 'G': // 10
				bitset.set(firstLocation, true);
				bitset.set(secondLocation, false);
				break;
			case 'T': // 11
				bitset.set(firstLocation, true);
				bitset.set(secondLocation, true);
				break;
				default:
					throw new IllegalArgumentException("non ACGT character in the gene");
			}	
		}
	}
	
	public String decompress() {
		if (bitset == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < (length * 2); i+=2) {
			final int firstBit = (bitset.get(i) ? 1 : 0);
			final int secondBit = (bitset.get(i + 1) ? 1 : 0);
			final int lastBits = firstBit << 1 | secondBit;
			switch (lastBits) {
			case 0b00: // 00 = 'A'
				sb.append('A');
				break;
			case 0b01:
				sb.append('C');
				break;
			case 0b10:
				sb.append('G');
				break;
			case 0b11:
				sb.append('T');
				break;
				default:
					throw new IllegalArgumentException("non ACGT character in the gene");
			}
		}
		return sb.toString();
	}

	public DNABitSet getBitset() {
		return bitset;
	}

	@Override
	public String toString() {
		return bitset.toString();
	}
	
	
	
	
	
}