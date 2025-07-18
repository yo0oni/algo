import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, Integer> count;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = new HashMap<>();

        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            String title = br.readLine();
            if(!count.containsKey(title)){
                count.put(title, -1);
            } else {
                count.put(title, count.get(title) - 1);
            }
        }

        ArrayList<String> keys =  new ArrayList<>(count.keySet());
        keys.sort(Comparator.comparing((String a) -> count.get(a))
                .thenComparing(a -> a));

        System.out.println(keys.get(0));
    }
}