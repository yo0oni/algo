import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] origin;
	static HashSet<Integer> numbers = new HashSet<>();
	static HashMap<Integer, Integer> indexing = new HashMap<>();
	static PriorityQueue<Integer> number;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		origin = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			int number = Integer.parseInt(st.nextToken());
			origin[i] = number;
			numbers.add(number);
		}
		
		number = new PriorityQueue<>(numbers);
		int index = 0;
		
		while(!number.isEmpty()) {
			indexing.put(number.poll(), index++);
		}
		
		for(int i = 0; i < N; i ++) {
			sb.append(indexing.get(origin[i])).append(" ");
		}
		System.out.println(sb);
 	}
}
