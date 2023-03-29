package pattern;
//public class pattern4 {
//	public static void main(String[] args) {
//		int i,j,k,n;
//		n=4;		
//		for(i=0;i<5;i++) {
//			String s=" ";
//			for(j=n;j>0;j--) {
//				s=s+" ";
//				
//			}
//			n--;
//						
//			for(k=0;k<=i;k++) {
//				s=s+"* ";
//			}			
//		System.out.println(s);	
//		}
//		
//	}
//
//}
public class pattern4{
	public static void main(String[] args) {
		int i,j,k,n;
		n=5;
		for(i=0;i<n;i++) {
			for(j=0;j<n-i-1;j++) {
				System.out.print(" ");
			}
			for(k=0;k< (2*i)+1;k++) {
				System.out.print("*");
			}
			System.out.println(" ");
		}
	}
}
