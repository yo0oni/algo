import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> croatianMap = new HashMap<>();
        croatianMap.put("c=", "č");
        croatianMap.put("c-", "ć");
        croatianMap.put("dz=", "dž");
        croatianMap.put("d-", "đ");
        croatianMap.put("lj", "lj");
        croatianMap.put("nj", "nj");
        croatianMap.put("s=", "š");
        croatianMap.put("z=", "ž");

        String str = br.readLine();
        int answer = 0;

        for (int i = 0; i < str.length(); ) {
            if (i + 2 < str.length() && croatianMap.containsKey(str.substring(i, i + 3))) {
                i += 3;
            }
            else if (i + 1 < str.length() && croatianMap.containsKey(str.substring(i, i + 2))) {
                i += 2;
            }
            else {
                i++;
            }
            answer++;
        }

        System.out.println(answer);
    }
}
