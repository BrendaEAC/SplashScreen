package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import modelos.Usuarios;

public class RegistroAcrivity extends AppCompatActivity {
    EditText txtUser, txtName, txtMail, txtAddress, txtPassword;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_acrivity);

        txtUser = findViewById(R.id.txtUsuario);
        txtName = findViewById(R.id.txtNombre);
        txtMail = findViewById(R.id.txtCorreo);
        txtAddress = findViewById(R.id.txtDireccion);
        txtPassword = findViewById(R.id.txtPassword);

        btnAdd = findViewById(R.id.btnAddRegistro);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamar la validacion de datos

                if (validarDatos(txtUser.getText().toString().trim(),
                        txtName.getText().toString().trim(),
                        txtMail.getText().toString().trim(),
                        txtAddress.getText().toString().trim(),
                        txtPassword.getText().toString())) {

                    //crear el objeto de tipo usuario

                    Usuarios usuario = new Usuarios();
                    usuario.setNickname(txtUser.getText().toString().trim());
                    usuario.setNombre(txtName.getText().toString().trim());
                    usuario.setCorreo(txtMail.getText().toString().trim());
                    usuario.setDireccion(txtAddress.getText().toString().trim());
                    usuario.setPassword(txtPassword.getText().toString());


                    //crear la instancia de la subclase asyncTask para
                    //realizar la conexion

                    new AddUser().execute(usuario);
                }
            }
        });
    }

    public boolean validarDatos(String nickname, String nombre, String correo, String direccion, String password) {

        if (nombre.isEmpty()) {
            txtName.setError("campo vacio");
            txtName.setFocusable(true);
            return false;
        }

        if (nickname.isEmpty()) {
            txtUser.setError("campo vacio");
            txtUser.setFocusable(true);
            return false;
        }

        if (correo.isEmpty()) {
            txtMail.setError("campo vacio");
            txtMail.setFocusable(true);
            return false;
        }
        if (direccion.isEmpty()) {
            txtAddress.setError("campo vacio");
            txtAddress.setFocusable(true);
            return false;
        }
        if (password.isEmpty()) {
            txtPassword.setError("campo vacio");
            txtPassword.setFocusable(true);
            return false;

        }

        return true;
    }


        //clase que permite correr en un hilo distinto
        class AddUser extends AsyncTask<Usuarios, Integer, Boolean> {


            @Override
            protected Boolean doInBackground(Usuarios... usuarios) {
                //preparar los datos de insersion

                String params = "";
                params = "user=" + usuarios[0].getNickname() + "&" +
                        "nombre=" + usuarios[0].getNombre() + "&" +
                        "correo=" + usuarios[0].getCorreo() + "&" +
                        "direccion=" + usuarios[0].getDireccion() + "&" +
                        "password=" + usuarios[0].getPassword();

                // preparar la conexion
                try {
                    URL url = new URL("http://172.18.26.67/cursoAndroid/vista/Usuario/crearUsuario.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                    connection.setRequestMethod("POST");
                    //indicar que llevara datos de entrada y regresar datos de salida
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    //segimos preparando la conexion
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    writer.write(params);
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    //Conectamos
                    connection.connect();

                    int responseCode = connection.getResponseCode();

                    //mostrarle al usuario si se conecto o no
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.i("AddUser", "usuario agregado con exito");
                        return true;
                    }else{
                        return false;

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return false;

            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

                if (aBoolean){
                    Toast.makeText(RegistroAcrivity.this, "Usuario agregado con exito", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(RegistroAcrivity.this, "Usuario no agregado", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }



