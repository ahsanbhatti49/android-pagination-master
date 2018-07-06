package mvp.bhatti.com.mvplogin.model.interacter;

public interface LoginInteracter {

    interface onLoginFinishedListener{

        void onUsernameError();
        void onPasswordError();
        void onSuccess();
        void onFailure(String message);
    }

    void login(String username,String password,onLoginFinishedListener onLoginFinishedListener);
    void onDestroy();
}
