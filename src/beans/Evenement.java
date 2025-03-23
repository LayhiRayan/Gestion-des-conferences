package beans;

import java.util.Date;

public class Evenement {

    private int id;
    private String titre;
    private EThemeEvenement theme;
    private Date date;
    private String lieu;

    public Evenement(int id, String titre, EThemeEvenement theme, Date date, String lieu) {
        this.id = id;
        this.titre = titre;
        this.theme = theme;
        this.date = date;
        this.lieu = lieu;
    }

    public Evenement(String titre, EThemeEvenement theme, Date date, String lieu) {
        this.titre = titre;
        this.theme = theme;
        this.date = date;
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public EThemeEvenement getTheme() {
        return theme;
    }

    public void setTheme(EThemeEvenement theme) {
        this.theme = theme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    @Override
public String toString() {
    return this.titre; // ✅ Retourner le titre de l'événement
}

}
