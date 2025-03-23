package services;

import beans.Intervenant;
import connexion.Connexion;
import dao.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntervenantService implements IDao<Intervenant> {
    private Connection connexion;

    public IntervenantService() {
        this.connexion = Connexion.getInstance().getConnexion();
    }

    @Override
    public boolean create(Intervenant i) {
        String req = "INSERT INTO intervenant (nom, prenom, specialite) VALUES (?, ?, ?)";
        try (Connection conn = Connexion.getInstance().getConnexion();
             PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, i.getNom());
            ps.setString(2, i.getPrenom());
            ps.setString(3, i.getSpecialite());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de l'ajout de l'intervenant", ex);
            return false;
        }
    }

    @Override
    public boolean delete(Intervenant i) {
        String req = "DELETE FROM intervenant WHERE id = ?";
        try (Connection conn = Connexion.getInstance().getConnexion();
             PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, i.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la suppression de l'intervenant", ex);
            return false;
        }
    }

    @Override
    public boolean update(Intervenant i) {
        String req = "UPDATE intervenant SET nom = ?, prenom = ?, specialite = ? WHERE id = ?";
        try (Connection conn = Connexion.getInstance().getConnexion();
             PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, i.getNom());
            ps.setString(2, i.getPrenom());
            ps.setString(3, i.getSpecialite());
            ps.setInt(4, i.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la mise à jour de l'intervenant", ex);
            return false;
        }
    }

    @Override
    public Intervenant findById(int id) {
        String req = "SELECT * FROM intervenant WHERE id = ?";
        try (Connection conn = Connexion.getInstance().getConnexion();
             PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Intervenant(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialite")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la récupération de l'intervenant", ex);
        }
        return null;
    }

    @Override
    public List<Intervenant> findAll() {
    List<Intervenant> intervenants = new ArrayList<>();
    Connection conn = Connexion.getInstance().getConnexion();
    if (conn == null) {
        System.err.println("❌ Erreur : Connexion fermée !");
        return intervenants;
    }

    String sql = "SELECT * FROM intervenant";
    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Intervenant i = new Intervenant(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("specialite")
            );
            intervenants.add(i);
        }
    } catch (SQLException ex) {
        System.err.println("❌ Erreur SQL dans findAll() : " + ex.getMessage());
    }

    System.out.println("📌 Nombre d'intervenants trouvés : " + intervenants.size()); // ✅ Vérification
    return intervenants;
}


    public List<Intervenant> findByNom(String nom) {
        List<Intervenant> intervenants = new ArrayList<>();
        String req = "SELECT * FROM intervenant WHERE nom LIKE ?";
        try (Connection conn = Connexion.getInstance().getConnexion();
             PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, "%" + nom + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                intervenants.add(new Intervenant(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialite")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la recherche par nom", ex);
        }
        return intervenants;
    }
}
