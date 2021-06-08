import java.rmi.Remote;
import java.rmi.RemoteException;  
 
public interface AreaClass extends Remote {  
   double findArea(int code,int length) throws RemoteException;
   double findArea(int code,int height, int width) throws RemoteException;

}