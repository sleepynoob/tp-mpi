import mpi.*;

public class Main {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.getRank();
        int size = MPI.COMM_WORLD.getSize();

        if (rank >= size - 1) {
            Receiver receiver = new Receiver();
            receiver.receive();
        } else {
            Emitter emitter = new Emitter();
            emitter.sendValues();
        }

        MPI.Finalize();
    }
}

