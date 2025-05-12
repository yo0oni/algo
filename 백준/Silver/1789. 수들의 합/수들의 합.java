import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		long sum = 0;

		for (long i = 1; i < S + 1; i++) {
			sum += i;
			if (sum == S) {
				System.out.println(i);
				break;
			} else if (sum > S) {
				System.out.println(i - 1);
				break;
			}
		}
	}

}
