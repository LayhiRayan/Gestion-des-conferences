package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {

    private static Connexion instance = null;
    private Connection connexion = null;

    // 🔹 Paramètres de connexion
    private final String urlBase = "jdbc:mysql://localhost:3306/gestion_evenements?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String utilisateurBase = "root";
    private final String motDePasseBase = "";

    private Connexion() {
        ouvrirConnexion();
    }

    private void ouvrirConnexion() {
        try {
            if (connexion == null || connexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connexion = DriverManager.getConnection(urlBase, utilisateurBase, motDePasseBase);
                System.out.println("✅ Connexion réussie à la base de données !");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "❌ Pilote MySQL introuvable !", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "❌ Erreur de connexion : " + ex.getMessage(), ex);
        }
    }

    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getConnexion() {
        return reconnecter();  // 🔹 Toujours retourner une connexion valide
    }

    private Connection reconnecter() {
        try {
            if (connexion == null || connexion.isClosed()) {
                System.out.println("🔄 Reconnexion en cours...");
                ouvrirConnexion();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la reconnexion", ex);
        }
        return connexion;
    }

    public void closeConnection() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
                System.out.println("✅ Connexion fermée avec succès !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "❌ Erreur lors de la fermeture de la connexion", ex);
        }
    }

    public static void main(String[] args) {
        Connexion connexionTest = Connexion.getInstance();
        if (connexionTest.getConnexion() != null) {
            System.out.println("✅ Connexion établie avec succès !");
        } else {
            System.err.println("❌ Échec de la connexion !");
        }

        // Fermer la connexion lorsque c'est terminé
        connexionTest.closeConnection();
    }
}
