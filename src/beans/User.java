package beans;

public class User {

    private String login;
    private String password;
    private String questionSecrete;
    private String reponseSecrete;

    public User(String login, String password, String questionSecrete, String reponseSecrete) {
        this.login = login;
        this.password = password;
        this.questionSecrete = questionSecrete;
        this.reponseSecrete = reponseSecrete;
    }

    // Getters et Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestionSecrete() {
        return questionSecrete;
    }

    public void setQuestionSecrete(String questionSecrete) {
        this.questionSecrete = questionSecrete;
    }

    public String getReponseSecrete() {
        return reponseSecrete;
    }

    public void setReponseSecrete(String reponseSecrete) {
        this.reponseSecrete = reponseSecrete;
    }

    @Override
    public String toString() {
        return "User{"
                + "login='" + login + '\''
                + ", password='" + password + '\''
                + ", questionSecrete='" + questionSecrete + '\''
                + ", reponseSecrete='" + reponseSecrete + '\''
                + '}';
    }
}
