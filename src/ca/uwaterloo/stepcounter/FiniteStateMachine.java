package ca.uwaterloo.stepcounter;
/*
 * Initial, state is 0 and the sensor value is between -0.13 and 0.5.
 * If the value goes above 0.5, we enter state 1.
 * In state 1, if the value goes below -0.13, we count a step and state goes back to 0;
 * But if the value goes further beyond 2, we get rid of this data and do not count for a step.
 * */
public class FiniteStateMachine 
{
	private static int state;
	public static int changeState(float sensorData)
	{
		switch(state)
		{
		case 0:
			if(sensorData>=0.5)
			{
				state = 1;
			}
			break;
		case 1:
			if(sensorData<=-0.13)
			{
				state = 0;
				return 1;
			}
			else if(sensorData>=2)
			{
				state = 2;
			}
			break;
		case 2:
			if(sensorData<0.5)
			{
				state = 0;
			}
			break;
		default: break;
		}
		return 0;
	}
	
	public static void clear()
	{
		state = 0;
	}
}
