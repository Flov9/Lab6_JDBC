import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class IStudentServerImplementation extends UnicastRemoteObject implements IStudentServer {
    private PreparedStatement pStatement;

    public IStudentServerImplementation() throws RemoteException {
        initializeDB();
    }

    public IStudentServerImplementation(int port) throws RemoteException {
        super(port);
        initializeDB();
    }

    protected void initializeDB() {
        try {
            // Load the JDBC driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection
                    ("jdbc:derby:javabook;user=scott;password=tiger");
            System.out.println("Database connected");

            String queryString = "SELECT * FROM scores WHERE name = ?";

            // Create a statement
            pStatement = connection.prepareStatement(queryString);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public double findScore(String name) throws RemoteException {
        double score = -1;

        try {
            pStatement.setString(1, name);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                if (rs.getString(3).equals("true")) {
                    score = rs.getDouble(2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(name + "\'s score is " + score);
        return score;
    }
}
