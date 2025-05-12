import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> numbers = new PriorityQueue<>();
		
		for(int i = 0; i < N; i ++) {
			numbers.add(Integer.parseInt(br.readLine()));
		}
		
		int answer = 0;
		while(!numbers.isEmpty() && numbers.size() >= 2) {
			int num1 = numbers.poll();
			int num2 = numbers.poll();
			
			answer += (num1 + num2);
			numbers.add(num1 + num2);
		}
		System.out.println(answer);;
	}

}
