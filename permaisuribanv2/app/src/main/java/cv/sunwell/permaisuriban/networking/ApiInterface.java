package cv.sunwell.permaisuriban.networking;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface
{
    @POST("login")
    Call<JsonObject> loginRequest(@Body JsonObject js);

    @POST("changePassword/{pathid}")
    Call<JsonObject> changePassword(@Path("pathid") int pathid, @Body JsonObject js);

    @GET("customer/{pathid}")
    Call<JsonObject> getUser(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid);

    @POST("register")
    Call<JsonObject> registerUser(@Body JsonObject js);

    @PUT("customer/{pathid}")
    Call<JsonObject> editUser(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid, @Body JsonObject js);
}
