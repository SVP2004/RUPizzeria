package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for customizing and ordering New York-style pizzas.
 * Users can select a pizza type, size, and toppings, then add it to their order.
 */
public class NYPizzaActivity extends AppCompatActivity {

    /**
     * Called when the activity is created.
     * Initializes the UI components and configures interaction logic.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most recently supplied.
     */
    private static final int MAX_TOPPINGS = 7;
    private static final double TOPPING_PRICE = 1.69;

    private Spinner pizzaTypeSpinner;
    private ImageView pizzaImage;
    private RadioGroup sizeRadioGroup;
    private RadioButton smallRadio, mediumRadio, largeRadio;
    private RecyclerView toppingsRecyclerView;
    private Button addToOrderButton;
    private TextView totalPriceTextView;

    private String selectedPizzaType;
    private Size selectedSize;
    private List<Topping> selectedToppings;
    private ToppingsAdapter toppingsAdapter;
    private NYPizza nyPizzaFactory;
    private double basePrice;
    private double toppingPrice;

    /**
     * Called when the activity is created.
     * Initializes the UI components and configures interaction logic.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nyc_pizza);

        initializeViews();
        setupPizzaTypeSpinner();
        setupSizeRadioGroup();
        setupBackButton();
        setupToppingsRecyclerView();
        setupAddToOrderButton();
    }

    /**
     * Configures the back button to return to the main activity.
     */
    private void setupBackButton() {
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * Initializes the UI components and sets default values for the activity.
     */
    private void initializeViews() {
        pizzaTypeSpinner = findViewById(R.id.pizza_type_spinner);
        pizzaImage = findViewById(R.id.pizza_image);
        sizeRadioGroup = findViewById(R.id.size_radio_group);
        smallRadio = findViewById(R.id.small_radio);
        mediumRadio = findViewById(R.id.medium_radio);
        largeRadio = findViewById(R.id.large_radio);
        toppingsRecyclerView = findViewById(R.id.toppings_recycler_view);
        addToOrderButton = findViewById(R.id.add_to_order_button);
        totalPriceTextView = findViewById(R.id.total_price_text_view);

        selectedToppings = new ArrayList<>();
        nyPizzaFactory = new NYPizza();
        smallRadio.setChecked(true);
        selectedSize = Size.SMALL;
    }

    /**
     * Sets up the pizza type selection spinner with available pizza types
     * and defines behavior for user selection.
     */
    private void setupPizzaTypeSpinner() {
        String[] pizzaTypes = {"Build Your Own", "BBQ Chicken", "Deluxe", "Meatzza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, pizzaTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaTypeSpinner.setAdapter(adapter);

        pizzaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPizzaType = pizzaTypes[position];
                updatePizzaComponents();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Sets up the size selection radio group and updates the selected size when a radio button is clicked.
     */
    private void setupSizeRadioGroup() {
        sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.small_radio) selectedSize = Size.SMALL;
            else if (checkedId == R.id.medium_radio) selectedSize = Size.MEDIUM;
            else if (checkedId == R.id.large_radio) selectedSize = Size.LARGE;

            updatePrice();
        });
    }

    /**
     * Configures the toppings RecyclerView with available toppings and manages the user's topping selections.
     */
    private void setupToppingsRecyclerView() {
        List<ToppingItem> toppingItems = createToppingItems();

        if (selectedPizzaType == null) {
            selectedPizzaType = "Build Your Own";
        }

        boolean isPredefinedPizza = !selectedPizzaType.equals("Build Your Own");
        toppingsAdapter = new ToppingsAdapter(toppingItems, this::onToppingSelected, isPredefinedPizza);
        toppingsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        toppingsRecyclerView.setAdapter(toppingsAdapter);
    }

    /**
     * Creates a list of available toppings for display in the RecyclerView.
     *
     * @return A list of {@link ToppingItem} objects representing available toppings.
     */
    private List<ToppingItem> createToppingItems() {
        List<ToppingItem> toppingItems = new ArrayList<>();
        toppingItems.add(new ToppingItem(Topping.SAUSAGE, "Sausage", R.drawable.topping_sausage));
        toppingItems.add(new ToppingItem(Topping.PEPPERONI, "Pepperoni", R.drawable.topping_pepperoni));
        toppingItems.add(new ToppingItem(Topping.GREEN_PEPPER, "Green Pepper", R.drawable.topping_green_pepper));
        toppingItems.add(new ToppingItem(Topping.ONION, "Onion", R.drawable.topping_onion));
        toppingItems.add(new ToppingItem(Topping.MUSHROOM, "Mushroom", R.drawable.topping_mushroom));
        toppingItems.add(new ToppingItem(Topping.BBQ_CHICKEN, "BBQ Chicken", R.drawable.topping_bbq_chicken));
        toppingItems.add(new ToppingItem(Topping.PROVOLONE, "Provolone", R.drawable.topping_provolone));
        toppingItems.add(new ToppingItem(Topping.CHEDDAR, "Cheddar", R.drawable.topping_cheddar));
        toppingItems.add(new ToppingItem(Topping.BEEF, "Beef", R.drawable.topping_beef));
        toppingItems.add(new ToppingItem(Topping.HAM, "Ham", R.drawable.topping_ham));
        toppingItems.add(new ToppingItem(Topping.JALAPENOS, "Jalapenos", R.drawable.topping_jalapenos));
        toppingItems.add(new ToppingItem(Topping.PINEAPPLES, "Pineapples", R.drawable.topping_pineapple));
        toppingItems.add(new ToppingItem(Topping.OLIVES, "Olives", R.drawable.topping_olives));
        return toppingItems;
    }

