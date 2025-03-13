package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The main activity of the RU Pizzeria app.
 * Provides navigation to different features, including Chicago-style pizza, NY-style pizza,
 * current orders, and store orders.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is created.
     * Initializes the UI components and sets up navigation for the app's main menu.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView chicagoPizzaImage = findViewById(R.id.chicago_pizza_image);
        ImageView nyPizzaImage = findViewById(R.id.ny_pizza_image);
        ImageView currentOrderImage = findViewById(R.id.shopping_cart_image);
        ImageView storeOrdersImage = findViewById(R.id.notepad_image);

        setupImageHandlers(chicagoPizzaImage, ChicagoPizzaActivity.class);
        setupImageHandlers(nyPizzaImage, NYPizzaActivity.class);
        setupImageHandlers(currentOrderImage, CartActivity.class);
        setupImageHandlers(storeOrdersImage, StoreOrdersActivity.class);
    }

    /**
     * Configures click and hover effects for images.
     *
     * @param imageView the ImageView to configure
     * @param targetActivity the Activity to launch when image is clicked
     */
    private void setupImageHandlers(ImageView imageView, final Class<?> targetActivity) {
        imageView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                imageView.setAlpha(0.6f);
            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                imageView.setAlpha(1.0f);
            }
            return false;
        });

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, targetActivity);
            startActivity(intent);
        });
    }
}