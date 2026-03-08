package org.springside.modules.utils.collection.type;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class PairTest_OE25Dev {
	
	@Test
	public void test(){
		Pair<String,Integer> pair = Pair.of("haha", 1);
		Pair<String,Integer> pair2 = Pair.of("haha", 2);
		
		assertThat(pair.equals(pair2)).isFalse();
		assertThat(pair.hashCode()!=pair2.hashCode()).isTrue();
		assertThat(pair.toString()).isEqualTo("Pair [first=haha, second=1]");
	}

	@Test
	public void test_1_oe(){
		Pair<String,Integer> pair = Pair.of("haha", 1);
		Pair<String,Integer> pair2 = Pair.of("haha", 2);
		
		assertThat(pair.equals(pair2)).isFalse();
	}

	@Test
	public void test_2_oe(){
		Pair<String,Integer> pair = Pair.of("haha", 1);
		Pair<String,Integer> pair2 = Pair.of("haha", 2);
		
		// removed other assertion
		assertThat(pair.hashCode()!=pair2.hashCode()).isTrue();
	}

	@Test
	public void test_3_oe(){
		Pair<String,Integer> pair = Pair.of("haha", 1);
		Pair<String,Integer> pair2 = Pair.of("haha", 2);
		
		// removed other assertion
		// removed other assertion
		assertThat(pair.toString()).isEqualTo("Pair [first=haha, second=1]");
	}

}
