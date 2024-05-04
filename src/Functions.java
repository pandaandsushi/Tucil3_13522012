import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Functions {
    public static List<String> generateNeighbors(String word, Dict dictionary){
        List<String> neighbors = new ArrayList<>();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char originalChar = charArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    charArray[i] = c;
                    String neighbor = new String(charArray).toUpperCase();
                    
                    // Check if the neighbor is in the dictionary and not equal to the original word
                    if (dictionary.containsWord(neighbor) && !(neighbor.toUpperCase()).equals(word.toUpperCase())) {
                        neighbors.add(neighbor);
                    }
                }
            }
            
            charArray[i] = originalChar;
        }
        return neighbors;
    }

    public static void printPrioQueue(PriorityQueue<Node> x){
        for (Node node : x) {
            System.out.println(node.getWord().toString());
        }
    }
}
