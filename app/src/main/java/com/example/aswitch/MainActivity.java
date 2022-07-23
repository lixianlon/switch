package com.example.aswitch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List images = new ArrayList();//定义一个链表
    ImageSwitcher imageswitcher;//定义获取图片控件的变量
    ViewGroup group;//定义一个控件数组用来存放线性控件
    ImageView[] tips;//定义图片数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageswitcher = findViewById(R.id.switcher);//获取图片控件
        group = findViewById(R.id.viewGroup);//获取线性控件

        initData();//用来对数据进行初始化，将链表运用到程序里面来

        imageswitcher.setFactory(new ViewSwitcher.ViewFactory() {//设置图片工厂
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);//新建一个图片控件并使该图片控件与MainActivity绑定
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置图片控件的拉伸格式
                return imageView;//将设置图片控件返回到原来的图片控件上
            }
        });
        imageswitcher.setInAnimation(this, android.R.anim.fade_in);//设置动画效果
        imageswitcher.setOutAnimation(this, android.R.anim.fade_out);//设置动画效果
        imageswitcher.setImageResource((Integer) images.get(0));//设置图片资源，图片资源在数据链表里面提取图片，get（0）是提取默认的第一张图片，即程序刚启动时提取第一张图片显示
        initPointer();//用来对小圆点进行初始化，并将小圆点运用在程序里

    }


    private void initData(){//打包好图片存放到链表里面，再将链表通过initData（）调用到程序里面
        images.add(R.drawable.t1);
        images.add(R.drawable.t2);
        images.add(R.drawable.t3);
        images.add(R.drawable.t1);
        images.add(R.drawable.t2);
        images.add(R.drawable.t3);

    }

    //初始化下面的小圆点的方法
    private void initPointer() {
        //有多少个界面就new多长的数组
        tips = new ImageView[images.size()];

        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            //设置控件的宽高
            imageView.setLayoutParams(new LinearLayoutCompat.LayoutParams(255, 255));
            //设置控件的padding属性
            imageView.setPadding(20, 0, 20, 0);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource((Integer) images.get(i));
            imageView.setId(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageswitcher.setImageResource((Integer)images.get(view.getId()));
                }
            });

            tips[i] = imageView;
            group.addView(tips[i]);
        }
    }
}