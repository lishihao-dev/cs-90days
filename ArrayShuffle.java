package Array;

import java.util.Random;

public class ArrayShuffle {
    public static void main(String[] args) {
        int arr[] = {10,20,30,40,50};
        int temp;
        Random r = new Random();
        for (int i = 0; i <arr.length ; i++) {
            int arrindex = r.nextInt(arr.length);
            temp = arr[i];
            arr[i] = arr[arrindex];
            arr[arrindex] = arr[i];
        }
        System.out.print("打乱后的数组是");
        for (int i = 0; i <arr.length ; i++) {
            System.out.println(arr[i]);
        }
    }

}
