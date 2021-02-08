import java.util.Scanner;

public class Solution {
  // in the Solution class we will store our methods to Bubble Sort
  // name oif class is Solution it was given in hackerrank
  // i will start off with the main class to run my methods
  public static void main(String[] args) {
    // Scanner imported to recieve the userInput i named it scanner it can be named whatever
    Scanner scanner = new Scanner(System.in);
    // int n is storing the value of the int the user inputs
    int n = scanner.nextInt();
    // here im storing the array of ints
    int[] a = new int[n];
    // ima run it through a for loop
    // to check each number in the array that was inputted by user
    for (int i = 0; i < a.length; i++) {
      a[i] = scanner.nextInt();
    }
     // here i will invoke my bubbleSort method and store it in new varible
     int swapNum = bubbleSort(a);

     // i print out to the console how many swaps
     // the first element
     // the last element using PrintF for format
     System.out.printf("Array is sorted in %d swaps \n", swapNum);
     System.out.printf("First Element: %d", a[0]);
     System.out.printf("Last Element: %d", a[a.length - 1]);

  }
  // This will be my method to do most of the work i will name it bubbleSort
  // this method returns a number for number of swaps done to sort out a single array
  // parameter is an array of ints
  public static int bubbleSort(int[] a) {
    // initalize my varible of how many swaps are done so we can print out to the console;
    int swapNum = 0;

    for(int i = 0; i < a.length; i++) {
      // Track the number of elements swapped during a singke array traversal
      int numberOfSwaps = 0;
      for (int j = 0; j < a.length - 1; j++) {
        //Swapnadjacent elements if they are in decreasing order
        if (a[j] > a[j + 1]){
          // this swap method is created below
          //
          swap(a, j, j+1);
          numberOfSwaps++;
          swapNum++;
        }

      }
      // If no elements were swapped during the traversal, array is sorted
      if (numberOfSwaps == 0) {
        break;
      }
    }
    // return the numberOfSwaps to print out to console
    return swapNum;
  }
// this method named swap does the swaping of the elements in an array
// created a temp var to hold the element while i swap and got to the next number
// til all the array is sorted out
  public static void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
