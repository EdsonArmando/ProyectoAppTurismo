package com.android.guix.edson.org.androidturismo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.guix.edson.org.androidturismo.volley.WebService;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroUsuario extends AppCompatActivity {
    private TextView txtNombre, txtCorreo, txtNick, txtpassword;
    private Button btnRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        btnRegistro=(Button) findViewById(R.id.btnRegistro);
        txtNombre=(TextView) findViewById(R.id.txtNombre);
        txtCorreo=(TextView) findViewById(R.id.txtCorreo);
        txtNick=(TextView) findViewById(R.id.txtNick);
        txtpassword=(TextView) findViewById(R.id.txtPassword);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params=new HashMap<String, String>();
                params.put("nombre", txtNombre.getText().toString());
                params.put("correo", txtCorreo.getText().toString());
                params.put("nick", txtNick.getText().toString());
                params.put("contrasena", txtpassword.getText().toString());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.registro, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    try{

                        if (response !=null){
                            Toast.makeText(getApplicationContext(),"Registrado correctamente",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegistroUsuario.this,Login.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"No se pudo registrar",Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception ex){
                        Log.e("Response exception", ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err) {
                        Log.d("Error: Response ", err.getMessage());
                    }
                });
                WebService.getInstance(v.getContext()).addToRequestQueue(request);
            }
        });
    }
}
