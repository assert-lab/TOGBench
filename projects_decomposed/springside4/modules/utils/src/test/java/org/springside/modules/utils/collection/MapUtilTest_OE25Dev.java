package org.springside.modules.utils.collection;

import static org.assertj.core.api.Assertions.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.Test;
import org.springside.modules.utils.collection.MapUtil.ValueCreator;

import com.google.common.collect.Ordering;

public class MapUtilTest_OE25Dev {

	// 抽出子函数，使得Key/Value的生命周琦过期
	private void initExpireAllMap(ConcurrentMap<MyBean, MyBean> weakKeyMap) {
		MyBean key = new MyBean("A");
		MyBean value = new MyBean("B");
		weakKeyMap.put(key, value);
		assertThat(weakKeyMap.get(key)).isEqualTo(value);
	}

	// 抽出子函数，使得key过期，value不过期
	private void initExpireKeyMap(ConcurrentMap<MyBean, MyBean> weakKeyMap, MyBean value) {
		MyBean key = new MyBean("A");
		weakKeyMap.put(key, value);
		assertThat(weakKeyMap.get(key)).isEqualTo(value);
	}

	// 抽出子函数，使得key不过期，value过期
	private void initExpireValueMap(ConcurrentMap<MyBean, MyBean> weakKeyMap, MyBean key) {
		MyBean value = new MyBean("B");
		weakKeyMap.put(key, value);
		assertThat(weakKeyMap.get(key)).isEqualTo(value);
	}

	// 抽出子函数，使得Key/Value的生命周琦过期
	private void initWeakValue(ConcurrentMap<MyBean, MyBean> weakKeyMap) {
		MyBean key = new MyBean("A");
		MyBean value = new MyBean("B");
		weakKeyMap.put(key, value);
		assertThat(weakKeyMap.get(new MyBean("A"))).isEqualTo(value);
	}

	public static class MyBean {
		String name;

		public MyBean(String name) {
			this.name = name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MyBean other = (MyBean) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

	}

	public enum EnumA {
		A, B, C
	}
	
	@Test
	public void IntObjectHashMap(){
		
	}

	@Test
	public void generalMethod_1_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		assertThat(MapUtil.isEmpty(map)).isTrue();
	}

