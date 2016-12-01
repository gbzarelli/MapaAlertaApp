package br.com.helpdev.mapaalerta.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import br.com.helpdev.mapaalerta.R;
import br.com.helpdev.mapaalerta.objetos.ObMapAlert;
import br.com.helpdev.mapaalerta.objetos.xml.XmlMapAlert;
import br.com.helpdev.supportlib.adapters.RecyclerViewAdapter;

/**
 * Created by Guilherme Biff Zarelli on 29/11/16.
 */

public class AdapterMapaAlerta extends RecyclerViewAdapter<ObMapAlert, AdapterMapaAlerta.MapAlertaHolder>
        implements RecyclerViewAdapter.RecyclerAdapterListener<ObMapAlert> {


    public interface AdapterMapaAlertaListener {
        void onClickItem(ObMapAlert obMapAlert);

        void onClickManageItem(ObMapAlert obMapAlert);
    }

    private AdapterMapaAlertaListener listener;

    public AdapterMapaAlerta(Context context, List<ObMapAlert> lista, AdapterMapaAlertaListener adapterMainListener) {
        super(context, lista, R.layout.item_main, 80, AdapterMapaAlerta.MapAlertaHolder.class);
        setListener(this);
        this.listener = adapterMainListener;
    }

    @Override
    public void onBindViewHolder(AdapterMapaAlerta.MapAlertaHolder holder, int position) {
        XmlMapAlert obMapAlert = getLista().get(position).getObMapAlertXml();
        holder.title.setText(obMapAlert.getTitle());
        try {
            if (obMapAlert.getFileImagem() != null && !obMapAlert.getFileImagem().isEmpty() && !holder.imvLoad) {
                File file = new File(obMapAlert.getFileImagem());
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                holder.imvMapa.setImageBitmap(bitmap);
                holder.imvLoad = true;
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public MapAlertaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MapAlertaHolder mp = super.onCreateViewHolder(parent, viewType);
        mp.imvManage.setTag(mp);
        mp.imvManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    MapAlertaHolder pos = (MapAlertaHolder) v.getTag();
                    listener.onClickManageItem(getLista().get(pos.getAdapterPosition()));
                }
            }
        });
        return mp;
    }

    @Override
    public void onClickItem(View view, int i, ObMapAlert obMap) {
        if (null != listener) listener.onClickItem(obMap);
    }

    @Override
    public boolean onLongClickItem(View view, int i, ObMapAlert o) {
        return false;
    }

    public static class MapAlertaHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        View imvManage;
        ImageView imvMapa;
        boolean imvLoad = false;

        public MapAlertaHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            imvManage = itemView.findViewById(R.id.imv_manage);
            imvMapa = (ImageView) itemView.findViewById(R.id.imv_map);
        }
    }
}
