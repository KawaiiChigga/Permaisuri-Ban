package cv.sunwell.permaisuriban.networking;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//http://178.128.220.10
public class ApiClient
{
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient (String baseUrl)
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor ();
        interceptor.setLevel (HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder ().addInterceptor (interceptor).build ();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder ()
                    .baseUrl (baseUrl)
                    .client (client)
                    .addConverterFactory (GsonConverterFactory.create ())
                    .build ();
        }
        return retrofit;
    }
}
