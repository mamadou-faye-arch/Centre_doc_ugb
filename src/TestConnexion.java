import dao.DocumentDAO;
import modele.Document;
import modele.UFR;
import modele.TypeDocument;
import modele.NiveauAcces;
import exception.ChampInvalideException;

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
    }
}