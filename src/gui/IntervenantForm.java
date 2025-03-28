/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import beans.Intervenant;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.IntervenantService;

/**
 *
 * @author HP-PC
 */
public class IntervenantForm extends javax.swing.JInternalFrame {

    private IntervenantService is;
    private DefaultTableModel model;
    private static int id;

    /**
     * Creates new form IntervenantForm
     */
    public IntervenantForm() {
        initComponents();
        this.setTitle("Gestion des intervenants");
        is = new IntervenantService();
        model = (DefaultTableModel) listIntervenants.getModel();
        load();
    }

    private void load() {

        model.setRowCount(0);

        for (Intervenant i : is.findAll()) {

            model.addRow(new Object[]{i.getId(), i.getNom(), i.getPrenom(), i.getSpecialite()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        txtPrenom = new javax.swing.JTextField();
        txtSpecialite = new javax.swing.JTextField();
        bnAdd = new javax.swing.JButton();
        bnUpdate = new javax.swing.JButton();
        bnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listIntervenants = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(241, 243, 244));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestion des Intervenants", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/edit_user.png"))); // NOI18N
        jLabel1.setText("Nom :");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/edit_user.png"))); // NOI18N
        jLabel2.setText("Prenom :");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/wrench-icon-vector (1).jpg"))); // NOI18N
        jLabel3.setText("Specialite :");

        txtPrenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrenomActionPerformed(evt);
            }
        });

        txtSpecialite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpecialiteActionPerformed(evt);
            }
        });

        bnAdd.setBackground(new java.awt.Color(0, 200, 83));
        bnAdd.setForeground(new java.awt.Color(255, 255, 255));
        bnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/edit_add.png"))); // NOI18N
        bnAdd.setText("Ajouter");
        bnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnAddActionPerformed(evt);
            }
        });

        bnUpdate.setBackground(new java.awt.Color(251, 192, 45));
        bnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/agt_update_recommended.png"))); // NOI18N
        bnUpdate.setText("Modifier");
        bnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnUpdateActionPerformed(evt);
            }
        });

        bnDelete.setBackground(new java.awt.Color(229, 57, 53));
        bnDelete.setForeground(new java.awt.Color(255, 255, 255));
        bnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/delete_user.png"))); // NOI18N
        bnDelete.setText("Supprimer");
        bnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPrenom, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txtNom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(34, 34, 34)
                        .addComponent(txtSpecialite, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSpecialite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnAdd)
                    .addComponent(bnDelete)
                    .addComponent(bnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Liste des intervenants", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12))); // NOI18N

        listIntervenants.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        listIntervenants.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        listIntervenants.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prenom", "Specialite"
            }
        ));
        listIntervenants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listIntervenantsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listIntervenants);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSpecialiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpecialiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpecialiteActionPerformed

    private void bnAddActionPerformed(java.awt.event.ActionEvent evt) {

        String nom = txtNom.getText().trim();
        String prenom = txtPrenom.getText().trim();
        String specialite = txtSpecialite.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || specialite.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Intervenant intervenant = new Intervenant(0, nom, prenom, specialite);

        if (is.create(intervenant)) {
            JOptionPane.showMessageDialog(this, "Intervenant bien enregistré !");
            load();
        } else {
            JOptionPane.showMessageDialog(this, "Problème lors de l'enregistrement.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listIntervenantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listIntervenantsMouseClicked
        id = Integer.parseInt(model.getValueAt(listIntervenants.getSelectedRow(), 0).toString());
        txtNom.setText(model.getValueAt(listIntervenants.getSelectedRow(), 1).toString());
        txtPrenom.setText(model.getValueAt(listIntervenants.getSelectedRow(), 2).toString());
        txtSpecialite.setText(model.getValueAt(listIntervenants.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_listIntervenantsMouseClicked

    private void bnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeleteActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment le supprimer?");
        if (response == 0) {
            is.delete(is.findById(id));
            load();
        }
    }//GEN-LAST:event_bnDeleteActionPerformed

    private void bnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnUpdateActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment le modifier?");
        if (response == 0) {
            String nom = txtNom.getText().toString();
            String prenom = txtPrenom.getText().toString();
            String specialite = txtSpecialite.getText().toString();
            if (is.update(new Intervenant(id, nom, prenom, specialite))) {
                JOptionPane.showMessageDialog(this, "Bien enregistree");
                load();
            } else {
                JOptionPane.showConfirmDialog(this, "Probleme");
            }
        }    }//GEN-LAST:event_bnUpdateActionPerformed

    private void txtPrenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrenomActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnAdd;
    private javax.swing.JButton bnDelete;
    private javax.swing.JButton bnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listIntervenants;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtSpecialite;
    // End of variables declaration//GEN-END:variables
}
