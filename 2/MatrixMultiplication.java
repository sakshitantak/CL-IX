import mpi.*;
import java.util.Arrays; 

//Class for matrix multiplication
public class MatrixMultiplication {

	//Function to initialize matrix
	public static void initializeMatrix(int[][] A,int size) {

		int temp = 1;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				
				A[i][j] = temp;
				temp++;
				
				if(temp == 10) 
					temp = 1;
			}
		}
	}

	//Function to print the matrix
	public static void printMatrix(int[][] A,int size) {

		for(int i=0;i<size;i++) {
			System.out.println(Arrays.toString(A[i]) + "\n");
		}

	}
 
	//Main
	public static void main(String args[]) throws MPIException {
		try {

			//Initialize MPI
			MPI.Init(args);

			//Read rank and size of current process on communicator
			int rank = MPI.COMM_WORLD.Rank();
			int size = MPI.COMM_WORLD.Size();

			//declare matrices
			int[][] A = new int[size][size];
			int[][] B = new int[size][size];

			//initialize matrix B
			initializeMatrix(B,size);

			int[] A_row = new int[size];
			int[] ans = new int[size];
			int[][] result = new int[size][size];
	
			//time count variables
			double t1 = 0;
			double t2 = 0;
			long startTime = 0;
			

			if(rank == 0) {
				startTime = System.currentTimeMillis();
				//initialize matrix A
				initializeMatrix(A,size);

				for(int i=0;i<size;i++) {
					A_row[i] = A[0][i];
				}

				//send A[i] objects over communicator
				//A[i] -> reference to buffer A_row
				///0 -> rank of process you want to send it to
				//size -> no. of elements n buffer to be sent to the destination
				//MPI.INT -> data type of data in buffer
				
				for(int i=1;i<size;i++) {
					MPI.COMM_WORLD.Send(A[i],0,size,MPI.INT,i,0);
				}
			}
			else 
				MPI.COMM_WORLD.Recv(A_row,0,size,MPI.INT,0,0); 

			//System.out.println(Arrays.toString(A_row) + "\n");

			for(int i=0;i<size;i++) {

				int res = 0;

				for(int j=0;j<size;j++) {

					int temp = A_row[j] * B[j][i];
					res = res + temp;
				}

				ans[i] = res;

			}

			if(rank != 0) {
				MPI.COMM_WORLD.Send(ans,0,size,MPI.INT,0,0);
			}
			else {

				result[0] = ans;

				for(int i=1;i<size;i++) {
					MPI.COMM_WORLD.Recv(result[i],0,size,MPI.INT,i,0);
				}

				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Time elapsed => " + estimatedTime);
				
				System.out.println("Result => \n");
				printMatrix(result,size);
			}

			MPI.Finalize();
		}
		catch (MPIException exp) {
			System.out.println(exp);
		}

	}

}