package Servers;

public class Messaging {
	
	public static Object ErrorMessage (Object errorMessage) {
		if (errorMessage != null) {
			return errorMessage;
		}
		else {
			return "";
		}
	}
	
}
