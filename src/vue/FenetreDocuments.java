package vue;

import dao.DocumentDAO;
import modele.Document;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class FenetreDocuments extends JFrame {

    private JTextField champRecherche;
    private JButton boutonRechercher;
    private JTable tableDocuments;
    private DefaultTableModel modeleTable;

    public FenetreDocuments() {
        super("Centre de Documentation UGB - Recherche de documents");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelHaut = new JPanel(new BorderLayout(10, 10));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        champRecherche = new JTextField();
        boutonRechercher = new JButton("Rechercher");

        panelHaut.add(new JLabel("Rechercher un document par titre :"), BorderLayout.NORTH);
        panelHaut.add(champRecherche, BorderLayout.CENTER);
        panelHaut.add(boutonRechercher, BorderLayout.EAST);

        String[] colonnes = {"Titre", "Auteur", "Annee", "Type", "Discipline", "Acces"};
        modeleTable = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableDocuments = new JTable(modeleTable);
        JScrollPane scrollPane = new JScrollPane(tableDocuments);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        add(panelHaut, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        boutonRechercher.addActionListener(e -> rechercher());

        rechercher();
    }

    private void rechercher() {
        String motCle = champRecherche.getText();
        modeleTable.setRowCount(0);

        try {
            DocumentDAO dao = new DocumentDAO();
            List<Document> resultats = dao.rechercherDocuments(motCle);

            if (resultats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aucun document trouve.");
            }

            for (Document doc : resultats) {
                modeleTable.addRow(new Object[]{
                        doc.getTitre(),
                        doc.getAuteur(),
                        doc.getAnnee(),
                        doc.getType(),
                        doc.getDiscipline(),
                        doc.getNiveauAcces()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}