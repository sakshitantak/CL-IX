import java.rmi.*;  
import java.rmi.registry.*;  

public class server {  

	public static void main(String args[]){  
		try{  
			AreaClassImpl stub = new AreaClassImpl();  
			Naming.rebind("rmi://localhost:5000/test",stub);  
		}catch(Exception e){
			System.out.println(e);
		}  
	}  
}  