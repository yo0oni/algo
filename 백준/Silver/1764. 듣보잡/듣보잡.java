import java.io.*;
import java.util.*;

public class Main {
	static HashMap<String, Integer> count = new HashMap<>();
	static PriorityQueue<String> names = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i ++) {
			count.put(br.readLine(), 1);
		}
		
		for(int i = 0; i < M; i ++) {
			String name = br.readLine();
			
			if(count.containsKey(name)) {
				names.add(name);
			}
		}
		System.out.println(names.size());
		
		while(!names.isEmpty()) {
			sb.append(names.poll()).append("\n");
		}
		
		System.out.println(sb);
		
	}
}