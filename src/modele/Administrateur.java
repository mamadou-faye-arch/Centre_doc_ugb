package modele;

import exception.ChampInvalideException;


public class Administrateur extends Utilisateur {

    public Administrateur(int idUtilisateur, String nom, String prenom, String email)
            throws ChampInvalideException {
        super(idUtilisateur, nom, prenom, email);
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}