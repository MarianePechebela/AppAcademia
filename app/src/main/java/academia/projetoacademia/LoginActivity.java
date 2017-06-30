package academia.projetoacademia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Mari on 09/04/2017.
 */
public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etEmailAluno = (EditText) findViewById(R.id.etEmailAluno);
        final EditText etSenhaAluno = (EditText) findViewById(R.id.etSenhaAluno);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final TextView registrarLink = (TextView) findViewById(R.id.tvRegistrarAluno);


        registrarLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registarIntent = new Intent(LoginActivity.this, RegistroActivity.class);
                LoginActivity.this.startActivity(registarIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_aluno = etEmailAluno.getText().toString();
                final String senha = etSenhaAluno.getText().toString();

                //VALIDAÇÃO E-MAIL
                if (etEmailAluno == null || etEmailAluno.equals("")){
                    etEmailAluno.setError("Campo obrigatório");
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean sucesso = jsonResponse.getBoolean("sucesso");


                            if (sucesso) {
                                Toast.makeText(getBaseContext(), "Bem-vindo", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(intent);


                            } else {
                                Toast.makeText(getBaseContext(), "E-mail/Senha Incorreto, ou nao Registrado", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Não foi possivel Logar")
                                        .setNegativeButton("Tente novamente", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(email_aluno, senha, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });
    }
}

