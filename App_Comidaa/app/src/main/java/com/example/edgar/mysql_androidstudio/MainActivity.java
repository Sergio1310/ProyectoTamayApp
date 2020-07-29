package com.example.edgar.mysql_androidstudio;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fragments.BebidasFragment;
import fragments.ComidasFragment;
import fragments.postres;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigation;

    private Toolbar toolbar;

   

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Botton navigation de abajo
        showSelectedFragment(new ComidasFragment());
        mBottomNavigation =(BottomNavigationView)findViewById(R.id.BtnNavigationBottom) ;
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.comidas){
                    showSelectedFragment(new ComidasFragment());
                }
                if(menuItem.getItemId() == R.id.bebida){
                    showSelectedFragment(new BebidasFragment());
                }
                if(menuItem.getItemId() == R.id.postres){
                    showSelectedFragment(new postres());
                }

                return true;
            }
        });
//Toolbar -->
        setUpToolbar();
        setUpHomeUpIconAndColor(R.drawable.ic_los_toxicos4, R.color.colorWhiteApp);
        customTitleToolbar();

        Bundle extras = getIntent().getExtras();
        String idcliente1= extras.getString("IDCliente");

        idclienteapp.idcliapp=idcliente1;



    }


    // TOOLBAR---->
    private void setUpToolbar(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon();
    }
    public void  showHomeUpIcon(){
        if(getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setUpHomeUpIconAndColor(int drawable, int color){
        if(getSupportActionBar() != null);
        final Drawable icon = getResources().getDrawable(drawable);
        icon.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(icon);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menuIconColor(menu, R.color.colorWhiteApp);
        return super.onCreateOptionsMenu(menu);
    }
    public void menuIconColor(Menu menu, int color){
        for(int i=0; i<menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null){
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
// opciones de los botones de la toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                showSelectedFragment(new ComidasFragment());
                break;
            case R.id.sesion:
                Intent welcome = new Intent(MainActivity.this, login.class);
                startActivity(welcome);
                break;
            case R.id.carrito:
                Log.d("TAG1", "Carrito");

                Intent welcome1 = new Intent(MainActivity.this, carrito.class);
                startActivity(welcome1);


                break;

            case R.id.pedidos:
                Intent welcome2 = new Intent(MainActivity.this, pedidos.class);
                startActivity(welcome2);
                break;
            default:
                //Error
        }
        return super.onOptionsItemSelected(item);
    }
    //Titulo del toolbar
    private void customTitleToolbar(){
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            TextView textView=toolbar.findViewById(R.id.toolbar_tittle);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG1", "TITLE");
                }
            });
        }
    }
    //metodo del fragment -->
    private void showSelectedFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();


    }
}
