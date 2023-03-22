import java.util.Scanner;
public class Passwordchecker {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String password;
		System.out.println("Enter password");
		password = inp.nextLine();
		int length;
		length = password.length();
		int i;
		int countcaptial = 0;
		int countlower = 0;
		int countnumber = 0;
		int countspace = 0;
		int countunique = 0;
		char s;
		if (length > 8) {
			for (i = 0; i < length; i++) {
				s = password.charAt(i);
				if (Character.isUpperCase(s)) {
					countcaptial++;
				} else if (Character.isLowerCase(s)) {
					countlower++;
				} else if (Character.isDigit(s)) {
					countnumber++;
				} else if (Character.isWhitespace(s)) {
					countspace++;
				} else {
					countunique++;
				}

			}
		} else {
			System.out.println("the password is not to long");
		}
//		System.out.println(countunique);
		if (countcaptial > 1 && countlower > 1 && countnumber > 1 && countunique >= 1) {
			System.out.println("passowrd is Strong");
//			System.out.println(countspace);
		} else {
			System.out.println("The password is weak");
		}

	}

}
