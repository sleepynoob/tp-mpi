import mpi.*;

public class WorkerProcess {
    private static final int TAG_PI = 0;
    private static final int TAG_RESULT = 1;

    public void performMonteCarloSimulation() throws MPIException {
        int n;

        // Receive n from the master process
        Status status = MPI.COMM_WORLD.Recv(new int[1], 0, 1, MPI.INT, 0, TAG_PI);
        n = status.Get_elements(MPI.INT)[0];

        int t = 0; // Number of iterations performed
        int c = 0; // Number of points inside the circle

        while (true) {
            // Generate random points and count those inside the circle
            for (int i = 0; i < n; i++) {
                double x = Math.random();
                double y = Math.random();
                if (x * x + y * y <= 1) {
                    c++;
                }
                t++;
            }

            // Send the result back to the master process
            MPI.COMM_WORLD.Send(new double[]{c}, 0, 1, MPI.DOUBLE, 0, TAG_RESULT);
        }
    }
}
