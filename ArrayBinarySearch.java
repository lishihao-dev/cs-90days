package Array;
import java.util.Scanner;
public class ArrayBinarySearch {
    public static void main(String[] args) {
        int arr[] = {10,20,30,40,50,60,70};
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个数字");
        int num = sc.nextInt();
        int count = 0;
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i]==num){
                System.out.print(i+1);
                count++;
                break;
            }
        }
        if(count==0){
            System.out.println("不存在");
        }
    }
}
