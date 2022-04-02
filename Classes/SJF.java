package Classes;

import java.util.*;

public class SJF{
  public static void SortArray(int[][] arr){
    Arrays.sort(arr, new Comparator<int[]>(){
      //compare values in column 1 aka burst time for each row in the array
      public int compare(final int[] bt1, final int[] bt2) {
        if (bt1[1] > bt2[1])
          return 1;
        else
          return -1;

      }
    });

  }
}
