// Cal Al-Dhubaib, CWRU 
// Assignment 3 - 2/5/16 (Modified from Assignment 2)

public class Sort {
  public static void quickSort(int[] array, int lowerIndex, int higherIndex) {
    int i = lowerIndex;
    int j = higherIndex;
    int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
    while (i <= j) {
      while (array[i] < pivot) { i++; }
      while (array[j] > pivot) { j--; }
      if (i <= j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        i++;
        j--;
      }
    }
    if (lowerIndex < j)
      quickSort(array, lowerIndex, j);
    if (i < higherIndex)
      quickSort(array, i, higherIndex);
  }
  
  public static void insertionSort(int[] data){
    int temp;
    for (int i = 1; i < data.length; i++) {
      for(int j = i ; j > 0 ; j--){
        if(data[j] < data[j-1]){
          temp = data[j];
          data[j] = data[j-1];
          data[j-1] = temp;
        }
      }
    }
  }
}
