package cv.sunwell.permaisuriban.networking;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface
{
    @POST("login")
    Call<JsonObject> loginRequest(@Body JsonObject js);

    //@POST("/login")
    //void loginRequest(@Body TypedInput body, Callback<Response> callback);
}
