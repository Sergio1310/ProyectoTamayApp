package com.example.edgar.mysql_androidstudio;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<comidas>mExampleList;

    public ExampleAdapter(Context context, ArrayList<comidas>exampleList){
        this.mContext=context;
        this.mExampleList=exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_layout, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        comidas currentItem = mExampleList.get(position);
        String imagenUrl = currentItem.getImagen();
        String nombreC=currentItem.getNombre();
        String descripcionC=currentItem.getDescripcion();
        int precioC=currentItem.getPrecio();

        holder.mTextViewnombre.setText(nombreC);
        holder.mTextViewDescripcion.setText(descripcionC);
        holder.mTextViewPrecio.setText("$ "+precioC);
        Picasso.with(mContext).load(imagenUrl).fit().centerInside().into(holder.mImagenView);

       /* comidas currentItem = mExampleList.get(position);

        Glide.with(mContext)
                .load(currentItem.getImagen())
                .into(holder.mImagenView);
        holder.mTextViewnombre.setText(currentItem.getNombre());
        holder.mTextViewDescripcion.setText(currentItem.getDescripcion());
        holder.mTextViewPrecio.setText(String.valueOf(currentItem.getPrecio()));*/



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImagenView;
        public TextView mTextViewnombre;
        public TextView mTextViewDescripcion;
        public TextView mTextViewPrecio;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImagenView=itemView.findViewById(R.id.id_imagen_view);
            mTextViewnombre=itemView.findViewById(R.id.txtnombre);
            mTextViewDescripcion=itemView.findViewById(R.id.txtdescripcion);
            mTextViewPrecio=itemView.findViewById(R.id.txtprecio);
        }
    }
}
