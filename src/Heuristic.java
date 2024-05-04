// Heuristic function - estimating cost from current node to target node
public class Heuristic {
    public static int hamming_distance(String word, String goal){
        int dist = 0;
        for (int i = 0; i<word.length(); i++){
            if (word.charAt(i) != goal.charAt(i)) {
                dist++;
            }
        }
        return dist;
    }
}