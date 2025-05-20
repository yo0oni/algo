import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			int number = Integer.parseInt(br.readLine());

			if (number == -1) {
				break;
			}
			int sum = 0;
			int last = 0;
			boolean[] arr = new boolean[number];

			for (int i = 1; i < number; i++) {
				if (number % i == 0) {
					sum += i;
					last = i;
					arr[i] = true;
				}
			}

			if (sum == number) {
				sb.append(number).append(" = ");

				for (int i = 0; i < arr.length; i++) {
					if (i == last) {
						sb.append(i).append("\n");
					} else if (arr[i]) {
						sb.append(i).append(" + ");
					}
				}
			} else {
				sb.append(number + " is NOT perfect.").append("\n");
			}

		}
		System.out.println(sb);
	}
}
