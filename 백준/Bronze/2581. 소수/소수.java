import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> primes = new ArrayDeque<>();
		int answer = 0;
		int min = Integer.MAX_VALUE;

		for (int number = M; number <= N; number++) {
			if (isPrime(number)) {
				primes.add(number);
			}
		}

		while (!primes.isEmpty()) {
			int number = primes.poll();
			min = Math.min(min, number);
			answer += number;
		}
		if (answer == 0) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
			System.out.println(min);
		}
	}

	private static boolean isPrime(int number) {
		if (number < 2) return false;
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) return false;
		}
		return true;
	}
}
