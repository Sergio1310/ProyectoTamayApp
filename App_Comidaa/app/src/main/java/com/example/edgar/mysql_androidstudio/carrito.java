package com.example.edgar.mysql_androidstudio;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fragments.BebidasFragment;
import fragments.ComidasFragment;
import fragments.postres;

public class carrito extends AppCompatActivity {

    private Toolbar toolbar;
    BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        setUpToolbar();
        setUpHomeUpIconAndColor(R.drawable.ic_los_toxicos4, R.color.colorWhiteApp);
        customTitleToolbar();

        //Botton navigation de abajo

        mBottomNavigation =(BottomNavigationView)findViewById(R.id.BtnNavigationBottom) ;
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.confirmar){
                    Intent welcome = new Intent(carrito.this, pedidos.class);
                    startActivity(welcome);
                }


                return true;
            }
        });


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
                Intent welcom1e = new Intent(carrito.this, MainActivity.class);
                startActivity(welcom1e);
                break;
            case R.id.sesion:
                Intent welcome = new Intent(carrito.this, login.class);
                startActivity(welcome);
                break;
            case R.id.carrito:
                Log.d("TAG1", "Carrito");

               Intent welcome1 = new Intent(carrito.this, carrito.class);
                startActivity(welcome1);


                break;

            case R.id.pedidos:
                Intent welcome2 = new Intent(carrito.this, pedidos.class);
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
