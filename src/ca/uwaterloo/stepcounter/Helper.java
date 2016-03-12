package ca.uwaterloo.stepcounter;

import android.hardware.SensorManager;

public class Helper
{
	/*
	 * Low pass filter is used to eliminate the noise and make the curve smoother.
	 * I do this by taking average of a set of the data.
	 * */
	private static float currentValue=0;
	public static float LowPassFilter(float newValue)
	{
		currentValue = currentValue+(newValue-currentValue)/40;
		return currentValue;
	}
	
	
	/*
	 * Take input from rotation sensor;
	 * Calculate the angle in radius from the north.
	 * */
	public static float getDirection(float[] rotationVector)
	{
		float[] direction = new float[9];
		float[] rotationMatrix = new float[9];
		SensorManager.getRotationMatrixFromVector(rotationMatrix, rotationVector);
		SensorManager.getOrientation(rotationMatrix, direction);
		return direction[0];
	}
}
