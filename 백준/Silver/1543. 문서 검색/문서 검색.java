import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String doc = br.readLine();
        String target = br.readLine();
        
        int count = 0;
        int index = 0;

        while (index <= doc.length() - target.length()) {
            if (doc.substring(index, index + target.length()).equals(target)) {
                count++;
                index += target.length();
            } else {
                index++;
            }
        }

        System.out.println(count);
    }
}
