package modele;

import exception.ChampInvalideException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Etudiant extends Utilisateur {

    private String codeEtudiant;
    private List<Telechargement> historiqueTelechargements;

    public Etudiant(int idUtilisateur, String nom, String prenom, String email, String codeEtudiant)
            throws ChampInvalideException {
        super(idUtilisateur, nom, prenom, email);
        if (codeEtudiant == null || codeEtudiant.isBlank()) {
            throw new ChampInvalideException("Le code étudiant est obligatoire.");
        }
        this.codeEtudiant = codeEtudiant;
        this.historiqueTelechargements = new ArrayList<>();
    }

    @Override
    public Role getRole() {
        return Role.ETUDIANT;
    }

    public String getCodeEtudiant() {
        return codeEtudiant;
    }

    public void setCodeEtudiant(String codeEtudiant) {
        this.codeEtudiant = codeEtudiant;
    }

    
    public void ajouterTelechargement(Telechargement telechargement) {
        this.historiqueTelechargements.add(telechargement);
    }

   
    public List<Telechargement> getHistoriqueTelechargements() {
        return Collections.unmodifiableList(historiqueTelechargements);
    }
}