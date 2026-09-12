package modele;


public class UFR {

    private int idUfr;
    private String nom;

    public UFR(int idUfr, String nom) {
        this.idUfr = idUfr;
        this.nom = nom;
    }

    public int getIdUfr() {
        return idUfr;
    }

    public void setIdUfr(int idUfr) {
        this.idUfr = idUfr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UFR)) return false;
        UFR autre = (UFR) obj;
        return this.idUfr == autre.idUfr;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idUfr);
    }
}