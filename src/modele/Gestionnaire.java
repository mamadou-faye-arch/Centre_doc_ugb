package modele;

import exception.ChampInvalideException;

public class Gestionnaire extends Utilisateur {

    private UFR ufr;

    public Gestionnaire(int idUtilisateur, String nom, String prenom, String email, UFR ufr)
            throws ChampInvalideException {
        super(idUtilisateur, nom, prenom, email);
        this.ufr = ufr;
    }

    @Override
    public Role getRole() {
        return Role.GESTIONNAIRE;
    }

    public UFR getUfr() {
        return ufr;
    }

    public void setUfr(UFR ufr) {
        this.ufr = ufr;
    }

    
    public boolean gereUfr(UFR autreUfr) {
        return this.ufr != null && this.ufr.equals(autreUfr);
    }
}