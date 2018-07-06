package mvp.bhatti.com.mvplogin.presenter;

import mvp.bhatti.com.mvplogin.view.LoginView;
import mvp.bhatti.com.mvplogin.model.interacter.LoginInteracter;
import mvp.bhatti.com.mvplogin.model.interacter.LoginInteracterImpl;

public class LoginPresenterImpl implements LoginPresenter,LoginInteracterImpl.onLoginFinishedListener {

    LoginView loginView;
    LoginInteracter loginInteracter;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteracter = new LoginInteracterImpl();
    }
    @Override
    public void validateCredetials(String username, String password) {
        if(loginView!=null){
            loginView.showProgress();
            loginInteracter.login(username,password,this);
        }
    }
    @Override
    public void onDestroy() {
        if(loginView!=null){
            loginView = null;
        }
    }
    @Override
    public void onUsernameError() {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.setUsernameError();
        }
    }
    @Override
    public void onPasswordError() {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.setPasswordError();
        }
    }
    @Override
    public void onSuccess() {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.navigateToMain();
        }
    }
    @Override
    public void onFailure(String message) {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.showAlertError(message);
        }
    }
}
