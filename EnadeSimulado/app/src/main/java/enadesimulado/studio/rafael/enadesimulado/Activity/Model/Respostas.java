package enadesimulado.studio.rafael.enadesimulado.Activity.Model;

/**
 * Created by Rafael on 25/05/2017.
 */
public class Respostas {
    private String question;
    private String situacao;

    public Respostas(String question, String situacao) {
        this.question = question;
        this.situacao = situacao;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
