package world.fallen.android_boilerplate.data.http;

import android.annotation.SuppressLint;
import android.util.Log;

import world.fallen.android_boilerplate.data.http.model.Data;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * This service handle http request from server.
 * For public functions please check *Implement* section.
 */
public class GithubService {

    /**
     * Constant
     */
    public final String HOST_URL = "https://api.github.com/";


    /**
     * Demo
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private void UsageDemo() {
        // Build Service
        GithubService cs = new GithubService();

        // Ready Parameter
        String repoPath = "testacc01/testrepo01";
        String sha = "4f8a0fd8ab3537b85a64dcffa1487f4196164d78";

        // Call API
        cs.getData(repoPath, sha).subscribe(data -> {
            Log.d("GithubService", data.content);
        }, Throwable::printStackTrace);
    }


    /**
     * Server API
     */
    private interface ServerAPI {
        @GET("repos/{repoUser}/{repoName}/git/blobs/{sha}")
        Observable<Data> getData(
                @Path("repoUser") String repoUser,
                @Path("repoName") String repoName,
                @Path("sha") String sha,
                @Query("TT") String tt
        );
    }


    /**
     * Implement
     */
    public Observable<Data> getData(String repoPath, String sha) {
        // Convert Data
        String[] repoInfo = splitRepoPath(repoPath);
        String repoUser = repoInfo[0];
        String repoName = repoInfo[1];

        // Call API
        return getAPI().getData(repoUser, repoName, sha, String.valueOf(System.currentTimeMillis() / 1000))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * Init
     */
    private ServerAPI service;

    private ServerAPI getAPI() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            service = retrofit.create(ServerAPI.class);
        }
        return service;
    }


    /**
     * Helpers
     */
    private String[] splitRepoPath(String path) {
        return path.split("/");
    }
}