package cv.sunwell.permaisuriban.networking;

public class ApiUtils {
    public static final String BASE_URL = "http://178.128.220.10/";

    public static ApiInterface getAPIService(){
        return ApiClient.getApiClient(BASE_URL).create(ApiInterface.class);
    }
}
