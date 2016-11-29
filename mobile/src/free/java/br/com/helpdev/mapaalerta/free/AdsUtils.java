package br.com.helpdev.mapaalerta.free;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Guilherme Biff Zarelli on 22/11/16.
 */

public class AdsUtils {

    public static void insertAds(Activity activity, int idLayoutAds) {
        final AdView mAdView = (AdView) activity.findViewById(idLayoutAds);
        mAdView.setVisibility(View.GONE);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });
        mAdView.loadAd(getNewAdRequest());
    }

    /**
     * <pre>
     * private void createInterstitial() {
     * interstitialAd = AdsUtils.getNewInsterstitial(this, R.string.banner_ad_intersticial_id, new AdListener() {
     * @Override
     * public void onAdClosed() {
     * interstitialAd.loadAd(AdsUtils.getNewAdRequest());
     * MainActivity.super.onClickFab();
     * }
     * });
     * }
     *
     * @Override
     * protected void onClickFab() {
     * if (interstitialAd.isLoaded()) {
     * interstitialAd.show();
     * } else {
     * interstitialAd.loadAd(AdsUtils.getNewAdRequest());
     * super.onClickFab();
     * }
     * }
     * </pre>
     *
     * @param context
     * @param resIdAdUnitId
     * @param adListener
     * @return
     */
    public static InterstitialAd getNewInsterstitial(Context context, int resIdAdUnitId, AdListener adListener) {
        InterstitialAd mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(resIdAdUnitId));
        mInterstitialAd.setAdListener(adListener);
        mInterstitialAd.loadAd(getNewAdRequest());
        return mInterstitialAd;
    }

    public static AdRequest getNewAdRequest() {
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("ED22D380D881867345E6858189EB4D35")
                .build();
    }

}