	@Test
	public void generalMethod_2_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		assertThat(MapUtil.isEmpty(null)).isTrue();
	}

	@Test
	public void generalMethod_3_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		assertThat(MapUtil.isNotEmpty(map)).isFalse();
	}

	@Test
	public void generalMethod_4_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(MapUtil.isNotEmpty(null)).isFalse();
	}

	@Test
	public void generalMethod_5_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		map.put("haha", 1);
		assertThat(MapUtil.isEmpty(map)).isFalse();
	}

	@Test
	public void generalMethod_6_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		map.put("haha", 1);
		// removed other assertion
		assertThat(MapUtil.isNotEmpty(map)).isTrue();
	}

	@Test
	public void generalMethod_7_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		map.put("haha", 1);
		// removed other assertion
		// removed other assertion

		//////////
		ConcurrentMap<String, Integer> map2 = MapUtil.newConcurrentHashMap();
		assertThat(MapUtil.putIfAbsentWithFinalValue(map2, "haha", 3)).isEqualTo(3);
	}

	@Test
	public void generalMethod_8_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		map.put("haha", 1);
		// removed other assertion
		// removed other assertion

		//////////
		ConcurrentMap<String, Integer> map2 = MapUtil.newConcurrentHashMap();
		// removed other assertion
		assertThat(MapUtil.putIfAbsentWithFinalValue(map2, "haha", 4)).isEqualTo(3);
	}

	@Test
	public void generalMethod_9_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		map.put("haha", 1);
		// removed other assertion
		// removed other assertion

		//////////
		ConcurrentMap<String, Integer> map2 = MapUtil.newConcurrentHashMap();
		// removed other assertion
		// removed other assertion

		MapUtil.createIfAbsent(map2, "haha", new ValueCreator<Integer>() {
			@Override
			public Integer get() {
				return 5;
			}
		});

		assertThat(map2).hasSize(1).containsEntry("haha", 3);
	}

	@Test
	public void generalMethod_10_oe() {
		HashMap<String, Integer> map = MapUtil.newHashMap();
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		map.put("haha", 1);
		// removed other assertion
		// removed other assertion

		//////////
		ConcurrentMap<String, Integer> map2 = MapUtil.newConcurrentHashMap();
		// removed other assertion
		// removed other assertion

		MapUtil.createIfAbsent(map2, "haha", new ValueCreator<Integer>() {
			@Override
			public Integer get() {
				return 5;
			}
		});

		// removed other assertion

		MapUtil.createIfAbsent(map2, "haha2", new ValueCreator<Integer>() {
			@Override
			public Integer get() {
				return 5;
			}
		});

		assertThat(map2).hasSize(2).containsEntry("haha2", 5);
	}

	@Test
	public void guavaBuildMap_1_oe() {
		HashMap<String, Integer> map1 = MapUtil.newHashMap();

		HashMap<String, Integer> map2 = MapUtil.newHashMapWithCapacity(10, 0.5f);
		map2 = MapUtil.newHashMapWithCapacity(10, 0.5f);

		HashMap<String, Integer> map3 = MapUtil.newHashMap("1", 1);
		assertThat(map3).hasSize(1).containsEntry("1", 1);
	}

	@Test
	public void guavaBuildMap_2_oe() {
		HashMap<String, Integer> map1 = MapUtil.newHashMap();

		HashMap<String, Integer> map2 = MapUtil.newHashMapWithCapacity(10, 0.5f);
		map2 = MapUtil.newHashMapWithCapacity(10, 0.5f);

		HashMap<String, Integer> map3 = MapUtil.newHashMap("1", 1);
		// removed other assertion

		HashMap<String, Integer> map4 = MapUtil.newHashMap(new String[] { "1", "2" }, new Integer[] { 1, 2 });
		assertThat(map4).hasSize(2).containsEntry("1", 1).containsEntry("2", 2);
	}

	@Test
	public void guavaBuildMap_3_oe() {
		HashMap<String, Integer> map1 = MapUtil.newHashMap();

		HashMap<String, Integer> map2 = MapUtil.newHashMapWithCapacity(10, 0.5f);
		map2 = MapUtil.newHashMapWithCapacity(10, 0.5f);

		HashMap<String, Integer> map3 = MapUtil.newHashMap("1", 1);
		// removed other assertion

		HashMap<String, Integer> map4 = MapUtil.newHashMap(new String[] { "1", "2" }, new Integer[] { 1, 2 });
		// removed other assertion

		HashMap<String, Integer> map5 = MapUtil.newHashMap(ArrayUtil.asList("1", "2", "3"), ArrayUtil.asList(1, 2, 3));
		assertThat(map5).hasSize(3).containsEntry("1", 1).containsEntry("2", 2).containsEntry("3", 3);
	}

	@Test
	public void jdkBuildMap_1_oe() {
		Map<String, Integer> map1 = MapUtil.emptyMap();
		assertThat(map1).hasSize(0);
	}

	@Test
	public void jdkBuildMap_2_oe() {
		Map<String, Integer> map1 = MapUtil.emptyMap();
		// removed other assertion

		Map<String, Integer> map2 = MapUtil.emptyMapIfNull(null);
		assertThat(map2).isNotNull().hasSize(0);
	}

	@Test
	public void jdkBuildMap_3_oe() {
		Map<String, Integer> map1 = MapUtil.emptyMap();
		// removed other assertion

		Map<String, Integer> map2 = MapUtil.emptyMapIfNull(null);
		// removed other assertion

		Map<String, Integer> map3 = MapUtil.emptyMapIfNull(map1);
		assertThat(map3).isSameAs(map1);
	}

	@Test
	public void jdkBuildMap_4_oe() {
		Map<String, Integer> map1 = MapUtil.emptyMap();
		// removed other assertion

		Map<String, Integer> map2 = MapUtil.emptyMapIfNull(null);
		// removed other assertion

		Map<String, Integer> map3 = MapUtil.emptyMapIfNull(map1);
		// removed other assertion

		Map<String, Integer> map4 = MapUtil.singletonMap("haha", 1);
		assertThat(map4).hasSize(1).containsEntry("haha", 1);
	}

	@Test
	public void jdkBuildMap_6_oe() {
		Map<String, Integer> map1 = MapUtil.emptyMap();
		// removed other assertion

		Map<String, Integer> map2 = MapUtil.emptyMapIfNull(null);
		// removed other assertion

		Map<String, Integer> map3 = MapUtil.emptyMapIfNull(map1);
		// removed other assertion

		Map<String, Integer> map4 = MapUtil.singletonMap("haha", 1);
		// removed other assertion
		try {
			map4.put("dada", 2);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void jdkBuildMap_8_oe() {
		Map<String, Integer> map1 = MapUtil.emptyMap();
		// removed other assertion

		Map<String, Integer> map2 = MapUtil.emptyMapIfNull(null);
		// removed other assertion

		Map<String, Integer> map3 = MapUtil.emptyMapIfNull(map1);
		// removed other assertion

		Map<String, Integer> map4 = MapUtil.singletonMap("haha", 1);
		// removed other assertion
		try {
			map4.put("dada", 2);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		Map<String, Integer> map5 = MapUtil.newHashMap();
		Map<String, Integer> map6 = MapUtil.unmodifiableMap(map5);

		try {
			map6.put("a", 2);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
	}
	}

	@Test
	public void weakMap_1_oe() {
		ConcurrentMap<MyBean, MyBean> weakKeyMap = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		initExpireAllMap(weakKeyMap);
		System.gc();
		assertThat(weakKeyMap.get(new MyBean("A"))).isNull();
	}

	@Test
	public void weakMap_2_oe() {
		ConcurrentMap<MyBean, MyBean> weakKeyMap = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		initExpireAllMap(weakKeyMap);
		System.gc();
		// removed other assertion
		assertThat(weakKeyMap).hasSize(1); // key仍然在;
	}

	@Test
	public void weakMap_3_oe() {
		ConcurrentMap<MyBean, MyBean> weakKeyMap = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		initExpireAllMap(weakKeyMap);
		System.gc();
		// removed other assertion
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap2 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean value = new MyBean("B");
		initExpireKeyMap(weakKeyMap2, value);
		System.gc();
		assertThat(weakKeyMap2.get(new MyBean("A"))).isNull();
	}

	@Test
	public void weakMap_4_oe() {
		ConcurrentMap<MyBean, MyBean> weakKeyMap = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		initExpireAllMap(weakKeyMap);
		System.gc();
		// removed other assertion
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap2 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean value = new MyBean("B");
		initExpireKeyMap(weakKeyMap2, value);
		System.gc();
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap3 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean key = new MyBean("A");
		initExpireValueMap(weakKeyMap3, key);
		System.gc();
		assertThat(weakKeyMap3.get(key)).isEqualTo(new MyBean("B"));
	}

	@Test
	public void weakMap_5_oe() {
		ConcurrentMap<MyBean, MyBean> weakKeyMap = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		initExpireAllMap(weakKeyMap);
		System.gc();
		// removed other assertion
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap2 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean value = new MyBean("B");
		initExpireKeyMap(weakKeyMap2, value);
		System.gc();
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap3 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean key = new MyBean("A");
		initExpireValueMap(weakKeyMap3, key);
		System.gc();
		// removed other assertion

		// weak value
		ConcurrentMap<MyBean, MyBean> weakValueMap = MapUtil.createWeakValueConcurrentHashMap(10, 1);
		initExpireAllMap(weakValueMap);
		System.gc();
		assertThat(weakValueMap.get(new MyBean("A"))).isNull();
	}

	@Test
	public void weakMap_6_oe() {
		ConcurrentMap<MyBean, MyBean> weakKeyMap = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		initExpireAllMap(weakKeyMap);
		System.gc();
		// removed other assertion
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap2 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean value = new MyBean("B");
		initExpireKeyMap(weakKeyMap2, value);
		System.gc();
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap3 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean key = new MyBean("A");
		initExpireValueMap(weakKeyMap3, key);
		System.gc();
		// removed other assertion

		// weak value
		ConcurrentMap<MyBean, MyBean> weakValueMap = MapUtil.createWeakValueConcurrentHashMap(10, 1);
		initExpireAllMap(weakValueMap);
		System.gc();
		// removed other assertion
		
		ConcurrentMap<MyBean, MyBean> weakValueMap2 = MapUtil.createWeakValueConcurrentHashMap(10, 1);
		MyBean value2 = new MyBean("B");
		initExpireKeyMap(weakValueMap2, value2);
		System.gc();
		assertThat(weakValueMap2.get(new MyBean("A"))).isEqualTo(new MyBean("B"));
	}

	@Test
	public void weakMap_7_oe() {
		ConcurrentMap<MyBean, MyBean> weakKeyMap = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		initExpireAllMap(weakKeyMap);
		System.gc();
		// removed other assertion
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap2 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean value = new MyBean("B");
		initExpireKeyMap(weakKeyMap2, value);
		System.gc();
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakKeyMap3 = MapUtil.createWeakKeyConcurrentHashMap(10, 1);
		MyBean key = new MyBean("A");
		initExpireValueMap(weakKeyMap3, key);
		System.gc();
		// removed other assertion

		// weak value
		ConcurrentMap<MyBean, MyBean> weakValueMap = MapUtil.createWeakValueConcurrentHashMap(10, 1);
		initExpireAllMap(weakValueMap);
		System.gc();
		// removed other assertion
		
		ConcurrentMap<MyBean, MyBean> weakValueMap2 = MapUtil.createWeakValueConcurrentHashMap(10, 1);
		MyBean value2 = new MyBean("B");
		initExpireKeyMap(weakValueMap2, value2);
		System.gc();
		// removed other assertion

		ConcurrentMap<MyBean, MyBean> weakValueMap3 = MapUtil.createWeakValueConcurrentHashMap(10, 1);
		MyBean key3 = new MyBean("A");
		initExpireValueMap(weakValueMap3, key3);
		System.gc();
		assertThat(weakValueMap3.get(new MyBean("A"))).isNull();
	}

}
