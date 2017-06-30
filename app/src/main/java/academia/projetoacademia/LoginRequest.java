package academia.projetoacademia;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mari on 11/05/2017.
 */

public class LoginRequest extends StringRequest {
    private static final String REQUEST_LOGIN_URL = "http://magalhaesg.pe.hu/loginAluno.php";
    private Map<String, String> params;

    public LoginRequest (String email_aluno, String senha, Response.Listener<String> listener) {
        super(Method.POST, REQUEST_LOGIN_URL, listener, null);
        params = new HashMap<>();
        params.put("email_aluno", email_aluno);
        params.put("senha", senha);
    }

    @Override

    public Map<String, String> getParams() {
        return params;
    }
}