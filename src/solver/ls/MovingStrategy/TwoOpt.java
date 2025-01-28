package solver.ls.MovingStrategy;

import solver.ls.Solution;
import solver.ls.VRPLocalSearch;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

// NOTE: reference: https://www.ncbi.nlm.nih.gov/pmc/articles/PMC8482434/
public class TwoOpt implements MovingStrategy {
    // example: 0 -> 5 -> 1 -> 2 -> 3 -> 4 -> 6 -> 0
    // might become: 0 -> 5 -> 1 -> 3 -> 2 -> 4 -> 6 -> 0 (if we remove the cross along the arc 2 -> 3)
    // (2 and 3 here are randomly picked)

    private final Random random = new Random(550);

    private int pickCustomerFromRoute(List<Integer> route) {
        // add 1 to result to prevent picking customer at index 0 (avoid depot)
        // subtract 2 at end -- subtract 1 to avoid picking last index (depot), and
        //   subtract another 1 to account for adding 1 in the beginning
        return 1 + random.nextInt(route.size() - 2);
    }
    