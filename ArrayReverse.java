package Array;

public class ArrayReverse {
    public static void main(String[] args) {
        int arr[] = {11,22,33,44,55};
        int left=0;
        int temp = 0;
        int right = arr.length-1;
        while(left<right){
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.print("反转后的数字是:");
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]);
        }
    }
}
