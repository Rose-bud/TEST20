package cn.edu.scu.test20.exam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.edu.scu.test20.MainPageActivity;
import cn.edu.scu.test20.R;
import cn.edu.scu.test20.tool_class.BottomNavigatorActivity;

public class TestResultActivity extends AppCompatActivity {
    private RecyclerView rvResultList;
    private TextView tvBack;
    private TextView tvRight,tvWrong;
    private TextView tvSummary;
    private boolean[] flagSelected;

  //  private EditText etKey;
    int listNum=1;
    int right=0;
    int wrong=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_result_activity);
        initUI();
        //////
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
           // Log.e("ResultActivity -- flag", flagSelected[i] + "");
        }
        tvRight=(TextView)findViewById(R.id.tv_result_right);
        tvWrong=(TextView)findViewById(R.id.tv_result_wrong);
        tvSummary=(TextView)findViewById(R.id.tv_result_summary);


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

        ///////从测试结果界面跳转回主页
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestResultActivity.this, BottomNavigatorActivity.class);

                startActivity(intent);
            }
        });
        ///////结果回顾
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
