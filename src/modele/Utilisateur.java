package modele;

import exception.ChampInvalideException;


public abstract class Utilisateur {

    private static final String SUFFIXE_EMAIL_UGB = "@ugb.edu.sn";

    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String email;

    protected Utilisateur(int idUtilisateur, String nom, String prenom, String email)
            throws ChampInvalideException {
        if (nom == null || nom.isBlank()) {
            throw new ChampInvalideException("Le nom de l'utilisateur est obligatoire.");
        }
        if (prenom == null || prenom.isBlank()) {
            throw new ChampInvalideException("Le prénom de l'utilisateur est obligatoire.");
        }
        if (email == null || !email.toLowerCase().endsWith(SUFFIXE_EMAIL_UGB)) {
            throw new ChampInvalideException(
                    "L'email doit être un email UGB valide (format " + SUFFIXE_EMAIL_UGB + ").");
        }

        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

   
    public abstract Role getRole();

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ChampInvalideException {
        if (email == null || !email.toLowerCase().endsWith(SUFFIXE_EMAIL_UGB)) {
            throw new ChampInvalideException(
                    "L'email doit être un email UGB valide (format " + SUFFIXE_EMAIL_UGB + ").");
        }
        this.email = email;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    @Override
    public String toString() {
        return getNomComplet() + " (" + getRole() + ") - " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Utilisateur)) return false;
        Utilisateur autre = (Utilisateur) obj;
        return this.idUtilisateur == autre.idUtilisateur;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idUtilisateur);
    }
}