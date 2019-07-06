package com.example.projeto_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Refeicoes extends AppCompatActivity {

    private Button btnMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        btnMove = findViewById(R.id.btn_3);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivity_main();
            }
        });


    }

    private void moveToActivity_main() {
        Intent intent = new Intent (Refeicoes.this,MainActivity.class);
        startActivity(intent);
    }
}