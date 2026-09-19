import vue.FenetreConnexion;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FenetreConnexion fenetre = new FenetreConnexion();
            fenetre.setVisible(true);
        });
    }
}