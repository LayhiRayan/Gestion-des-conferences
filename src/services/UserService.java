package services;

import beans.User;
import connexion.Connexion;
import dao.IUserDao;
import util.SecurityUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements IUserDao {

    private Connexion connexion;

    public UserService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean addUser(User user) {
        String req = "INSERT INTO user (login, password, question_secrete, reponse_secrete) VALUES (?, MD5(?), ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getQuestionSecrete());
            ps.setString(4, SecurityUtil.hashSHA1(user.getReponseSecrete())); // Hachage pour plus de sécurité
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        String req = "SELECT * FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("login"), 
                    rs.getString("password"), 
                    rs.getString("question_secrete"), 
                    rs.getString("reponse_secrete")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de l'utilisateur : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) {
        String req = "SELECT * FROM user WHERE login = ? AND password = MD5(?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'authentification : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean userExists(String login) {
        String req = "SELECT * FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean updatePassword(String login, String newPassword) {
        String req = "UPDATE user SET password = MD5(?) WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, newPassword);
            ps.setString(2, login);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour du mot de passe : " + ex.getMessage());
        }
        return false;
    }

    public boolean verifySecurityQuestion(String login, String reponse) {
        String req = "SELECT reponse_secrete FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedReponseStockee = rs.getString("reponse_secrete");
                String hashedReponseUtilisateur = SecurityUtil.hashSHA1(reponse);

                return hashedReponseStockee.equals(hashedReponseUtilisateur);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la vérification de la question secrète : " + ex.getMessage());
        }
        return false;
    }

    public String getSecurityQuestion(String login) {
        String req = "SELECT question_secrete FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("question_secrete");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de la question secrète : " + ex.getMessage());
        }
        return null;
    }
}
