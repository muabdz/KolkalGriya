package muadz.kolkalgriya;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class link extends StringRequest {

    private static final String linkRinku = "https://kolkalgriya.000webhostapp.com/linkGriya.php";
    private Map<String, String> params;

    public link (String id_user, Response.Listener<String> respon){
        super(Method.POST, linkRinku, respon, null);
        params = new HashMap<>();
        params.put("id_user", id_user);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
