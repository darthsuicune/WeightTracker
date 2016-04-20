package dlgdev.weighttracker.domain;

import java.util.List;

public class MathUtils {
	public static Double average(List<Double> elements) {
		double avg = 0.0;
		for(Double item : elements) {
			avg += item;
		}
		return avg / elements.size();
	}

	public static double average(double[] elements) {
		double avg = 0.0;
		for(int i = 0, len = elements.length; i < len; i++) {
			avg += elements[i];
		}
		return avg / elements.length;
	}
}
