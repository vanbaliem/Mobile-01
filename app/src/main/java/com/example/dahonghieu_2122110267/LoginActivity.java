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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        // Nút đăng nhập
        Button btnNext = findViewById(R.id.btnLogin);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objEmail = findViewById(R.id.txtEmail);
                String txtEmail = objEmail.getText().toString().trim();

                EditText objPass = findViewById(R.id.txtPass);
                String txtPass = objPass.getText().toString().trim();

                // Gọi API kiểm tra user
                String url = "https://68931182c49d24bce86949c7.mockapi.io/users";
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    boolean isLoginSuccess = false;
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject user = jsonArray.getJSONObject(i);
                                        String email = user.getString("email");
                                        String password = user.getString("password");

                                        if (email.equals(txtEmail) && password.equals(txtPass)) {
                                            isLoginSuccess = true;
                                            break;
                                        }
                                    }

                                    if (isLoginSuccess) {
                                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        intent.putExtra("email", txtEmail);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(LoginActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, "API Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                requestQueue.add(stringRequest);
            }
        });

        // Nút đăng ký
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(it);
            }
        });
    }
}
