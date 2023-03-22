import java.util.Scanner;
public class Fibonaci {
	public static void main(String[] args) {
		int n;
		Scanner inp=new Scanner(System.in);
		System.out.println("Enter input");
		n=inp.nextInt();
		int a,i;
		int b,c=0;
		a=0;
		b=1;
		System.out.println(a);
		System.out.println(b);
		for(i=2;i<=n;i++) {
			c=a+b;
			a=b;
			b=c;
			System.out.println(c);
			
		}
	}

}
