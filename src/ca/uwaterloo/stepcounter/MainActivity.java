package ca.uwaterloo.stepcounter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	int numberOfStep=0;
	TextView tv;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LinearLayout layout1 = ((LinearLayout)findViewById(R.id.layout1));
		
		//Register sensor listener
		SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		Sensor LinerAcceleratormeter = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		SensorEventListener a1 = new GeneralSensorEventListener();
		sm.registerListener(a1, LinerAcceleratormeter,SensorManager.SENSOR_DELAY_FASTEST);
		
		//TextView setup
		tv = new TextView(this);
		layout1.addView(tv);
		tv.setText("Total number of steps: 0");
		
		button = new Button(this);
		button.setText("Clear");
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				numberOfStep = 0;
				FiniteStateMachine.clear();
				tv.setText("Total number of steps: "+numberOfStep);
			}
		});
	}
	
	
	public class GeneralSensorEventListener implements SensorEventListener
	{
		float filtData;
		@Override
		public void onSensorChanged(SensorEvent event) 
		{
			switch (event.sensor.getType())
			{
			case (Sensor.TYPE_LINEAR_ACCELERATION):
				filtData = Helper.LowPassFilter(event.values[1]);
				numberOfStep += FiniteStateMachine.changeState(filtData);
				break;
			default: break;
			}
			tv.setText("Total number of steps: "+numberOfStep);
		}
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
