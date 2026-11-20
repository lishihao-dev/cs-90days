package Array;

public class ArraySumMax {
    public static void main(String[] args) {
        int arr[] = {15,23,7,44,9};
        int sum=0;
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            if(arr[i]>max){
                max = arr[i];
            }
        }
        System.out.println("总数是"+sum);
        System.out.println("最大值是"+max);
    }
}
