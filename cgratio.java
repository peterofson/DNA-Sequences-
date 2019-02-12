package StringsThirdAssignments;


/**
 * Write a description of cgratio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
import edu.duke.StorageResource;
import edu.duke.*;
import java.io.*;


public class cgratio {
    double cgRatio(String dna){
    
        
        int count = 0;
        int ct = 0;
        char first = 'C';
        char second = 'G';
        double lgt = dna.length();
        
        for (int i = 0; i < lgt; i++){
            if (dna.charAt(i) == first){
                count++;
            }
        }
        
        for (int i = 0; i < lgt; i++){
            if (dna.charAt(i) == second){
                ct++;
            }
        }
        
        double result = count + ct;
        double ratio = result / lgt;
        
        return ratio;
    }
    
    public void processGenes(StorageResource sr ){
        int ct1 = 0; 
        int ct2 = 0;
        
        // print all the Strings in sr that are longer than 60 characters
        for (String word : sr.data()){
            if (word.length() > 60){
                System.out.println("Gene longer than 60: " + word);
                ct1++;      
            }
        }
        
       // print the number of Strings in sr that are longer than 60 characters
        System.out.println("There are a total of " + ct1 + " genes longer than 60");
        
        
        //print the Strings in sr whose C-G-ratio is higher than 0.35
        for (String word : sr.data()){
            if(cgRatio(word) > 0.35){
                System.out.println("C-G ratio gene: " + word);
                ct2++;
            }
        }
        
        //print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.print("Total C-G raito: " + ct2);
        
        // print the length of the longest gene in sr
        int max = 0;
        
        for (String gene : sr.data()){
            int lgt = gene.length();
            
            if ( lgt > max){
                max = lgt;
            }
        }
        System.out.println("length of the longest gene = " + max);
    
    
    }
    
    public StorageResource getAllGenes(String dna){
        
        StorageResource genelist = new StorageResource();
        int startindex = 0; 
        int ct = 0;
        
        while (true){
            String currentGene = findGene(dna, startindex);
            
            if (currentGene.isEmpty()){
                break;
            }
            genelist.add(currentGene);
            startindex = dna.indexOf(currentGene, startindex) + currentGene.length();
            ct ++;
        }   
        
        return genelist;
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
            int curIndex = dna.indexOf(stopCodon,startIndex+3);
            
            while (curIndex != -1){
                int diff = startIndex - curIndex;
                if (diff % 3 == 0){
                 return curIndex;   
                }
                else {
                    curIndex = dna.indexOf(stopCodon, curIndex+1);
                }
        }
        
        return -1;
    }
     
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        
        int taaIndex = findStopCodon(dna,startIndex, "TAA");
        int tagIndex = findStopCodon(dna,startIndex, "TAG");
        int tgaIndex = findStopCodon(dna,startIndex, "TGA");
        // int temp = Math.min(taaIndex, tagIndex);
        //int min = Math.min(temp, tgaIndex);
        int min = 0;
        
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex))
        {
            min = tgaIndex;
        }
        else min = taaIndex;
        
        if (min == -1 || (tagIndex != -1 && tagIndex < min))
        {
            min = tagIndex;
        }
        
        if (min == -1){
            return "";
        }
        String a = dna.substring(startIndex, min+3);
        return a;
    }
    
    public void test(){
        String dna = "ATGCCATAG";
        //System.out.println("The ratio of 'C' to 'G' is " + cgRatio(dna));
        
    
    
    }
       
     
   
    
       
   
       
    
    
     
    
    

    
}
