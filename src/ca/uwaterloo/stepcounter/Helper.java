package ca.uwaterloo.stepcounter;

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
}
