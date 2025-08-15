    package com.example.hitcapp;
    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
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
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.Volley;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    public class LoginActivity extends AppCompatActivity {
        private static final String TAG = "LoginActivity";
        private RequestQueue requestQueue;
        private EditText edtEmail, edtPassword;
        private Button btnLogin, btnRegister;
        private String apiUrl = "https://68940ecabe3700414e11e179.mockapi.io/uses";

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

            edtEmail = findViewById(R.id.txtEmail);
            edtPassword = findViewById(R.id.txtPass);
            btnLogin = findViewById(R.id.btnLogin);
            btnRegister = findViewById(R.id.btnRegister);
            requestQueue = Volley.newRequestQueue(this);

            btnLogin.setOnClickListener(v -> {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            });

            btnRegister.setOnClickListener(v -> {
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
            });
        }

        private void loginUser(String email, String password) {
            String loginUrlWithParams = apiUrl + "?email=" + email;

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, loginUrlWithParams, null,
                    response -> {
                        try {
                            if (response.length() > 0) {
                                JSONObject user = response.getJSONObject(0);
                                String storedPassword = user.getString("password");

                                if (password.equals(storedPassword)) {
                                    String emailResponse = user.getString("email");
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                                    Log.d(TAG, "Đăng nhập thành công, email: " + emailResponse);
                                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                                    it.putExtra("email", emailResponse);
                                    startActivity(it);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: Mật khẩu không đúng.", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: Email không tồn tại.", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Lỗi phân tích dữ liệu phản hồi", Toast.LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        String errorMessage = "Đăng nhập thất bại: Lỗi mạng hoặc server.";
                        if (error.networkResponse != null) {
                            errorMessage += " Status Code: " + error.networkResponse.statusCode;
                        }
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Lỗi đăng nhập: " + errorMessage);
                    });


            requestQueue.add(jsonArrayRequest);
        }
    }