package enadesimulado.studio.rafael.enadesimulado.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import enadesimulado.studio.rafael.enadesimulado.Activity.Config.ConfiguracaoFirebase;
import enadesimulado.studio.rafael.enadesimulado.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseAuth autenticacao;
    private Spinner sistemas_ano;
    private Spinner sistemas_curso;
    private ImageButton oqButton;
    private Button iniciSimu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600){
            setContentView(R.layout.activity_main_tablet);
        }else {
            setContentView(R.layout.activity_main);
        }

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        sistemas_ano = (Spinner) findViewById(R.id.spin_ano);
        ArrayAdapter adapter_ano = ArrayAdapter.createFromResource(this, R.array.ano,R.layout.support_simple_spinner_dropdown_item);
        sistemas_ano.setAdapter(adapter_ano);

        sistemas_curso = (Spinner) findViewById(R.id.spin_curso);
        ArrayAdapter adapter_curso = ArrayAdapter.createFromResource(this, R.array.curso,R.layout.support_simple_spinner_dropdown_item);
        sistemas_curso.setAdapter(adapter_curso);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("Enade Simulado");
        setSupportActionBar(toolbar);

        oqButton = (ImageButton) findViewById(R.id.bt_oqe);
        oqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("O que é ENADE SIMULADO");
                builder.setMessage("O Enade Simulado é um app onde você pode responder provas de edições anteriores do Enade. " +
                        "Dessa maneira você pode melhorar suas habilidades e conhecimento, para ir mais preparado para a prova. " +
                        "Escolha a prova referente ao ano e o Curso que deseja simular e basta iniciar o simulado. " +
                        "Boa prova e bons estudos !! ");
                builder.setCancelable(false);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        iniciSimu = (Button) findViewById(R.id.bt_IniciSimu);
        iniciSimu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirQuestionario();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sair:
                deslogarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deslogarUsuario(){

        autenticacao.signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void abrirQuestionario(){
        if (sistemas_ano.getSelectedItem().toString().equals("Escolha o ano") ||
                sistemas_curso.getSelectedItem().toString().equals("Escolha o curso")){
            Toast.makeText(MainActivity.this,"Escolha o ano e o curso para continuar",Toast.LENGTH_LONG).show();
        }else {
            String ano = sistemas_ano.getSelectedItem().toString();
            String curso = sistemas_curso.getSelectedItem().toString();
            Intent intent = new Intent(MainActivity.this, QuestoesActivity.class);
            intent.putExtra("ano", ano);
            intent.putExtra("curso", curso);
            startActivity(intent);
        }

        //finish();
    }

}
