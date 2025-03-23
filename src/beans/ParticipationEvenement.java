package beans;

/**
 * Classe représentant la participation d’un intervenant à un événement.
 * Cette classe modélise une relation plusieurs-à-plusieurs entre Evenement et Intervenant.
 */
public class ParticipationEvenement {

    private Evenement evenement;
    private Intervenant intervenant;

    // Constructeur
    public ParticipationEvenement(Evenement evenement, Intervenant intervenant) {
        this.evenement = evenement;
        this.intervenant = intervenant;
    }

    // Getter et Setter pour Evenement
    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Getter et Setter pour Intervenant
    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    // Redéfinir equals et hashCode si tu veux gérer les doublons dans des listes ou sets
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ParticipationEvenement that = (ParticipationEvenement) obj;

        return evenement.getId() == that.evenement.getId()
            && intervenant.getId() == that.intervenant.getId();
    }

    @Override
    public int hashCode() {
        return 31 * evenement.getId() + intervenant.getId();
    }
}
