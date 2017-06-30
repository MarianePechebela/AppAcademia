package academia.projetoacademia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton btnAddExercicio = (ImageButton) findViewById(R.id.btnAddExercicio);
        final ImageButton btnListarExercicio = (ImageButton) findViewById(R.id.btnListarExercicios);

        btnAddExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exercicioIntent = new Intent(MainActivity.this, ExercicioActivity.class);
                MainActivity.this.startActivity(exercicioIntent);

            }
        });

    }
}
