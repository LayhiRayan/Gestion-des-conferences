
package test;

import beans.Evenement;
import beans.EThemeEvenement;
import beans.Intervenant;
import beans.ParticipationEvenement;
import services.EvenementService;
import services.IntervenantService;
import services.ParticipationEvenementService;

import java.sql.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        EvenementService evenementService = new EvenementService();
        ParticipationEvenementService participationService = new ParticipationEvenementService();
        IntervenantService intervenantService = new IntervenantService();

        evenementService.create(new Evenement(0, "Vers l'avenir", EThemeEvenement.TECHNOLOGIE, Date.valueOf("2025-06-10"), "Rabat", null));
        evenementService.create(new Evenement(0, "Exploration scientifique", EThemeEvenement.SCIENCE, Date.valueOf("2025-06-14"), "Marrakech", null));
        evenementService.create(new Evenement(0, "Innovations du futur", EThemeEvenement.INNOVATION, Date.valueOf("2025-03-10"), "Tanger", null));
        evenementService.create(new Evenement(0, "Éducation 2030", EThemeEvenement.EDUCATION, Date.valueOf("2025-04-10"), "Marrakech", null));

        List<Evenement> evenements = evenementService.findAll();
        System.out.println("\n Liste des événements :");
        for (Evenement e : evenements) {
            System.out.println(e.getId() + " - " + e.getTheme() + " - " + e.getDate() + " - " + e.getLieu());
        }

        intervenantService.create(new Intervenant(0, "LAYHI", "Rayan", "Intelligence Artificielle"));
        intervenantService.create(new Intervenant(0, "ALAMI", "Redouan", "CyberSécurité"));
        intervenantService.create(new Intervenant(0, "ZAHID", "Noureddine", "Data Science"));
        intervenantService.create(new Intervenant(0, "LACHGAR", "Mohamed", "Technologies Web"));

        List<Intervenant> intervenants = intervenantService.findAll();
        System.out.println("\n Liste des intervenants :");
        for (Intervenant i : intervenants) {
            System.out.println(i.getId() + " - " + i.getNom() + " " + i.getPrenom() + " - " + i.getSpecialite());
        }

        if (!evenements.isEmpty() && !intervenants.isEmpty()) {
            for (int i = 0; i < evenements.size() && i < intervenants.size(); i++) {
                participationService.create(new ParticipationEvenement(evenements.get(i), intervenants.get(i)));
            }
        }

        List<ParticipationEvenement> participations = participationService.findAll();
        System.out.println("\n Liste des participations :");
        for (ParticipationEvenement p : participations) {
            System.out.println("Événement : " + p.getEvenement().getTheme() + " | Intervenant : " + p.getIntervenant().getNom());
        }

        Date dateRecherche = Date.valueOf("2025-06-10");
        System.out.println("\n Recherche des événements par date (" + dateRecherche + ") :");
        for (Evenement e : evenementService.findByDate(dateRecherche)) {
            System.out.println(e.getId() + " - " + e.getTitre() + " - " + e.getTheme() + " - " + e.getDate() + " - " + e.getLieu());
        }

        String lieuRecherche = "Marrakech";
        System.out.println("\n Recherche des événements par lieu (" + lieuRecherche + ") :");
        for (Evenement e : evenementService.findByLieu(lieuRecherche)) {
            System.out.println(e.getId() + " - " + e.getTheme() + " - " + e.getDate() + " - " + e.getLieu());
        }

        String nomRecherche = "LAYHI";
        System.out.println("\n Recherche d'un intervenant par nom (" + nomRecherche + ") :");
        for (Intervenant i : intervenantService.findByNom(nomRecherche)) {
            System.out.println(i.getId() + " - " + i.getNom() + " " + i.getPrenom() + " - " + i.getSpecialite());
        }
    }
}


