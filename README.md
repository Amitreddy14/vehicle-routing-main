# vehicle-routing

A simulated-annealing based local search algorithm for solving the NP-complete Capacitated Vehicle Routing Problem (CVRP), built in Java.

> CVRP is a combinatorial optimization problem in which one attempts to identify optimal routes for a fleet of vehicles to serve a set of customers where:
> 1. Each customer has a known demand which must be completely met
> 2. Each customer is served entirely by a single vehicle
> 3. Each vehicle has a limited capacity in terms of the total demand it can carry (so the total demand served by any vehicle can't exceed this capacity)
> 4. Each vehicle starts out at a depot (warehouse) and makes a single trip: on this trip (whose route is to be identified), it serves a subset of the customers, and then returns to the depot
>
> The goal of the problem is to optimize for distance: while ensuring that all of the aforementioned constraints are met, we want to minimize the total distance travelled by the fleet of vehicles (in order to minimize travel-related costs).

For information on how we built our CVRP solver, optimizations we made, and more, look through our [presentation](https://github.com/Amitreddy14/vehicle-routing-main/blob/main/vehicle-routing-main/Presentation.pdf)!

All the code for our solver is within the [`src/solver/ls/`](https://github.com/Amitreddy14/vehicle-routing-main/tree/main/vehicle-routing-main/src/solver/ls) subdirectory.

## Results

While there is certainly further work that could be done to improve our solver, we were quite happy with the results we achieved. For reference, our solver outperformed Google OR-Tools' CVRP solver on all instances in the `input/` directory after running for 5 minutes.

You can see the solver's results after running on these instances in the [`results.log`](https://github.com/Amitreddy14/vehicle-routing-main/blob/main/vehicle-routing-main/results.log) file, along with an explanation of how to interpret the result in a further section of this README.

## Usage

In order to construct the initial solution we use IBM CPLEX's Constraint Programming solver (we then optimize this using our local search routine). Therefore, in order to run our solver, you would need a local installation of CPLEX.

Once this is done, you initially need to compile the code. This can be done by running
```bash
./compile.sh
```
Within the `compile.sh` script, you would need to change the filepath for the CPLEX installation to reflect the path to your local installation of CPLEX. The `compile.sh` file contains the path on Brown University's department machines.

Now, in order to run the (compiled) solver on a particular instance, run
```bash
./run.sh <input-file>
```
* For example, to run the solver on the instance in the `input/16_5_1.vrp` file, you would run
  ```bash
  ./run.sh input/16_5_1.vrp
  ```
Like the `compile.sh` script, even in the `run.sh` script you would need to change the filepath for the CPLEX installation to reflect the path to your local installation of CPLEX. The `run.sh` file contains the path for Brown University's department machines.

If you want to run the solver on all the instance files in a particular directory, you can run
```bash
./runAll.sh <input-folder> <timeout (in seconds)> <output-filename>
```
* For example, to generate the [`results.log`](https://github.com/Amitreddy14/vehicle-routing-main/blob/main/vehicle-routing-main/results.log) file containing solver results on all the instances in the `input/` directory with a 300 second timeout (per instance), we ran
  ```bash
  ./runAll.sh input/ 300 results.log
  ```

> The `compileLocal.sh` and `runLocal.sh` scripts are variants of the `compile.sh` and `run.sh` scripts with CPLEX installation filepaths modified to represent what they might look like after installing on an Apple device. These can serve as an example for how one needs to modify the filepaths in the scripts to compile and run the solver's code locally.

### Input Format

Each instance is expected to be found in its own file. The structure of this file should be
```
<num-customers> <num-vehicles> <vehicle-capacity>
<customer-0-demand> <customer-0-x-coord> <customer-0-y-coord>
...
<customer-n-demand> <customer-n-x-coord> <customer-n-y-coord>
```
* Here, `customer-0` represents the depot. Therefore, `customer-0`'s demand is always 0, and the only relevant information on this line is the x and y coordinates of the depot.
* You can see an example of an input file in the [`toy_inputs/5_4_10.vrp`](https://github.com/Amitreddy14/vehicle-routing-main/blob/main/toy_inputs/5_4_10.vrp) file

### Output Format

The final output of the solver after running on a single instance is in the following format
```
{"Instance": <instance-filename>, "Time": <time-spent-running-on-instance>, "Result": <solver-result>, "Solution": <solution>}
```
* `<solver-result>` represents the total distance traveled by all vehicles in the fleet in the most optimal (feasible) solution found by the solver.
* `<solution>` is a string used to represent the routes generated by the solver.
  * The first value is `0` if the value is non-optimal (no proof of it being the most optimal possible solution), and `1` if guaranteed to be the optimal value. Since our solver uses local search (which is an incomplete method), we don't have proofs of optimality, so this value is always `0` in our outputs.
  * After this, follows `<num-vehicles>` routes. Each route begins and ends in `0` (at the depot) -- this is how individual routes can be extracted from the `<solution>` string. Between the `0`s, is a sequence of numbers corresponding to the customers visited by this vehicle in the order in which they are visited. For example, the route `0 1 3 2 0` means that the vehicle starts at the depot, then goes to customer 1, then customer 3, then customer 2, and then back to the depot.
* You can see the [`results.log`](https://github.com/Amitreddy14/vehicle-routing-main/blob/main/results.log) file to see what the outputs look like for all the instances in the `input/` directory.


