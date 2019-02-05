package Oving1;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Oving1_test {

    @Test
    public void testDuplicateMethodWhenDuplicated(){

        int[] duplicatedValues = {1,4,5,6,7,8,9,1};

        boolean duplicated = Oving1.duplicates(duplicatedValues);

        assertEquals(true, duplicated);
    }


    @Test
    public void testDuplicateMethodWhenNotDuplicated(){

        int[] duplicatedValues = {1,4,5,6,7,8,9};

        boolean duplicated = Oving1.duplicates(duplicatedValues);

        assertEquals(false, duplicated);
    }

    @Test
    public void testForDuplicatePrimes() {

        int amountOfThreads = 1000;
        int startInterval = 0;
        int endInterval = 100000;
        int[] result = Oving1.getAllPrimesFromIntervalWithThreads(amountOfThreads, startInterval, endInterval);

        assertEquals(false, Oving1.duplicates(result));

    }

    @Test
    public void testForCorrectPrimesFound() {
        int amountOfThreads = 10;
        int startInterval = 0;
        int endInterval = 100;

        int[] expected = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int[] result = Oving1.getAllPrimesFromIntervalWithThreads(amountOfThreads, startInterval, endInterval);
        boolean different = false;

        for(int i = 0; i<result.length; i++){
            if(expected[i] != result[i]){
                different = true;
                break;
            }
        }

        assertEquals(false, different);

        //Another test cases
    }

}