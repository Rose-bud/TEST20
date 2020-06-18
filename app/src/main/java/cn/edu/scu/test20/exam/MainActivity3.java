package cn.edu.scu.test20.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scu.test20.R;

public class MainActivity3 extends AppCompatActivity {
    private TextView tvStartExam;
    private TextView tvStartExam2;
    private TextView tvStartExam3;
    private TextView tvStartExam4;
    private TextView tvStartExam5;
    private TextView tvStartExam6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvStartExam = (TextView) findViewById(R.id.tv_main_start_test1);
        tvStartExam2= (TextView) findViewById(R.id.tv_main_start_test2);
        tvStartExam3= (TextView) findViewById(R.id.tv_main_start_test3);
        tvStartExam4= (TextView) findViewById(R.id.tv_main_start_test4);
        tvStartExam5= (TextView) findViewById(R.id.tv_main_start_test5);
        tvStartExam6= (TextView) findViewById(R.id.tv_main_start_test6);
        tvStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,ExamActivity.class);
                intent.putExtra("testId",1);
                startActivity(intent);
            }
        });

        tvStartExam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,ExamActivity.class);
                intent.putExtra("testId",2);
                startActivity(intent);
            }
        });
        tvStartExam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,ExamActivity.class);
                intent.putExtra("testId",3);
                startActivity(intent);
            }
        });
        tvStartExam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,ExamActivity.class);
                intent.putExtra("testId",4);
                startActivity(intent);
            }
        });
        tvStartExam5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,ExamActivity.class);
                intent.putExtra("testId",5);
                startActivity(intent);
            }
        });
        tvStartExam6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,ExamActivity.class);
                intent.putExtra("testId",6);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
