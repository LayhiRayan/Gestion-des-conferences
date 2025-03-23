package services;

import beans.Evenement;
import beans.EThemeEvenement;
import beans.Intervenant;
import beans.ParticipationEvenement;
import connexion.Connexion;
import dao.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvenementService implements IDao<Evenement> {

    private final Connexion connexion;

    public EvenementService() {
        this.connexion = Connexion.getInstance();
    }

    private Connection getSafeConnection() {
        Connection conn = Connexion.getInstance().getConnexion();
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("🔄 Reconnexion en cours...");
                Connexion.getInstance().closeConnection();
                conn = Connexion.getInstance().getConnexion();
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la vérification de la connexion : " + e.getMessage());
        }
        return conn;
    }

    @Override
    public boolean create(Evenement evenement) {
        throw new UnsupportedOperationException("❌ Utilisez create(Evenement, List<Intervenant>) à la place.");
    }

    public boolean create(Evenement evenement, List<Intervenant> intervenants) {
        String reqEvenement = "INSERT INTO Evenement (titre, theme, date, lieu) VALUES (?, ?, ?, ?)";
        String reqParticipation = "INSERT INTO participation_evenement (evenement_id, intervenant_id) VALUES (?, ?)";

        try (Connection conn = getSafeConnection();
             PreparedStatement psEvent = conn.prepareStatement(reqEvenement, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psParticipation = conn.prepareStatement(reqParticipation)) {

            psEvent.setString(1, evenement.getTitre());
            psEvent.setString(2, evenement.getTheme().name());
            psEvent.setDate(3, new java.sql.Date(evenement.getDate().getTime()));
            psEvent.setString(4, evenement.getLieu());

            int rowsAffected = psEvent.executeUpdate();
            if (rowsAffected == 0) return false;

            try (ResultSet rs = psEvent.getGeneratedKeys()) {
                if (rs.next()) {
                    int eventId = rs.getInt(1);
                    for (Intervenant intervenant : intervenants) {
                        psParticipation.setInt(1, eventId);
                        psParticipation.setInt(2, intervenant.getId());
                        psParticipation.addBatch();
                    }
                    psParticipation.executeBatch();
                }
            }

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la création de l'événement", ex);
        }

        return false;
    }

    @Override
    public boolean delete(Evenement o) {
        String deleteParticipation = "DELETE FROM participation_evenement WHERE evenement_id = ?";
        String deleteEvent = "DELETE FROM Evenement WHERE id = ?";
        try (Connection conn = getSafeConnection();
             PreparedStatement psPart = conn.prepareStatement(deleteParticipation);
             PreparedStatement psEvent = conn.prepareStatement(deleteEvent)) {

            psPart.setInt(1, o.getId());
            psPart.executeUpdate();

            psEvent.setInt(1, o.getId());
            return psEvent.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la suppression de l'événement", ex);
        }
        return false;
    }

    @Override
    public boolean update(Evenement o) {
        String req = "UPDATE Evenement SET titre = ?, theme = ?, date = ?, lieu = ? WHERE id = ?";
        try (Connection conn = getSafeConnection();
             PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setString(1, o.getTitre());
            ps.setString(2, o.getTheme().name());
            ps.setDate(3, new java.sql.Date(o.getDate().getTime()));
            ps.setString(4, o.getLieu());
            ps.setInt(5, o.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la mise à jour de l'événement", ex);
        }
        return false;
    }

    @Override
    public Evenement findById(int id) {
        String reqEvent = "SELECT * FROM Evenement WHERE id = ?";
        try (Connection conn = getSafeConnection();
             PreparedStatement ps = conn.prepareStatement(reqEvent)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Evenement(
                            rs.getInt("id"),
                            rs.getString("titre"),
                            EThemeEvenement.valueOf(rs.getString("theme")),
                            rs.getDate("date"),
                            rs.getString("lieu")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la récupération de l'événement", ex);
        }
        return null;
    }

    public List<Evenement> findAll() {
        List<Evenement> evenements = new ArrayList<>();
        String req = "SELECT * FROM Evenement";

        try (Connection conn = getSafeConnection();
             PreparedStatement ps = conn.prepareStatement(req);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                evenements.add(new Evenement(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getDate("date"),
                        rs.getString("lieu")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la récupération des événements", ex);
        }

        return evenements;
    }

    public List<Evenement> findByDate(Date date) {
        List<Evenement> evenements = new ArrayList<>();
        String req = "SELECT * FROM Evenement WHERE date = ?";

        try (Connection conn = getSafeConnection();
             PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setDate(1, new java.sql.Date(date.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    evenements.add(new Evenement(
                            rs.getInt("id"),
                            rs.getString("titre"),
                            EThemeEvenement.valueOf(rs.getString("theme")),
                            rs.getDate("date"),
                            rs.getString("lieu")
                    ));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la recherche des événements par date", ex);
        }

        return evenements;
    }

    public List<Intervenant> findIntervenantsByEvenement(int evenementId) {
        List<Intervenant> intervenants = new ArrayList<>();
        String req = "SELECT i.* FROM Intervenant i " +
                     "JOIN participation_evenement p ON p.intervenant_id = i.id " +
                     "WHERE p.evenement_id = ?";

        try (Connection conn = getSafeConnection();
             PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setInt(1, evenementId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    intervenants.add(new Intervenant(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("specialite")
                    ));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la récupération des intervenants", ex);
        }

        return intervenants;
    }
}