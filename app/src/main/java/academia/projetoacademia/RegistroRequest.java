package academia.projetoacademia;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mari on 11/05/2017.
 */

public class RegistroRequest extends StringRequest {
    private static final String REQUEST_USUARIO_INSERT_URL = "http://magalhaesg.pe.hu/registroAluno.php";
    private Map<String, String> params;

    public RegistroRequest(String nome_aluno, String nascimento_aluno, String sexo_aluno, long cpf_aluno, long rg_aluno, String email_aluno, long celular_aluno, String objetivo_aluno, String senha, Response.Listener<String> listener) {
        super(Method.POST, REQUEST_USUARIO_INSERT_URL, listener, null);
        params = new HashMap<>();
        params.put("nome_aluno", nome_aluno);
        params.put("nascimento_aluno", nascimento_aluno);
        params.put("sexo_aluno", sexo_aluno);
        params.put("cpf_aluno", cpf_aluno + "");
        params.put("rg_aluno", rg_aluno + "");
        params.put("email_aluno", email_aluno);
        params.put("celular_aluno", celular_aluno + "");
        params.put("objetivo_aluno", objetivo_aluno);
        params.put("senha", senha);

    }

    @Override

    public Map<String, String> getParams() {
        return params;
    }

}
