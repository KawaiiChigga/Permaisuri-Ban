package cv.sunwell.permaisuriban.modules.auth;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AuthSingleton
{
    private static AuthSingleton asInstance;
    private RequestQueue requestQueue;
    private static Context asCtx;

    private AuthSingleton(Context _context)
    {
        asCtx = _context;
        requestQueue = getRequestQueue ();
    }

    public RequestQueue getRequestQueue ()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue (asCtx.getApplicationContext ());
        }
        return requestQueue;
    }

    public static synchronized AuthSingleton getAsInstance(Context _context)
    {
        if(asInstance == null)
        {
            asInstance = new AuthSingleton (_context);
        }
        return asInstance;
    }

    public<T> void addToRequestQueue(Request<T> _request)
    {
        requestQueue.add(_request);
    }
}
