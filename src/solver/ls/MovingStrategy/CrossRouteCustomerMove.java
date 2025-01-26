package solver.ls.MovingStrategy;

import solver.ls.Solution;
import solver.ls.VRPLocalSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Randomly picks a customer. Moves them to every other possible route (in current
// position) --> this is the entire neighborhood
public class CrossRouteCustomerMove implements MovingStrategy {
    private final Random random = new Random(350);

    private int pickRandomVehicle(Solution currentSolution) {
        // need to ensure that the vehicle picked is serving at least 1 customer
        int vehicleIdx;
        do {
            vehicleIdx = random.nextInt(currentSolution.routes.size());
        } while (currentSolution.routes.get(vehicleIdx).size() <= 2);
        return vehicleIdx;
    }