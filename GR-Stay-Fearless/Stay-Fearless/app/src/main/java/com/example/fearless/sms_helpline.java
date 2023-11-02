package com.example.fearless;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.telephony.SmsManager;
import android.content.Intent;
import android.net.Uri;

public class sms_helpline extends AppCompatActivity {

    private Button startStopButton;
    private boolean isSendingAlert = false;
    private String[] contactNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_helpline);

        startStopButton = findViewById(R.id.startStopButton);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSendingAlert) {
                    stopSendingAlert();
                    startStopButton.setText("Start");
                } else {
                    startSendingAlert();
                    startStopButton.setText("Stop");
                }
            }
        });

        // Initialize your CardViews here
        CardView ambulanceCard = findViewById(R.id.ambulancecard);
        CardView womenDistressCard = findViewById(R.id.womendistresscard);
        CardView studentChildCard = findViewById(R.id.student_childcard);

        ambulanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialNumber("108");
            }
        });

        womenDistressCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialNumber("1091");
            }
        });

        studentChildCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialNumber("1098");
            }
        });
    }

    private void dialNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void updateButtonBackground() {
        if (isSendingAlert) {
            startStopButton.setBackgroundResource(R.drawable.button_start_pressed);
        } else {
            startStopButton.setBackgroundResource(R.drawable.button_start_normal);
        }
    }

    private void startSendingAlert() {
        isSendingAlert = true;
        updateButtonBackground();
        sendAlertSMS();
    }

    private void stopSendingAlert() {
        isSendingAlert = false;
        updateButtonBackground();
    }

    private void sendAlertSMS() {
        saved_contacts savedContactsActivity = new saved_contacts();
        String[] contactNumbersArray = savedContactsActivity.getContactNumbers(this);

        if (contactNumbersArray != null) {
            contactNumbers = contactNumbersArray;

            for (String number : contactNumbers) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    String message = "I think I could get in Danger, Stay Alert! and Wait for my PANIC MESSAGE.";
                    smsManager.sendTextMessage(number, null, message, null, null);
                    Toast.makeText(this, "Alert SMS sent to: " + number, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Failed to send SMS to: " + number, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
