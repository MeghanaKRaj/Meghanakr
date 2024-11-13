import java.util.Arrays;

public class MergeSort {

    // Main function to sort an array using merge sort
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // Base case: if array has 1 or fewer elements, it's already sorted
        }

        // Find the middle point of the array
        int mid = array.length / 2;

        // Split the array into two halves
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        // Recursively sort both halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    // Function to merge two sorted arrays into a single sorted array
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0; // Initial index for left sub-array
        int j = 0; // Initial index for right sub-array
        int k = 0; // Initial index for the merged array

        // Merge the two arrays into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy the remaining elements from left array (if any)
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy the remaining elements from right array (if any)
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Main function to test the mergeSort
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Original array: " + Arrays.toString(array));

        // Call mergeSort to sort the array
        mergeSort(array);

        // Output the sorted array
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}

