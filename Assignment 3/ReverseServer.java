import ReverseApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class ReverseServer {

	public static void main(String args[]) {

		try {
			//System.out.println("Enter String : ");
			ORB orb = ORB.init(args,null);

			//resolve reference to the root POA object
			ReverseServant reverseRef = new ReverseServant();
			orb.connect(reverseRef);

			//CORBA object ref to register the new corba object in naming context
			org.omg.CORBA.Object objRef = 
				orb.resolve_initial_references("NamingService");

			NamingContext ncRef = NamingContextHelper.narrow(objRef);	

			NameComponent nc = new NameComponent("Reverse","");
			NameComponent path[] = {nc};
			ncRef.rebind(path, reverseRef);

			java.lang.Object sync = new java.lang.Object();
			synchronized(sync) {
				sync.wait();
			}

		}catch(Exception e) {
			e.printStackTrace(System.out);
		}

	}
}

class ReverseServant extends _ReverseImplBase {

	public String reverseString(String str) {
		StringBuilder sb = new StringBuilder(str);  
    	sb.reverse();  
	System.out.println("Reversed string is : "+sb.toString());
    	return sb.toString();
	}
}