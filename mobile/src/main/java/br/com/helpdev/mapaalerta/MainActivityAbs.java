package br.com.helpdev.mapaalerta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.helpdev.mapaalerta.adapters.AdapterMapaAlerta;
import br.com.helpdev.mapaalerta.objetos.ObMapAlert;
import br.com.helpdev.supportlib_maps.activities.AppCompatLocation;
import br.com.helpdev.supportlib_maps.locations.LocationUtils;

/**
 * Created by Guilherme Biff Zarelli on 22/11/16.
 */

public abstract class MainActivityAbs extends AppCompatLocation implements View.OnClickListener, LocationUtils.ConnectionCallback, AdapterMapaAlerta.AdapterMapaAlertaListener {

    private static final int REQUEST_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        String[] permissoes = new String[]{
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
        };
        ActivityCompat.requestPermissions(this, permissoes, REQUEST_PERMISSIONS);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        RecyclerView recycleViww = (RecyclerView) findViewById(R.id.recycler_view);
        List<ObMapAlert> lista = new ArrayList<>();
        lista.add(new ObMapAlert("titulo1"));
        lista.add(new ObMapAlert("titulo2"));
        lista.add(new ObMapAlert("titulo3"));
        lista.add(new ObMapAlert("titulo4"));
        lista.add(new ObMapAlert("titulo5"));
        lista.add(new ObMapAlert("titulo6"));
        lista.add(new ObMapAlert("titulo7"));

        AdapterMapaAlerta ad = new AdapterMapaAlerta(this, lista, this);
        recycleViww.setAdapter(ad);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            onClickFab();
        }
    }

    @Override
    public void onClickItem(ObMapAlert obMapAlert) {

    }

    @Override
    public void onClickManageItem(ObMapAlert obMapAlert) {
        Intent it = new Intent(this, MapEditorActivity.class);
        it.putExtra(MapEditorActivity.PARAM_OB_MAPALERT_EDIT, obMapAlert);
        startActivity(it);
    }

    @Override
    public void onConnectedLocation() {

    }

    protected void onClickFab() {
        startActivity(new Intent(this, MapEditorActivity.class));
    }

}
