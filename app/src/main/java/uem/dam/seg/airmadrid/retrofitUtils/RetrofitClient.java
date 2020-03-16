package uem.dam.seg.airmadrid.retrofitUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit = null;

    public Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient okBuilder = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC).setLevel
                    (HttpLoggingInterceptor.Level.BODY).setLevel(HttpLoggingInterceptor.Level.HEADERS)).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(APICalidadDelAireService.BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .client(okBuilder).build();
        }
        return retrofit;
    }
}
