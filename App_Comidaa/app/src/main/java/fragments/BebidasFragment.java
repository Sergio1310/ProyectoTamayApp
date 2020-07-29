package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.edgar.mysql_androidstudio.DetailActivity;
import com.example.edgar.mysql_androidstudio.ExampleAdapter;
import com.example.edgar.mysql_androidstudio.R;
import com.example.edgar.mysql_androidstudio.comidas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BebidasFragment extends Fragment implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imagen";
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_DESCRIPCION = "descripcion";
    public static final String EXTRA_PRECIO = "precio";

    private static final String URL="http://toxicosrest.000webhostapp.com/buscar_bebida.php";
    ArrayList<comidas> bebidasList;
    ExampleAdapter exampleAdapter;
    RecyclerView mRecyclerView;
    RequestQueue mRequestQueue;

    public BebidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bebidas,container,false);

        mRecyclerView=view.findViewById(R.id.recyclerView11);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bebidasList = new ArrayList<>();
        mRequestQueue= Volley.newRequestQueue(getContext());
        loadBebidas();





        return view;
    }

    private void loadBebidas(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject comidas = array.getJSONObject(i);
                                bebidasList.add(new comidas(
                                        comidas.getString("nombre"),
                                        comidas.getString("descripcion"),
                                        comidas.getInt("precio"),
                                        comidas.getString("imagen"),
                                        comidas.getString("id")
                                ));
                            }

                            exampleAdapter = new ExampleAdapter(getContext(), bebidasList);
                            mRecyclerView.setAdapter(exampleAdapter);
                            exampleAdapter.setOnItemClickListener(BebidasFragment.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(getContext()).add(stringRequest);


    }

    @Override
    public void onItemClick(int position) {
        Intent popup =new Intent(getContext(), DetailActivity.class);
        comidas clickedItem = bebidasList.get(position);
        popup.putExtra(EXTRA_URL, clickedItem.getImagen());
        popup.putExtra(EXTRA_ID, clickedItem.getIndicadorID());
        popup.putExtra(EXTRA_NOMBRE, clickedItem.getNombre());
        popup.putExtra(EXTRA_DESCRIPCION, clickedItem.getDescripcion());
        popup.putExtra(EXTRA_PRECIO, clickedItem.getPrecio());

        startActivity(popup);
    }
}
