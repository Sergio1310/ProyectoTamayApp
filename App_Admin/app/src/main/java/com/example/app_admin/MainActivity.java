package com.example.app_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    EditText mUsu, mPas;
    Button btnIngresar;
    String usuario,contrasena;
    int id_tipousuario;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsu = (EditText)findViewById(R.id.editText);
        mPas = (EditText)findViewById(R.id.editText2);
        btnIngresar = (Button) findViewById(R.id.button);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario("https://toxicosrest.000webhostapp.com/login.php?usuario="+mUsu.getText()+"");
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
                        id_tipousuario = jsonObject.getInt("id_tipo_usuario");

                        if(usuario.equals(mUsu.getText().toString())&& contrasena.equals(mPas.getText().toString()) && id_tipousuario == 1)
                        {
                            Toast.makeText(MainActivity.this, "Bienvenido " + usuario + "te estaba esperando.", Toast.LENGTH_SHORT).show();
                            Intent welcome = new Intent(MainActivity.this, Vista_Admin.class);
                            startActivity(welcome);

                            break;
                        }
                        Toast.makeText(MainActivity.this, "Usuario o Contraseña Incorrecta.", Toast.LENGTH_SHORT).show();



                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrecta.",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}
