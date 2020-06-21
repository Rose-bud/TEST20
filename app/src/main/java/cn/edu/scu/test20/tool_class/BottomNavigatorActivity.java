package cn.edu.scu.test20.tool_class;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.edu.scu.test20.LittleVideoActivity;
import cn.edu.scu.test20.MainPageActivity;
import cn.edu.scu.test20.R;
import cn.edu.scu.test20.UserActivity;
import cn.edu.scu.test20.bean.User;
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
    //使用相册中的图片
    public static final int SELECT_PIC_CODE = 1;
    //图片裁剪
    private static final int PHOTO_CROP_CODE = 2;
    private String path = "/sdcard/file.jpg";
    private File mFile;
    private Uri mUri;
    final User user = BmobUser.getCurrentUser(User.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigator_activity);
        initView(savedInstanceState);
        mFile = new File(path);
        mUri = Uri.fromFile(mFile);
        Bmob.initialize(this, "56a832cf0b1a430d9eada88f2c39a39a");//绑定后端
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PIC_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            photoClip(selectedImage);
        }

        if (requestCode == PHOTO_CROP_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Activity curActivity = manager.getActivity("QualityActivity4");
            ((UserActivity)curActivity).showImage(path);
            sendImage(path);
        }
    }

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 9998);
        intent.putExtra("aspectY", 9999);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 160);
        intent.putExtra("outputY", 160);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,mUri);
        startActivityForResult(intent, PHOTO_CROP_CODE);
    }

    //上传图片
    private void sendImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        Image_String image_string=new Image_String();
        String data=image_string.convertIconToString(bm);
        user.setHeadPic(data);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(BottomNavigatorActivity.this,"更新头像成功",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(BottomNavigatorActivity.this,"更新头像失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
