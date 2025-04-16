
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();       // 연결 여부
    static int[] population; // 인구수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = Integer.MAX_VALUE;
        
        population = new int[n+1];
        for (int i = 1; i < n+1; i ++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            for (int j = 0; j < t; j ++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        int[] numbers = new int[n+1];
        for (int i = 1; i < n+1; i++) numbers[i] = i;

        List<List<Integer>> group1 = new ArrayList<>();
        List<List<Integer>> group2 = new ArrayList<>();
        partition(numbers, 1, new ArrayList<>(), new ArrayList<>(), group1, group2);

        // 연결 여부 확인
        for (int i = 0; i < group1.size(); i++) {
            if (is_connected(group1.get(i)) && is_connected(group2.get(i))) {
                answer = Math.min(answer, Math.abs(sum_popul(group1.get(i)) - sum_popul(group2.get(i))));
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void partition(int[] numbers, int index, List<Integer> current1, List<Integer> current2,
                          List<List<Integer>> group1, List<List<Integer>> group2) {
        if (index == numbers.length) {
            if (!current1.isEmpty() && !current2.isEmpty()) {
                group1.add(new ArrayList<>(current1));
                group2.add(new ArrayList<>(current2));
            }
            return;
        }

        current1.add(numbers[index]);
        partition(numbers, index + 1, current1, current2, group1, group2);
        current1.remove(current1.size() - 1);

        current2.add(numbers[index]);
        partition(numbers, index + 1, current1, current2, group1, group2);
        current2.remove(current2.size() - 1);
    }

    // 선거구 인구수 합
    static int sum_popul(List<Integer> number) {
        int total = 0;

        for (int num : number) {
            total += population[num];
        }
        
        return total;
    }

    // 구역 연결 여부 확인
    static boolean is_connected(List<Integer> number) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> dq = new ArrayDeque<>();
        int start = number.get(0);
        dq.offer(start); visited.add(start);

        while (!dq.isEmpty()) {
            int current = dq.poll();

            for (int next : graph.get(current)) {
                if (number.contains(next) && !visited.contains(next)) {
                    dq.offer(next);
                    visited.add(next);
                }
            }
        }
        return visited.size() == number.size();
    }
}
