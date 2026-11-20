package Array;
public class ArrayDistinct {
    public static void main(String[] args) {
        int arr[] = {1,2,2,3,3,3,4,5,5};
        boolean flag = false;
        int count = 1;
        for (int i = 1; i <arr.length ;i++) {
            flag = true;
            for(int j=0;j<count;j++){
               if(arr[j] == arr[i]) {
                   flag = false;
                   break;
               }
            }
            if(flag){
                arr[count] = arr[i];
                count++;
            }
        }
        System.out.print("去重后");
        for (int i = 0; i < count; i++) {
            System.out.print(arr[i]);
        }
    }
}