    /**
     * Handles the selection or deselection of a topping.
     *
     * @param toppingItem The topping item that was selected or deselected.
     * @param isChecked   Whether the topping was selected (true) or deselected (false).
     */

    private void onToppingSelected(ToppingItem toppingItem, boolean isChecked) {
        if (isChecked && !selectedToppings.contains(toppingItem.topping)) {
            selectedToppings.add(toppingItem.topping);
        } else if (!isChecked) {
            selectedToppings.remove(toppingItem.topping);
        }

        updateToppingAvailability();
        updatePrice();
    }

    /**
     * Updates the availability of topping selection based on the maximum allowed toppings.
     */
    private void updateToppingAvailability() {
        boolean canAddMoreToppings = selectedToppings.size() < MAX_TOPPINGS;
        toppingsAdapter.setToppingSelectionEnabled(canAddMoreToppings);
    }

    /**
     * Updates the displayed pizza components (image and toppings) based on the selected pizza type.
     */
    private void updatePizzaComponents() {
        switch (selectedPizzaType) {
            case "Deluxe":
                pizzaImage.setImageResource(R.drawable.deluxe_ny_pizza);
                break;
            case "BBQ Chicken":
                pizzaImage.setImageResource(R.drawable.bbq_chicken_ny_pizza);
                break;
            case "Meatzza":
                pizzaImage.setImageResource(R.drawable.meatzza_ny_pizza);
                break;
            default:
                pizzaImage.setImageResource(R.drawable.ny_pizza);
        }

        if (selectedPizzaType.equals("Build Your Own")) {
            selectedToppings.clear();
            toppingsAdapter.resetToppingSelections();
            toppingsAdapter.setToppingSelectionEnabled(true);
        } else {
            selectedToppings = Topping.getToppings(selectedPizzaType);
            toppingsAdapter.setPreselectedToppings(selectedToppings);
            toppingsAdapter.setToppingSelectionEnabled(false);
        }

        updatePrice();
    }

    /**
     * Configures the "Add to Order" button to validate and add the customized pizza to the user's order.
     */
    private void setupAddToOrderButton() {
        addToOrderButton.setOnClickListener(v -> {
            if (validatePizzaOrder()) {
                addPizzaToOrder();
            }
        });
    }

    /**
     * Validates the pizza order by checking the selected pizza type and toppings.
     *
     * @return true if the order is valid; false otherwise.
     */
    private boolean validatePizzaOrder() {
        if (selectedPizzaType == null) {
            Toast.makeText(this, "Please select a pizza type", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!selectedPizzaType.equals("Build Your Own") && selectedToppings.isEmpty()) {
            Toast.makeText(this, "Please add toppings to your pizza", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * Adds the customized pizza to the user's order and shows a confirmation message.
     */
    private void addPizzaToOrder() {
        Pizza newPizza = createPizza();
        newPizza.setSize(selectedSize);

        for (Topping topping : selectedToppings) {
            newPizza.addTopping(topping);
        }

        if (OrderManager.getInstance().addPizza(newPizza)) {
            Toast.makeText(this, "Pizza added to order", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to add pizza to order", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates a {@link Pizza} object based on the selected pizza type and returns it.
     *
     * @return The created {@link Pizza} object.
     */
    private Pizza createPizza() {
        switch (selectedPizzaType) {
            case "BBQ Chicken":
                return nyPizzaFactory.createBBQChicken();
            case "Deluxe":
                return nyPizzaFactory.createDeluxe();
            case "Meatzza":
                return nyPizzaFactory.createMeatzza();
            case "Build Your Own":
            default:
                return nyPizzaFactory.createBuildYourOwn();
        }
    }

    /**
     * Updates the displayed price based on the selected pizza type, size, and toppings.
     */
    private void updatePrice() {
        switch (selectedPizzaType.toLowerCase()) {
            case "deluxe":
                basePrice = (selectedSize == Size.SMALL) ? 16.99 :
                        (selectedSize == Size.MEDIUM) ? 18.99 : 20.99;
                break;
            case "bbq chicken":
                basePrice = (selectedSize == Size.SMALL) ? 14.99 :
                        (selectedSize == Size.MEDIUM) ? 16.99 : 19.99;
                break;
            case "meatzza":
                basePrice = (selectedSize == Size.SMALL) ? 17.99 :
                        (selectedSize == Size.MEDIUM) ? 19.99 : 21.99;
                break;
            case "build your own":
                basePrice = (selectedSize == Size.SMALL) ? 8.99 :
                        (selectedSize == Size.MEDIUM) ? 10.99 : 12.99;
                break;
        }

        toppingPrice = selectedPizzaType.equals("Build Your Own") ?
                selectedToppings.size() * TOPPING_PRICE : 0.0;

        double totalPrice = basePrice + toppingPrice;
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));
    }
}
