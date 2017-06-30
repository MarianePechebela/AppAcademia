package academia.projetoacademia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mari on 11/05/2017.
 */

public class RegistroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aluno);

        //INICIALIZA VARIÁVES DA ACTIVITY
        final EditText etNomeAluno = (EditText) findViewById(R.id.etNomeAluno);
        final EditText etNascimentoAluno = (EditText) findViewById(R.id.etNascimentoAluno);
        final Spinner spSexoAluno = (Spinner) findViewById(R.id.spinnerSexoAluno);
        final EditText etCpfAluno = (EditText) findViewById(R.id.etCPFAluno);
        final EditText etRgAluno = (EditText) findViewById(R.id.etRgAluno);
        final EditText etEmailAluno = (EditText) findViewById(R.id.etEmailAluno);
        final EditText etCelularAluno = (EditText) findViewById(R.id.etCelularAluno);
        final Spinner spOnjetivoAluno = (Spinner) findViewById(R.id.spinnerObjetivo);
        final EditText etSenhaAluno = (EditText) findViewById(R.id.etSenhaAluno);
        final Button btnSalvar = (Button) findViewById(R.id.btnSalvar);


        //SPINNER SEXO ALUNO
        ArrayAdapter<String> spinnerAdapterSexo = new ArrayAdapter<String>(RegistroActivity.this
                , android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sexo_aluno));
        spinnerAdapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSexoAluno.setAdapter(spinnerAdapterSexo);

        //SPINNER OBJETIVOS ALUNO
        ArrayAdapter<String> spinnerAdapterObjetivo = new ArrayAdapter<String>(RegistroActivity.this
                , android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.objetivos_aluno));
        spinnerAdapterObjetivo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOnjetivoAluno.setAdapter(spinnerAdapterObjetivo);

        //ACÃO DO BOTÃO SALVAR
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nome_aluno = etNomeAluno.getText().toString();
                final String nascimento_aluno = etNascimentoAluno.getText().toString();
                final String sexo_aluno = spSexoAluno.getSelectedItem().toString();
                final long cpf_aluno = Long.parseLong(etCpfAluno.getText().toString());
                final long rg_aluno = Long.parseLong(etRgAluno.getText().toString());
                final String email_aluno = etEmailAluno.getText().toString();
                final long celular_aluno = Long.parseLong(etCelularAluno.getText().toString());
                final String objetivo_aluno = spOnjetivoAluno.getSelectedItem().toString();
                final String senha = etSenhaAluno.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean sucesso = jsonResponse.getBoolean("sucesso");

                            if (sucesso){
                                Toast.makeText(getBaseContext(), "Aluno Registrado Com Sucesso",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                                RegistroActivity.this.startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                                builder.setMessage("Não foi possivel gravar dados")
                                        .setNegativeButton("Tente novamente", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //ADICIONA AS VARIÁVES A FILA DA REQUEST PARA ENVIAR AO WEBSERVICE
                RegistroRequest registroRequest = new RegistroRequest(nome_aluno, nascimento_aluno, sexo_aluno, cpf_aluno, rg_aluno, email_aluno, celular_aluno,objetivo_aluno, senha, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegistroActivity.this);
                queue.add(registroRequest);
            }
        });

    }

}

