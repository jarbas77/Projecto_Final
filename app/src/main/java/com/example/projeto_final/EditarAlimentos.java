package com.example.projeto_final;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class EditarAlimentos extends AppCompatActivity {
    private Button btnEditar;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alimentos);

        btnEditar = findViewById(R.id.btn_addAlimento);

        btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarCliente();
                }
            });

        id = getIntent().getStringExtra("ID");//temos que verDisto!!!!!
        }

        void enviarCliente(){

            EditText editTextNome = findViewById(R.id.et_nomeAlimento);
            String nomealimento = editTextNome.getText().toString();

            EditText editTextNomePerfil = findViewById(R.id.et_valEnerg);
            String valorEnergetico = editTextNomePerfil.getText().toString();

            EditText editTextMorada = findViewById(R.id.et_Gordura);
            String gordura = editTextMorada.getText().toString();

            EditText editTextContribuinte = findViewById(R.id.et_Acucar);
            String acucar = editTextContribuinte.getText().toString();

            EditText editTextContacto = findViewById(R.id.et_Prota);
            String proteina = editTextContacto.getText().toString();

            JSONObject json = new JSONObject();

            try {
                json.put("idAlim",nomealimento);
                json.put("alValEnergetico",valorEnergetico);
                json.put("alGordura",gordura);
                json.put("alAcucar",acucar);
                json.put("alProteina",proteina);

                WS put = new WS("http://192.168.7.1/meals/public/api/alimentos");
                put.resposta = new WSResposta() {
                    @Override
                    public void respostaRecebida(String resposta) {
                        finish();
                    }
                };
                put.execute();
            } catch (JSONException e) {
                e.printStackTrace();
                mostarErro();
            }
        }

        private void mostarErro() {
            Snackbar.make(btnEditar, "Ocorreu um erro.", Snackbar.LENGTH_LONG).show();
        }
    }
}
