package dlgdev.weighttrackerdomain.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dlgdev.weighttracker.domain.MathUtils;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MathUtilsTest {
    @Test public void average_isCorrect() throws Exception {
		List<Double> list = new ArrayList<>();
		list.add(1.0);
		list.add(2.5);
		list.add(3.87);
		list.add(0.01);
		assertEquals(1.845, MathUtils.average(list), 0.0001);
    }

	@Test public void arrayAverage_isCorrect() throws Exception {
		double[] list = {1.0, 2.5, 3.87, 0.01 };
		assertEquals(1.845, MathUtils.average(list), 0.0001);
	}
}