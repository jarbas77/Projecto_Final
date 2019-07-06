package com.example.projeto_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /**BaseDeDados db;
    EditText etNome, etVE, etGordura, etAcucar, etProtaina;
    Button btnEliminar;
    Button btnModificar; */

    private Button btnMove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMove = findViewById(R.id.btn_1);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAlimentos();
            }
        });


    }

    private void moveToAlimentos() {
        Intent intent = new Intent(MainActivity.this, Alimentos.class);
        startActivity(intent);
    }


    private Button btnMove2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMove2 = findViewById(R.id.btn_2);

        btnMove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToRefeicoes();
            }
        });
    }

    private void moveToRefeicoes() {
        Intent intent = new Intent(MainActivity.this, Refeicoes.class);
        startActivity(intent);
    }
/** asd*/
}