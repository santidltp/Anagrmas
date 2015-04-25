

public class MergeSort {
	
	public static void mergeSort(String[][] words) {
        if (words[0].length >= 2) {
            String[][] left = new String[2][words[0].length / 2];
            String[][] right = new String[2][words[0].length - words[0].length / 2];

            for (int i = 0; i < left[0].length; i++) {
                left[0][i] = words[0][i];
                left[1][i] = words[1][i];
            }

            for (int i = 0; i < right[0].length; i++) {
                right[0][i] = words[0][i + words[0].length / 2];
                right[1][i] = words[1][i + words[0].length / 2];
            }

            mergeSort(left);
            mergeSort(right);
            merge(words, left, right);
        }
    }

    public static void merge(String[][] words, String[][] left, String[][] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < words[0].length; i++) {
            if (b >= right[0].length || (a < left[0].length && left[1][a].compareToIgnoreCase(right[1][b]) < 0)) {
            	words[0][i] = left[0][a];
                words[1][i] = left[1][a];
                a++;
              
            } else {          	
	                words[0][i] = right[0][b];
	                words[1][i] = right[1][b];
	                b++;
            	
            }
        }
    }
    
    


}
