package net.n_19010417.acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
    implements SensorEventListener {

    private TextView txtCoordenadaX;
    private TextView txtCoordenadaY;
    private TextView txtCoordenadaZ;

    private float actualX = 0, actualY = 0, actualZ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtCoordenadaX = findViewById(R.id.txtCoordenadaX);
        txtCoordenadaY = findViewById(R.id.txtCoordenadaY);
        txtCoordenadaZ = findViewById(R.id.txtCoordenadaZ);
    }

    @Override
    protected void onResume(){
        super.onResume();

        SensorManager sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        List <Sensor> sensors =
                sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size()>0){
            sensorManager.registerListener(this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onStop(){
        SensorManager sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

    @Override
    public void onSensorChanged(SensorEvent event){
        synchronized (this){
            actualX = event.values[0];
            actualY = event.values[1];
            actualZ = event.values[2];

            txtCoordenadaX.setText("Valor de X: " + actualX);
            txtCoordenadaY.setText("Valor de Y: " + actualY);
            txtCoordenadaZ.setText("Valor de Z: " + actualZ);

        }
    }

}