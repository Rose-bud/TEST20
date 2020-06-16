package cn.edu.scu.test20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.edu.scu.test20.bean.User;

public class UserActivity extends AppCompatActivity {
    private TextView txtNickName;
    private TextView txtStudentID;
    private TextView txtSchool;
    private TextView txtEMail;
    private TextView txtPhone;
    private Button bnt_c_Password;
    private Button bnt_c_Message;
    private Button bnt_quit;
    private ImageView HeadPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bmob.initialize(this, "56a832cf0b1a430d9eada88f2c39a39a");//绑定后端
        /*绑定控件*/
        txtNickName=findViewById(R.id.txt_NickName);
        txtStudentID=findViewById(R.id.txt_studentID);
        txtEMail=findViewById(R.id.txt_mail);
        txtPhone=findViewById(R.id.txt_phone);
        txtSchool=findViewById(R.id.txt_school);
        bnt_c_Message=findViewById(R.id.bnt_ChangeMessage);
        bnt_c_Password=findViewById(R.id.bnt_ChangePassWord);
        bnt_quit=findViewById(R.id.bnt_quit);
        HeadPic=findViewById(R.id.HeadPic1);

        getMessage();

        bnt_c_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modify();
            }
        });
        bnt_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
                Intent intent= new Intent(UserActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        bnt_c_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }

    public void getMessage(){//得到用户信息
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            String StudentID = (String) user.getUsername();
            String NickName = (String) BmobUser.getObjectByKey("NickName");
            String School = (String) BmobUser.getObjectByKey("School");
            String mobilePhoneNumber = (String) BmobUser.getObjectByKey("mobilePhoneNumber");
            String EMail = (String) BmobUser.getObjectByKey("email");
            File HeadP = (File) BmobUser.getObjectByKey("HeadPic");

            txtNickName.setText(NickName);
            txtStudentID.setText(StudentID);
            txtSchool.setText(School);
            txtPhone.setText(mobilePhoneNumber);
            txtEMail.setText(EMail);

        } else {
            Toast.makeText(UserActivity.this,"尚未登录，请先登录",Toast.LENGTH_LONG).show();
        }
    }

    public void UpdateMessage(String nickname,String School,String Email,String Phone){//更新用户数据
        final User user = BmobUser.getCurrentUser(User.class);
        user.setNickName(nickname);
        user.setSchool(School);
        user.setEmail(Email);
        user.setMobilePhoneNumber(Phone);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(UserActivity.this,"更新用户信息成功",Toast.LENGTH_LONG).show();
                    getMessage();
                } else {
                    Toast.makeText(UserActivity.this,"更新用户信息失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updatePassword(){//更新密码
        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
        LayoutInflater factory =LayoutInflater.from(UserActivity.this);
        View modyfyView =factory.inflate(R.layout.update_password_layout,null);
        final EditText edtOldPwd = modyfyView.findViewById(R.id.edt_oldPwd);
        final EditText edtNewPwd = modyfyView.findViewById(R.id.edt_newPwd);
        final EditText edtNewPwd2 = modyfyView.findViewById(R.id.edt_newPwd2);

        builder.setTitle("修改密码:");
        builder.setView(modyfyView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldPwd=edtOldPwd.getText().toString();
                String newPwd=edtNewPwd.getText().toString();
                String newPwd2=edtNewPwd2.getText().toString();
                if(oldPwd.length()==0||newPwd.length()==0){
                    edtOldPwd.setError("密码不能为空");
                    edtOldPwd.setFocusable(true);
                }else if(!newPwd.equals(newPwd2)){
                    edtNewPwd2.setError("密码输入不一致");
                    edtNewPwd2.setFocusable(true);
                }else{
                    BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(UserActivity.this,"更新成功",Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(UserActivity.this,"更新失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void Modify(){//更改用户信息窗口
        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
        LayoutInflater factory =LayoutInflater.from(UserActivity.this);
        View modyfyView =factory.inflate(R.layout.update_message_layout,null);
        final EditText edtNickName = modyfyView.findViewById(R.id.edt_nickname);
        final EditText edtEmail = modyfyView.findViewById(R.id.edt_mail);
        final EditText edtSchool = modyfyView.findViewById(R.id.edt_school);
        final EditText edtPhone = modyfyView.findViewById(R.id.edt_phone);

        edtNickName.setText(txtNickName.getText().toString());
        edtSchool.setText(txtSchool.getText().toString());
        edtEmail.setText(txtEMail.getText().toString());
        edtPhone.setText(txtPhone.getText().toString());

        builder.setTitle("修改个人信息:");
        builder.setView(modyfyView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UpdateMessage(edtNickName.getText().toString(),edtSchool.getText().toString(),edtEmail.getText().toString(),edtPhone.getText().toString());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}