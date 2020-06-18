package cn.edu.scu.test20.exam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.edu.scu.test20.BottomNavigatorActivity;
import cn.edu.scu.test20.R;

import java.io.IOException;

//import bean.LoginUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 86130 on 2019/8/3.
 */

public class TestResultActivity extends AppCompatActivity {
    private RecyclerView rvResultList;
    private TextView tvBack;
    private TextView tvRight,tvWrong;
    private TextView tvSummary;
    private boolean[] flagSelected;
    private Button btnSubmitAccurate;
    private EditText etKey;
    int listNum=1;
    int right=0;
    int wrong=0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_result_activity);
        //initUI();
        btnSubmitAccurate=(Button)findViewById(R.id.submit_accurate);
       // etKey=(EditText)findViewById(R.id.et_key);
   //     btnSubmitAccurate.setOnClickListener(new View.OnClickListener() {
         //   @Override
//            public void onClick(View v) {
//                if(etKey.getText().toString().equals("")){
//                    Toast.makeText(TestResultActivity.this,"请输入正确的用户类型（teacher或者student）", Toast.LENGTH_SHORT).show();
//                }else{
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                           final int key= Integer.parseInt(etKey.getText().toString());
//                            OkHttpClient client=new OkHttpClient();
                           // String studentName= ;
                            //http://localhost:11036/Android/servlet/InsertClassIdServlet?classId=133&teacherName=aaa
                          //  Request request=new Request.Builder().url("http://118.25.139.202:8080/Android/servlet/InsertClassrelateServlet?classId="+key+"&studentName="+studentName+"&accurate="+ String.format("%.2f", ((right+0.0)/listNum))
                          //  ).build();
                           // System.out.println("http://118.25.139.202:8080/Android/servlet/InsertClassrelateServlet?classId="+key+"&studentName="+studentName+"&accurate="+ String.format("%.2f", ((right+0.0)/listNum)));
//                            try {
//                                Response response=client.newCall(request).execute();//new OkHttpClient.newCall(new Request.Builder().url("").build()).execute();
//                                //final String responseData[]=response.body().string().split("\\|");
//                                final String insertMessage=response.body().string();
//                                if(insertMessage.equals("add succeed")){
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            //System.out.println("设置当前位置: " + viewPager.getCurrentItem());
//                                            Toast.makeText(TestResultActivity.this,"上传测试成绩成功。", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }else{
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            //System.out.println("设置当前位置: " + viewPager.getCurrentItem());
//                                            Toast.makeText(TestResultActivity.this,"班级邀请码错误，上传测试成绩失败。", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }
                                // System.out.println("body "+response.body().string());
                                // System.out.println("responseData[0]:"+loginMessage[0]+"  responseData[1]:"+loginMessage[1]);

//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//                }
//            }
//        });
   }

        private void initUI(){
        listNum=getIntent().getIntExtra("totalNum",0);
        boolean isSelected=false;
        flagSelected=getIntent().getBooleanArrayExtra("flag");

         right=0;
         wrong=0;
        for(int i=0;i<flagSelected.length;i++){
            isSelected=flagSelected[i];
            if(isSelected){
                right++;
            }else{
                wrong++;
            }
            Log.e("ResultActivity -- flag", flagSelected[i] + "");
        }
        tvRight=(TextView)findViewById(R.id.tv_result_right);
        tvWrong=(TextView)findViewById(R.id.tv_result_wrong);
        tvSummary=(TextView)findViewById(R.id.tv_result_summary);

        tvRight.setText("答对正确数目："+right);
        tvWrong.setText("答对错误数目："+wrong);
        if (right > wrong && right < listNum) {
            tvSummary.setText("共" + listNum + "道题，回答正确：" + right
                    + ", 回答错误：" + wrong + " 正确率"+String.format("%.2f", ((right+0.0)/listNum)*100)+"%,总体表现不错，继续加油哦！");
        } else if (right < wrong && right != 0) {
            tvSummary.setText("共" + listNum + "道题，回答正确：" + right
                    + ", 回答错误：" + wrong + " 正确率"+String.format("%.2f", ((right+0.0)/listNum)*100)+"%,稍微有点落后了，不要灰心，一定要更努力。");
        } else if (right == 0) {
            tvSummary.setText("正确率为0%"+"，很遗憾全部回答错误了。");
        } else if (right == listNum) {
            tvSummary.setText("正确率为100%"+"，你居然全部答对了，真是个小天才。");
        }
        tvRight.setText("答对正确数目："+right);
        tvWrong.setText("答对错误数目："+wrong);
        if (right > wrong && right < listNum) {
            tvSummary.setText("共" + listNum + "道题"+"      \t"+" 正确率"+ String.format("%.2f", ((right+0.0)/listNum)*100)+"%"+"\n" +"总体表现不错，继续加油哦！");
        } else if (right < wrong && right != 0) {
            tvSummary.setText("共" + listNum + "道题"+"      \t"+" 正确率"+ String.format("%.2f", ((right+0.0)/listNum)*100)+"%"+"\n" +"稍微有点落后了，不要灰心，继续努力。");
        } else if (right == 0) {
            tvSummary.setText("正确率为0%"+"，很遗憾全部回答错误了。");
        } else if (right == listNum) {
            tvSummary.setText("正确率为100%"+"，做的很好，请再接再厉。");
        }
        rvResultList=(RecyclerView)findViewById(R.id.rv_result_list);
        tvBack=(TextView)findViewById(R.id.tv_result_back);
//        tvBack.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TestResultActivity.this, BottomNavigatorActivity.class);
//                intent.putExtra("name", LoginUser.getUname());
//                intent.putExtra("utype",LoginUser.getUtype());
//                startActivity(intent);
//            }
//        });

        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 5);
        rvResultList.setLayoutManager(manager);

        ResultGridAdapter adapter = new ResultGridAdapter(this, listNum, flagSelected, new OnRecyclerItemClickListener() {
            @Override
            public void onTtemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                finish();
            }
         });
        rvResultList.setAdapter(adapter);
    }
}
