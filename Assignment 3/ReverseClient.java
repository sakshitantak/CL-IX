import ReverseApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class ReverseClient {
	public static void main(String args[]) {
		try {
			System.out.println("Enter String : ");
			ORB orb = ORB.init(args,null);

			org.omg.CORBA.Object objRef = 
				orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			NameComponent nc = new NameComponent("Reverse","");
			NameComponent path[] = {nc};
			Reverse reverseRef = ReverseHelper.narrow(ncRef.resolve(path));

			Scanner scanner = new Scanner(System.in);  

    		String str1 = scanner.nextLine();  

			String str = reverseRef.reverseString(str1);
			System.out.println(str);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}