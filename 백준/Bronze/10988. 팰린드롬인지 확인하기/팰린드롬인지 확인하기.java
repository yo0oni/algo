import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		boolean isPalindrome = true;

		for (int i = 0; i <= str.length() / 2; i++) {
			if(str.charAt(i) != str.charAt(str.length() - i - 1)) {
				isPalindrome = false;
				break;
			}
		}
		
		if(isPalindrome) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
}