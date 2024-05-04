import java.util.*;
public class Greedy {
    public static Result findGreedy(String start, String end, Dict dictionary){
        long startTime = System.nanoTime(); // Record start time
        Result r1;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost()));
        ArrayList<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        int numofcheckednodes = 0;
        queue.offer(new Node(start, null, 0));
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            numofcheckednodes++;
            result.add(currentNode.getWord());
            
            // DEBUG
            System.out.print("Sekarang kita check Node:");
            System.out.println(currentNode.getWord());

            // found
            if ((currentNode.getWord()).equals(end)){
                long endTime = System.nanoTime(); // Record end time
                double elapsedTimeInSeconds = (endTime - startTime) / 1e9; // Calculate elapsed time in seconds
                r1 = new Result(Ucs.backtrackPath(currentNode),numofcheckednodes,elapsedTimeInSeconds);
                return r1;
            }

            // not found continue, add to queue from currentNode
            for (String neighbor : Functions.generateNeighbors(currentNode.getWord(),dictionary)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    int cost = Heuristic.hamming_distance(neighbor,end) ;
                    queue.offer(new Node(neighbor, currentNode, cost));
                }
            }
        }
        long endTime = System.nanoTime(); // Record end time
        double elapsedTimeInSeconds = (endTime - startTime) / 1e9; // Calculate elapsed time in seconds
        r1 = new Result(null, numofcheckednodes, elapsedTimeInSeconds);
        return r1;
    }
}
