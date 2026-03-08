package org.springside.modules.utils.collection;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springside.modules.utils.number.RandomUtil;

public class ArrayUtilTest_OE25Dev {

	@Test
	public void shuffle() {
		String[] arrays = new String[] { "d", "a", "c", "b", "e", "i", "g" };
		Arrays.sort(arrays);
		assertThat(arrays).containsExactly("a", "b", "c", "d", "e", "g", "i");
		ArrayUtil.shuffle(arrays);
		System.out.println(Arrays.toString(arrays));
		Arrays.sort(arrays);

		ArrayUtil.shuffle(arrays, RandomUtil.secureRandom());
	}

	@Test
	public void asList() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		assertThat(list).hasSize(7).containsExactly("d", "a", "c", "b", "e", "i", "g");

		try {
			list.add("a");
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
		}

		List<String> list2 = ArrayUtil.asList("d", new String[] { "a", "c", "b", "e", "i", "g" });
		assertThat(list2).hasSize(7).containsExactly("d", "a", "c", "b", "e", "i", "g");

		try {
			list2.add("a");
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
		}

		List<Integer> list3 = ArrayUtil.intAsList(1, 2, 3);
		assertThat(list3).hasSize(3).containsExactly(1, 2, 3);

		List<Long> list4 = ArrayUtil.longAsList(1L, 2L, 3L);
		assertThat(list4).hasSize(3).containsExactly(1L, 2L, 3L);

		List<Double> list5 = ArrayUtil.doubleAsList(1.1d, 2.2d, 3.3d);
		assertThat(list5).hasSize(3).containsExactly(1.1d, 2.2d, 3.3d);
	}

	@Test
	public void contact() {
		String[] array = new String[] { "d", "a", "c" };
		assertThat(ArrayUtil.concat("z", array)).containsExactly("z", "d", "a", "c");
		assertThat(ArrayUtil.concat(array, "z")).containsExactly("d", "a", "c", "z");
	}

	@Test
	public void shuffle_1_oe() {
		String[] arrays = new String[] { "d", "a", "c", "b", "e", "i", "g" };
		Arrays.sort(arrays);
		assertThat(arrays).containsExactly("a", "b", "c", "d", "e", "g", "i");
	}

	@Test
	public void asList_1_oe() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		assertThat(list).hasSize(7).containsExactly("d", "a", "c", "b", "e", "i", "g");
	}

	@Test
	public void asList_3_oe() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		// removed other assertion

		try {
			list.add("a");
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void asList_4_oe() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		// removed other assertion

		try {
			list.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list2 = ArrayUtil.asList("d", new String[] { "a", "c", "b", "e", "i", "g" });
		assertThat(list2).hasSize(7).containsExactly("d", "a", "c", "b", "e", "i", "g");
	}

	@Test
	public void asList_6_oe() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		// removed other assertion

		try {
			list.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list2 = ArrayUtil.asList("d", new String[] { "a", "c", "b", "e", "i", "g" });
		// removed other assertion

		try {
			list2.add("a");
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void asList_7_oe() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		// removed other assertion

		try {
			list.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list2 = ArrayUtil.asList("d", new String[] { "a", "c", "b", "e", "i", "g" });
		// removed other assertion

		try {
			list2.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<Integer> list3 = ArrayUtil.intAsList(1, 2, 3);
		assertThat(list3).hasSize(3).containsExactly(1, 2, 3);
	}

	@Test
	public void asList_8_oe() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		// removed other assertion

		try {
			list.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list2 = ArrayUtil.asList("d", new String[] { "a", "c", "b", "e", "i", "g" });
		// removed other assertion

		try {
			list2.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<Integer> list3 = ArrayUtil.intAsList(1, 2, 3);
		// removed other assertion

		List<Long> list4 = ArrayUtil.longAsList(1L, 2L, 3L);
		assertThat(list4).hasSize(3).containsExactly(1L, 2L, 3L);
	}

	@Test
	public void asList_9_oe() {
		List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
		// removed other assertion

		try {
			list.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list2 = ArrayUtil.asList("d", new String[] { "a", "c", "b", "e", "i", "g" });
		// removed other assertion

		try {
			list2.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<Integer> list3 = ArrayUtil.intAsList(1, 2, 3);
		// removed other assertion

		List<Long> list4 = ArrayUtil.longAsList(1L, 2L, 3L);
		// removed other assertion

		List<Double> list5 = ArrayUtil.doubleAsList(1.1d, 2.2d, 3.3d);
		assertThat(list5).hasSize(3).containsExactly(1.1d, 2.2d, 3.3d);
	}

	@Test
	public void contact_1_oe() {
		String[] array = new String[] { "d", "a", "c" };
		assertThat(ArrayUtil.concat("z", array)).containsExactly("z", "d", "a", "c");
	}

	@Test
	public void contact_2_oe() {
		String[] array = new String[] { "d", "a", "c" };
		// removed other assertion
		assertThat(ArrayUtil.concat(array, "z")).containsExactly("d", "a", "c", "z");
	}

}
