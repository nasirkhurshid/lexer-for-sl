import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		String file;
		System.out.println("Enter file name: ");
		Scanner input = new Scanner(System.in);
		file = input.next();
		Lexer lexer;
		try {
			FileReader reader = new FileReader(file);
			int i;
			while((i=reader.read())!=-1)    
		          System.out.print((char)i);   
//			lexer = new Lexer(reader);
//			while (true) {
//				TokenClass token = lexer.getToken();
//				if (token == null)
//					break;
//				else
//					System.out.println(token.getToken() + ": " + token.getValue());
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
