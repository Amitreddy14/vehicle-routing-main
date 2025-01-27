package solver.ls.MovingStrategy;

import solver.ls.Solution;
import solver.ls.VRPLocalSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This approach picks a random customer to move. It then defines the neighborhood as
 * all possible places they could be moved to within their own route (change the order
 * in which they are visited within their route), and also moving them to other routes
 */
public class RandomCustomerMovement implements MovingStrategy {

    private final Random random = new Random(450);

    private int pickRandomVehicle(Solution currentSolution) {
        // need to ensure that the vehicle picked is serving at least 1 customer
        int vehicleIdx;
        do {
            vehicleIdx = random.nextInt(currentSolution.routes.size());
        } while (currentSolution.routes.get(vehicleIdx).size() <= 2);
        return vehicleIdx;
    }

    private int pickRandomCustomerFromVehicleRoute(Solution currentSolution, int vehicleIdx) {
        // add 1 to result to prevent picking customer at index 0 (avoid depot)
        // subtract 2 at end -- subtract 1 to avoid picking last index (depot), and
        //   subtract another 1 to account for adding 1 in the beginning
        return 1 + random.nextInt(currentSolution.routes.get(vehicleIdx).size() - 2);
    }

    /**
     * Picks a random customer, and moves them to a random position in some route
     * (could be the same route in which they already were)
     *
     * @param currentSolution: the solution from which we are moving
     * @return the move that is made to get to the next solution
     */
    public Solution getSingleNeighbor(Solution currentSolution, VRPLocalSearch instance) {
        Solution newSolution = currentSolution.copy();

        // pick a random customer to move
        int sourceRouteIdx = pickRandomVehicle(currentSolution);
        if (currentSolution.routes.get(sourceRouteIdx).size() <= 2)
            return newSolution;
        int customerSourceIdx = pickRandomCustomerFromVehicleRoute(currentSolution, sourceRouteIdx); 