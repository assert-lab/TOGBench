package org.springside.modules.utils.collection;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.google.common.collect.Ordering;

public class ListUtilTest_OE25Dev {

	@Test
	public void guavaBuildList_1_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b");
		assertThat(list2).hasSize(2).containsExactly("a", "b");
	}

	@Test
	public void guavaBuildList_2_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b");
		// removed other assertion

		List<String> list3 = ListUtil.newArrayList(SetUtil.newHashSet("a", "b"));
		assertThat(list2).hasSize(2).containsExactly("a", "b");
	}

	@Test
	public void guavaBuildList_3_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b");
		// removed other assertion

		List<String> list3 = ListUtil.newArrayList(SetUtil.newHashSet("a", "b"));
		// removed other assertion

		List<String> list4 = ListUtil.newArrayListWithCapacity(10);

		List<String> list5 = ListUtil.newCopyOnWriteArrayList();

		List<String> list6 = ListUtil.newCopyOnWriteArrayList("a", "b");
		assertThat(list6).hasSize(2).containsExactly("a", "b");
	}

	@Test
	public void jdkBuild_1_oe() {
		List<String> list1 = ListUtil.emptyList();

		assertThat(list1).hasSize(0);
	}

	@Test
	public void jdkBuild_2_oe() {
		List<String> list1 = ListUtil.emptyList();

		// removed other assertion

		List<String> list2 = ListUtil.emptyListIfNull(null);
		assertThat(list2).isNotNull().hasSize(0);
	}

	@Test
	public void jdkBuild_3_oe() {
		List<String> list1 = ListUtil.emptyList();

		// removed other assertion

		List<String> list2 = ListUtil.emptyListIfNull(null);
		// removed other assertion

		List<String> list3 = ListUtil.emptyListIfNull(list1);
		assertThat(list3).isSameAs(list1);
	}

	@Test
	public void jdkBuild_5_oe() {
		List<String> list1 = ListUtil.emptyList();

		// removed other assertion

		List<String> list2 = ListUtil.emptyListIfNull(null);
		// removed other assertion

		List<String> list3 = ListUtil.emptyListIfNull(list1);
		// removed other assertion

		try {
			list1.add("a");
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void jdkBuild_6_oe() {
		List<String> list1 = ListUtil.emptyList();

		// removed other assertion

		List<String> list2 = ListUtil.emptyListIfNull(null);
		// removed other assertion

		List<String> list3 = ListUtil.emptyListIfNull(list1);
		// removed other assertion

		try {
			list1.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list4 = ListUtil.singletonList("1");
		assertThat(list4).hasSize(1).contains("1");
	}

	@Test
	public void jdkBuild_8_oe() {
		List<String> list1 = ListUtil.emptyList();

		// removed other assertion

		List<String> list2 = ListUtil.emptyListIfNull(null);
		// removed other assertion

		List<String> list3 = ListUtil.emptyListIfNull(list1);
		// removed other assertion

		try {
			list1.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list4 = ListUtil.singletonList("1");
		// removed other assertion
		try {
			list4.add("a");
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void jdkBuild_10_oe() {
		List<String> list1 = ListUtil.emptyList();

		// removed other assertion

		List<String> list2 = ListUtil.emptyListIfNull(null);
		// removed other assertion

		List<String> list3 = ListUtil.emptyListIfNull(list1);
		// removed other assertion

		try {
			list1.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list4 = ListUtil.singletonList("1");
		// removed other assertion
		try {
			list4.add("a");
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		List<String> list5 = ListUtil.newArrayList();
		List<String> list6 = ListUtil.unmodifiableList(list5);

		try {
			list6.add("a");
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void general_1_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		assertThat(ListUtil.isEmpty(list1)).isTrue();
	}

	@Test
	public void general_2_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		assertThat(ListUtil.isEmpty(null)).isTrue();
	}

	@Test
	public void general_3_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		assertThat(ListUtil.isEmpty(list2)).isFalse();
	}

	@Test
	public void general_4_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(ListUtil.isNotEmpty(list1)).isFalse();
	}

	@Test
	public void general_5_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(ListUtil.isNotEmpty(null)).isFalse();
	}

	@Test
	public void general_6_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(ListUtil.isNotEmpty(list2)).isTrue();
	}

	@Test
	public void general_7_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(ListUtil.getFirst(list2)).isEqualTo("a");
	}

	@Test
	public void general_8_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(ListUtil.getLast(list2)).isEqualTo("c");
	}

	@Test
	public void general_9_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		assertThat(ListUtil.getFirst(list3)).isEqualTo("a");
	}

	@Test
	public void general_10_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(ListUtil.getLast(list3)).isEqualTo("a");
	}

	@Test
	public void general_11_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		assertThat(ListUtil.getFirst(list1)).isNull();
	}

	@Test
	public void general_12_oe() {
		List<String> list1 = ListUtil.newArrayList();

		List<String> list2 = ListUtil.newArrayList("a", "b", "c");
		List<String> list3 = ListUtil.newArrayList("a");

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(ListUtil.getFirst(null)).isNull();
	}

	@Test
	public void sortAndSearch_1_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		assertThat(list).hasSize(7).containsExactly("a", "b", "c", "d", "e", "g", "i");
	}

	@Test
	public void sortAndSearch_2_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		// removed other assertion

		ListUtil.shuffle(list);
		ListUtil.shuffle(list, new Random());
		System.out.println("shuffle list:" + list);

		ListUtil.sort(list, Ordering.natural());

		assertThat(list).hasSize(7).containsExactly("a", "b", "c", "d", "e", "g", "i");
	}

	@Test
	public void sortAndSearch_3_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		// removed other assertion

		ListUtil.shuffle(list);
		ListUtil.shuffle(list, new Random());
		System.out.println("shuffle list:" + list);

		ListUtil.sort(list, Ordering.natural());

		// removed other assertion

		assertThat(ListUtil.binarySearch(list, "b")).isEqualTo(1);
	}

	@Test
	public void sortAndSearch_4_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		// removed other assertion

		ListUtil.shuffle(list);
		ListUtil.shuffle(list, new Random());
		System.out.println("shuffle list:" + list);

		ListUtil.sort(list, Ordering.natural());

		// removed other assertion

		// removed other assertion
		assertThat(ListUtil.binarySearch(list, "b", Ordering.natural())).isEqualTo(1);
	}

	@Test
	public void sortAndSearch_5_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		// removed other assertion

		ListUtil.shuffle(list);
		ListUtil.shuffle(list, new Random());
		System.out.println("shuffle list:" + list);

		ListUtil.sort(list, Ordering.natural());

		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(ListUtil.binarySearch(list, "x")).isEqualTo(-8);
	}

	@Test
	public void sortAndSearch_6_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		// removed other assertion

		ListUtil.shuffle(list);
		ListUtil.shuffle(list, new Random());
		System.out.println("shuffle list:" + list);

		ListUtil.sort(list, Ordering.natural());

		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// reverse
		List list8 = ListUtil.reverse(list);
		assertThat(list8).hasSize(7).containsExactly("i", "g", "e", "d", "c", "b", "a");
	}

	@Test
	public void sortAndSearch_7_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		// removed other assertion

		ListUtil.shuffle(list);
		ListUtil.shuffle(list, new Random());
		System.out.println("shuffle list:" + list);

		ListUtil.sort(list, Ordering.natural());

		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// reverse
		List list8 = ListUtil.reverse(list);
		// removed other assertion

		// sortReverse
		ListUtil.shuffle(list8);
		ListUtil.sortReverse(list8);
		assertThat(list8).hasSize(7).containsExactly("i", "g", "e", "d", "c", "b", "a");
	}

	@Test
	public void sortAndSearch_8_oe() {

		List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");
		ListUtil.sort(list);

		// removed other assertion

		ListUtil.shuffle(list);
		ListUtil.shuffle(list, new Random());
		System.out.println("shuffle list:" + list);

		ListUtil.sort(list, Ordering.natural());

		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// reverse
		List list8 = ListUtil.reverse(list);
		// removed other assertion

		// sortReverse
		ListUtil.shuffle(list8);
		ListUtil.sortReverse(list8);
		// removed other assertion

		ListUtil.shuffle(list8);
		ListUtil.sortReverse(list8, Ordering.natural());
		assertThat(list8).hasSize(7).containsExactly("i", "g", "e", "d", "c", "b", "a");
	}

	@Test
	public void collectionCalc_1_oe() {
		List<String> list1 = ListUtil.newArrayList("1", "2", "3", "6", "6");
		List<String> list2 = ListUtil.newArrayList("4", "5", "6", "7", "6", "6");

		List<String> result = ListUtil.union(list1, list2);
		assertThat(result).containsExactly("1", "2", "3", "6", "6", "4", "5", "6", "7", "6", "6");
	}

	@Test
	public void collectionCalc_2_oe() {
		List<String> list1 = ListUtil.newArrayList("1", "2", "3", "6", "6");
		List<String> list2 = ListUtil.newArrayList("4", "5", "6", "7", "6", "6");

		List<String> result = ListUtil.union(list1, list2);
		// removed other assertion

		List<String> result2 = ListUtil.intersection(list1, list2);
		assertThat(result2).containsExactly("6", "6");
	}

	@Test
	public void collectionCalc_3_oe() {
		List<String> list1 = ListUtil.newArrayList("1", "2", "3", "6", "6");
		List<String> list2 = ListUtil.newArrayList("4", "5", "6", "7", "6", "6");

		List<String> result = ListUtil.union(list1, list2);
		// removed other assertion

		List<String> result2 = ListUtil.intersection(list1, list2);
		// removed other assertion

		List<String> result3 = ListUtil.difference(list2, list1);
		assertThat(result3).containsExactly("4", "5", "7", "6");
	}

	@Test
	public void collectionCalc_4_oe() {
		List<String> list1 = ListUtil.newArrayList("1", "2", "3", "6", "6");
		List<String> list2 = ListUtil.newArrayList("4", "5", "6", "7", "6", "6");

		List<String> result = ListUtil.union(list1, list2);
		// removed other assertion

		List<String> result2 = ListUtil.intersection(list1, list2);
		// removed other assertion

		List<String> result3 = ListUtil.difference(list2, list1);
		// removed other assertion

		List<String> result4 = ListUtil.disjoint(list1, list2);
		assertThat(result4).containsExactly("1", "2", "3", "4", "5", "7", "6");
	}

}
