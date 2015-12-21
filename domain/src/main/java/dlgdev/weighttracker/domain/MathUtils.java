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

	public static Double average(double[] elements) {
		double avg = 0.0;
		for(Double item : elements) {
			avg += item;
		}
		return avg / elements.length;
	}
}
