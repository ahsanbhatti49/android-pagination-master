package mvp.bhatti.com.mvplogin.presenter;

public interface LoginPresenter {

    void validateCredetials(String username,String password);
    void onDestroy();
}
