package com.example.edgar.mysql_androidstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    EditText mUsu, mPas;
    String usuario,contrasena,idUsuario;
    Button btnIngresar, btnRegistro;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsu=(EditText)findViewById(R.id.et_email);
        mPas=(EditText)findViewById(R.id.et_password);
        btnIngresar=(Button) findViewById(R.id.btn_login);
        btnRegistro=(Button) findViewById(R.id.btn_login2);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario("http://toxicosrest.000webhostapp.com/login.php?usuario="+mUsu.getText()+"");
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcome = new Intent(login.this, registro.class);
                startActivity(welcome);
            }
        });

    }

    private void buscarUsuario(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        usuario= jsonObject.getString("usuario");
                        contrasena= jsonObject.getString("contrasena");
                        idUsuario=jsonObject.getString("id");
                        String U=idUsuario;

                        /*mUsu.setText(usuario);
                        mPas.setText(contrasena);
                        mUsu.setText(jsonObject.getString("usuario"));
                        mPas.setText(jsonObject.getString("contrasena"));*/


                        if(usuario.toString().equals(mUsu.getText().toString())&& contrasena.toString().equals(mPas.getText().toString()))
                        {
                            Toast.makeText(login.this, "Bienvenido ID: "+ U, Toast.LENGTH_SHORT).show();



                            Intent welcome = new Intent(login.this, MainActivity.class);
                           welcome.putExtra("IDCliente",U);
                            startActivity(welcome);



                            break;
                        }
                        Toast.makeText(login.this, "Usuario o contraseña invalida", Toast.LENGTH_SHORT).show();





                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"invalida",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}
