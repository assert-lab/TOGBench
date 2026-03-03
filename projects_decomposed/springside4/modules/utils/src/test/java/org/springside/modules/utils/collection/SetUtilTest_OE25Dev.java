package org.springside.modules.utils.collection;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.springside.modules.utils.collection.type.ConcurrentHashSet;

import com.google.common.collect.Ordering;

public class SetUtilTest_OE25Dev {

	@Test
	public void guavaBuildSet_1_oe() {
		HashSet<String> set1 = SetUtil.newHashSet();

		HashSet<String> set2 = SetUtil.newHashSetWithCapacity(10);

		HashSet<String> set3 = SetUtil.newHashSet("1", "2", "2");

		assertThat(set3).hasSize(2).contains("1", "2");
	}

	@Test
	public void guavaBuildSet_2_oe() {
		HashSet<String> set1 = SetUtil.newHashSet();

		HashSet<String> set2 = SetUtil.newHashSetWithCapacity(10);

		HashSet<String> set3 = SetUtil.newHashSet("1", "2", "2");


		HashSet<String> set4 = SetUtil.newHashSet(ListUtil.newArrayList("1", "2", "2"));
		assertThat(set4).hasSize(2).contains("1", "2");
	}

	@Test
	public void jdkBuildSet_1_oe() {
		Set<String> set1 = SetUtil.emptySet();
		assertThat(set1).hasSize(0);
	}

	@Test
	public void jdkBuildSet_2_oe() {
		Set<String> set1 = SetUtil.emptySet();

		Set<String> set2 = SetUtil.emptySetIfNull(null);
		assertThat(set2).isNotNull().hasSize(0);
	}

	@Test
	public void jdkBuildSet_3_oe() {
		Set<String> set1 = SetUtil.emptySet();

		Set<String> set2 = SetUtil.emptySetIfNull(null);

		Set<String> set3 = SetUtil.emptySetIfNull(set1);
		assertThat(set3).isSameAs(set1);
	}

	@Test
	public void jdkBuildSet_5_oe() {
		Set<String> set1 = SetUtil.emptySet();

		Set<String> set2 = SetUtil.emptySetIfNull(null);

		Set<String> set3 = SetUtil.emptySetIfNull(set1);

		try {
			set1.add("a");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void jdkBuildSet_6_oe() {
		Set<String> set1 = SetUtil.emptySet();

		Set<String> set2 = SetUtil.emptySetIfNull(null);

		Set<String> set3 = SetUtil.emptySetIfNull(set1);

		try {
			set1.add("a");
		} catch (Throwable t) {
		}

		Set<String> set4 = SetUtil.singletonSet("1");
		assertThat(set4).hasSize(1).contains("1");
	}

	@Test
	public void jdkBuildSet_8_oe() {
		Set<String> set1 = SetUtil.emptySet();

		Set<String> set2 = SetUtil.emptySetIfNull(null);

		Set<String> set3 = SetUtil.emptySetIfNull(set1);

		try {
			set1.add("a");
		} catch (Throwable t) {
		}

		Set<String> set4 = SetUtil.singletonSet("1");
		try {
			set4.add("a");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void jdkBuildSet_10_oe() {
		Set<String> set1 = SetUtil.emptySet();

		Set<String> set2 = SetUtil.emptySetIfNull(null);

		Set<String> set3 = SetUtil.emptySetIfNull(set1);

		try {
			set1.add("a");
		} catch (Throwable t) {
		}

		Set<String> set4 = SetUtil.singletonSet("1");
		try {
			set4.add("a");
		} catch (Throwable t) {
		}

		Set<String> set5 = SetUtil.newHashSet();
		Set<String> set6 = SetUtil.unmodifiableSet(set5);

		try {
			set6.add("a");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void collectionCaculate_1_oe() {
		HashSet<String> set1 = SetUtil.newHashSet("1", "2", "3", "6");
		HashSet<String> set2 = SetUtil.newHashSet("4", "5", "6", "7");

		Set<String> set3 = SetUtil.unionView(set1, set2);
		assertThat(set3).hasSize(7).contains("1", "2", "3", "4", "5", "6", "7");
	}

	@Test
	public void collectionCaculate_2_oe() {
		HashSet<String> set1 = SetUtil.newHashSet("1", "2", "3", "6");
		HashSet<String> set2 = SetUtil.newHashSet("4", "5", "6", "7");

		Set<String> set3 = SetUtil.unionView(set1, set2);

		Set<String> set4 = SetUtil.intersectionView(set1, set2);
		assertThat(set4).hasSize(1).contains("6");
	}

	@Test
	public void collectionCaculate_3_oe() {
		HashSet<String> set1 = SetUtil.newHashSet("1", "2", "3", "6");
		HashSet<String> set2 = SetUtil.newHashSet("4", "5", "6", "7");

		Set<String> set3 = SetUtil.unionView(set1, set2);

		Set<String> set4 = SetUtil.intersectionView(set1, set2);

		Set<String> set5 = SetUtil.differenceView(set1, set2);
		assertThat(set5).hasSize(3).contains("1", "2", "3");
	}

	@Test
	public void collectionCaculate_4_oe() {
		HashSet<String> set1 = SetUtil.newHashSet("1", "2", "3", "6");
		HashSet<String> set2 = SetUtil.newHashSet("4", "5", "6", "7");

		Set<String> set3 = SetUtil.unionView(set1, set2);

		Set<String> set4 = SetUtil.intersectionView(set1, set2);

		Set<String> set5 = SetUtil.differenceView(set1, set2);

		Set<String> set6 = SetUtil.disjointView(set1, set2);
		assertThat(set6).hasSize(6).contains("1", "2", "3", "4", "5", "7");
	}

	@Test
	public void collectionCaculate_6_oe() {
		HashSet<String> set1 = SetUtil.newHashSet("1", "2", "3", "6");
		HashSet<String> set2 = SetUtil.newHashSet("4", "5", "6", "7");

		Set<String> set3 = SetUtil.unionView(set1, set2);

		Set<String> set4 = SetUtil.intersectionView(set1, set2);

		Set<String> set5 = SetUtil.differenceView(set1, set2);

		Set<String> set6 = SetUtil.disjointView(set1, set2);

		try {
			set6.add("a");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

}
