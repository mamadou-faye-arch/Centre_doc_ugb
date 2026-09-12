package modele;
 

public enum NiveauAcces {
    TELECHARGEABLE,
    CONSULTATION_SEULE,
    RESTREINT;
 
    
    public boolean autoriseTelechargement() {
        return this == TELECHARGEABLE;
    }
 
    public boolean autoriseConsultation() {
        return this == TELECHARGEABLE || this == CONSULTATION_SEULE;
    }
}
 