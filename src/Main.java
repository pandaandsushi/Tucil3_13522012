import java.util.*;
public class Main{
    public static void printOutSol(List<String> solution){
        if (solution != null) {
            System.out.println("Solution found: ");
            solution.forEach(System.out::println);
            System.out.println("The shortest solution length: " + solution.size());
        } else {
            System.out.println("No solution found.");
        }
    }
    public static void main(String[] args){
        boolean stop = false;
        while (!stop) {
            System.out.println("Welcome to Word Ladder Solver ^^");
            Dict wordDictionary = new Dict("Dictionary.txt");
            Scanner scanner = new Scanner(System.in);
            String start;
            String finish;
            while (true) {
                System.out.println("-----------------------------------------");
                System.out.println("Enter the start and finish word");
                System.out.print("Start: ");
                start = scanner.next().toUpperCase();
                System.out.print("Finish: ");
                finish = scanner.next().toUpperCase();
                // testing in here
                // List<String> neighbors = Ucs.generateNeighbors(start,wordDictionary);
                // neighbors.forEach(System.out::println);
                if (start.length()==finish.length()){
                    if (start.equals(finish)){
                        System.out.println("Both cannot be the same word");
                    }
                    else if (!wordDictionary.containsWord(finish)&&!wordDictionary.containsWord(start)){
                        System.out.println("Both words do not exist in the dictionary.");
                    }
                    else if (!wordDictionary.containsWord(start)){
                        System.out.println("Start word does not exist in the dictionary.");
                    }
                    else if (!wordDictionary.containsWord(finish)){
                        System.out.println("Finish word does not exist in the dictionary.");
                    }
                    else{
                        break;
                    }
                }
                else{
                    System.out.println("Both needs to be the same length.");
                }
            }
            System.out.println("");
            while (true) {
                System.out.println("Choose your algorithm to solve:");
                System.out.println("1. Uniform Cost Search");
                System.out.println("2. Greedy Best First Search");
                System.out.println("3. A*");
                System.out.println("Enter `0` to quit.");
                System.out.print(">> ");
                String input = scanner.next();
                if(input.equals("1") || input.equals("2") || input.equals("3")){
                    Result res;
                    if (input.equals("1")){
                        System.out.println("You have chosen UCS algo!");
                        res = Ucs.findUCS(start, finish, wordDictionary);
                        printOutSol(res.getResultlist());
                        System.out.println("Num of nodes checked: " + res.getnumofcheckednodes());
                        System.out.println("Algorithm execution time: " + res.getexecutiontime() + " seconds");
                        break;
                    }
                    else if (input.equals("2")){
                        System.out.println("You have chosen Greedy algo!");
                        res = Greedy.findGreedy(start, finish, wordDictionary);
                        printOutSol(res.getResultlist());
                        System.out.println("Num of nodes checked: " + res.getnumofcheckednodes());
                        System.out.println("Algorithm execution time: " + res.getexecutiontime() + " seconds");
                        break;
                    }
                    else if (input.equals("3")){
                        System.out.println("You have chosen A* algo!");
                        // res = Astar.findAstar(start, finish, wordDictionary);
                        // printOutSol(res.getResultlist());
                        // System.out.println("Num of nodes checked: " + res.getnumofcheckednodes());
                        // System.out.println("Algorithm execution time: " + res.getexecutiontime() + " seconds");
                        break;
                    }
                }
                else if (input.equals("0")){
                    System.out.println("Quitting...");
                    break;
                }
                else{
                    System.out.println("Wrong input. Retrying...");
                    continue;
                }
                
                
            }
            while (true){
                System.out.println("Do you want to search for another (Y/N)?");
                System.out.print(">> ");
                String ans = scanner.next();
                if (ans.equals("N")){
                    stop = true;
                    System.out.println("Goodbye!");
                    scanner.close();
                    break;
                }
                else if (ans.equals("Y")){
                    break;
                }
                else if (!ans.equals("Y")){
                    System.out.println("Wrong input");
                }
            }
        }
    }
}