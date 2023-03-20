
public class pattern5 {
public static void main(String[] args) {
	int i,j,k,n,m;
	n=6;
	m=4;
	for(i=0;i<6;i++) {
		String s=" ";
		
		for(j=0;j<m;j++) {
			s=s+" ";
		}
		m--;
	
	for(k=n;k>0;k--) {
		s=s+"*";
	}
	n--;
	
	
	System.out.println(s);
	
}
	
}
}
