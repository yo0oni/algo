import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static class Player implements Comparable<Player> {
		int level;
		String name;

		public Player(int level, String name) {
			this.level = level;
			this.name = name;
		}

		@Override
		public int compareTo(Player o) {
			return this.name.compareTo(o.name);
		}
	}

	static class Room {
		int level;
		int count;
		boolean isStarted;
		ArrayList<Player> players;

		public Room(Player player) {
			this.level = player.level;
			this.count = 1;
			this.isStarted = false;

			players = new ArrayList<Player>();
			players.add(new Player(player.level, player.name));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Room> rooms = new ArrayList<>();

		for (int p = 0; p < P; p++) {
			st = new StringTokenizer(br.readLine());
			int level = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			Player player = new Player(level, name);

			boolean matched = false;
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.get(i);

				if (room.level - 10 <= player.level && player.level <= room.level + 10) {
					if (room.count < M) {
						room.players.add(player);
						room.count++;
						matched = true;

						if (room.count == M) {
							room.isStarted = true;
						}
						
						break;
					}
				}
			}

			if (!matched) {
				Room room = new Room(player);
				rooms.add(room);
				
				if(room.players.size() == M) {
					room.isStarted = true;
				}
			}
		}

		for(Room room : rooms) {
			Collections.sort(room.players);
			
			if(room.isStarted) {
				sb.append("Started!").append("\n");
			} else {
				sb.append("Waiting!").append("\n");
			}
			
			for(Player player : room.players) {
				sb.append(player.level).append(" ").append(player.name).append("\n");
			}
		}
		System.out.println(sb);
	}

}
