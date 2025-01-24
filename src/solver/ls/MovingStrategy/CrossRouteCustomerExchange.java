package solver.ls.MovingStrategy;

import solver.ls.Solution;
import solver.ls.VRPLocalSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Randomly picks two customers in different routes and then exchanges their position.
// For the whole neighborhood, this repeats the process above numVehicles/2 times
public class CrossRouteCustomerExchange implements MovingStrategy {

    private final Random random = new Random(250);

    public Solution getSingleNeighbor(Solution currentSolution, VRPLocalSearch instance) {
        int numVehicles = currentSolution.routes.size();
        final int NUM_TRIES = 5;
        List<Integer> emptyList = new ArrayList<>();

        Solution newSolution = currentSolution.copy();