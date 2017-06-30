package academia.projetoacademia;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mari on 30/06/2017.
 */

public class ExercicioRequest extends StringRequest {

    private static final String REQUEST_PARTIDA_REGISTRO_URL = "http://magalhaesg.pe.hu/registroExercicio.php";
    private Map<String, String> params;

    public ExercicioRequest (String nome_exercicio, int series, int repeticoes, int intervalo, Response.Listener<String> listener) {
        super(Method.POST, REQUEST_PARTIDA_REGISTRO_URL, listener, null);
        params = new HashMap<>();
        params.put("nome_exercicio", nome_exercicio);
        params.put("series", series + "");
        params.put("repeticoes", repeticoes + "");
        params.put("intervalo", intervalo + "");
    }

    @Override

    public Map<String, String> getParams() {
        return params;
    }
}
