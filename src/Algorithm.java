import java.util.*;
public class Algorithm {
    public static Result findPath(String start, String end, Dict dictionary, String algorithmtype){
        long startTime = System.nanoTime();
        Result r1;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost()));
        Set<String> visited = new HashSet<>();
        int numofcheckednodes = 0;
        queue.offer(new Node(start, null, 0));
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            numofcheckednodes++;
            
            // found
            if ((currentNode.getWord()).equals(end)){
                long endTime = System.nanoTime();
                double elapsedTimeInSeconds = (endTime - startTime) / 1e9;
                r1 = new Result(Functions.backtrackPath(currentNode),numofcheckednodes,elapsedTimeInSeconds);
                return r1;
            }

            // not found continue, add to queue from currentNode
            for (String neighbor : Functions.generateNeighbors(currentNode.getWord(),dictionary)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    int cost = 0;
                    if(algorithmtype.equals("GBFS")){
                        cost = Functions.hamming_distance(neighbor,end) ;
                    }
                    else if (algorithmtype.equals("UCS")){
                        cost = currentNode.getCost() + 1;
                    }
                    else if (algorithmtype.equals("Astar")){
                        cost = Functions.hamming_distance(neighbor,end) + currentNode.getCost() + 1;
                    }
                    queue.offer(new Node(neighbor, currentNode, cost));
                }
            }
        }
        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1e9;
        r1 = new Result(null, numofcheckednodes, elapsedTimeInSeconds);
        return r1;
    }
}
