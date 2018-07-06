package mvp.bhatti.com.mvplogin.model.interacter;

import android.text.TextUtils;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class LoginInteracterImpl implements LoginInteracter {
    @Override
    public void login(String username1, String password1, onLoginFinishedListener onLoginFinishedListener) {


        String password = null;
        String username = null;
        try {
            password = AESCrypt.decrypt("pswrd", password1);
            username = AESCrypt.decrypt("username", username1);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(username)) {
            onLoginFinishedListener.onUsernameError();
        } else if (TextUtils.isEmpty(password)) {
            onLoginFinishedListener.onPasswordError();
        } else if (username.equals("ahsan") && password.equals("123")) {
            onLoginFinishedListener.onSuccess();
        } else {
            onLoginFinishedListener.onFailure("Invalid Username or Password!");
        }
    }

    @Override
    public void onDestroy() {

    }


}
