package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FenetreConnexion extends JFrame {

    private String roleActuel = "ETUDIANT";

    private JPanel banniere;
    private JLabel badgeRole, titreBanniere, descBanniere;
    private JButton boutonEtudiant, boutonGestion, boutonValider;

    private JTextField champNom, champPrenom, champEmail, champCodeEtudiant;
    private JLabel labelNom, labelPrenom, labelEmail, labelCodeEtudiant;

    private final Color COULEUR_ETUDIANT = new Color(2, 132, 199);
    private final Color COULEUR_ADMIN = new Color(124, 58, 237);

    public FenetreConnexion() {
        super("Centre de Documentation UGB - Connexion");

        setSize(750, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel carte = new JPanel(new GridLayout(1, 2));
        carte.setBorder(new EmptyBorder(20, 20, 20, 20));
        carte.setBackground(Color.WHITE);

        carte.add(creerBanniere());
        carte.add(creerFormulaire());

        add(carte, BorderLayout.CENTER);
        appliquerRole("ETUDIANT");
    }

    private JPanel creerBanniere() {
        banniere = new JPanel();
        banniere.setLayout(new BoxLayout(banniere, BoxLayout.Y_AXIS));
        banniere.setBorder(new EmptyBorder(40, 30, 40, 30));

        JLabel logo = new JLabel("📚 CENTRE DOC UGB");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        badgeRole = new JLabel("Espace Étudiant");
        badgeRole.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        badgeRole.setForeground(new Color(255, 255, 255, 200));
        badgeRole.setAlignmentX(Component.LEFT_ALIGNMENT);
        badgeRole.setBorder(new EmptyBorder(60, 0, 15, 0));

        titreBanniere = new JLabel("<html><body style='width:200px'>Recherchez et consultez les thèses et mémoires</body></html>");
        titreBanniere.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titreBanniere.setForeground(Color.WHITE);
        titreBanniere.setAlignmentX(Component.LEFT_ALIGNMENT);

        descBanniere = new JLabel("<html><body style='width:220px'>Accédez au fonds documentaire de votre UFR en toute simplicité.</body></html>");
        descBanniere.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descBanniere.setForeground(new Color(255, 255, 255, 190));
        descBanniere.setAlignmentX(Component.LEFT_ALIGNMENT);
        descBanniere.setBorder(new EmptyBorder(15, 0, 0, 0));

        banniere.add(logo);
        banniere.add(badgeRole);
        banniere.add(titreBanniere);
        banniere.add(descBanniere);

        return banniere;
    }

    private JPanel creerFormulaire() {
        JPanel formulaire = new JPanel();
        formulaire.setLayout(new BoxLayout(formulaire, BoxLayout.Y_AXIS));
        formulaire.setBorder(new EmptyBorder(40, 40, 40, 40));
        formulaire.setBackground(Color.WHITE);

        JPanel onglets = new JPanel(new GridLayout(1, 2, 5, 0));
        onglets.setBackground(new Color(240, 240, 240));
        onglets.setBorder(new EmptyBorder(4, 4, 4, 4));
        onglets.setMaximumSize(new Dimension(400, 45));
        onglets.setAlignmentX(Component.LEFT_ALIGNMENT);

        boutonEtudiant = new JButton("Étudiant");
        boutonGestion = new JButton("Gestionnaire / Admin");
        for (JButton b : new JButton[]{boutonEtudiant, boutonGestion}) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 12));
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        onglets.add(boutonEtudiant);
        onglets.add(boutonGestion);

        labelNom = creerLabelChamp("Nom");
        champNom = creerChamp();
        labelPrenom = creerLabelChamp("Prénom");
        champPrenom = creerChamp();
        labelEmail = creerLabelChamp("Email UGB");
        champEmail = creerChamp();
        labelCodeEtudiant = creerLabelChamp("Code étudiant");
        champCodeEtudiant = creerChamp();

        boutonValider = new JButton("Se connecter →");
        boutonValider.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boutonValider.setForeground(Color.WHITE);
        boutonValider.setFocusPainted(false);
        boutonValider.setBorderPainted(false);
        boutonValider.setHorizontalAlignment(SwingConstants.CENTER);
        boutonValider.setMaximumSize(new Dimension(400, 48));
        boutonValider.setAlignmentX(Component.LEFT_ALIGNMENT);
        boutonValider.setCursor(new Cursor(Cursor.HAND_CURSOR));

        formulaire.add(onglets);
        formulaire.add(labelNom);
        formulaire.add(champNom);
        formulaire.add(labelPrenom);
        formulaire.add(champPrenom);
        formulaire.add(labelEmail);
        formulaire.add(champEmail);
        formulaire.add(labelCodeEtudiant);
        formulaire.add(champCodeEtudiant);
        formulaire.add(Box.createVerticalStrut(25));
        formulaire.add(boutonValider);

        boutonEtudiant.addActionListener(e -> appliquerRole("ETUDIANT"));
        boutonGestion.addActionListener(e -> appliquerRole("GESTION"));
        boutonValider.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Connexion (" + roleActuel + ") à venir")
        );

        return formulaire;
    }

    private JLabel creerLabelChamp(String texte) {
        JLabel label = new JLabel(texte);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setForeground(Color.GRAY);
        label.setBorder(new EmptyBorder(12, 0, 6, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField creerChamp() {
        JTextField champ = new JTextField();
        champ.setMaximumSize(new Dimension(400, 40));
        champ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        champ.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(8, 12, 8, 12)
        ));
        champ.setAlignmentX(Component.LEFT_ALIGNMENT);
        return champ;
    }

    private void appliquerRole(String role) {
        roleActuel = role;
        Color couleur = role.equals("ETUDIANT") ? COULEUR_ETUDIANT : COULEUR_ADMIN;

        banniere.setBackground(couleur);
        boutonValider.setBackground(couleur);

        boutonEtudiant.setBackground(role.equals("ETUDIANT") ? Color.WHITE : new Color(240, 240, 240));
        boutonEtudiant.setForeground(role.equals("ETUDIANT") ? couleur : Color.GRAY);
        boutonGestion.setBackground(role.equals("GESTION") ? Color.WHITE : new Color(240, 240, 240));
        boutonGestion.setForeground(role.equals("GESTION") ? couleur : Color.GRAY);

        boolean estEtudiant = role.equals("ETUDIANT");

        labelNom.setVisible(estEtudiant);
        champNom.setVisible(estEtudiant);
        labelPrenom.setVisible(estEtudiant);
        champPrenom.setVisible(estEtudiant);
        labelCodeEtudiant.setVisible(estEtudiant);
        champCodeEtudiant.setVisible(estEtudiant);

        labelEmail.setText(estEtudiant ? "Email UGB" : "Email professionnel UGB");

        if (estEtudiant) {
            badgeRole.setText("Espace Étudiant");
            titreBanniere.setText("<html><body style='width:200px'>Recherchez et consultez les thèses et mémoires</body></html>");
        } else {
            badgeRole.setText("Espace Gestion");
            titreBanniere.setText("<html><body style='width:200px'>Gérez le fonds documentaire de votre UFR</body></html>");
        }
    }
}