package com.example.rosmhyperezar.proyectofinal.vista.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rosmhyperezar.proyectofinal.R;
import com.example.rosmhyperezar.proyectofinal.modelo.Camping;
import com.example.rosmhyperezar.proyectofinal.vista.MainActivity;

import java.util.List;

public class CampingAdapter extends RecyclerView.Adapter<CampingAdapter.ViewHolder> {
    private static List<Camping> items;
    private Context context;
    private MainActivity a;

    public CampingAdapter(Context context, List<Camping> items, MainActivity a) {
        this.context = context;
        this.items = items;
        this.a = a;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeCampoin) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_camping, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Camping item = items.get(position);
        holder.name.setText(item.getNombre());
        holder.direccion.setText(item.getDirección());
        holder.telefono.setText(item.getTeléfono());
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView direccion;
        public TextView telefono;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nombre_texto);
            direccion = (TextView) itemView.findViewById(R.id.direccion_texto);
            telefono = (TextView) itemView.findViewById(R.id.telefono_texto );
        }
    }
}
