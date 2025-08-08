package com.example.dahonghieu_2122110267;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class  RegisterActivity extends AppCompatActivity {

    private static final String URL = "https://68931182c49d24bce86949c7.mockapi.io/users";

    private EditText txtFullName, txtEmailRegister, txtPasswordRegister, txtConfirmPassword;
    private Button btnRegisterAccount, btnBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Bind views
        txtFullName         = findViewById(R.id.txtFullName);
        txtEmailRegister    = findViewById(R.id.txtEmailRegister);
        txtPasswordRegister = findViewById(R.id.txtPasswordRegister);
        txtConfirmPassword  = findViewById(R.id.txtConfirmPassword);
        btnRegisterAccount  = findViewById(R.id.btnRegisterAccount);
        btnBackToLogin      = findViewById(R.id.btnBackToLogin);

        btnBackToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnRegisterAccount.setOnClickListener(v -> doRegister());
    }

    private void doRegister() {
        String fullName = txtFullName.getText().toString().trim();
        String email    = txtEmailRegister.getText().toString().trim();
        String pass     = txtPasswordRegister.getText().toString().trim();
        String pass2    = txtConfirmPassword.getText().toString().trim();

        if (fullName.isEmpty() || email.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(pass2)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject body = new JSONObject();
        try {
            body.put("fullName", fullName);
            body.put("email", email);
            body.put("password", pass);
            body.put("avatar", "https://i.pravatar.cc/150?u=" + email); // tuỳ chọn
        } catch (JSONException e) {
            Toast.makeText(this, "Lỗi tạo dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                body,
                response -> {
                    Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                },
                (VolleyError error) ->
                        Toast.makeText(this, "Lỗi API: " + (error.getMessage() == null ? error.toString() : error.getMessage()),
                                Toast.LENGTH_SHORT).show()
        );

        queue.add(req);
    }
}
