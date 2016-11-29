package br.com.helpdev.mapaalerta.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.helpdev.mapaalerta.R;
import br.com.helpdev.mapaalerta.objetos.ObMapAlert;

/**
 * Created by Guilherme Biff Zarelli on 29/11/16.
 */

public class AdapterMapaAlerta extends RecyclerViewAdapter2<ObMapAlert, AdapterMapaAlerta.MapAlertaHolder>
        implements RecyclerViewAdapter2.RecyclerAdapterListener<ObMapAlert> {


    public interface AdapterMainListener {
        void onClickItem(ObMapAlert obMapAlert);

        void onClickManageItem(ObMapAlert obMapAlert);
    }

    private AdapterMainListener listener;

    public AdapterMapaAlerta(Context context, List<ObMapAlert> lista, AdapterMainListener adapterMainListener) {
        super(context, lista, R.layout.item_main, 80, AdapterMapaAlerta.MapAlertaHolder.class);
        setListener(this);
        this.listener = adapterMainListener;
    }

    @Override
    public void onBindViewHolder(AdapterMapaAlerta.MapAlertaHolder holder, int position) {
        ObMapAlert obMapAlert = getLista().get(position);
        holder.title.setText(obMapAlert.getTitle());
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

        public MapAlertaHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            imvManage = itemView.findViewById(R.id.imv_manage);
        }
    }
}
