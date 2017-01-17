package com.projet_100_heures.lemurrescuecenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.projet_100_heures.lemurrescuecenter.R;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView imageView = (ImageView) findViewById(R.id.lemurLogo);
        final Animation animationRotate = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation animationFadeOut = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);


        imageView.startAnimation(animationRotate);
        animationRotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.startAnimation(animationFadeOut);
                        finish();
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 3000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
