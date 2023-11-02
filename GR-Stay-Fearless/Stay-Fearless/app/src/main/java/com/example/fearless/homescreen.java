package com.example.fearless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.telephony.SmsManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class homescreen extends AppCompatActivity {

    public CardView card1, card2, card3, card4, card5, card6;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private boolean permissionsRequested = false;
    private Button panicButton;
    private boolean isButtonPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        card1 = findViewById(R.id.addcontact);
        card2 = findViewById(R.id.defencecard);
        card3 = findViewById(R.id.helplinecard);
        card4 = findViewById(R.id.nearestcard);
        card5 = findViewById(R.id.savedcontactcard);
        card6 = findViewById(R.id.smsalertcard);
        panicButton = findViewById(R.id.panicbutton);

        // Check and request permissions if not granted before
        if (!permissionsRequested) {
            requestPermissions();
            permissionsRequested = true;
        }

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homescreen.this, Add_contacts.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homescreen.this, self_defence.class);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPolice();
            }
        });

        // Handle the Nearest Police Station CardView click
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNearestPoliceStation();
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homescreen.this, saved_contacts.class);
                startActivity(intent);
            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homescreen.this, sms_helpline.class);
                startActivity(intent);
            }
        });

        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePanicButtonClick();
            }
        });
    }

    private void requestPermissions() {
        String[] permissions = {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    private void callPolice() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:100"));
        startActivity(intent);
    }

    private void openNearestPoliceStation() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=nearest+police+station");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void handlePanicButtonClick() {
        if (isButtonPressed) {
            panicButton.setBackgroundResource(R.drawable.button_panic_normal);
            isButtonPressed = false;
            showToast("PANIC MODE IS DEACTIVATED");
        } else {
            panicButton.setBackgroundResource(R.drawable.button_panic_pressed);
            isButtonPressed = true;
            showToast("PANIC MODE IS ACTIVATED");
            // Retrieve contact numbers from saved_contacts
            String[] contactNumbers = saved_contacts.getContactNumbers(this);
            // Send alert messages to retrieved contact numbers
            sendAlertMessages(contactNumbers);
            // Implement your panic action code here
            // For example, sending messages to saved contacts
            sendPanicAlert();
        }
    }

    private void sendAlertMessages(String[] contactNumbers) {
        for (String number : contactNumbers) {
            sendAlertMessageToNumber(number,"This is Emergency alert! I need help.");
        }
    }

    private void sendPanicAlert() {
        String[] contactNumbers = saved_contacts.getContactNumbers(this);
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();
                                String googleMapsLink = "https://www.google.com/maps?q=" + latitude + "," + longitude;
                                String message = "This is Emergency alert! I need help. My last known location: " + googleMapsLink;

                                for (String number : contactNumbers) {
                                    sendAlertMessageToNumber(number, message);
                                }
                            } else {
                                showToast("Location not available");
                            }
                        }
                    });
        } else {
            showToast("Location permission not granted. Cannot get current location.");
        }
    }

    private void sendAlertMessageToNumber(String number, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Check if all permissions were granted
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (!allPermissionsGranted) {
                // Explain to the user that permissions are needed to use the app
                Toast.makeText(this, "Please grant all required permissions to use the app.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAndEnableGPS();
    }

    private void checkAndEnableGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!isGPSEnabled) {
            showGPSEnableDialog();
        }
    }

    private void showGPSEnableDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This app requires GPS to be enabled. Do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent gpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(gpsIntent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
