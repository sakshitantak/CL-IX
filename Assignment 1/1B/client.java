import java.rmi.*;  

public class client{  

	public static void main(String args[]){  
		try {  
			AreaClassImpl_Stub stub = (AreaClassImpl_Stub)Naming.lookup("rmi://localhost:5000/test");  
			System.out.println("Area of square with length = 5 : " + stub.findArea(1,5));
			System.out.println("Area of circle with radius = 14 : " + stub.findArea(2,14));
			System.out.println("Area of rectangle with height = 5 and width = 6 : " + stub.findArea(1,5,6));
			System.out.println("Area of triangle with height = 5 width = 6 : " + stub.findArea(2,5,6));
		}catch(Exception e){
			System.out.println(e);
		}
	}
}