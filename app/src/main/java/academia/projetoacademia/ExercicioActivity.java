package academia.projetoacademia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mari on 30/06/2017.
 */

public class ExercicioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        final EditText etNomeExercicio = (EditText) findViewById(R.id.etNomeExercicio);
        final EditText etSeries = (EditText) findViewById(R.id.etSeries);
        final EditText etRepeticoes = (EditText) findViewById(R.id.etRepeticoes);
        final EditText etIntervalo = (EditText) findViewById(R.id.etIntervalo);
        final Button btnCadastrar = (Button) findViewById(R.id.btnCadastrarExercicio);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nome_exercicio = etNomeExercicio.getText().toString();
                final int series = Integer.parseInt(etSeries.getText().toString());
                final int repeticoes = Integer.parseInt(etRepeticoes.getText().toString());
                final int intervalo = Integer.parseInt(etIntervalo.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean sucesso = jsonResponse.getBoolean("sucesso");


                            if (sucesso) {
                                Toast.makeText(getBaseContext(), "Exercicio cadastrado", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ExercicioActivity.this, MainActivity.class);
                                ExercicioActivity.this.startActivity(intent);
                            }
                            else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ExercicioActivity.this);
                                    builder.setMessage("Não foi possivel gravar")
                                            .setNegativeButton("Tente novamente", null)
                                            .create()
                                            .show();
                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ExercicioRequest exercicioRequest = new ExercicioRequest(nome_exercicio, series, repeticoes, intervalo, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ExercicioActivity.this);
                queue.add(exercicioRequest);


            }
        });

    }
}
