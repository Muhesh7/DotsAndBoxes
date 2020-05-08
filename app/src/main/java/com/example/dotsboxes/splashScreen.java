package com.example.dotsboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {

    ImageView mImageView1;
    ImageView mImageView2;
    ImageView mImageView3;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageView2=(ImageView)findViewById(R.id.splash2);
                mImageView3=(ImageView)findViewById(R.id.splash3);
                mTextView=(TextView)findViewById(R.id.textView12);
                mImageView2.setTranslationY(1000);
                Intent intent = new Intent(splashScreen.this,gameset.class);
                finish();
                startActivity(intent);

            }
        },2000);

    }


}
