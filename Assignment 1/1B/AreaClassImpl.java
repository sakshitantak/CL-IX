import java.rmi.*;  
import java.rmi.server.*;

// Implementing the remote interface 
public class AreaClassImpl extends UnicastRemoteObject implements AreaClass {  
   
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	AreaClassImpl() throws RemoteException {
		super();
	}

   // Implementing the interface method 
  	public double findArea(int code,int length) {

  		switch(code) {
  			case 1: 
  				return length*length;
  			case 2:
  				return 3.14*length*length;
  		}

  		return 0;

  	}

  	public double findArea(int code,int height,int width) {

  		switch(code) {
  			case 1: 
  				return height*width;
  			case 2:
  				return 0.5*height*width;
  		}

  		return 0;

  	}


} 