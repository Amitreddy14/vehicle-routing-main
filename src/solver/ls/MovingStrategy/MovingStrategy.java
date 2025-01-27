package solver.ls.MovingStrategy;

import solver.ls.Solution;
import solver.ls.VRPLocalSearch;

import java.util.ArrayList;
import java.util.List;

public interface MovingStrategy {

    Solution getSingleNeighbor(Solution currentSolution, VRPLocalSearch instance);

    default List<Solution> getNeighborhood(Solution currentSolution, VRPLocalSearch instance, int numNeighbors) {
        List<Solution> neighborhood = new ArrayList<>();
        for (int i = 0; i < numNeighbors; i++)
            neighborhood.add(getSingleNeighbor(currentSolution, instance));
        return neighborhood;
    }