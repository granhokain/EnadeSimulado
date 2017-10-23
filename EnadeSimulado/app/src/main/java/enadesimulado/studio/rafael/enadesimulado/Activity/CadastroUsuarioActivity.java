package enadesimulado.studio.rafael.enadesimulado.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import enadesimulado.studio.rafael.enadesimulado.Activity.Config.ConfiguracaoFirebase;
import enadesimulado.studio.rafael.enadesimulado.Activity.Model.Usuario;
import enadesimulado.studio.rafael.enadesimulado.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (EditText) findViewById(R.id.edit_cadastro_nome);
        email = (EditText) findViewById(R.id.edit_cadastro_email);
        senha = (EditText) findViewById(R.id.edit_cadastro_senha);
        botaoCadastrar = (Button) findViewById(R.id.bt_cadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nome.getText().toString().equals("") || email.getText().toString().equals("") || senha.getText().equals("")) {
                    Toast.makeText(CadastroUsuarioActivity.this, "Insira nome, email e senha para cadastrar", Toast.LENGTH_LONG).show();
                } else {
                    usuario = new Usuario();
                    usuario.setNome(nome.getText().toString());
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    cadastrarUsuario();
                }
            }
        });

    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(
                    CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CadastroUsuarioActivity.this, "Cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                                FirebaseUser usuarioFirebase = task.getResult().getUser();
                                usuario.setId(usuarioFirebase.getUid());
                                usuario.salvar();
                                autenticacao.signOut();
                                finish();
                            } else {
                                String erroExcecao = "";
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    erroExcecao = "Digite uma senha mais forte,com 6 caracteres";
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    erroExcecao = "O e-mail inválido, digite um novo email";
                                } catch (FirebaseAuthUserCollisionException e) {
                                    erroExcecao = "Esse email já está cadastrado !";
                                } catch (Exception e) {
                                    erroExcecao = "Cadastro não Efetuado";
                                    e.printStackTrace();
                                }

                                Toast.makeText(CadastroUsuarioActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );

    }
}
