package services;

import beans.EThemeEvenement;
import beans.Evenement;
import beans.Intervenant;
import beans.ParticipationEvenement;
import connexion.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParticipationEvenementService {

    private final Connection connection;

    public ParticipationEvenementService() {
        this.connection = Connexion.getInstance().getConnexion();
    }

    // 🔹 Ajouter une participation
    public void create(Evenement evenement, Intervenant intervenant) {
        Connection conn = Connexion.getInstance().getConnexion();
        if (conn == null) {
            System.err.println("❌ Connexion échouée !");
            return;
        }

        // Vérifier si l'affectation existe déjà
        String checkSql = "SELECT COUNT(*) FROM participation_evenement WHERE evenement_id = ? AND intervenant_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, evenement.getId());
            checkStmt.setInt(2, intervenant.getId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("⚠️ Affectation déjà existante !");
                return; // ne rien faire
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la vérification d'existence : " + e.getMessage());
            return;
        }

        // Si elle n'existe pas, on insère
        String sql = "INSERT INTO participation_evenement (evenement_id, intervenant_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evenement.getId());
            stmt.setInt(2, intervenant.getId());
            stmt.executeUpdate();
            System.out.println("✅ Participation ajoutée !");
        } catch (SQLException ex) {
            System.err.println("❌ Erreur SQL à l'insertion : " + ex.getMessage());
        }
    }

    // 🔹 Supprimer une participation spécifique
   public boolean delete(Evenement evenement, Intervenant intervenant) {
    Connection conn = Connexion.getInstance().getConnexion();
    try {
        if (conn == null || conn.isClosed()) {
            System.out.println("🔄 Connexion fermée, reconnexion...");
            Connexion.getInstance().closeConnection();
            conn = Connexion.getInstance().getConnexion();
        }

        String sql = "DELETE FROM participation_evenement WHERE evenement_id = ? AND intervenant_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, evenement.getId());
            ps.setInt(2, intervenant.getId());
            return ps.executeUpdate() > 0;
        }
    } catch (SQLException e) {
        Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, e);
    }
    return false;
}


    // 🔹 Vérifier si une affectation existe déjà
    public boolean exists(Evenement evenement, Intervenant intervenant) {
        Connection conn = Connexion.getInstance().getConnexion();
        String sql = "SELECT 1 FROM participation_evenement WHERE evenement_id = ? AND intervenant_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evenement.getId());
            stmt.setInt(2, intervenant.getId());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("❌ Erreur exists() : " + e.getMessage());
            return false;
        }
    }

    // 🔹 Récupérer les participations d’un événement
   public List<ParticipationEvenement> findByEvenement(Evenement evenement) {
    List<ParticipationEvenement> participations = new ArrayList<>();
    Connection conn = Connexion.getInstance().getConnexion();

    if (conn == null) {
        System.err.println("❌ Connexion fermée !");
        return participations;
    }

    String sql = "SELECT i.id, i.nom, i.prenom, i.specialite FROM participation_evenement pe " +
                 "JOIN intervenant i ON pe.intervenant_id = i.id " +
                 "WHERE pe.evenement_id = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, evenement.getId());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Intervenant intervenant = new Intervenant(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("specialite")
            );

            // 🛡️ Créer une copie de l’événement pour chaque objet si nécessaire
            Evenement copie = new Evenement(
                evenement.getId(),
                evenement.getTitre(),
                evenement.getTheme(),
                evenement.getDate(),
                evenement.getLieu()
            );

            participations.add(new ParticipationEvenement(copie, intervenant));
        }
    } catch (SQLException ex) {
        System.err.println("❌ Erreur SQL : " + ex.getMessage());
    }

    return participations;
}


    // 🔹 Récupérer tous les intervenants liés à un événement
    public List<Intervenant> findIntervenantsByEvenement(Evenement evenement) {
        List<Intervenant> list = new ArrayList<>();
        String sql = "SELECT i.id, i.nom, i.prenom, i.specialite " +
                     "FROM participation_evenement pe " +
                     "JOIN intervenant i ON pe.intervenant_id = i.id " +
                     "WHERE pe.evenement_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, evenement.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Intervenant(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialite")
                ));
            }
        } catch (SQLException e) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    // 🔹 Récupérer toutes les participations
    public List<ParticipationEvenement> findAll() {
        List<ParticipationEvenement> list = new ArrayList<>();
        String sql = "SELECT e.id AS eid, e.titre, e.theme, e.date, e.lieu, " +
                     "i.id AS iid, i.nom, i.prenom, i.specialite " +
                     "FROM participation_evenement pe " +
                     "JOIN evenement e ON pe.evenement_id = e.id " +
                     "JOIN intervenant i ON pe.intervenant_id = i.id";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evenement e = new Evenement(
                        rs.getInt("eid"),
                        rs.getString("titre"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getDate("date"),
                        rs.getString("lieu")
                );
                Intervenant i = new Intervenant(
                        rs.getInt("iid"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialite")
                );
                list.add(new ParticipationEvenement(e, i));
            }
        } catch (SQLException e) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
}