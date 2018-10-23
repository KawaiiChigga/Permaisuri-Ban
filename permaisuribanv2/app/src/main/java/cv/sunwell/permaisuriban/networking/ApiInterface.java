package cv.sunwell.permaisuriban.networking;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    @POST("login")
    Call<JsonObject> loginRequest(@Body JsonObject js);

    @POST("saveAddress/{pathid}")
    Call<JsonObject> saveAddress(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid, @Body JsonObject js);

    @DELETE("deleteAddress/{pathid}")
    Call<JsonObject> deleteAddress(@Header("remember_token") String remember_token, @Header("userid") int userid,  @Path("pathid") int pathid, @Query("address_id") int addressid);

    @PUT("updateAddress/{pathid}")
    Call<JsonObject> updateAddress(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid, @Body JsonObject js);

    @GET("getAddress/{pathid}")
    Call<JsonObject> getAddress(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid);

    @GET("province")
    Call<JsonObject> getProvince(@Header("remember_token") String remember_token, @Header("userid") int userid);

    @GET("regency/{pathid}")
    Call<JsonObject> getRegency(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid);

    @POST("changePassword/{pathid}")
    Call<JsonObject> changePassword(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid, @Body JsonObject js);

    @GET("customer/{pathid}")
    Call<JsonObject> getUser(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid);

    @POST("register")
    Call<JsonObject> registerUser(@Body JsonObject js);

    @PUT("customer/{pathid}")
    Call<JsonObject> editUser(@Header("remember_token") String remember_token, @Header("userid") int userid, @Path("pathid") int pathid, @Body JsonObject js);

    @GET("category")
    Call<JsonObject> getCategory(@Header("remember_token") String remember_token, @Header("userid") int userid);

    @GET("item")
    Call<JsonObject> getItem(@Header("remember_token") String remember_token, @Header("userid") int userid);
}
