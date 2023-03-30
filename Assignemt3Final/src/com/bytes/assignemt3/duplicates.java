import java.util.Scanner;
public class duplicates {
	public static void main(String[] args){
		Scanner inp=new Scanner(System.in);
		int n;
		System.out.println("Enter array length");
		n=inp.nextInt();
		int array1[]=new int[n];
		int array2[]=new int[n];
		int i;
		int j;
		int k=0;
		int element;
		for(i=0;i<n;i++) {
			System.out.println("Enter array elements");
			array1[i]=inp.nextInt();
		}
		
		for(i=0;i<n;i++) {
			element=array1[i];
			int flag=0;
			for(j=0;j<n;j++) {
				if(element==array2[j]) {
					flag=1;
					break;
				}
			}
			if(flag==0) {
				array2[k++]=element;
			}
			
		}
		for(i=0;i<k;i++) {
			System.out.println(array2[i]);
		}
		
		
	}
	

}

