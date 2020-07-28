package com.example.edgar.mysql_androidstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class registro extends AppCompatActivity {

    EditText edtnombre,edtdireccion,edtTelefono, edtusuario, edtcontrasena, edttipo;
    Button btnregistrar, btningreso2;
    String id;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        edtnombre=(EditText)findViewById(R.id.et_nombre);
        edtdireccion=(EditText)findViewById(R.id.et_direccion);
        edtTelefono=(EditText)findViewById(R.id.et_telefono);
        edtusuario=(EditText)findViewById(R.id.et_usuario);
        edtcontrasena=(EditText)findViewById(R.id.et_password);
        edttipo=(EditText)findViewById(R.id.et_tipo);
        btnregistrar=(Button) findViewById(R.id.btn_registro);
        btningreso2=(Button) findViewById(R.id.btn_ingresa2);




        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://toxicosrest.000webhostapp.com/registrar.php");
            }
        });

        btningreso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcome = new Intent(registro.this, login.class);
                startActivity(welcome);
            }
        });
    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                Random();

                parametros.put("id",id);
                parametros.put("nombre",edtnombre.getText().toString());
                parametros.put("direccion",edtdireccion.getText().toString());
                parametros.put("telefono",edtTelefono.getText().toString());
                parametros.put("usuario",edtusuario.getText().toString());
                parametros.put("contrasena",edtcontrasena.getText().toString());
                parametros.put("id_tipo_usuario",edttipo.getText().toString());

                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void limpiarFormulario(){

        edtnombre.setText("");
        edtdireccion.setText("");
        edtTelefono.setText("");
        edtusuario.setText("");
        edtcontrasena.setText("");
        edttipo.setText("");
    }
    private void Random(){

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf,sdf2;

        sdf2 = new SimpleDateFormat("HHmmss");
        sdf2.setTimeZone(TimeZone.getDefault().getTimeZone("HHmmss"));
        id = sdf2.format(date) ;
    }
}
