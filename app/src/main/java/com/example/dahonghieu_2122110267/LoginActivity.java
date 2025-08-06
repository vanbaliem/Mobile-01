package com.example.dahonghieu_2122110267;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            //connect button Login with
        Button btnNext = findViewById(R.id.btnLogin);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objEmail = findViewById(R.id.txtEmail);
                String txtEmail = objEmail.getText().toString();

                EditText objPass = findViewById(R.id.txtPass);
                String txtPass = objPass.getText().toString();

                CharSequence text = txtEmail + " " + txtPass;
                int duration = Toast.LENGTH_LONG;
                if (txtEmail.equals("hieu") && txtPass.equals("123"))
                {
                    Intent it = new Intent(getApplicationContext(),MainActivity.class);
                    it.putExtra("email", txtEmail); // For String
                    it.putExtra("pass", txtPass); // For Integer
                    startActivity(it);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Login fail",duration);
                    toast.show();
                }
            }
        });
        Button btnReister = findViewById(R.id.btnRegister);
        btnReister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),RegisterActivity.class);


                startActivity(it);
            }
        });
    }
}