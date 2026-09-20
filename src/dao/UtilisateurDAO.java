package dao;

import connexion.ConnexionBD;
import exception.DoublonException;
import modele.Etudiant;
import modele.Gestionnaire;
import java.sql.*;
import exception.AuthentificationException;
import exception.ChampInvalideException;
import modele.UFR;


public class UtilisateurDAO {

    
    public void ajouter(Etudiant etudiant) throws SQLException, DoublonException {

        if (emailExiste(etudiant.getEmail())) {
            throw new DoublonException("Un utilisateur avec cet email existe deja : " + etudiant.getEmail());
        }

        String sql = "INSERT INTO utilisateurs (nom, prenom, email, role, code_etudiant) VALUES (?, ?, ?, 'ETUDIANT', ?)";

        try (Connection conn = ConnexionBD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getEmail());
            stmt.setString(4, etudiant.getCodeEtudiant());

            stmt.executeUpdate();
        }
    }

    
    public void ajouter(Gestionnaire gestionnaire) throws SQLException, DoublonException {

        if (emailExiste(gestionnaire.getEmail())) {
            throw new DoublonException("Un utilisateur avec cet email existe deja : " + gestionnaire.getEmail());
        }

        String sql = "INSERT INTO utilisateurs (nom, prenom, email, role, id_ufr) VALUES (?, ?, ?, 'GESTIONNAIRE', ?)";

        try (Connection conn = ConnexionBD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, gestionnaire.getNom());
            stmt.setString(2, gestionnaire.getPrenom());
            stmt.setString(3, gestionnaire.getEmail());
            stmt.setInt(4, gestionnaire.getUfr().getIdUfr());

            stmt.executeUpdate();
        }
    }

    private boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE email = ?";

        try (Connection conn = ConnexionBD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public void rechercherParEmail(String email) throws SQLException {
        String sql = "SELECT * FROM utilisateurs WHERE email = ?";

        try (Connection conn = ConnexionBD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Utilisateur trouve : " + rs.getString("nom") + " " + rs.getString("prenom"));
            } else {
                System.out.println("Aucun utilisateur trouve avec cet email.");
            }
        }
    }
    public Etudiant authentifierEtudiant(String nom, String prenom, String email, String codeEtudiant) 
        throws AuthentificationException {
    String sql = "SELECT * FROM utilisateurs WHERE nom=? AND prenom=? AND email=? AND code_etudiant=?";
    try (Connection conn = ConnexionBD.getConnexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, email);
        stmt.setString(4, codeEtudiant);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Etudiant(rs.getInt("id_utilisateur"), nom, prenom, email, codeEtudiant);
        } else {
            throw new AuthentificationException("Identifiants incorrects.");
        }
    } catch (SQLException ex) {
        throw new AuthentificationException("Erreur de connexion : " + ex.getMessage());
    } catch (ChampInvalideException ex) {
        throw new AuthentificationException("Données invalides : " + ex.getMessage());
    }
}
public Gestionnaire authentifierGestionnaire(String email) throws AuthentificationException {
    String sql = "SELECT * FROM utilisateurs WHERE email=?";
    try (Connection conn = ConnexionBD.getConnexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int idUfr = rs.getInt("id_ufr");
            UFR ufr = new UFR(idUfr, "");  // à adapter si UFR a besoin du nom aussi
            return new Gestionnaire(
                rs.getInt("id_utilisateur"),
                rs.getString("nom"),
                rs.getString("prenom"),
                email,
                ufr
            );
        } else {
            throw new AuthentificationException("Identifiants incorrects.");
        }
    } catch (SQLException ex) {
        throw new AuthentificationException("Erreur de connexion : " + ex.getMessage());
    } catch (ChampInvalideException ex) {
        throw new AuthentificationException("Données invalides : " + ex.getMessage());
    }
}

}