import mpi.*;

public class Main {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        if (rank == 0) {
            MasterProcess master = new MasterProcess(size - 1);
            master.calculatePi();
        } else {
            WorkerProcess worker = new WorkerProcess();
            worker.performMonteCarloSimulation();
        }

        MPI.Finalize();
    }
}
