package  connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

     private static final String URL = "jdbc:mysql://localhost:3306/centre_doc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
     private static final String UTILISATEUR = "root";
     private static final String MOT_DE_PASSE = "root2026";

     private static Connection connexion;

     private ConnexionBD() {

     }
     public static Connection getConnexion() throws SQLException{
        if (connexion == null || connexion.isClosed()) {
            
            connexion = DriverManager.getConnection(URL,UTILISATEUR,MOT_DE_PASSE);
        }
        return connexion;
     }
}