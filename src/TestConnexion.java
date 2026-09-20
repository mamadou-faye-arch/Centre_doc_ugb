import dao.DocumentDAO;
import modele.Document;
import modele.UFR;
import modele.TypeDocument;
import modele.NiveauAcces;
import exception.ChampInvalideException;
import modele.Gestionnaire;
import dao.UtilisateurDAO;

public class TestConnexion {
    public static void main(String[] args) {
        DocumentDAO dao = new DocumentDAO();

        try {
            UFR ufr = new UFR(1, "UFR Sciences Appliquees et Technologie");

            Document document = new Document(
                0,
                "Etude des systemes distribues",
                "Awa Diop",
                "Pr. Mamadou Faye",
                2026,
                TypeDocument.MEMOIRE,
                ufr,
                "Informatique",
                "Ce memoire etudie les systemes distribues modernes.",
                "/documents/memoire_awa.pdf",
                NiveauAcces.TELECHARGEABLE
            );

            dao.ajouter(document);
            System.out.println("Document ajoute avec succes !");

            dao.rechercherParTitre("systemes");

        } catch (ChampInvalideException e) {
            System.out.println("Champ invalide : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        try {
    UtilisateurDAO daoUtilisateur = new UtilisateurDAO();
    UFR ufr = new UFR(1, "UFR Sciences Appliquées et Technologie");
    Gestionnaire gest = new Gestionnaire(0, "Faye", "Mamadou", "mamadou.faye@ugb.edu.sn", ufr);
    daoUtilisateur.ajouter(gest);
    System.out.println("Gestionnaire ajoute avec succes !");
} catch (Exception ex) {
    System.out.println("Erreur : " + ex.getMessage());
}
    }
}