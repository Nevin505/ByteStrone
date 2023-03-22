import java.util.Scanner;

public class Multidim {
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		int i, j, n, nofgrades;
		System.out.println("Enter number of students");
		n = inp.nextInt();
		System.out.println("Enter the grades");
		nofgrades = inp.nextInt();
		String[] name1 = new String[n];
		int grades[][] = new int[n][nofgrades];
		double average=0;
//to deaclare name of each students
		for (i = 0; i < n; i++) {
			System.out.println("Enter the studenst 1=" + (i + 1));
			name1[i] = inp.next();
		}
//to declare grades of each students
		for (i = 0; i < n; i++) {
			System.out.println("Enter the grades of "+name1[i]);
			for (j = 0; j < nofgrades; j++) {
				grades[i][j] = inp.nextInt();
			}
		}
		double aver[]=new double[n];
		
		for(i=0;i<n;i++) {
			int sum1=0;
			for(j=0;j<nofgrades;j++) {
				sum1=sum1+grades[i][j];
				
			}
			average=sum1/nofgrades;
			aver[i]=average;
		}
		
		for(i=0;i<n;i++) {
			System.out.println(name1[i]+"  "+aver[i]);
		}

	}
}