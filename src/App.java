import allocation.allocations.BestFit;
import allocation.allocations.CircularFit;
import allocation.allocations.FirstFit;
import allocation.allocations.WorstFit;

public class App {
    public static void main(String[] args) throws Exception {
        BestFit bestFit = new BestFit();
        WorstFit worstFit = new WorstFit();
        FirstFit firstFit = new FirstFit();
        CircularFit circularFit = new CircularFit();

        firstFit.execute();
        circularFit.execute();
        bestFit.execute();
        worstFit.execute();
    }
}
