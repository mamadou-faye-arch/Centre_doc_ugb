package modele;

import exception.ChampInvalideException;
import java.util.HashSet;
import java.util.Set;


public class Document {

    private int idDocument;
    private String titre;
    private String auteur;
    private String encadrant;
    private int annee;
    private TypeDocument type;
    private UFR ufr;
    private String discipline;
    private String resume;
    private Set<String> motsCles;
    private String cheminPdf;
    private NiveauAcces niveauAcces;

    public Document(int idDocument, String titre, String auteur, String encadrant, int annee,
                     TypeDocument type, UFR ufr, String discipline, String resume,
                     String cheminPdf, NiveauAcces niveauAcces) throws ChampInvalideException {
        if (titre == null || titre.isBlank()) {
            throw new ChampInvalideException("Le titre du document est obligatoire.");
        }
        if (auteur == null || auteur.isBlank()) {
            throw new ChampInvalideException("L'auteur du document est obligatoire.");
        }
        if (ufr == null) {
            throw new ChampInvalideException("L'UFR du document est obligatoire.");
        }

        this.idDocument = idDocument;
        this.titre = titre;
        this.auteur = auteur;
        this.encadrant = encadrant;
        this.annee = annee;
        this.type = type;
        this.ufr = ufr;
        this.discipline = discipline;
        this.resume = resume;
        this.motsCles = new HashSet<>();
        this.cheminPdf = cheminPdf;
        this.niveauAcces = niveauAcces;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(String encadrant) {
        this.encadrant = encadrant;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public TypeDocument getType() {
        return type;
    }

    public void setType(TypeDocument type) {
        this.type = type;
    }

    public UFR getUfr() {
        return ufr;
    }

    public void setUfr(UFR ufr) {
        this.ufr = ufr;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Set<String> getMotsCles() {
        return motsCles;
    }

    public void ajouterMotCle(String motCle) {
        if (motCle != null && !motCle.isBlank()) {
            this.motsCles.add(motCle.trim().toLowerCase());
        }
    }

    public String getCheminPdf() {
        return cheminPdf;
    }

    public void setCheminPdf(String cheminPdf) {
        this.cheminPdf = cheminPdf;
    }

    public NiveauAcces getNiveauAcces() {
        return niveauAcces;
    }

    public void setNiveauAcces(NiveauAcces niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    @Override
    public String toString() {
        return titre + " (" + type + ", " + annee + ") - " + auteur;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Document)) return false;
        Document autre = (Document) obj;
        return this.idDocument == autre.idDocument;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idDocument);
    }
}