package dao;

import connexion.ConnexionBD;
import java.sql.*;

public class UtilisateurDAO {
    public void ajouter (String nom,String prenom,String email,String role) throws SQLException {
        String sql = "INSERT INTO Utilisateurs(nom, prenom, email, role) VALUES(?, ?, ?, ?)";
        try(Connection conn = ConnexionBD.getConnexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setString(3, email);
                stmt.setString(4, role);

                stmt.executeUpdate();
            }
    }
    public void rechercherParEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Utilisateurs WHERE email = ?";

        try (Connection conn = ConnexionBD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if(rs.next()) {
                    System.out.println("Utilisateur trouve : " + rs.getString("nom")+ " " + rs.getString("prenom"));
                } else {
                    System.out.println("Aucun utilisateur trouve avec cet email.");
                }
             }
    }
}