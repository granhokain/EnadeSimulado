package enadesimulado.studio.rafael.enadesimulado.Activity.Model;

import com.google.firebase.storage.StorageReference;

/**
 * Created by Rafael on 25/05/2017.
 */
public class Question {
    private StorageReference pictureID;
    private String questionText;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String choiceE;

    private String correctAnswer;
    private boolean creditAlreadyGiven;

    public Question(StorageReference pictureID, String questionText, String choiceA, String choiceB, String choiceC, String choiceD, String choiceE, String correctAnswer) {
        this.pictureID = pictureID;
        this.questionText = questionText;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.choiceE = choiceE;
        this.correctAnswer = correctAnswer;
        this.creditAlreadyGiven = false;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getChoiceE() {
        return choiceE;
    }

    public void setChoiceE(String choiceE) {
        this.choiceE = choiceE;
    }

    public StorageReference getPictureID() {
        return pictureID;
    }

    public void setPictureID(StorageReference pictureID) {
        this.pictureID = pictureID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isCreditAlreadyGiven() {
        return creditAlreadyGiven;
    }

    public void setCreditAlreadyGiven(boolean creditAlreadyGiven) {
        this.creditAlreadyGiven = creditAlreadyGiven;
    }


    public boolean isCorrectAnswer(String selectedAnswer){
        return (selectedAnswer.equals(correctAnswer));
    }

    @Override
    public String toString() {
        return questionText;
    }
}
