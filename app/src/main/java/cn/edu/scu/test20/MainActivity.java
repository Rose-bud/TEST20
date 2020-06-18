package cn.edu.scu.test20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;
import cn.edu.scu.test20.bean.TEST;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.edu.scu.test20.bean.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private int RememberFlag = 0;
    private EditText edtUserName;
    private EditText edtPassword;
    private Button bntLogin;
    private Button bntR;
    private CheckBox rememberBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "56a832cf0b1a430d9eada88f2c39a39a");//绑定Bmob后端
        /*绑定控件*/
        edtPassword=findViewById(R.id.et_login_pwd);
        edtUserName=findViewById(R.id.et_login_username);
        bntLogin=findViewById(R.id.bt_login_submit);
        bntR=findViewById(R.id.bt_login_register);
        rememberBox=findViewById(R.id.cb_remember_login);
        rememberBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    RememberFlag=1;
                }else{
                    RememberFlag=0;
                }
            }
        });
        bntR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtUserName.getText().toString();
                String password=edtPassword.getText().toString();
                if(username.length()==0||password.length()==0){
                    edtUserName.setError("用户名或密码不能为空");
                    edtUserName.setFocusable(true);
                }else {
                    SignUp(username,password);
                }
            }
        });
        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtUserName.getText().toString();
                String password=edtPassword.getText().toString();
                if(username.length()==0||password.length()==0){
                    edtUserName.setError("用户名或密码不能为空");
                    edtUserName.setFocusable(true);
                }else {
                    loginByAccount(username,password);
                }
            }
        });
        /*判定是否已经登录*/
//        if(RememberFlag==1){
//        }
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,BottomNavigatorActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this,"已登录",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this,"未登录",Toast.LENGTH_LONG).show();
        }
    }
    private void SignUp(String username,String password){//注册方法
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void loginByAccount(String username,String password) {//登录方法
        BmobUser.loginByAccount(username, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
//                    intent.setClass(MainActivity.this,UserActivity.class);
                    intent.setClass(MainActivity.this, LittleVideoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}