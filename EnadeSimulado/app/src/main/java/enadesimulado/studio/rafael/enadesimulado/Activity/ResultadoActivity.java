package enadesimulado.studio.rafael.enadesimulado.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import enadesimulado.studio.rafael.enadesimulado.Activity.Config.ConfiguracaoFirebase;
import enadesimulado.studio.rafael.enadesimulado.Activity.Model.Respostas;
import enadesimulado.studio.rafael.enadesimulado.R;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseAuth autenticacao;
    private TextView qt1,qt2,qt3,qt4,qt5,qt6,qt7,qt8,qt9,qt10;
    private TextView st1,st2,st3,st4,st5,st6,st7,st8,st9,st10;
    private Button novo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600){
            setContentView(R.layout.activity_resultado_tablet);
        }else {
            setContentView(R.layout.activity_resultado);
        }

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("Enade Simulado");
        setSupportActionBar(toolbar);

        qt1 = (TextView) findViewById(R.id.questao1_id);
        qt2 = (TextView) findViewById(R.id.questao2_id);
        qt3 = (TextView) findViewById(R.id.questao3_id);
        qt4 = (TextView) findViewById(R.id.questao4_id);
        qt5 = (TextView) findViewById(R.id.questao5_id);
        qt6 = (TextView) findViewById(R.id.questao6_id);
        qt7 = (TextView) findViewById(R.id.questao7_id);
        qt8 = (TextView) findViewById(R.id.questao8_id);
        qt9 = (TextView) findViewById(R.id.questao9_id);
        qt10 = (TextView) findViewById(R.id.questao10_id);

        st1 = (TextView) findViewById(R.id.situacao1_id);
        st2 = (TextView) findViewById(R.id.situacao2_id);
        st3 = (TextView) findViewById(R.id.situacao3_id);
        st4 = (TextView) findViewById(R.id.situacao4_id);
        st5 = (TextView) findViewById(R.id.situacao5_id);
        st6 = (TextView) findViewById(R.id.situacao6_id);
        st7 = (TextView) findViewById(R.id.situacao7_id);
        st8 = (TextView) findViewById(R.id.situacao8_id);
        st9 = (TextView) findViewById(R.id.situacao9_id);
        st10 = (TextView) findViewById(R.id.situacao10_id);

        Intent intent = getIntent();
        String situ1 = intent.getStringExtra("situ1");
        qt1.setText("Questão 1");
        st1.setText(situ1.toString());
        String situ2 = intent.getStringExtra("situ2");
        qt2.setText("Questão 2");
        st2.setText(situ2.toString());
        String situ3 = intent.getStringExtra("situ3");
        qt3.setText("Questão 3");
        st3.setText(situ3.toString());
        String situ4 = intent.getStringExtra("situ4");
        qt4.setText("Questão 4");
        st4.setText(situ4.toString());
        String situ5 = intent.getStringExtra("situ5");
        qt5.setText("Questão 5");
        st5.setText(situ5.toString());
        String situ6 = intent.getStringExtra("situ6");
        qt6.setText("Questão 6");
        st6.setText(situ6.toString());
        String situ7 = intent.getStringExtra("situ7");
        qt7.setText("Questão 7");
        st7.setText(situ7.toString());
        String situ8 = intent.getStringExtra("situ8");
        qt8.setText("Questão 8");
        st8.setText(situ8.toString());
        String situ9 = intent.getStringExtra("situ9");
        qt9.setText("Questão 9");
        st9.setText(situ9.toString());
        String situ10 = intent.getStringExtra("situ10");
        qt10.setText("Questão 10");
        st10.setText(situ10.toString());

        novo = (Button) findViewById(R.id.novo_id);
        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
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
    public boolean onOptionsItemSelected(MenuItem item1) {
        switch (item1.getItemId()){
            case R.id.action_sair_resul:
                deslogarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item1);
        }
    }

    public void deslogarUsuario(){

        autenticacao.signOut();
        Intent intent = new Intent(ResultadoActivity.this,LoginActivity.class);
        startActivity(intent);
        //finish();
    }
}
