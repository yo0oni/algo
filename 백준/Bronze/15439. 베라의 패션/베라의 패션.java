import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        str.append(N*(N-1));

        System.out.print(str);
    }
}