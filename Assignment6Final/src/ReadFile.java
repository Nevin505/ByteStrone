import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
public class ReadFile{
	public static void main(String[] args) throws FileNotFoundException{
		String data;
		File f1=new File("C:\\Java\\replace.txt");

			Scanner  myReader = new Scanner(f1);
		
		while (myReader.hasNextLine()) {
			data = myReader.nextLine();
			  System.out.println(data);
			  String s1=data.replace("Wordpad","Notepad");
				System.out.println(s1);
			  
			  
		}
		
	}
	
} 

    
  