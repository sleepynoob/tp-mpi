import mpi.*;

public class Receiver {
    private static final int TAG = 0;

    public void receive() throws MPIException {
        int rank = MPI.COMM_WORLD.getRank();
        int size = MPI.COMM_WORLD.getSize();
        int sum = 0;

        while (true) {
            Status status = MPI.COMM_WORLD.recv(MPI.ANY_SOURCE, TAG);
            int n = status.getTag();
            sum += n;

            System.out.println("signal(" + n + ") received from A[" + status.getSource() + "]");
            System.out.println("sum = " + sum);
        }
    }
}

