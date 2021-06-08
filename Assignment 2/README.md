Install MPJ Express
1. Download MPJ Express and unpack it
2. Assuming mpjexpress is in 'C:\mpj', add a variable 'MPJ_HOME' in Environment variables as 'C:\mpj'
3. Append the Path variable with 'C:\mpj\bin'

For more detailed instructions, check http://mpj-express.org/docs/guides/windowsguide.pdf

How to run MPJ Express code
1. Compile with command 'javac -cp .;%MPJ_HOME%/lib/mpj.jar MatrixMultiplication.java'
2. After successful compilation, run it with command mpjrun.bat -np <number_of_thread> MatrixMultiplication