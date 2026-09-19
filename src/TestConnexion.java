import dao.UtilisateurDAO;
import modele.Etudiant;
import exception.ChampInvalideException;

public class TestConnexion {
    public static void main(String[] args) {
        UtilisateurDAO dao = new UtilisateurDAO();

        try {
            
            Etudiant etudiant = new Etudiant(0, "Diop", "Awa", "awa.diop@ugb.edu.sn", "ETU2026001");

            
            dao.ajouter(etudiant);
            System.out.println("Etudiant ajoute avec succes !");

        } catch (ChampInvalideException e) {
            System.out.println("Champ invalide : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}