package com.rebaiahmed.hrm;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    // L'accéléromètre
    Sensor HRM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instancier le gestionnaire des capteurs,  le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Instancier l'accéléromètre
        HRM = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d("Accuracy", "onAccuracyChanged - accuracy: " + i);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, HRM);
        super.onPause();

    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, HRM, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }


    public void onSensorChanged(SensorEvent event) {   // Récupérer les valeurs du capteur
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            String msg = "" + (int) event.values[0];
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            Log.d("Heart rate: ", msg);
        } else
            Log.d("Error", "Unknown sensor type");
    }


}
