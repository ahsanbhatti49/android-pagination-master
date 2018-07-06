package mvp.bhatti.com.mvplogin.view;

import mvp.bhatti.com.mvplogin.model.MovieResponse;

public interface MainViewInterface {

    void showToast(String s);
    void displayMovies(MovieResponse movieResponse);
    void displayError(String s);
    void showProgress();
    void hideProgress();
}