package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtPass;
    String user, password;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.TxtEmail2);
        txtPass = findViewById(R.id.TxtPassword2);
        btnRegistrar = findViewById(R.id.botonRegistrar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });



    }

    public void login(View view) {

        user = txtUser.getText().toString().trim();
        password = txtPass.getText().toString();
        //vaidar los datos de la interfaz (usuario,password)

        if (TextUtils.isEmpty(user)) {
            txtUser.setError("usuario vacio");
            txtUser.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            txtPass.setError("contraseña vacia");
            txtPass.setFocusable(true);
            return;
        }
        new LoginRest().execute(user, password);
    }

//clase asyncTask

    class LoginRest extends AsyncTask<String, Integer, String> {
        //variable peticioon
        URLConnection connection = null;
        //variable para resultado de la peticion
        String result = "0";

        @Override
        protected String doInBackground(String... strings) {

            try {
                connection = new URL("http://172.18.26.66/cursoAndroid/vista/usuario/iniciarSesion.php?usuario=" + strings[0] + "&password=" + strings[1]).openConnection();

                InputStream inputStream = (InputStream) connection.getContent();
                byte[] buffer = new byte[10000];
                //indica cuantos datos son datos en la cadena de respuesta
                int size = inputStream.read(buffer);
                result = new String(buffer, 0, size);
                Log.i("result", result);


            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // equals para comparar cadenas
            if (s.equals("1")) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }




}