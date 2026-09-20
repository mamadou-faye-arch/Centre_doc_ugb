package dao;

import connexion.ConnexionBD;
import modele.Document;
import java.sql.*;
import modele.UFR;
import modele.TypeDocument;
import modele.NiveauAcces;
import exception.ChampInvalideException;
import java.util.List;
import java.util.ArrayList;


public class DocumentDAO {

    
    public void ajouter(Document document) throws SQLException {
        String sql = "INSERT INTO documents (titre, auteur, encadrant, annee, type, id_ufr, discipline, resume, mots_cles, chemin_pdf, niveau_acces) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, document.getTitre());
            stmt.setString(2, document.getAuteur());
            stmt.setString(3, document.getEncadrant());
            stmt.setInt(4, document.getAnnee());
            stmt.setString(5, document.getType().name());
            stmt.setInt(6, document.getUfr().getIdUfr());
            stmt.setString(7, document.getDiscipline());
            stmt.setString(8, document.getResume());
            stmt.setString(9, String.join(",", document.getMotsCles()));
            stmt.setString(10, document.getCheminPdf());
            stmt.setString(11, document.getNiveauAcces().name());

            stmt.executeUpdate();
        }
    }

    
    public void rechercherParTitre(String motCle) throws SQLException {
        String sql = "SELECT * FROM documents WHERE titre LIKE ?";

        try (Connection conn = ConnexionBD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + motCle + "%");
            ResultSet rs = stmt.executeQuery();

            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                System.out.println(rs.getString("titre") + " - " + rs.getString("auteur")
                        + " (" + rs.getInt("annee") + ") - Acces: " + rs.getString("niveau_acces"));
            }
            if (!trouve) {
                System.out.println("Aucun document trouve pour : " + motCle);
            }
        }
    }
   public List<Document> rechercherDocuments(String motCle) throws SQLException {
    List<Document> resultats = new ArrayList<>();
    String sql = "SELECT * FROM documents WHERE titre LIKE ?";

    try (Connection conn = ConnexionBD.getConnexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + motCle + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            try {
                UFR ufr = new UFR(rs.getInt("id_ufr"), "");

                Document doc = new Document(
                        rs.getInt("id_document"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("encadrant"),
                        rs.getInt("annee"),
                        TypeDocument.valueOf(rs.getString("type")),
                        ufr,
                        rs.getString("discipline"),
                        rs.getString("resume"),
                        rs.getString("chemin_pdf"),
                        NiveauAcces.valueOf(rs.getString("niveau_acces"))
                );

                String motsClesTexte = rs.getString("mots_cles");
                if (motsClesTexte != null && !motsClesTexte.isBlank()) {
                    for (String mot : motsClesTexte.split(",")) {
                        doc.ajouterMotCle(mot);
                    }
                }

                resultats.add(doc);
            } catch (ChampInvalideException ex) {
                System.out.println("Document ignore (champ invalide) : " + ex.getMessage());
            }
        }
    }
    return resultats;
}
}