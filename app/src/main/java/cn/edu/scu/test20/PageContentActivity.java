package cn.edu.scu.test20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class PageContentActivity extends AppCompatActivity {
    public static final String NEWS_ID="news_id";
    public static final String FRUIT_TITLE="fruit_title";
    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";
    public static final String FRUIT_CONTENT="fruit_content";
    public static final String FRUIT_INTEREST="fruit_interest";
    Context context=this;
    EditText editComment;
    Button btn_submitComment;

    //private List<Message> recommendHeros=new ArrayList<Message>();
    RecyclerView recyclerView;
    //PageContentRecommendAdapter adapter;

    //private List<Comment> commentList=new ArrayList<Comment>();
    RecyclerView recyclerViewComment;
    //CommentAdapter commentAdapter;

//    InterestQueue<String> interestQueue=MainPageActivity.getInterestQueue();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_content_activity);
        final Intent intent=getIntent();

        final String interest=intent.getStringExtra(FRUIT_INTEREST);
        String content=intent.getStringExtra(FRUIT_CONTENT);
        String fruitName1=intent.getStringExtra(FRUIT_NAME);
        int fruitImageId=intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView=(ImageView)findViewById(R.id.fruit_image_view);
        TextView fruitContentText=(TextView)findViewById(R.id.fruit_content_text);


        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitName1);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent=generateFuitContent(content);
        fruitContentText.setText(fruitContent);




        final int newsId=intent.getIntExtra(NEWS_ID,0);
        final Context context=this;






    }


    private String generateFuitContent(String fruitName){
        StringBuilder fruitContent=new StringBuilder();
       /* for(int i=0;i<500;i++){
            fruitContent.append(fruitName);
        }*/
        fruitContent.append(fruitName);
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
