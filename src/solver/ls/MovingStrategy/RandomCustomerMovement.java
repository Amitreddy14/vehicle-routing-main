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
