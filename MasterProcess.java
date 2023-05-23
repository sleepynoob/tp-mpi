import mpi.*;

public class MasterProcess {
    private static final int TAG_PI = 0;
    private static final int TAG_RESULT = 1;

    private int numWorkers;

    public MasterProcess(int numWorkers) {
        this.numWorkers = numWorkers;
    }

    public void calculatePi() throws MPIException {
        int n = 1000000; // Number of iterations for each worker

        // Send n to each worker
        for (int i = 1; i <= numWorkers; i++) {
            MPI.COMM_WORLD.Send(new int[]{n}, 0, 1, MPI.INT, i, TAG_PI);
        }

        double totalPointsInCircle = 0;

        // Receive results from each worker and accumulate
        for (int i = 1; i <= numWorkers; i++) {
            Status status = MPI.COMM_WORLD.Recv(new double[1], 0, 1, MPI.DOUBLE, i, TAG_RESULT);
            totalPointsInCircle += status.Get_elements(MPI.DOUBLE)[0];
        }

        double pi = 4 * totalPointsInCircle / (n * numWorkers);
        System.out.println("Approximation of pi: " + pi);
    }
}
