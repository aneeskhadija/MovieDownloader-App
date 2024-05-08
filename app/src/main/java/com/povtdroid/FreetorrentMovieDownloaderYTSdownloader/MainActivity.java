package com.povtdroid.FreetorrentMovieDownloaderYTSdownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Activities.HomeActivity;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout_one,linearLayout_two;
    Button btn_next;
    Animation uptodown,downtoup;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove Action Bar
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_main);

        btn_next = (Button)findViewById(R.id.id_btn_next);
        linearLayout_one = (LinearLayout) findViewById(R.id.id_linearLayout_one);
        linearLayout_two = (LinearLayout) findViewById(R.id.id_linearLayout_two);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        linearLayout_one.setAnimation(uptodown);
        linearLayout_two.setAnimation(downtoup);

        interstitialAd = new InterstitialAd(this, "694370814627253_694371517960516");
        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
            //    Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
            //    Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
               // Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
               // Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
               // Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
              //  Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intnt_home = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intnt_home);

                interstitialAd.loadAd();
            }
        });
    }
}