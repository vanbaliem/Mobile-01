package com.example.hitcapp;
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

        // Xử lý để giao diện không bị tràn vào các vùng hệ thống như thanh trạng thái
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ nút Login từ layout
        Button btnNext = findViewById(R.id.btnLogin); // <-- Chắc chắn id trong layout là btnLogin

        // Thiết lập sự kiện khi click vào nút Login
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText Email
                EditText objEmail = findViewById(R.id.txtEmail); // <-- Chắc chắn id là txtEmail
                String txtEmail = objEmail.getText().toString();

                // Lấy dữ liệu từ EditText Password
                EditText objPass = findViewById(R.id.txtPass); // <-- Chắc chắn id là txtPass
                String txtPass = objPass.getText().toString();

                // Kiểm tra điều kiện đăng nhập
                if (txtEmail.equals("liem") && txtPass.equals("123")) {
                    // Tạo Intent để chuyển sang MainActivity
                    Intent it = new Intent(LoginActivity.this, MainActivity.class);

                    // Đính kèm dữ liệu email và mật khẩu vào Intent
                    it.putExtra("email", txtEmail);
                    it.putExtra("pass", txtPass);

                    // Khởi động MainActivity
                    startActivity(it);
                } else {
                    // Hiển thị thông báo khi đăng nhập thất bại
                    Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),RegisterActivity.class);


                startActivity(it);
            }
        });
    }
}