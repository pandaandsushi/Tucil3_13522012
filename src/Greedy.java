import java.util.*;
public class Greedy {
    public static List<String> findGreedy(String start, String end, Dict dictionary){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost()));
        ArrayList<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Node initial = new Node(start, null, 0);
        queue.offer(initial);
        result.add(initial.getWord());
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            // DEBUG
            System.out.print("Sekarang kita check Node:");
            System.out.println(currentNode.getWord());
            // System.out.println(currentNode.getParent().getWord());
            System.out.println("");
            
            // found
            if ((currentNode.getWord()).equals(end)){
                result.add(end);
                return result;
            }

            // not found continue, add to queue from currentNode
            for (String neighbor : Functions.generateNeighbors(currentNode.getWord(),dictionary)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    int cost = currentNode.getCost() + 1;
                    queue.offer(new Node(neighbor, currentNode, cost));
                }
            }
        }
        return result;
    }
}
