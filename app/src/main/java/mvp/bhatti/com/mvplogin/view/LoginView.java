package mvp.bhatti.com.mvplogin.view;

public interface LoginView {

    void showProgress();
    void hideProgress();
    void setPasswordError();
    void setUsernameError();
    void navigateToMain();
    void showAlertError(String message);
}
