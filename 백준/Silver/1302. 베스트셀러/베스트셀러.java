import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, Integer> count;
    static int N;

    static class Word implements Comparable<Word> {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(Word o) {
            if(o.count != this.count) {
                return o.count - this.count;
            }
            return this.word.compareTo(o.word);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = new HashMap<>();

        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            String title = br.readLine();
            if(!count.containsKey(title)){
                count.put(title, 1);
            } else {
                count.put(title, count.get(title) + 1);
            }
        }

        PriorityQueue<Word> pq = new PriorityQueue<>();
        for(String word : count.keySet()){
            pq.add(new Word(word, count.get(word)));
        }
        System.out.println(pq.peek().word);
    }
}