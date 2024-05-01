import java.util.*;
import java.io.*;
public class Dict {
    private Set<String> dictionary;
    public Dict(String filename){
        dictionary = new HashSet<>();
        loadWords(filename);
    }
    private void loadWords(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean containsWord(String word) {
        return dictionary.contains(word.toUpperCase());
    }
}

