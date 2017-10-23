package enadesimulado.studio.rafael.enadesimulado.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import enadesimulado.studio.rafael.enadesimulado.Activity.Config.ConfiguracaoFirebase;
import enadesimulado.studio.rafael.enadesimulado.Activity.Model.Question;
import enadesimulado.studio.rafael.enadesimulado.Activity.Model.Respostas;
import enadesimulado.studio.rafael.enadesimulado.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class QuestoesActivity extends AppCompatActivity {

    private ImageView iv_picture;
    private RadioButton rbtnA,rbtnB,rbtnC,rbtnD,rbtnE;
    private RadioButton selecionado;
    private RadioGroup alternativas;
    private Button proxima;
    private TextView tv_question;
    private FirebaseStorage storage;
    private int currentQuestionIndex;
    private ArrayList<Question> questions;
    private ArrayList<Respostas> resp;
    private String situ1,situ2,situ3,situ4,situ5,situ6,situ7,situ8,situ9,situ10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);

        rbtnA = (RadioButton) findViewById(R.id.radioButton_A);
        rbtnB = (RadioButton) findViewById(R.id.radioButton_B);
        rbtnC = (RadioButton) findViewById(R.id.radioButton_C);
        rbtnD = (RadioButton) findViewById(R.id.radioButton_D);
        rbtnE = (RadioButton) findViewById(R.id.radioButton_E);
        alternativas = (RadioGroup) findViewById(R.id.grupoRadio);
        tv_question = (TextView) findViewById(R.id.question_tv);
        iv_picture = (ImageView) findViewById(R.id.questoes_imv);
        proxima = (Button) findViewById(R.id.bt_Proxima);
        currentQuestionIndex = 0;

        Intent intent = getIntent();
        String ano = intent.getStringExtra("ano");
        String curso = intent.getStringExtra("curso");
        if (ano.equals("2011") && curso.equals("Ciência da Computação")){
            this.initializeCC2011();
        }else if (ano.equals("2011") && curso.equals("Engenharia Civil")){
            this.initializeENG2011();
        }else if (ano.equals("2014") && curso.equals("Ciência da Computação")){
            this.initializeCC2014();
        }else if (ano.equals("2014") && curso.equals("Engenharia Civil")){
            this.initializeENG2014();
        }


        proxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resp = new ArrayList<Respostas>();
                int id = alternativas.getCheckedRadioButtonId();
                if (id == -1) {
                    Toast.makeText(QuestoesActivity.this, "Selecione uma alternativa", Toast.LENGTH_LONG).show();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 1")){
                    if (salvarResp()){
                        situ1 = "Certo";
                    }else{
                        situ1 = "Errado";
                    }
                    avancar();
                }else if(questions.get(currentQuestionIndex).getQuestionText().equals("Questão 2")) {
                    if (salvarResp()) {
                        situ2 = "Certo";
                    } else {
                        situ2 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 3")) {
                    if (salvarResp()) {
                        situ3 = "Certo";
                    } else {
                        situ3 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 4")) {
                    if (salvarResp()) {
                        situ4 = "Certo";
                    } else {
                        situ4 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 5")) {
                    if (salvarResp()) {
                        situ5 = "Certo";
                    } else {
                        situ5 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 6")) {
                    if (salvarResp()) {
                        situ6 = "Certo";
                    } else {
                        situ6 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 7")) {
                    if (salvarResp()) {
                        situ7 = "Certo";
                    } else {
                        situ7 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 8")) {
                    if (salvarResp()) {
                        situ8 = "Certo";
                    } else {
                        situ8 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 9")) {
                    if (salvarResp()) {
                        situ9 = "Certo";
                    } else {
                        situ9 = "Errado";
                    }
                    avancar();
                }else if (questions.get(currentQuestionIndex).getQuestionText().equals("Questão 10")) {
                    if (salvarResp()) {
                        situ10 = "Certo";
                    } else {
                        situ10 = "Errado";
                    }
                    avancar();
                }
            }

        });

       }

    private boolean salvarResp(){
        String res ="x";
        int qt = alternativas.getCheckedRadioButtonId();
        selecionado = (RadioButton) findViewById(qt);
        if (selecionado == rbtnA) res = "a";
        if (selecionado == rbtnB) res = "b";
        if (selecionado == rbtnC) res = "c";
        if (selecionado == rbtnD) res = "d";
        if (selecionado == rbtnE) res = "e";
        return questions.get(currentQuestionIndex).isCorrectAnswer(res);
    }

    private void initializeCC2011(){

        storage = ConfiguracaoFirebase.getStorage();
        questions = new ArrayList<Question>();

        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao1.jpg"),"Questão 1",".A",".B",".C",".D",".E","c"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao2.jpg"),"Questão 2",".A",".B",".C",".D",".E","b"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao3.jpg"),"Questão 3",".A",".B",".C",".D",".E","d"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao4.jpg"),"Questão 4",".A",".B",".C",".D",".E","a"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao5.jpg"),"Questão 5",".A",".B",".C",".D",".E","b"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao6.jpg"),"Questão 6",".A",".B",".C",".D",".E","e"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao7.jpg"),"Questão 7",".A",".B",".C",".D",".E","d"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao8.jpg"),"Questão 8",".A",".B",".C",".D",".E","a"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao9.jpg"),"Questão 9",".A",".B",".C",".D",".E","e"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao10.jpg"),"Questão 10",".A",".B",".C",".D",".E","d"));
        //Essa questão abaixo é uma copia da 10 somente para ir para proxima tela. Essa questão nao é exibida!
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2011").child("questao10.jpg"),"Questão 10",".A",".B",".C",".D",".E","d"));
        this.displayQuestion(currentQuestionIndex);

    }

    private void initializeENG2011(){

        storage = ConfiguracaoFirebase.getStorage();
        questions = new ArrayList<Question>();

        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao1.jpg"),"Questão 1",".A",".B",".C",".D",".E","a"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao2.jpg"),"Questão 2",".A",".B",".C",".D",".E","d"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao3.jpg"),"Questão 3",".A",".B",".C",".D",".E","d"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao4.jpg"),"Questão 4",".A",".B",".C",".D",".E","a"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao5.jpg"),"Questão 5",".A",".B",".C",".D",".E","d"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao6.jpg"),"Questão 6",".A",".B",".C",".D",".E","c"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao7.jpg"),"Questão 7",".A",".B",".C",".D",".E","c"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao8.jpg"),"Questão 8",".A",".B",".C",".D",".E","e"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao9.jpg"),"Questão 9",".A",".B",".C",".D",".E","b"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao10.jpg"),"Questão 10",".A",".B",".C",".D",".E","b"));
        //test
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2011").child("questao10.jpg"),"Questão 10",".A",".B",".C",".D",".E","b"));
        this.displayQuestion(currentQuestionIndex);

    }

    private void initializeCC2014(){

        storage = ConfiguracaoFirebase.getStorage();
        questions = new ArrayList<Question>();

        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao1.jpg"),"Questão 1",".A",".B",".C",".D",".E","a"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao2.jpg"),"Questão 2",".A",".B",".C",".D",".E","b"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao3.jpg"),"Questão 3",".A",".B",".C",".D",".E","c"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao4.jpg"),"Questão 4",".A",".B",".C",".D",".E","a"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao5.jpg"),"Questão 5",".A",".B",".C",".D",".E","e"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao6.jpg"),"Questão 6",".A",".B",".C",".D",".E","b"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao7.jpg"),"Questão 7",".A",".B",".C",".D",".E","a"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao8.jpg"),"Questão 8",".A",".B",".C",".D",".E","c"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao9.jpg"),"Questão 9",".A",".B",".C",".D",".E","b"));
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao10.jpg"),"Questão 10",".A",".B",".C",".D",".E","b"));
        //Essa questão abaixo é uma copia da 10 somente para ir para proxima tela. Essa questão nao é exibida!
        questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Ciencia2014").child("questao10.jpg"),"Questão 10",".A",".B",".C",".D",".E","b"));
        this.displayQuestion(currentQuestionIndex);

    }

    private void initializeENG2014(){

        storage = ConfiguracaoFirebase.getStorage();
        questions = new ArrayList<Question>();

            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao1.jpg"), "Questão 1", ".A", ".B", ".C", ".D", ".E", "e"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao2.jpg"), "Questão 2", ".A", ".B", ".C", ".D", ".E", "d"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao3.jpg"), "Questão 3", ".A", ".B", ".C", ".D", ".E", "c"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao4.jpg"), "Questão 4", ".A", ".B", ".C", ".D", ".E", "d"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao5.jpg"), "Questão 5", ".A", ".B", ".C", ".D", ".E", "a"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao6.jpg"), "Questão 6", ".A", ".B", ".C", ".D", ".E", "c"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao7.jpg"), "Questão 7", ".A", ".B", ".C", ".D", ".E", "c"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao8.jpg"), "Questão 8", ".A", ".B", ".C", ".D", ".E", "a"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao9.jpg"), "Questão 9", ".A", ".B", ".C", ".D", ".E", "d"));
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao10.jpg"), "Questão 10", ".A", ".B", ".C", ".D", ".E", "d"));
            //Essa questão abaixo é uma copia da 10 somente para ir para proxima tela. Essa questão nao é exibida!
            questions.add(new Question(storage.getReferenceFromUrl("gs://enade-simulado.appspot.com/Engenharia2014").child("questao10.jpg"), "Questão 10", ".A", ".B", ".C", ".D", ".E", "d"));
            this.displayQuestion(currentQuestionIndex);

    }

    private void displayQuestion(int index){
        StorageReference storageRef = questions.get(currentQuestionIndex).getPictureID();
        try {
            final File localFile = File.createTempFile("image","jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap questao = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    iv_picture.setImageBitmap(questao);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_question.setText(questions.get(currentQuestionIndex).getQuestionText());
        rbtnA.setText(questions.get(currentQuestionIndex).getChoiceA());
        rbtnB.setText(questions.get(currentQuestionIndex).getChoiceB());
        rbtnC.setText(questions.get(currentQuestionIndex).getChoiceC());
        rbtnD.setText(questions.get(currentQuestionIndex).getChoiceD());
        rbtnE.setText(questions.get(currentQuestionIndex).getChoiceE());
        alternativas.clearCheck();

    }

    private void avancar(){
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        displayQuestion(currentQuestionIndex);
        Intent teste = new Intent(QuestoesActivity.this, ResultadoActivity.class);
        teste.putExtra("situ1",situ1);
        teste.putExtra("situ2",situ2);
        teste.putExtra("situ3",situ3);
        teste.putExtra("situ4",situ4);
        teste.putExtra("situ5",situ5);
        teste.putExtra("situ6",situ6);
        teste.putExtra("situ7",situ7);
        teste.putExtra("situ8",situ8);
        teste.putExtra("situ9",situ9);
        teste.putExtra("situ10",situ10);
        if (currentQuestionIndex == 10){
            startActivity(teste);
        }
    }
}
