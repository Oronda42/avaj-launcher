package src.exception;

public class AvajEx extends Exception {

    public static void notice() {
        System.out.println("First Line : ");
        System.out.println("\t<uint>\t(weatherChangeCount)");
        System.out.println("All the other lines : ");
        System.out.println("\t(Type) (Name) (Longitude) (Latitude) (Height)");
        System.out.println("\t<String> <String> <uint> <uint> <uint>");
        System.out.println(" types of aircraft available: Baloon, Helicopter, Baloon.\n");

    }
    public AvajEx(String msg)  {
		super(msg);
	}

}
