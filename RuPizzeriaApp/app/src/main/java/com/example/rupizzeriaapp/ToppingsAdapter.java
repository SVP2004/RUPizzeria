package com.example.rupizzeriaapp;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Adapter for displaying and managing pizza topping selections in a RecyclerView.
 * Handles both predefined pizza toppings and "Build Your Own" topping selections.
 */
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ToppingViewHolder> {
    private List<ToppingItem> toppingItems;
    private OnToppingSelectedListener listener;
    private boolean toppingSelectionEnabled = true;
    private static final int MAX_TOPPINGS = 7;
    private boolean isPredefinedPizza;
    private List<Topping> predefinedToppings;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    /**
     * Listener interface for reacting to topping selection changes.
     */
    public interface OnToppingSelectedListener {
        /**
         * Called when a topping is selected or deselected.
         *
         * @param toppingItem The topping item that was selected or deselected.
         * @param isChecked   Whether the topping is currently selected.
         */
        void onToppingSelected(ToppingItem toppingItem, boolean isChecked);
    }

    /**
     * Constructs a new {@code ToppingsAdapter}.
     *
     * @param toppingItems       List of {@link ToppingItem} objects representing the available toppings.
     * @param listener           Listener to handle topping selection events.
     * @param isPredefinedPizza  Indicates whether the pizza is predefined or "Build Your Own".
     */
    public ToppingsAdapter(List<ToppingItem> toppingItems, OnToppingSelectedListener listener, boolean isPredefinedPizza) {
        this.toppingItems = toppingItems;
        this.listener = listener;
        this.isPredefinedPizza = isPredefinedPizza;
    }

    @NonNull
    @Override
    public ToppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topping_item, parent, false);
        return new ToppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToppingViewHolder holder, int position) {
        ToppingItem toppingItem = toppingItems.get(position);
        holder.bind(toppingItem);
    }

    @Override
    public int getItemCount() {
        return toppingItems.size();
    }

    /**
     * Enables or disables the ability to select toppings.
     *
     * @param enabled True to enable topping selection; false to disable.
     */
    public void setToppingSelectionEnabled(boolean enabled) {
        this.toppingSelectionEnabled = enabled;
        mainHandler.post(this::notifyDataSetChanged);
    }

    /**
     * Sets the toppings that should be preselected (for predefined pizzas).
     *
     * @param preselectedToppings List of preselected {@link Topping} objects.
     */
    public void setPreselectedToppings(List<Topping> preselectedToppings) {
        this.predefinedToppings = preselectedToppings;

        for (ToppingItem toppingItem : toppingItems) {
            toppingItem.isSelected = predefinedToppings.contains(toppingItem.topping);
        }

        mainHandler.post(this::notifyDataSetChanged);
    }

    /**
     * Resets all topping selections to unselected.
     */
    public void resetToppingSelections() {
        for (ToppingItem toppingItem : toppingItems) {
            toppingItem.isSelected = false;
        }
        mainHandler.post(this::notifyDataSetChanged);
    }

    /**
     * ViewHolder class for displaying individual topping items in the RecyclerView.
     */
    class ToppingViewHolder extends RecyclerView.ViewHolder {
        private ImageView toppingImage;
        private TextView toppingName;
        private CheckBox toppingCheckBox;
        private TextView toppingStatus;

        /**
         * Constructs a {@code ToppingViewHolder}.
         *
         * @param itemView The view representing a single topping item.
         */
        public ToppingViewHolder(@NonNull View itemView) {
            super(itemView);
            toppingImage = itemView.findViewById(R.id.topping_image);
            toppingName = itemView.findViewById(R.id.topping_name);
            toppingCheckBox = itemView.findViewById(R.id.topping_checkbox);
            toppingStatus = itemView.findViewById(R.id.topping_status);
        }

        /**
         * Binds a {@link ToppingItem} to the view, updating the UI to reflect the topping's state.
         *
         * @param toppingItem The topping item to bind to this view.
         */
        public void bind(ToppingItem toppingItem) {
            toppingImage.setImageResource(toppingItem.imageResourceId);
            toppingName.setText(toppingItem.name);

            if (isPredefinedPizza) {
                toppingCheckBox.setChecked(predefinedToppings.contains(toppingItem.topping));
                toppingCheckBox.setEnabled(false);

                toppingCheckBox.setOnTouchListener((v, event) -> true); // Block all touch events

                if (predefinedToppings.contains(toppingItem.topping)) {
                    toppingStatus.setVisibility(View.VISIBLE);
                    toppingStatus.setText("Preset Topping");
                } else {
                    toppingStatus.setVisibility(View.GONE);
                }
            }else{

                handleBuildYourOwnToppings(toppingItem);
            }
        }

        private void handlePredefinedPizzaToppings(ToppingItem toppingItem) {
            boolean isPredefinedTopping = predefinedToppings.contains(toppingItem.topping);

            toppingCheckBox.setChecked(isPredefinedTopping);

            toppingCheckBox.setOnCheckedChangeListener(null);

            toppingCheckBox.setEnabled(false);

            toppingCheckBox.setAlpha(1.0f);

            toppingCheckBox.setClickable(false);

            itemView.setOnClickListener(null);

            if (isPredefinedTopping) {
                toppingStatus.setVisibility(View.VISIBLE);
                toppingStatus.setText("Preset Topping");
            } else {
                toppingStatus.setVisibility(View.GONE);
            }
        }

        private void handleBuildYourOwnToppings(ToppingItem toppingItem) {
            if (isPredefinedPizza) {
                toppingCheckBox.setChecked(predefinedToppings.contains(toppingItem.topping));
                toppingCheckBox.setEnabled(false);
                toppingCheckBox.setOnTouchListener((v, event) -> true);

                if (predefinedToppings.contains(toppingItem.topping)) {
                    toppingStatus.setVisibility(View.VISIBLE);
                    toppingStatus.setText("Preset Topping");
                } else {
                    toppingStatus.setVisibility(View.GONE);
                }
                return;
            }


            toppingCheckBox.setOnCheckedChangeListener(null);
            toppingCheckBox.setChecked(toppingItem.isSelected);

            int selectedCount = getSelectedToppingsCount();

            if (toppingItem.isSelected) {
                toppingCheckBox.setEnabled(true);
                toppingCheckBox.setAlpha(1.0f);
                toppingStatus.setVisibility(View.VISIBLE);
                toppingStatus.setText("Selected Topping");
            }
            else if (selectedCount < MAX_TOPPINGS) {
                toppingCheckBox.setEnabled(toppingSelectionEnabled);
                toppingCheckBox.setAlpha(toppingSelectionEnabled ? 1.0f : 0.5f);
                toppingStatus.setVisibility(View.GONE);
            }
            else {
                toppingCheckBox.setEnabled(false);
                toppingCheckBox.setAlpha(0.5f);
                toppingStatus.setVisibility(View.VISIBLE);
                toppingStatus.setText("Max 7 Toppings");
            }

            toppingCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (toppingItem.isSelected) {
                    toppingItem.isSelected = false;
                    listener.onToppingSelected(toppingItem, false);
                    notifyDataSetChanged();
                    return;
                }

                if (getSelectedToppingsCount() < MAX_TOPPINGS) {
                    toppingItem.isSelected = true;
                    listener.onToppingSelected(toppingItem, true);
                    notifyDataSetChanged();
                } else {
                    buttonView.setChecked(false);
                    Toast.makeText(buttonView.getContext(),
                            "Maximum of 7 toppings allowed",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        private int getSelectedToppingsCount() {
            int count = 0;
            for (ToppingItem item : toppingItems) {
                if (item.isSelected) {
                    count++;
                }
            }
            return count;
        }
    }
}