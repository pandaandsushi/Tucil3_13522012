import java.util.*;
public class Ucs {
    public static List<String> findUCS(String start, String end, Dict dictionary) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost()));
        Set<String> visited = new HashSet<>();
        // enqueue first Node
        queue.offer(new Node(start, null, 0));
        while (!queue.isEmpty()) {
            // dequeue
            Node currentNode = queue.poll();

            // found
            if ((currentNode.getWord()).equals(end)){
                return backtrackPath(currentNode);
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
        return null;
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
