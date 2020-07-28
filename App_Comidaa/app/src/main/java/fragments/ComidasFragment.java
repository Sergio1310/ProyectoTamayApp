package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.edgar.mysql_androidstudio.ExampleAdapter;
import com.example.edgar.mysql_androidstudio.R;
import com.example.edgar.mysql_androidstudio.comidas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComidasFragment extends Fragment {

    /*private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<comidas>mExampleList;
    private RequestQueue mRequestQueue;*/

    private static final String URL="http://toxicosrest.000webhostapp.com/buscar_comidas.php";
    ArrayList<comidas> comidasList;
    ExampleAdapter exampleAdapter;
    RecyclerView mRecyclerView;
    RequestQueue mRequestQueue;


    public ComidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_comidas,container,false);

        mRecyclerView=view.findViewById(R.id.recyclerView11);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        comidasList= new ArrayList<>();
        mRequestQueue= Volley.newRequestQueue(getContext());
        loadComidas();





        return view;

    }
    private void loadComidas(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject comidas = array.getJSONObject(i);
                                comidasList.add(new comidas(
                                        comidas.getString("nombre"),
                                        comidas.getString("descripcion"),
                                        comidas.getInt("precio"),
                                        comidas.getString("imagen")
                                ));
                            }
                            exampleAdapter = new ExampleAdapter(getContext(), comidasList);
                            mRecyclerView.setAdapter(exampleAdapter);

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
}
