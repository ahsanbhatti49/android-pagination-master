package mvp.bhatti.com.mvplogin.model.network;

import io.reactivex.Observable;
import mvp.bhatti.com.mvplogin.model.MovieResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkInterface {

    @GET("discover/movie")
    Observable<MovieResponse> getMovies(@Query("api_key") String api_key,
                                        @Query("page") int page);
}