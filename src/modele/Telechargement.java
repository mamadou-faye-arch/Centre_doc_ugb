package modele;
 
import java.time.LocalDateTime;
 
public class Telechargement {
 
    private int idTelechargement;
    private Etudiant etudiant;
    private Document document;
    private LocalDateTime dateTelechargement;
 
    public Telechargement(int idTelechargement, Etudiant etudiant, Document document) {
        this.idTelechargement = idTelechargement;
        this.etudiant = etudiant;
        this.document = document;
        this.dateTelechargement = LocalDateTime.now();
    }
 
    public int getIdTelechargement() {
        return idTelechargement;
    }
 
    public Etudiant getEtudiant() {
        return etudiant;
    }
 
    public Document getDocument() {
        return document;
    }
 
    public LocalDateTime getDateTelechargement() {
        return dateTelechargement;
    }
 
    @Override
    public String toString() {
        return etudiant.getNomComplet() + " a téléchargé \"" + document.getTitre()
                + "\" le " + dateTelechargement;
    }
}
 
