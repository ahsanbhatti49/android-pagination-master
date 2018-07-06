package mvp.bhatti.com.mvplogin.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import mvp.bhatti.com.mvplogin.presenter.LoginPresenter;
import mvp.bhatti.com.mvplogin.presenter.LoginPresenterImpl;
import mvp.bhatti.com.mvplogin.MainActivity;
import mvp.bhatti.com.mvplogin.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    EditText etUsername,etPassword;
    Button btLogin;
    ProgressBar progressBar;

    LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);
        btLogin = findViewById(R.id.bt_login);
        progressBar = findViewById(R.id.pg_progress);
        mLoginPresenter = new LoginPresenterImpl(this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String username = AESCrypt.encrypt("username",etUsername.getText().toString().trim());
                    String pswrd = AESCrypt.encrypt("pswrd",etPassword.getText().toString().trim());
                    mLoginPresenter.validateCredetials(username,pswrd);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setPasswordError() {
        etPassword.setError("Enter Password");

    }

    @Override
    public void setUsernameError() {
        etUsername.setError("Enter Username");

    }

    @Override
    public void navigateToMain() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }

    @Override
    public void showAlertError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }
}
