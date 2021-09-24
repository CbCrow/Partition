import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Example showing partition search
 * @author Collin Crowthers
 */

public class CollinCrowthersAssignment6 {

    public static void main(String[] args) {
        //menu system which sets up the array. I am using an array list because
        //it can be added to easily and this is about using partition
        //and not necessarily array manipulation. 
        
        Scanner in = new Scanner(System.in);
        int input;
        int size=0;
        ArrayList<Integer> arrx = new ArrayList<Integer>();
        do{
            
            System.out.println("\n1. Set up a new array");
            System.out.println("2. Find the k-th smallest value");
            System.out.println("3. Exit");
            
            input = in.nextInt();
            int knum, low, high;
            System.out.println("");
            
            switch(input){
                   //set up new array
                case 1:
                    //make array empty
                    arrx = new ArrayList<Integer>();
                    //fill with new values
                    System.out.println("What number would you like to add to the array? (input -1 to stop)");
                    input = in.nextInt();
                    while (input != -1) {
                        //was not -1 so it is added
                        arrx.add(input);
                        System.out.println("What number would you like to add to the array? (input -1 to stop)");
                        input = in.nextInt();
                    }
                    size = arrx.size();

                    break;
                    
                case 2:
                    System.out.println("What k-th smallest number would you like to find?");
                    knum = in.nextInt();
                    
                    //check if this is a number capable of being in the array
                    if (knum <= size && knum != 0) {
                        //call method
                        high = size-1;
                        low = 0;
                        System.out.println(partitionSearch(arrx, knum, low, high));
                        //partitionSearch(arrx, knum, low, high);
                    } else {
                        System.out.println("This number is too large or 0. Try again");
                    }
                    
                    break;
                    
                case 3:
                    
                    break;
                default:
                    System.out.println("Invalid selection. Try it again.");
            } 
        } while(input != 3);
        
    }
    
    //recursive partition algorithm to search for the k-th smallest number
    //directions dont mention if the original array should or shouldnt be effected
    //so it is here.
    static int partitionSearch(ArrayList<Integer> arrx, int knum, int low, int high){
        
        int i, j;
        int pivotitem = arrx.get(low);
        j = low;
        for (i = low+1; i <= high; i++) {
            if (arrx.get(i)<pivotitem) {
                j++;
                //exchange
                Collections.swap(arrx, i, j);
            }
            
        }
        int pivotpoint = j;
        //exchange
        Collections.swap(arrx, low, pivotpoint);
        //pivotpoint is the k-1th value currently
        
        //if the index we are looking for is the pivotpoint, return
        if (pivotpoint == knum-1) {
            return pivotitem;
        } else if (pivotpoint > knum-1){
            return partitionSearch(arrx, knum, low, pivotpoint-1);
        }
        // if neither of these have returned then it must be less than
        return partitionSearch(arrx, knum, pivotpoint+1, high);
    }

}
