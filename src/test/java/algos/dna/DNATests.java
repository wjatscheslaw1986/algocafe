package algos.dna;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DNATests {

    @Test
    public void decompressTest() {
        final String gene = "AGACCATATTA";
        CompressedGene compressed = new CompressedGene(gene);
        Assertions.assertNotEquals(gene, compressed);
        final String decompressed = compressed.decompress();
        Assertions.assertEquals(gene, decompressed);
        Assertions.assertEquals(22, compressed.getBitset().getTotalBits());
    }

    @Test
    public void linearContainsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAGT"));
        Gene g = new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAG");
        Assertions.assertFalse(GeneUtil.linearContains(g.getGene(), new Gene.Codon("GTT")));
        Assertions.assertTrue(GeneUtil.linearContains(g.getGene(), new Gene.Codon("ATT")));
    }

    @Test
    public void binaryContainsTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAGT"));
		Gene g = new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAG");
        Assertions.assertFalse(GeneUtil.binaryContains(g.getSortedCodons(), new Gene.Codon("GTT")));
        Assertions.assertTrue(GeneUtil.binaryContains(g.getSortedCodons(), new Gene.Codon("ATT")));
    }

    @Test
    public void linearContainsStaticTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAGT"));
        Gene g = new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAG");
        Assertions.assertFalse(GeneUtil.linearContains(g.getGene(), new Gene.Codon("GTT")));
        Assertions.assertTrue(GeneUtil.linearContains(g.getGene(), new Gene.Codon("ATT")));
    }

    @Test
    public void binaryContainsStaticTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAGT"));
        Gene g = new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAG");
        Assertions.assertFalse(GeneUtil.binaryContains(g.getSortedCodons(), new Gene.Codon("GTT")));
        Assertions.assertTrue(GeneUtil.binaryContains(g.getSortedCodons(), new Gene.Codon("ATT")));
    }

    @Test
    public void performanceTest() {
        LocalDateTime startLinear = LocalDateTime.now();
        for (int i = 0; i < 1_000_000; i++) {
            Gene g = new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAGATGATGATAGCACCATCACACTGAGACAGCAATGATAGATACACAACACGCCATAGATAGTAGATCGCTCGCTGAGCGGATGATGCGGTAGATAGGATGGAAGCACACGATAGAGATAGTAACCAGACGCCGCATAGTAGT");
            GeneUtil.linearContains(g.getGene(), new Gene.Codon("GTT"));
            GeneUtil.linearContains(g.getGene(), new Gene.Codon("CGG"));
        }
        System.out.println("One million times linear contains checked for " + Duration.between(startLinear, LocalDateTime.now()).toMillis() + " millis");

        LocalDateTime startBinary = LocalDateTime.now();
        for (int i = 0; i < 1_000_000; i++) {
            Gene g = new Gene("AGACCATATTAAGACCATATTAAGACCATATTAAGACCATATTAGATGATGATAGCACCATCACATGCAGACAGCAATGATAGATACACAACACGCATAGACTAGTAGATCGCTCGCTGAGCGGATGATGCGGTAGATAGGATGGAAGCACACGATAGAGATAGTAACCAGACGCCGCATAGTAGT");
            GeneUtil.binaryContains(g.getSortedCodons(), new Gene.Codon("GTT"));
            GeneUtil.binaryContains(g.getSortedCodons(), new Gene.Codon("CGG"));
        }
        System.out.println("One million times binary contains checked for " + Duration.between(startBinary, LocalDateTime.now()).toMillis() + " millis");
    }
    
    @Test
    public void performanceTest2() {
        List<Boolean> rslts = new LinkedList<>();
        Gene g = GeneUtil.generateRandomGene();
        LocalDateTime startLinear = LocalDateTime.now();
        int times = 1_000;
        for (int i = 0; i < times; i++) rslts.add(GeneUtil.linearContains(g.getGene(), GeneUtil.generateRandomCodon()));

        System.out.println("" + times + " times linear contains checked for " + Duration.between(startLinear, LocalDateTime.now()).toMillis() + " millis");

        LocalDateTime startBinary = LocalDateTime.now();
        for (int i = 0; i < times; i++) rslts.add(GeneUtil.binaryContains(g.getSortedCodons(), GeneUtil.generateRandomCodon()));

        System.out.println("" + times + " times binary contains checked for " + Duration.between(startBinary, LocalDateTime.now()).toMillis() + " millis");
        System.out.println("Num " + rslts.parallelStream().count());
    }
}