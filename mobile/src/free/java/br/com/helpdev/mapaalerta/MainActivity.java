package br.com.helpdev.mapaalerta;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

import br.com.helpdev.mapaalerta.free.AdsUtils;

public class MainActivity extends MainActivityAbs {
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdsUtils.insertAds(this, R.id.adView);
        createInterstitial();
    }

    private void createInterstitial() {
        interstitialAd = AdsUtils.getNewInsterstitial(this, R.string.banner_ad_intersticial_id, new AdListener() {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(AdsUtils.getNewAdRequest());
                MainActivity.super.onClickFab();
            }
        });
    }

    @Override
    protected void onClickFab() {
        if (new Random().nextInt(1) != 0 || interstitialAd == null) {
            super.onClickFab();
            return;
        }

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            interstitialAd.loadAd(AdsUtils.getNewAdRequest());
            super.onClickFab();
        }
    }
}
