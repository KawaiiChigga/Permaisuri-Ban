package cv.sunwell.permaisuriban.networking;

import cv.sunwell.permaisuriban.model.JSON.Login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface
{
    @POST("login")
    Call<Login> loginRequest(@Body Login login);
}
