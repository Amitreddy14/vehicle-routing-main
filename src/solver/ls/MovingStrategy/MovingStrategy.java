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

    default boolean isRouteFeasible(List<Integer> route, VRPLocalSearch instance) {
        // the route should begin and end at the depot (and have at least 2 elements)
        if (route.size() < 2)
            return false;
        if (route.get(0) != 0 || route.get(route.size() - 1) != 0)
            return false;

        // no customer in the route is visited more than once
        int[] seenCustomers = new int[instance.getNumCustomers()];
        for (int i = 1; i < route.size() - 1; i++) {
            int customer = route.get(i);
            if (seenCustomers[customer] == 1)
                return false;
            seenCustomers[customer] = 1;
        }
        