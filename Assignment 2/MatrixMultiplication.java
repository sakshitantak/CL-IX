import mpi.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner; 

//Class for matrix multiplication
public class MatrixMultiplication {

	//Function to initialize matrix
	public static void initializeMatrix(int[][] A, int size) {

		int temp = 1;

		//Generate random integers in Matrix
		// Random rand = new Random();
		// for(int i=0;i<size;i++){
		// 	for(int j=0;j<size;j++){
		// 		int t = rand.nextInt(20);
		// 		A[i][j] = t;
		// 	}
		// }

		//Generate matrix with elements between 1 and 10 serially
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				
				A[i][j] = temp;
				temp++;
				
				if(temp == 10) 
					temp = 1;
			}
		}

		//NOTE : It is recommended that std in and out are not used in MPI programs and rather files are used to read anything. So we generate matrix and not accept it.

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

			//Initialize MPI execution environment to make sure result of any concurrent
			//process is not influenced by intermediate result of any other process
			MPI.Init(args);

			//Set rank of processes on communicator
			int rank = MPI.COMM_WORLD.Rank();
			
			//accept size for communicator, which is the number of processes to be created in the communicator
			//In our case for matrix multiplication, no. of process in communicator = size of matrix
			int size = MPI.COMM_WORLD.Size();

			//declare matrices
			int[][] A = new int[size][size];
			int[][] B = new int[size][size];

			//initialize matrix B
			initializeMatrix(B,size);

			//declare array variable for storing one row at a time of matrix A 
			int[] A_row = new int[size];
			//declare array variable to store one row that is computed by one process
			int[] ans = new int[size];

			//declare a matrix to return as final multiplied matrix
			int[][] result = new int[size][size];
	
			//time count variables
			double t1 = 0;
			double t2 = 0;
			long startTime = 0;
			

			if(rank == 0) {
				startTime = System.currentTimeMillis();

				//initialize matrix A
				initializeMatrix(A,size);
				
				//store the first row of A in A_row
				for(int i=0;i<size;i++) {
					A_row[i] = A[0][i];
				}

				//send A[i] objects over communicator using Send()
				//A[i] -> reference to buffer A_row
				///0 -> rank of process you want to send it to
				//size -> no. of elements in buffer to be sent to the destination
				//MPI.INT -> data type of data in buffer
				
				for(int i=1;i<size;i++) {
					MPI.COMM_WORLD.Send(A[i],0,size,MPI.INT,i,0);
				}
			}
			else 
				MPI.COMM_WORLD.Recv(A_row,0,size,MPI.INT,0,0); 

			//Perform row wise multiplication
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
				
				System.out.println("Matrix A");
				printMatrix(A, size);

				System.out.println("Matrix B");
				printMatrix(B, size);

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