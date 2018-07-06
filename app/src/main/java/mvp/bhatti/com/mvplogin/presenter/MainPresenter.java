package mvp.bhatti.com.mvplogin.presenter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import mvp.bhatti.com.mvplogin.model.MovieResponse;
import mvp.bhatti.com.mvplogin.model.network.NetworkClient;
import mvp.bhatti.com.mvplogin.model.network.NetworkInterface;
import mvp.bhatti.com.mvplogin.view.MainViewInterface;

public class MainPresenter implements MianPresenterInterface {

    MainViewInterface mvi;
    private String TAG = "MainPresenter";
    private int page=0;

    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;
    }
    @SuppressLint("CheckResult")
    @Override
    public void getMovies() {
        mvi.showProgress();
        getObservable().subscribeWith(getObserver());
    }

    public io.reactivex.Observable<MovieResponse> getObservable(){

        page++;
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMovies("56c3d135b11985f49ebff49c9d9b8fcd",page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResponse> getObserver(){
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG,"OnNext"+movieResponse.getTotalResults());
                mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                mvi.hideProgress();
            }
        };
    }
}