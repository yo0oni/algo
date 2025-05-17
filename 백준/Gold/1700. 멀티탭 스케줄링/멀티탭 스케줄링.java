import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Product implements Comparable<Product> {
		int productNumber;
		int nextIndex;

		public Product(int productNumber, int nextIndex) {
			this.productNumber = productNumber;
			this.nextIndex = nextIndex;
		}

		@Override
		public int compareTo(Product other) {
			return this.nextIndex - other.nextIndex;
		}
	}

	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] numbers = new int[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int count = 0;
		HashSet<Integer> plug = new HashSet<Integer>();

		for (int i = 0; i < K; i++) {
			int number = numbers[i];

			if (plug.contains(number))
				continue;

			if (plug.size() < N) {
				plug.add(number);
				continue;
			}

			int lastest = -1;
			int removeProduct = -1;

			for (int p : plug) {
				int nextIndex = Integer.MAX_VALUE;

				for (int j = i + 1; j < K; j++) {
					if (numbers[j] == p) {
						nextIndex = j;
						break;
					}
				}

				if (lastest < nextIndex) {
					lastest = nextIndex;
					removeProduct = p;
				}
			}

			plug.remove(removeProduct);
			plug.add(number);
			count++;
		}

		System.out.println(count);
	}

}
