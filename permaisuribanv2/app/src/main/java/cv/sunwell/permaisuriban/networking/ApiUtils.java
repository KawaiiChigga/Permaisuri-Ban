package cv.sunwell.permaisuriban.networking;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    public static final String BASE_URL = "http://178.128.220.10/";


    private static Retrofit getApiClient(String baseUrl) {
        Retrofit retrofit;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder ().addInterceptor (interceptor).build ();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client (client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static ApiInterface getAPIService(){
        return getApiClient(BASE_URL).create(ApiInterface.class);
    }
}
