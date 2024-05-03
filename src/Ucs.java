import java.util.*;
public class Ucs {
    public static List<String> findUCS(String start, String end, Dict dictionary) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost()));
        Set<String> visited = new HashSet<>();
        // enqueue first Node
        queue.offer(new Node(start, null, 0));
        queue.forEach(System.out::println);
        while (!queue.isEmpty()) {
            // dequeue
            // DEBUG
            Node currentNode = queue.poll();
            System.out.println("OwOOwOOwOOwOOwOOwOOwO");
            System.out.print("Sekarang kita check Node:");
            System.out.println(currentNode.getWord());
            // System.out.println(currentNode.getParent().getWord());
            System.out.println("");
            // found
            if ((currentNode.getWord()).equals(end)){
                return backtrackPath(currentNode);
            }

            // not found continue, add to queue by cost
            for (String neighbor : generateNeighbors(currentNode.getWord(),dictionary)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    int cost = currentNode.getCost() + 1;
                    queue.offer(new Node(neighbor, currentNode, cost));
                }
            }
        }
        return null;
    }

    public static List<String> generateNeighbors(String word, Dict dictionary){
        List<String> neighbors = new ArrayList<>();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char originalChar = charArray[i];
            
            // Try replacing the current character with other letters
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
            
            // Restore the original character at this position for the next iteration
            charArray[i] = originalChar;
        }
        return neighbors;
    }

    public static void printPrioQueue(PriorityQueue<Node> x){
        for (Node node : x) {
            System.out.println(node.getWord().toString());
        }
    }
    
    public static List<String> backtrackPath(Node node){
        List<String> result = new ArrayList<>();
        Node currentNode = node;
        while (currentNode!=null) {
            result.add(0,currentNode.getWord());
            currentNode = currentNode.getParent();
        }
        return result;
    }

    
}
