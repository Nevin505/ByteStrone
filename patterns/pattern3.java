//
//public class pattern3 {
//	public static void main(String[] args) {
//		int i,j;
//		for(i=0;i<5;i++) {
//			for(j=0;j<5;j++) {
//				System.out.print("*");
//			}
//			System.out.println(" ");
//		}
//	}
//
//}
public class pattern3 {
public static void main(String[] args) {
	int i,j,row;
	row=5;
	for(i=0;i<=row;i++) {
		for(j=0;j<row-i+1;j++){
			System.out.print("*");
			
		}
		System.out.println(" ");
	}
}

}