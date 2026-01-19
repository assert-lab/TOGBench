package org.springside.modules.utils.collection.type;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.utils.collection.ListUtil;

import com.google.common.collect.Ordering;

public class SortedArrayListTest_OE25Dev {

	@Test
	public void sortedArrayList_1_oe() {
		SortedArrayList<String> list = ListUtil.createSortedArrayList();
		list.add("9");
		list.add("1");
		list.add("6");
		list.add("9");
		list.add("3");

		assertThat(list).containsExactly("1", "3", "6", "9", "9");
	}

	@Test
	public void sortedArrayList_2_oe() {
		SortedArrayList<String> list = ListUtil.createSortedArrayList();
		list.add("9");
		list.add("1");
		list.add("6");
		list.add("9");
		list.add("3");

		// removed other assertion

		list.remove(2);
		assertThat(list).containsExactly("1", "3", "9", "9");
	}

	@Test
	public void sortedArrayList_3_oe() {
		SortedArrayList<String> list = ListUtil.createSortedArrayList();
		list.add("9");
		list.add("1");
		list.add("6");
		list.add("9");
		list.add("3");

		// removed other assertion

		list.remove(2);
		// removed other assertion

		assertThat(list.contains("3")).isTrue();
	}

	@Test
	public void sortedArrayList_4_oe() {
		SortedArrayList<String> list = ListUtil.createSortedArrayList();
		list.add("9");
		list.add("1");
		list.add("6");
		list.add("9");
		list.add("3");

		// removed other assertion

		list.remove(2);
		// removed other assertion

		// removed other assertion
		assertThat(list.contains("2")).isFalse();
	}

	@Test
	public void sortedArrayList_6_oe() {
		SortedArrayList<String> list = ListUtil.createSortedArrayList();
		list.add("9");
		list.add("1");
		list.add("6");
		list.add("9");
		list.add("3");

		// removed other assertion

		list.remove(2);
		// removed other assertion

		// removed other assertion
		// removed other assertion

		try {
			list.add(1, "2");
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void sortedArrayList_8_oe() {
		SortedArrayList<String> list = ListUtil.createSortedArrayList();
		list.add("9");
		list.add("1");
		list.add("6");
		list.add("9");
		list.add("3");

		// removed other assertion

		list.remove(2);
		// removed other assertion

		// removed other assertion
		// removed other assertion

		try {
			list.add(1, "2");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			list.set(1, "2");
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void sortedArrayList_9_oe() {
		SortedArrayList<String> list = ListUtil.createSortedArrayList();
		list.add("9");
		list.add("1");
		list.add("6");
		list.add("9");
		list.add("3");

		// removed other assertion

		list.remove(2);
		// removed other assertion

		// removed other assertion
		// removed other assertion

		try {
			list.add(1, "2");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			list.set(1, "2");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		SortedArrayList<String> list2 = ListUtil.createSortedArrayList(Ordering.natural());
		list2.addAll(ListUtil.newArrayList("3", "1", "2"));
		assertThat(list2).containsExactly("1", "2", "3");
	}

}
