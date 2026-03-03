package org.springside.modules.utils.collection.type;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.utils.collection.SetUtil;

public class ConcurrentHashSetTest_OE25Dev {

	@Test
	public void concurrentHashSet_1_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");

		assertThat(conrrentHashSet.isEmpty()).isFalse();
	}

	@Test
	public void concurrentHashSet_2_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");

		assertThat(conrrentHashSet.contains("a")).isTrue();
	}

	@Test
	public void concurrentHashSet_3_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");

		assertThat(conrrentHashSet.contains("d")).isFalse();
	}

	@Test
	public void concurrentHashSet_4_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");


		assertThat(conrrentHashSet).hasSize(3).contains("a", "b", "c");
	}

	@Test
	public void concurrentHashSet_5_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");
		assertThat(conrrentHashSet).hasSize(2);
	}

	@Test
	public void concurrentHashSet_6_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();
		assertThat(strings).hasSize(2).contains("a", "b");
	}

	@Test
	public void concurrentHashSet_7_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();

		conrrentHashSet.toArray(new String[conrrentHashSet.size()]);
		conrrentHashSet.hashCode();
		conrrentHashSet.toString();

		ConcurrentHashSet<String> conrrentHashSet2 = SetUtil.newConcurrentHashSet();
		conrrentHashSet2.add("a");

		assertThat(conrrentHashSet.equals(conrrentHashSet)).isTrue();
	}

	@Test
	public void concurrentHashSet_8_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();

		conrrentHashSet.toArray(new String[conrrentHashSet.size()]);
		conrrentHashSet.hashCode();
		conrrentHashSet.toString();

		ConcurrentHashSet<String> conrrentHashSet2 = SetUtil.newConcurrentHashSet();
		conrrentHashSet2.add("a");

		assertThat(conrrentHashSet.equals(conrrentHashSet2)).isFalse();
	}

	@Test
	public void concurrentHashSet_9_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();

		conrrentHashSet.toArray(new String[conrrentHashSet.size()]);
		conrrentHashSet.hashCode();
		conrrentHashSet.toString();

		ConcurrentHashSet<String> conrrentHashSet2 = SetUtil.newConcurrentHashSet();
		conrrentHashSet2.add("a");


		assertThat(conrrentHashSet.containsAll(conrrentHashSet2)).isTrue();
	}

	@Test
	public void concurrentHashSet_10_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();

		conrrentHashSet.toArray(new String[conrrentHashSet.size()]);
		conrrentHashSet.hashCode();
		conrrentHashSet.toString();

		ConcurrentHashSet<String> conrrentHashSet2 = SetUtil.newConcurrentHashSet();
		conrrentHashSet2.add("a");



		conrrentHashSet.retainAll(conrrentHashSet2);
		assertThat(conrrentHashSet).hasSize(1).contains("a");
	}

	@Test
	public void concurrentHashSet_11_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();

		conrrentHashSet.toArray(new String[conrrentHashSet.size()]);
		conrrentHashSet.hashCode();
		conrrentHashSet.toString();

		ConcurrentHashSet<String> conrrentHashSet2 = SetUtil.newConcurrentHashSet();
		conrrentHashSet2.add("a");



		conrrentHashSet.retainAll(conrrentHashSet2);
		assertThat(conrrentHashSet.equals(conrrentHashSet2)).isTrue();
	}

	@Test
	public void concurrentHashSet_12_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();

		conrrentHashSet.toArray(new String[conrrentHashSet.size()]);
		conrrentHashSet.hashCode();
		conrrentHashSet.toString();

		ConcurrentHashSet<String> conrrentHashSet2 = SetUtil.newConcurrentHashSet();
		conrrentHashSet2.add("a");



		conrrentHashSet.retainAll(conrrentHashSet2);

		conrrentHashSet.removeAll(conrrentHashSet2);
		assertThat(conrrentHashSet.isEmpty()).isTrue();
	}

	@Test
	public void concurrentHashSet_13_oe() {
		ConcurrentHashSet<String> conrrentHashSet = SetUtil.newConcurrentHashSet();
		conrrentHashSet.add("a");
		conrrentHashSet.add("b");
		conrrentHashSet.add("c");



		for (String key : conrrentHashSet) {
			System.out.print(key + ",");
		}

		conrrentHashSet.remove("c");

		Object[] strings = conrrentHashSet.toArray();

		conrrentHashSet.toArray(new String[conrrentHashSet.size()]);
		conrrentHashSet.hashCode();
		conrrentHashSet.toString();

		ConcurrentHashSet<String> conrrentHashSet2 = SetUtil.newConcurrentHashSet();
		conrrentHashSet2.add("a");



		conrrentHashSet.retainAll(conrrentHashSet2);

		conrrentHashSet.removeAll(conrrentHashSet2);

		conrrentHashSet2.clear();
		assertThat(conrrentHashSet2.isEmpty()).isTrue();
	}

}
