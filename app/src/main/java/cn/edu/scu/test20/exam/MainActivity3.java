package cn.edu.scu.test20.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scu.test20.R;

public class MainActivity3 extends AppCompatActivity {
    private Button tvStartExam;
    private Button tvStartExam2;
    private Button tvStartExam3;
    private Button tvStartExam4;
    private Button tvStartExam5;
    private Button tvStartExam6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvStartExam = (Button) findViewById(R.id.bnt_test1);
        tvStartExam2= (Button) findViewById(R.id.bnt_test2);
        tvStartExam3= (Button) findViewById(R.id.bnt_test3);
        tvStartExam4= (Button) findViewById(R.id.bnt_test4);
        tvStartExam5= (Button) findViewById(R.id.bnt_test5);
        tvStartExam6= (Button) findViewById(R.id.bnt_test6);
        tvStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, ExamActivity.class);
                intent.putExtra("testId",1);
                startActivity(intent);
            }
        });

        tvStartExam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, ExamActivity.class);
                intent.putExtra("testId",2);
                startActivity(intent);
            }
        });
        tvStartExam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, ExamActivity.class);
                intent.putExtra("testId",3);
                startActivity(intent);
            }
        });
        tvStartExam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, ExamActivity.class);
                intent.putExtra("testId",4);
                startActivity(intent);
            }
        });
        tvStartExam5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, ExamActivity.class);
                intent.putExtra("testId",5);
                startActivity(intent);
            }
        });
        tvStartExam6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, ExamActivity.class);
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
