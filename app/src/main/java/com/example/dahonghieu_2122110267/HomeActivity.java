package com.example.dahonghieu_2122110267;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvCategory, rvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvCategory = findViewById(R.id.rvCategory);
        rvProduct  = findViewById(R.id.rvProduct);

        setupCategory();
        setupProduct();
    }

    /** Danh mục ngang */
    private void setupCategory() {
        rvCategory.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        List<CategoryItem> cats = Arrays.asList(
                new CategoryItem(R.drawable.cat_coffee,  "Cà phê"),
                new CategoryItem(R.drawable.cat_tea,     "Trà"),
                new CategoryItem(R.drawable.cat_frappe,  "Đá xay"),
                new CategoryItem(R.drawable.cat_cake,    "Bánh ngọt"),
                new CategoryItem(R.drawable.cat_topping, "Topping"),
                new CategoryItem(R.drawable.cat_combo,   "Combo")
        );

        rvCategory.setAdapter(new CategoryAdapter(cats));
    }

    /** Sản phẩm dạng lưới 2 cột */
    private void setupProduct() {
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2));
        rvProduct.setHasFixedSize(false);

        List<ProductItem> products = Arrays.asList(
                new ProductItem(R.drawable.prod_1, "Espresso",   "45.000đ" ),
                new ProductItem(R.drawable.prod_2, "Cappuccino", "59.000đ"),
                new ProductItem(R.drawable.prod_3, "Latte",      "55.000đ"),
                new ProductItem(R.drawable.prod_4, "Mocha",      "62.000đ"),
                new ProductItem(R.drawable.prod_5, "Caramel",    "65.000đ")
        );

        ProductAdapter adapter = new ProductAdapter(products, item -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.link)));
            } catch (Exception e) {
                Toast.makeText(this, "Liên kết không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });
        rvProduct.setAdapter(adapter);
    }
}
