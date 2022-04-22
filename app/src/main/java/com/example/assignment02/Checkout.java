// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment02.Util.ShoppingCart;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class Checkout extends AppCompatActivity {
    MaterialButton submitButton;
    RadioButton pickupRadio;
    RadioButton deliveredRadio;
    TextView timeRemaining;
    private final int TIMER_MINUTES = 1;
    private View layoutView;

    /**
     *  method to initial the components
     */
    private void initComponant(){
        submitButton = findViewById(R.id.submitButton);
        pickupRadio = findViewById(R.id.radio_button_pick_up);
        deliveredRadio = findViewById(R.id.radio_button_delivered);
        layoutView = findViewById(R.id.layout_view);
        timeRemaining = findViewById(R.id.timeRemaining);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initComponant();
        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        /**
         * when the button is click
         *  if pickup is chosen that Toast and send back to the MainActivity
         *  if delevere is chosen that timer 1 min and Toast(is deliverer) , send back to the mainActivity
         */
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pickupRadio.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Your order is ready for pickup", Toast.LENGTH_SHORT).show();
                    ShoppingCart.getInstance().clearproducts();
                    startActivity(returnIntent);

                } else if (deliveredRadio.isChecked()) {
                        view.setEnabled(false);
                        //start a timer
                        new CountDownTimer(TIMER_MINUTES * 60 * 1000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                timeRemaining.setText("Your delivery is own its way: " + millisUntilFinished / 1000 + " seconds");
                            }

                            public void onFinish() {
                                Toast.makeText(getApplicationContext(), "Your order has been delivered", Toast.LENGTH_SHORT).show();
                                ShoppingCart.getInstance().clearproducts();
                                startActivity(returnIntent);
                            }
                        }.start();
            }
        }
        });
    }
}