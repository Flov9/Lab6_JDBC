import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
1. Create a server object
2. Obtain a reference to the RMI registry
3. Register the object in the registry
 */
public class RegisterWithRMIServer {
    public static void main(String[] args) {
        try {
            IStudentServer obj = new IStudentServerImplementation();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("IStudentServerImplementation", obj);
            System.out.println("Student server " + obj + " registered");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
