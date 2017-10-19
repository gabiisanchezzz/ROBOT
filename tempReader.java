import rxtxrobot.*;

public class tempReader {

		public static RXTXRobot robot;
		
		// TODO Auto-generated method stub
		public static void main(String[] args) {
			
			RXTXRobot robot = new ArduinoUno(); 
			
			//Connect to the arduino
			robot = new ArduinoUno();
			robot.setPort("/dev/tty.usbmodem1421");
			robot.connect();
			
			//Get the average thermistor reading
			int thermistorReading = getThermistorReading(robot);
			
			double temp = (thermistorReading - 7.155080214)/259.4679144;
			
			//Print the results
			System.out.println("The probe reads the value: " + thermistorReading);
			System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
			System.out.println("Temperature = " + temp);
			
		}
		
		public static int getThermistorReading(RXTXRobot robot) {
			
			int sum = 0;
			int readingCount = 10;
			
			//Read the analog pin values ten times, adding to sum each time
			for (int i = 0; i < readingCount; i++) {
				
				//Refresh the analog pins so we get new readings
				robot.refreshAnalogPins();
				int reading = robot.getAnalogPin(0).getValue();
				sum += reading;
			}
			
			//Return the average reading
			return sum / readingCount;
			
		}
	

}
