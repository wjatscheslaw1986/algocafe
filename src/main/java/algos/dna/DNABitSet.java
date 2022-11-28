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