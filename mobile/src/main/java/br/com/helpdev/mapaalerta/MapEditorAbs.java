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
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.helpdev.mapaalerta.objetos.ObMapAlert;
import br.com.helpdev.mapaalerta.objetos.xml.XmlMapAlert;
import br.com.helpdev.mapaalerta.objetos.xml.XmlMapLocation;
import br.com.helpdev.supportlib.file_selector.FileSelectorActivity;
import br.com.helpdev.supportlib_maps.activities.MapRouteEditorActivity;
import br.com.helpdev.supportlib_maps.gpx.GpxMapUtils;
import br.com.helpdev.supportlib_maps.gpx.ObGpxMap;
import br.com.helpdev.supportlib_maps.gpx.objetos.Gpx;

public abstract class MapEditorAbs extends MapRouteEditorActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GpxMapUtils.LoadGpxAsyncCallback {

    public static final String PARAM_OB_MAPALERT_EDIT = "PARAM_OB_MAPALERT_EDIT";

    private static final int REQUEST_CODE_GPX_SELECTOR = 1;
    private Snackbar mLoadSnack;
    private ObMapAlert obMapAlert;
    private ObGpxMap obGpxMap;

    public MapEditorAbs() {
        super(R.layout.activity_maps, R.id.map, R.color.colorAccentDark);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setEnableBtTerrain(R.id.bt_terreno, true);

        if (savedInstanceState == null) {

            if (getIntent().hasExtra(PARAM_OB_MAPALERT_EDIT)) {
                obMapAlert = (ObMapAlert) getIntent().getSerializableExtra(PARAM_OB_MAPALERT_EDIT);
            }

            Toast.makeText(this, R.string.hint_edit_map, Toast.LENGTH_LONG).show();

            if (obMapAlert == null) {
                obMapAlert = new ObMapAlert(new XmlMapAlert(""));
                setTitleMapEditor(getString(R.string.sem_titulo));
                return;
            }

            setTitleMapEditor(obMapAlert.getObMapAlertXml().getTitle());
            if (obMapAlert.getObMapAlertXml().getFileGpx() != null) {
                GpxMapUtils.loadGpxAsync(this, getMap(), new File(obMapAlert.getObMapAlertXml().getFileGpx()), this);
            }
            refreshMapRoute();
        }
    }

    private void refreshMapRoute() {
        if (obMapAlert.getObMapAlertXml().getObMapRoute() != null && !obMapAlert.getObMapAlertXml().getObMapRoute().isEmpty()) {
            List<LatLng> locations = new ArrayList<>();
            for (XmlMapLocation xmlMapLocation : obMapAlert.getObMapAlertXml().getObMapRoute()) {
                locations.add(new LatLng(xmlMapLocation.getLatitude(), xmlMapLocation.getLongitude()));
            }
            super.loadMap(locations);
        }
    }

    private void setTitleMapEditor(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mapa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.load_gpx) {
            selectFileGPX();
        } else if (item.getItemId() == R.id.remove_gpx) {
            if (obGpxMap != null) {
                obGpxMap.remove();
                obGpxMap = null;
                goToMyLocation();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectFileGPX() {
        Intent intent = new Intent(this, FileSelectorActivity.class);
        ArrayList<String> lista = new ArrayList<>();
        lista.add("gpx");
        intent.putStringArrayListExtra(FileSelectorActivity.PARAM_IT_FILE_TYPES_ARRAYLIST, lista);
        startActivityForResult(intent, REQUEST_CODE_GPX_SELECTOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GPX_SELECTOR && resultCode == RESULT_OK) {
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
    public void gpxLoadSucess(Gpx gpx, ObGpxMap polyline) {
        if (this.obGpxMap != null) {
            this.obGpxMap.remove();
        }
        obMapAlert.setGpx(gpx);
        this.obGpxMap = polyline;
        dismissLoadSnack();
    }
}
