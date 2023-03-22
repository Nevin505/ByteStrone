import java.util.Scanner;
public class Recur {
	Scanner inp = new Scanner(System.in);
	public  int Factorial(int n1) {

		if (n1 == 0 || n1 == 1) {
			return 1;
		} else {
			return n1 * Factorial(n1-1);
		}

	}

}
