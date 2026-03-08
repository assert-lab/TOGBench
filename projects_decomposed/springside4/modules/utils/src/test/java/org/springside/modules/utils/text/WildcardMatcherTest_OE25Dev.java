package org.springside.modules.utils.text;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class WildcardMatcherTest_OE25Dev {

	@Test
	public void matchString() {
		assertThat(WildcardMatcher.match("abc", "*")).isTrue();
		assertThat(WildcardMatcher.match("abc", "*c")).isTrue();
		assertThat(WildcardMatcher.match("abc", "a*")).isTrue();
		assertThat(WildcardMatcher.match("abc", "a*c")).isTrue();

		assertThat(WildcardMatcher.match("abc", "a?c")).isTrue();
		assertThat(WildcardMatcher.match("abcd", "a?c?")).isTrue();
		assertThat(WildcardMatcher.match("abcd", "a??d")).isTrue();

		assertThat(WildcardMatcher.match("abcde", "a*d?")).isTrue();

		assertThat(WildcardMatcher.match("abcde", "a*d")).isFalse();
		assertThat(WildcardMatcher.match("abcde", "a*x")).isFalse();
		assertThat(WildcardMatcher.match("abcde", "a*df")).isFalse();

		assertThat(WildcardMatcher.match("abcde", "?abcd")).isFalse();

		assertThat(WildcardMatcher.match("ab\\\\*cde", "ab\\\\*c*")).isTrue();
		assertThat(WildcardMatcher.match("ab\\\\*cde", "ab\\\\*?de")).isTrue();

		// matchOne
		assertThat(WildcardMatcher.matchOne("abcde", new String[] { "a*d?", "abde?" })).isEqualTo(0);
		assertThat(WildcardMatcher.matchOne("abcde", new String[] { "?abcd", "a*d?" })).isEqualTo(1);
		assertThat(WildcardMatcher.matchOne("abcde", new String[] { "?abcd", "xyz*" })).isEqualTo(-1);
	}

	@Test
	public void matchPath() {
		assertThat(WildcardMatcher.matchPath("/a/b/dd", "**")).isTrue();

		assertThat(WildcardMatcher.matchPath("/a/b/dd", "**/dd")).isTrue();
		assertThat(WildcardMatcher.matchPath("/a/b/c/dd", "/a/**/dd")).isTrue();
		assertThat(WildcardMatcher.matchPath("/a/b/dd", "/a/*/dd")).isTrue();
		assertThat(WildcardMatcher.matchPath("/a/b/dd", "/a/*/d?")).isTrue();
		assertThat(WildcardMatcher.matchPath("/a/b/ddxxa", "/a/*/dd*")).isTrue();
		assertThat(WildcardMatcher.matchPath("/a/b/ddxxa", "/a/?/dd*")).isTrue();
		assertThat(WildcardMatcher.matchPath("a/b/ddxxa", "a/?/dd*")).isTrue();
		assertThat(WildcardMatcher.matchPath("a/b/dd", "**/dd")).isTrue();

		assertThat(WildcardMatcher.matchPath("/a/b/c/dd", "/a/*/dd")).isFalse();

		// matchOne
		assertThat(WildcardMatcher.matchPathOne("/a/b/c/dd", new String[] { "/a/*/dd", "**/dd" })).isEqualTo(1);
		assertThat(WildcardMatcher.matchPathOne("/a/b/c/dd", new String[] { "/a/**/dd", "**/dd" })).isEqualTo(0);
		assertThat(WildcardMatcher.matchPathOne("/a/b/c/dd", new String[] { "/b/d", "/a/c/*" })).isEqualTo(-1);

	}

	@Test
	public void matchString_1_oe() {
		assertThat(WildcardMatcher.match("abc", "*")).isTrue();
	}

	@Test
	public void matchString_2_oe() {
		// removed other assertion
		assertThat(WildcardMatcher.match("abc", "*c")).isTrue();
	}

	@Test
	public void matchString_3_oe() {
		// removed other assertion
		// removed other assertion
		assertThat(WildcardMatcher.match("abc", "a*")).isTrue();
	}

	@Test
	public void matchString_4_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(WildcardMatcher.match("abc", "a*c")).isTrue();
	}

	@Test
	public void matchString_5_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(WildcardMatcher.match("abc", "a?c")).isTrue();
	}

	@Test
	public void matchString_6_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(WildcardMatcher.match("abcd", "a?c?")).isTrue();
	}

	@Test
	public void matchString_7_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(WildcardMatcher.match("abcd", "a??d")).isTrue();
	}

	@Test
	public void matchString_8_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(WildcardMatcher.match("abcde", "a*d?")).isTrue();
	}

	@Test
	public void matchString_9_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion

		assertThat(WildcardMatcher.match("abcde", "a*d")).isFalse();
	}

	@Test
	public void matchString_10_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion

		// removed other assertion
		assertThat(WildcardMatcher.match("abcde", "a*x")).isFalse();
	}

	@Test
	public void matchString_11_oe() {
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
		assertThat(WildcardMatcher.match("abcde", "a*df")).isFalse();
	}

	@Test
	public void matchString_12_oe() {
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

		assertThat(WildcardMatcher.match("abcde", "?abcd")).isFalse();
	}

	@Test
	public void matchString_13_oe() {
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

		// removed other assertion

		assertThat(WildcardMatcher.match("ab\\\\*cde", "ab\\\\*c*")).isTrue();
	}

	@Test
	public void matchString_14_oe() {
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

		// removed other assertion

		// removed other assertion
		assertThat(WildcardMatcher.match("ab\\\\*cde", "ab\\\\*?de")).isTrue();
	}

	@Test
	public void matchString_15_oe() {
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

		// removed other assertion

		// removed other assertion
		// removed other assertion

		// matchOne
		assertThat(WildcardMatcher.matchOne("abcde", new String[] { "a*d?", "abde?" })).isEqualTo(0);
	}

	@Test
	public void matchString_16_oe() {
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

		// removed other assertion

		// removed other assertion
		// removed other assertion

		// matchOne
		// removed other assertion
		assertThat(WildcardMatcher.matchOne("abcde", new String[] { "?abcd", "a*d?" })).isEqualTo(1);
	}

	@Test
	public void matchString_17_oe() {
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

		// removed other assertion

		// removed other assertion
		// removed other assertion

		// matchOne
		// removed other assertion
		// removed other assertion
		assertThat(WildcardMatcher.matchOne("abcde", new String[] { "?abcd", "xyz*" })).isEqualTo(-1);
	}

	@Test
	public void matchPath_1_oe() {
		assertThat(WildcardMatcher.matchPath("/a/b/dd", "**")).isTrue();
	}

	@Test
	public void matchPath_2_oe() {
		// removed other assertion

		assertThat(WildcardMatcher.matchPath("/a/b/dd", "**/dd")).isTrue();
	}

	@Test
	public void matchPath_3_oe() {
		// removed other assertion

		// removed other assertion
		assertThat(WildcardMatcher.matchPath("/a/b/c/dd", "/a/**/dd")).isTrue();
	}

}
