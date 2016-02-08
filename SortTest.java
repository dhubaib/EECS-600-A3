// Cal Al-Dhubaib, CWRU 
// Assignment 3 - 2/5/16 (Modified from Assignment 2)

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class SortTest {
  public static void main(String[] args) {
    // Use args [sortMethod, array size, test array config]
    long startTime, stopTime; // For recording start/stop times
    int baseSize = 100000;
    int sortMethod = 0; // QuickSort is default, Insertion Sort = 1
    int testArray = 0; // Default configuration is reverse ordered. Random order = 1
    int testNum = 0; // To identify test number / modify output file name accordingly
    int nTrial = 10;
    
    // Set default analysis parameters via passed args
    if (args.length == 0){
      System.out.println("Default settings\nAlgorithm = quick sort, test array size = 1e5, test array = reverse");
    }
    if (args.length >= 1){
      if(args[0].equals("insert")){
        System.out.println("Using insertion sort");
        sortMethod = 1;
      }
    }
    if (args.length >= 2){
      try{ // Try to set base
        baseSize = Integer.parseInt(args[1]);
        System.out.println("Test size = " + baseSize);
      } finally {}
    }
    if(args.length >= 3){
      if(args[2].equals("random")){
        testArray = 1;
        System.out.println("Test array is random ordered");
      }
    }
    if(args.length >= 4){
      try{ 
        testNum = Integer.parseInt(args[3]);
      } finally {}
    }
    if (args.length > 4)
      System.out.println("Warning: Too many arguments. Ignoring extra inputs.");
    
    // Set up arrays to collect times
    int[] trialSizes = new int[nTrial];
    float[] sortTimes = new float[nTrial]; // Multiple times for each array size
    trialSizes[0] = (int)baseSize;
    // Run test case on various-sized arrays
    for (int trial = 0; trial < nTrial; trial++){
      trialSizes[trial] = baseSize*(trial+1); // Fill array of sequence
      int maxNum = trialSizes[trial];
      int[] seq = new int[maxNum]; // Initialize the array
      // Build various reverse-ordered sequences
      for (int j = 0; j < seq.length; j++)
        seq[j] = maxNum--; // Fill with reverse order
      // Random shuffle array if option selected
      if(testArray == 1){
        Random rnd = new Random();
        for (int j = 0; j < seq.length; j++){
          int index = rnd.nextInt(seq.length);
          int temp = seq[j];
          seq[j] = seq[index];
          seq[index] = temp;
        }
      }
      // Run sort test on sequence here for each sample size
      startTime = System.currentTimeMillis();
      if (sortMethod == 1)
        Sort.insertionSort(seq);
      else
        Sort.quickSort(seq,0,seq.length - 1);
      stopTime = System.currentTimeMillis();
      sortTimes[trial] = (float)(stopTime - startTime);
      if(sortMethod == 1)
        System.out.print("Insertion Sort ");
      else
        System.out.print("Quick Sort ");
      System.out.println("["+trial+ "]: " + (stopTime - startTime));
    }
    
    // Output timing results to file
    File f = null;
    String filename = "";
    if(sortMethod == 1)
      filename = filename + "insertion";
    else
      filename = filename + "quick";
    if(testArray == 1)
      filename = filename + "_rand";
    else
      filename = filename + "_reverse";
    filename = filename + "_test"+testNum+".csv";
    try{
      f = new File(filename);
      f.createNewFile();
      
      FileOutputStream fis = new FileOutputStream(f);
      PrintStream out = new PrintStream(fis);
      System.setOut(out);
      
      // First row in each file = sequence length
      for (int trial = 0; trial < nTrial; trial++){
        System.out.print(trialSizes[trial]); 
        if(trial < nTrial - 1)
          System.out.print(",");
      }
      System.out.print("\n");
      
      // Second row in each file = sorting time
      for (int trial = 0; trial < nTrial; trial++){
        System.out.print(sortTimes[trial]);
        if(trial < nTrial - 1)
          System.out.print(",");
      }
      System.out.print("\n");
      out.close();// Close print streams
    } catch(Exception e){}
  }
}