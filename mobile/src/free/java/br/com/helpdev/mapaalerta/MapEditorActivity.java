package br.com.helpdev.mapaalerta;

import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.helpdev.mapaalerta.free.AdsUtils;

/**
 * Created by Guilherme Biff Zarelli on 22/11/16.
 */

public class MapEditorActivity extends MapEditorAbs {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdsUtils.insertAds(this, R.id.adView);
    }
}
