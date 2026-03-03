package org.springside.modules.utils.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class RandomUtilTest_OE25Dev {

	@Test
	public void getRandom() {
		System.out.println(RandomUtil.secureRandom().nextInt());
		System.out.println(RandomUtil.threadLocalRandom().nextInt());
	}

	@Test
	public void randomNumber_1_oe() {

		int i = RandomUtil.nextInt();
		assertThat(i).isBetween(0, Integer.MAX_VALUE);
	}

	@Test
	public void randomNumber_2_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());
		assertThat(i).isBetween(0, Integer.MAX_VALUE);
	}

	@Test
	public void randomNumber_3_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		assertThat(i).isBetween(0, 10);
	}

	@Test
	public void randomNumber_4_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);
		assertThat(i).isBetween(0, 10);
	}

	@Test
	public void randomNumber_5_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		assertThat(i).isBetween(10, 20);
	}

	@Test
	public void randomNumber_6_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);
		assertThat(i).isBetween(10, 20);
	}

	@Test
	public void randomNumber_7_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		assertThat(l).isBetween(0L, Long.MAX_VALUE);
	}

	@Test
	public void randomNumber_8_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());
		assertThat(l).isBetween(0L, Long.MAX_VALUE);
	}

	@Test
	public void randomNumber_9_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		assertThat(l).isBetween(0L, 10L);
	}

	@Test
	public void randomNumber_10_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);
		assertThat(l).isBetween(0L, 10L);
	}

	@Test
	public void randomNumber_11_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		assertThat(l).isBetween(10L, 20L);
	}

	@Test
	public void randomNumber_12_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10, 20);
		assertThat(l).isBetween(10L, 20L);
	}

	@Test
	public void randomNumber_13_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10, 20);

		double d = RandomUtil.nextDouble();
		assertThat(d).isBetween(0d, Double.MAX_VALUE);
	}

	@Test
	public void randomNumber_14_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10, 20);

		double d = RandomUtil.nextDouble();
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom());
		assertThat(d).isBetween(0d, Double.MAX_VALUE);
	}

	@Test
	public void randomNumber_15_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10, 20);

		double d = RandomUtil.nextDouble();
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom());

		d = RandomUtil.nextDouble(10);
		assertThat(d).isBetween(0d, 10d);
	}

	@Test
	public void randomNumber_16_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10, 20);

		double d = RandomUtil.nextDouble();
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom());

		d = RandomUtil.nextDouble(10);
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom(), 10L);
		assertThat(d).isBetween(0d, 10d);
	}

	@Test
	public void randomNumber_17_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10, 20);

		double d = RandomUtil.nextDouble();
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom());

		d = RandomUtil.nextDouble(10);
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom(), 10L);

		d = RandomUtil.nextDouble(10L, 20L);
		assertThat(d).isBetween(10d, 20d);
	}

	@Test
	public void randomNumber_18_oe() {

		int i = RandomUtil.nextInt();
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom());

		i = RandomUtil.nextInt(10);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10);

		i = RandomUtil.nextInt(10, 20);
		i = RandomUtil.nextInt(RandomUtil.threadLocalRandom(), 10, 20);

		long l = RandomUtil.nextLong();
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom());

		l = RandomUtil.nextLong(10);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10L);

		l = RandomUtil.nextLong(10L, 20L);
		l = RandomUtil.nextLong(RandomUtil.threadLocalRandom(), 10, 20);

		double d = RandomUtil.nextDouble();
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom());

		d = RandomUtil.nextDouble(10);
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom(), 10L);

		d = RandomUtil.nextDouble(10L, 20L);
		d = RandomUtil.nextDouble(RandomUtil.threadLocalRandom(), 10, 20);
		assertThat(d).isBetween(10d, 20d);
	}

	@Test
	public void generateString_1_oe() {
		System.out.println(RandomUtil.randomStringFixLength(5));
		System.out.println(RandomUtil.randomStringRandomLength(5, 10));

		System.out.println(RandomUtil.randomStringFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomStringRandomLength(RandomUtil.threadLocalRandom(), 5, 10));

		assertThat(RandomUtil.randomStringFixLength(5)).hasSize(5);
	}

	@Test
	public void generateString_2_oe() {
		System.out.println(RandomUtil.randomStringFixLength(5));
		System.out.println(RandomUtil.randomStringRandomLength(5, 10));

		System.out.println(RandomUtil.randomStringFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomStringRandomLength(RandomUtil.threadLocalRandom(), 5, 10));

		assertThat(RandomUtil.randomStringFixLength(RandomUtil.threadLocalRandom(), 5)).hasSize(5);
	}

	@Test
	public void generateString_3_oe() {
		System.out.println(RandomUtil.randomStringFixLength(5));
		System.out.println(RandomUtil.randomStringRandomLength(5, 10));

		System.out.println(RandomUtil.randomStringFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomStringRandomLength(RandomUtil.threadLocalRandom(), 5, 10));


		System.out.println(RandomUtil.randomLetterFixLength(5));
		System.out.println(RandomUtil.randomLetterRandomLength(5, 10));

		System.out.println(RandomUtil.randomLetterFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomLetterRandomLength(RandomUtil.threadLocalRandom(), 5, 10));

		assertThat(RandomUtil.randomLetterFixLength(5)).hasSize(5);
	}

	@Test
	public void generateString_4_oe() {
		System.out.println(RandomUtil.randomStringFixLength(5));
		System.out.println(RandomUtil.randomStringRandomLength(5, 10));

		System.out.println(RandomUtil.randomStringFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomStringRandomLength(RandomUtil.threadLocalRandom(), 5, 10));


		System.out.println(RandomUtil.randomLetterFixLength(5));
		System.out.println(RandomUtil.randomLetterRandomLength(5, 10));

		System.out.println(RandomUtil.randomLetterFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomLetterRandomLength(RandomUtil.threadLocalRandom(), 5, 10));

		assertThat(RandomUtil.randomLetterFixLength(RandomUtil.threadLocalRandom(), 5)).hasSize(5);
	}

	@Test
	public void generateString_5_oe() {
		System.out.println(RandomUtil.randomStringFixLength(5));
		System.out.println(RandomUtil.randomStringRandomLength(5, 10));

		System.out.println(RandomUtil.randomStringFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomStringRandomLength(RandomUtil.threadLocalRandom(), 5, 10));


		System.out.println(RandomUtil.randomLetterFixLength(5));
		System.out.println(RandomUtil.randomLetterRandomLength(5, 10));

		System.out.println(RandomUtil.randomLetterFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomLetterRandomLength(RandomUtil.threadLocalRandom(), 5, 10));


		System.out.println(RandomUtil.randomAsciiFixLength(5));
		System.out.println(RandomUtil.randomAsciiRandomLength(5, 10));

		System.out.println(RandomUtil.randomAsciiFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomAsciiRandomLength(RandomUtil.threadLocalRandom(), 5, 10));

		assertThat(RandomUtil.randomAsciiFixLength(5)).hasSize(5);
	}

	@Test
	public void generateString_6_oe() {
		System.out.println(RandomUtil.randomStringFixLength(5));
		System.out.println(RandomUtil.randomStringRandomLength(5, 10));

		System.out.println(RandomUtil.randomStringFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomStringRandomLength(RandomUtil.threadLocalRandom(), 5, 10));


		System.out.println(RandomUtil.randomLetterFixLength(5));
		System.out.println(RandomUtil.randomLetterRandomLength(5, 10));

		System.out.println(RandomUtil.randomLetterFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomLetterRandomLength(RandomUtil.threadLocalRandom(), 5, 10));


		System.out.println(RandomUtil.randomAsciiFixLength(5));
		System.out.println(RandomUtil.randomAsciiRandomLength(5, 10));

		System.out.println(RandomUtil.randomAsciiFixLength(RandomUtil.threadLocalRandom(), 5));
		System.out.println(RandomUtil.randomAsciiRandomLength(RandomUtil.threadLocalRandom(), 5, 10));

		assertThat(RandomUtil.randomAsciiFixLength(RandomUtil.threadLocalRandom(), 5)).hasSize(5);
	}

}
