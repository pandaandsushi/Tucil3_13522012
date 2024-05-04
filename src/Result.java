import java.util.List;

public class Result {
    private List<String> resultlist;
    private int numofcheckednodes;
    private double executiontime;
    public Result(List<String> resultlList, int numofcheckednodes, double elapsedTimeInSeconds){
        this.resultlist = resultlList;
        this.numofcheckednodes = numofcheckednodes;
        this.executiontime = elapsedTimeInSeconds;
    }

    public int getnumofcheckednodes() {
        return numofcheckednodes;
    }

    public double getexecutiontime() {
        return executiontime;
    }

    public List<String> getResultlist() {
        return resultlist;
    }
}
