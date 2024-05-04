import java.util.*;
public class Ucs {
    public static Result findUCS(String start, String end, Dict dictionary) {
        long startTime = System.nanoTime(); // Record start time
        Result r1;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost()));
        Set<String> visited = new HashSet<>();
        int numofcheckednodes = 0;
        // enqueue first Node
        queue.offer(new Node(start, null, 0));
        while (!queue.isEmpty()) {
            // dequeue
            Node currentNode = queue.poll();
            numofcheckednodes++;
            // found
            if ((currentNode.getWord()).equals(end)){
                long endTime = System.nanoTime(); // Record end time
                double elapsedTimeInSeconds = (endTime - startTime) / 1e9; // Calculate elapsed time in seconds
                
                r1 = new Result(backtrackPath(currentNode),numofcheckednodes,elapsedTimeInSeconds);
                return r1;

            }

            // not found continue, add to queue by cost
            for (String neighbor : Functions.generateNeighbors(currentNode.getWord(),dictionary)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    int cost = currentNode.getCost() + 1;
                    queue.offer(new Node(neighbor, currentNode, cost));
                }
            }
        }
        long endTime = System.nanoTime(); // Record end time
        double elapsedTimeInSeconds = (endTime - startTime) / 1e9; // Calculate elapsed time in seconds
        r1 = new Result(null, numofcheckednodes, elapsedTimeInSeconds);
        return r1;
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
