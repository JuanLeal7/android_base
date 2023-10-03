package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {



    EditText valor,cubo,cuadrado,raiz,raizz;

    RequestQueue requestQueue;

    Button accion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valor=findViewById(R.id.valor);
        cubo=findViewById(R.id.cubo);
        cuadrado=findViewById(R.id.cuadrado);
        raiz=findViewById(R.id.raiz);
        raizz=findViewById(R.id.raizz);
        accion=(Button)findViewById(R.id.accion);


        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                buscar("https://sqliteonline.com/#sharecon=kyleigh="+valor.getText()+" ");

            }


        });
    }

    private void buscar(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length();i++) {
                    try{
                        jsonObject = response.getJSONObject(i);
                        cubo.setText((int) jsonObject.getDouble("cubo"));
                        cuadrado.setText((int) jsonObject.getDouble("cuadrado"));
                        raiz.setText((int) jsonObject.getDouble("raiz"));
                        raizz.setText((int) jsonObject.getDouble("raizz"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR ", Toast.LENGTH_SHORT).show();
            }
        }

    );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}
