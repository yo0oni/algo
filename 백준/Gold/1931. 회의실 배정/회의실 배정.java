import java.io.*;
import java.util.*;

public class Main {

	static class Room implements Comparable<Room> {
		int start, end;

		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Room o) {
			if (this.end == o.end) {
				return this.start - o.start;
			}
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Room> rooms = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			rooms.add(new Room(start, end));
		}
		Collections.sort(rooms);

		int count = 0;
		int lastEnd = 0;

		for (Room room : rooms) {
			if (room.start >= lastEnd) {
				count++;
				lastEnd = room.end;
			}
		}

		System.out.println(count);

	}

}
