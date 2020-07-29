package com.example.edgar.mysql_androidstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static fragments.ComidasFragment.EXTRA_DESCRIPCION;
import static fragments.ComidasFragment.EXTRA_ID;
import static fragments.ComidasFragment.EXTRA_NOMBRE;
import static fragments.ComidasFragment.EXTRA_PRECIO;
import static fragments.ComidasFragment.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    EditText txtcantidadselct;
    Button cancel, acept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        String imagenURL=intent.getStringExtra(EXTRA_URL);
        final String id =intent.getStringExtra(EXTRA_ID);
        String nombre=intent.getStringExtra(EXTRA_NOMBRE);
        String descripcion =intent.getStringExtra(EXTRA_DESCRIPCION);
        int precio =intent.getIntExtra(EXTRA_PRECIO,0);

        ImageView imagenView =findViewById(R.id.imagen_view_detail);
        TextView textViewNombre =findViewById(R.id.nombrecomida_detail);
        TextView textViewDescripcion =findViewById(R.id.descripcionComidaDetail);
        TextView textViewPrecio =findViewById(R.id.precioComidaDetail);
        TextView textViewid =findViewById(R.id.ordenComidaDetail);

        Picasso.with(this).load(imagenURL).fit().centerInside().into(imagenView);
        textViewNombre.setText(nombre);
        textViewDescripcion.setText(descripcion);
        textViewPrecio.setText("$ "+ precio);
        textViewid.setText(id);

        txtcantidadselct=(EditText)findViewById(R.id.txtCantidad);
        cancel=(Button) findViewById(R.id.btnCancelar);
        acept=(Button) findViewById(R.id.btnAceptar);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcome = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(welcome);
            }
        });

        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idcli=idclienteapp.idcliapp;



                Toast.makeText(DetailActivity.this, "ID producto " + id+ " ID usuario: "+idcli, Toast.LENGTH_SHORT).show();

            }
        });




    }
    private void recibirDatos(){


    }
}
