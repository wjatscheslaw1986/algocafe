package algos.dna;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {
	
	private ArrayList<Codon> gene = new ArrayList();
	private ArrayList<Codon> sortedCodons = null;
	
	public Gene(String geneStr) {
		if (geneStr.length()%3!=0) throw new IllegalArgumentException();
		for (int i = 0; i < geneStr.length(); i+=3) {
			gene.add(new Codon(geneStr.substring(i, i+3)));
		}
		sortedCodons = new ArrayList(gene);
		LocalDateTime strt = LocalDateTime.now();
		Collections.sort(sortedCodons);
		System.out.println("Sort done in " + Duration.between(strt, LocalDateTime.now()).toMillis() + " millis");
	}
	
	public enum Nucleotide {
		A, C, G, T
	}
	
	public static class Codon implements Comparable<Codon> {
		
		public final Nucleotide first, second, third;
		
		Comparator<Codon> comparator = Comparator.comparing(Codon::getFirst).thenComparing(Codon::getSecond).thenComparing(Codon::getThird);
		
		public Codon(String codonStr) {
			first = Nucleotide.valueOf(codonStr.substring(0,1));
			second = Nucleotide.valueOf(codonStr.substring(1,2));
			third = Nucleotide.valueOf(codonStr.substring(2,3));
		}
		
		public Nucleotide getFirst() {
			return this.first;
		}

		public Nucleotide getSecond() {
			return this.second;
		}

		public Nucleotide getThird() {
			return this.third;
		}

		@Override
		public int compareTo(Codon o) {
			return this.comparator.compare(this, o);
		}
	}

	public ArrayList<Codon> getGene() {
		return gene;
	}

	public ArrayList<Codon> getSortedCodons() {
		return sortedCodons;
	}
}