// SPDX-License-Identifier: CC0-1.0

package edu.unca.csci202;

import java.util.Arrays;
import java.util.Random;

/**
 * @author John Mitchell
 * @author Nathaniel Fitzenrider
*/
public class SortLab2 {

	public static final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final int MIN_SIZE = 5000;
	private static final int MAX_SIZE = 50000;
	private static final int INCREMENT = 5000;
	private static final int MIN_LEN = 3;
	private static final int MAX_LEN = 10;
	
	/**
	 * Generates an array and copies it twice and then times how long it takes to sort the array using each of the 3 sorting methods.
	 */
	
	public static void main(String[] args) {
		for(int i = MIN_SIZE; i <= MAX_SIZE; i += INCREMENT) {
			String[] arr1 = generateArray(i, MIN_LEN, MAX_LEN);
			String[] arr2 = arr1.clone();
			String[] arr3 = arr1.clone();
			System.out.println("\nArray length: " + arr1.length);
			long start = System.nanoTime();
			Arrays.sort(arr1);
			long end = System.nanoTime();
			System.out.println("Arrays.sort(): " + (end - start) / 1000000000.0);
			start = System.nanoTime();
			Sorting.quickSort(arr2);
			end = System.nanoTime();
			System.out.println("QuickSort: " + (end - start) / 1000000000.0);
			start = System.nanoTime();
			Sorting.selectionSort(arr3);
			end = System.nanoTime();
			System.out.println("SelectionSort: " + (end - start) / 1000000000.0);
		}
		

	}
	/**
	 * creates a new string array of a length n and randomly chooses a letter to add to the string in each index until specified index is a randomly determined length.
	 */	
	public static String[] generateArray(int n, int minLength, int maxLength) {
		String[] finalArray = new String[n];
		Random rand = new Random();
		for(int i = 0; i < n; i++) {
			int len = rand.nextInt(minLength, maxLength + 1);
			StringBuilder sb = new StringBuilder();
			while(len-- > 0) {
				sb.append(letters[rand.nextInt(0, letters.length)]);
			}
			finalArray[i] = sb.toString();
		}
		return finalArray;
	}

}
