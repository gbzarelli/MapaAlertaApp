package br.com.helpdev.mapaalerta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.File;
import java.util.ArrayList;

import br.com.helpdev.mapaalerta.gpx.GpxMapUtils;
import br.com.helpdev.supportlib.file_selector.FileSelectorActivity;

public class MapsActivity extends SimpleMapActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GpxMapUtils.LoadGpxAsyncCallback {

    private Snackbar mLoadSnack;

    public MapsActivity() {
        super(R.layout.activity_maps, R.id.map);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setEnableBtTerrain(R.id.bt_terreno, true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mapa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_gpx) {
            selectFileGPX();
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectFileGPX() {
        Intent intent = new Intent(this, FileSelectorActivity.class);
        ArrayList<String> lista = new ArrayList<>();
        lista.add("gpx");
        intent.putStringArrayListExtra(FileSelectorActivity.PARAM_IT_FILE_TYPES_ARRAYLIST, lista);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (null == data) {
                showSnackBar(getString(R.string.problema_recuperar_gpx), Snackbar.LENGTH_INDEFINITE);
                return;
            }
            String stringExtra = data.getStringExtra(FileSelectorActivity.PARAM_IT_FILE_RESPONSE);
            GpxMapUtils.loadGpxAsync(this, getMap(), new File(stringExtra), this);
        }
    }


    private void dismissLoadSnack() {
        if (mLoadSnack != null && mLoadSnack.isShown()) {
            mLoadSnack.dismiss();
            mLoadSnack = null;
        }
    }

    protected void showSnackBar(String string, int time) {
        getSnackBar(string, time).show();
    }

    protected Snackbar getSnackBar(String string, int time) {
        Snackbar snackBar = Snackbar.make(findViewById(R.id.layout), string, time);
        View view = snackBar.getView();
        TextView tv = (TextView) snackBar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(this, R.color.snackBarColor));
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.CENTER;
        view.setLayoutParams(params);
        return snackBar;
    }


    @Override
    public void gpxLoadStart() {
        mLoadSnack = getSnackBar(getString(R.string.aguarde_carregando_arquivo), Snackbar.LENGTH_INDEFINITE);
        mLoadSnack.show();
    }

    @Override
    public void gpxLoadError(Throwable t) {
        dismissLoadSnack();
        showSnackBar(getString(R.string.problema_carregar_gpx), Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    public void gpxLoadSucess() {
        dismissLoadSnack();
    }
}
