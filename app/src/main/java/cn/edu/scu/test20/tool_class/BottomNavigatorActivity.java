package cn.edu.scu.test20.tool_class;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.scu.test20.LittleVideoActivity;
import cn.edu.scu.test20.MainPageActivity;
import cn.edu.scu.test20.R;
import cn.edu.scu.test20.UserActivity;
import cn.edu.scu.test20.exam.MainActivity3;
import cn.edu.scu.test20.tool_class.MyViewPageAdapter;


public class BottomNavigatorActivity extends AppCompatActivity {
    private ImageView img1,img2,img3,img4;
    private ViewPager vp;

    private LocalActivityManager manager;
    private MyViewPageAdapter viewPageAdapter;
    private View.OnClickListener clickListener;
    private ViewPager.OnPageChangeListener pageChangeListener;
    private DrawerLayout mDrawerLayout;
    private LinearLayout homepageLayout, questionBankLayout, videoLayout, infoLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigator_activity);
        initView(savedInstanceState);

    }
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.toolbar,menu);
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.settings:
//                Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show();
//                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:

        }
        return true;
    }


    private void initView(Bundle savedInstanceState){
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);



        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });


        manager= new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);

        vp=(ViewPager)findViewById(R.id.viewpager);
        homepageLayout = findViewById(R.id.homepageLayout);
        questionBankLayout = findViewById(R.id.questionbankLayout);
        videoLayout = findViewById(R.id.videoLayout);
        infoLayout = findViewById(R.id.infoLayout);
        img1 = findViewById(R.id.main_img1);
        img2 = findViewById(R.id.main_img2);
        img3 = findViewById(R.id.main_img3);
        img4 = findViewById(R.id.main_img4);
        clickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.homepageLayout:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.questionbankLayout:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.videoLayout:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.infoLayout:
                        vp.setCurrentItem(3);
                        break;

                }
            }
        };
        homepageLayout.setOnClickListener(clickListener);
        questionBankLayout.setOnClickListener(clickListener);
        videoLayout.setOnClickListener(clickListener);
        infoLayout.setOnClickListener(clickListener);
        initPager();
    }
    private void initPager(){
        pageChangeListener=new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        img1.setImageResource(R.drawable.tab01_sel);
                        img2.setImageResource(R.drawable.tab02_unsel);
                        img3.setImageResource(R.drawable.tab03_unsel);
                        img4.setImageResource(R.drawable.tab05_my);
                        break;
                    case 1:
                        img1.setImageResource(R.drawable.tab01_unsel);
                        img2.setImageResource(R.drawable.tab02_sel);
                        img3.setImageResource(R.drawable.tab03_unsel);
                        img4.setImageResource(R.drawable.tab05_my);
                        break;
                    case 2:
                        img1.setImageResource(R.drawable.tab01_unsel);
                        img2.setImageResource(R.drawable.tab02_unsel);
                        img3.setImageResource(R.drawable.tab03_sel);
                        img4.setImageResource(R.drawable.tab05_my);
                        break;
                    case 3:
                        img1.setImageResource(R.drawable.tab01_unsel);
                        img2.setImageResource(R.drawable.tab02_unsel);
                        img3.setImageResource(R.drawable.tab03_unsel);
                        img4.setImageResource(R.drawable.tab05_my);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        addActivityToViewPager();
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(pageChangeListener);

    }
    private void addActivityToViewPager(){
        List<View> mViews=new ArrayList<View>();
        Intent intent=new Intent();

        intent.setClass(this, MainPageActivity.class);
        intent.putExtra("id",1);
        intent.putExtra("name",getIntent().getStringExtra("name"));
        mViews.add(getView("QualityActivity1",intent));

        //change by sc,6/19
        intent.setClass(this, MainActivity3.class);
        intent.putExtra("id",2);
//        intent.putExtra("name",getIntent().getStringExtra("name"));
//        intent.putExtra("student",utype);
        mViews.add(getView("QualityActivity2",intent));

        intent.setClass(this, LittleVideoActivity.class);
        intent.putExtra("id",3);
        mViews.add(getView("QualityActivity3",intent));

        intent.setClass(this, UserActivity.class);
        intent.putExtra("id",4);
        mViews.add(getView("QualityActivity4",intent));


        viewPageAdapter=new MyViewPageAdapter(mViews);
        vp.setAdapter(viewPageAdapter);
    }
    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }
}
