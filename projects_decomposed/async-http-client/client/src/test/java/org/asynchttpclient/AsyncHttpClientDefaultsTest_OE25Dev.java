/*
 * Copyright (c) 2015 AsyncHttpClient Project. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at
 *     http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient;

import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.config.AsyncHttpClientConfigHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.asynchttpclient.config.AsyncHttpClientConfigDefaults.ASYNC_CLIENT_CONFIG_ROOT;

@Test
public class AsyncHttpClientDefaultsTest_OE25Dev {

  public void testDefaultMaxConnectionPerHost() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxConnectionsPerHost(), -1);
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultConnectTimeout(), 5 * 1000);
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultPooledConnectionIdleTimeout(), 60 * 1000);
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultReadTimeout(), 60 * 1000);
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultRequestTimeout(), 60 * 1000);
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultConnectionTtl(), -1);
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultFollowRedirect());
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxRedirects(), 5);
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultCompressionEnforced());
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultUserAgent(), "AHC/2.1");
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxySelector());
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxyProperties());
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultStrict302Handling());
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    Assert.assertTrue(AsyncHttpClientConfigDefaults.defaultKeepAlive());
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxRequestRetry(), 5);
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultDisableUrlEncodingForBoundRequests());
    testBooleanSystemProperty("disableUrlEncodingForBoundRequests", "defaultDisableUrlEncodingForBoundRequests", "true");
  }

  public void testDefaultUseInsecureTrustManager() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseInsecureTrustManager());
    testBooleanSystemProperty("useInsecureTrustManager", "defaultUseInsecureTrustManager", "false");
  }

  public void testDefaultHashedWheelTimerTickDuration() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultHashedWheelTimerTickDuration(), 100);
    testIntegerSystemProperty("hashedWheelTimerTickDuration", "defaultHashedWheelTimerTickDuration", "100");
  }

  public void testDefaultHashedWheelTimerSize() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultHashedWheelTimerSize(), 512);
    testIntegerSystemProperty("hashedWheelTimerSize", "defaultHashedWheelTimerSize", "512");
  }

  private void testIntegerSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      Assert.assertEquals(method.invoke(null), Integer.parseInt(value));
    } catch (Exception e) {
      Assert.fail("Couldn't find or execute method : " + methodName, e);
    }
    if (previous != null)
      System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, previous);
    else
      System.clearProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
  }

  private void testBooleanSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      Assert.assertEquals(method.invoke(null), Boolean.parseBoolean(value));
    } catch (Exception e) {
      Assert.fail("Couldn't find or execute method : " + methodName, e);
    }
    if (previous != null)
      System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, previous);
    else
      System.clearProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
  }

  private void testStringSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      Assert.assertEquals(method.invoke(null), value);
    } catch (Exception e) {
      Assert.fail("Couldn't find or execute method : " + methodName, e);
    }
    if (previous != null)
      System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, previous);
    else
      System.clearProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
  }

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxConnections(), -1);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_2_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxConnectionsPerHost(), -1);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_3_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultConnectTimeout(), 5 * 1000);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_4_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultPooledConnectionIdleTimeout(), 60 * 1000);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_5_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultReadTimeout(), 60 * 1000);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_6_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultRequestTimeout(), 60 * 1000);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_7_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultConnectionTtl(), -1);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_8_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultFollowRedirect());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_9_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxRedirects(), 5);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_10_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultCompressionEnforced());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_11_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultUserAgent(), "AHC/2.1");
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_12_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxySelector());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_13_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxyProperties());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_14_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultStrict302Handling());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_15_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    Assert.assertTrue(AsyncHttpClientConfigDefaults.defaultKeepAlive());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_16_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxRequestRetry(), 5);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_17_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    // removed other assertion
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultDisableUrlEncodingForBoundRequests());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_18_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    // removed other assertion
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    // removed other assertion
    testBooleanSystemProperty("disableUrlEncodingForBoundRequests", "defaultDisableUrlEncodingForBoundRequests", "true");
  }

  public void testDefaultUseInsecureTrustManager() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseInsecureTrustManager());
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_19_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    // removed other assertion
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    // removed other assertion
    testBooleanSystemProperty("disableUrlEncodingForBoundRequests", "defaultDisableUrlEncodingForBoundRequests", "true");
  }

  public void testDefaultUseInsecureTrustManager() {
    // removed other assertion
    testBooleanSystemProperty("useInsecureTrustManager", "defaultUseInsecureTrustManager", "false");
  }

  public void testDefaultHashedWheelTimerTickDuration() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultHashedWheelTimerTickDuration(), 100);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_20_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    // removed other assertion
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    // removed other assertion
    testBooleanSystemProperty("disableUrlEncodingForBoundRequests", "defaultDisableUrlEncodingForBoundRequests", "true");
  }

  public void testDefaultUseInsecureTrustManager() {
    // removed other assertion
    testBooleanSystemProperty("useInsecureTrustManager", "defaultUseInsecureTrustManager", "false");
  }

  public void testDefaultHashedWheelTimerTickDuration() {
    // removed other assertion
    testIntegerSystemProperty("hashedWheelTimerTickDuration", "defaultHashedWheelTimerTickDuration", "100");
  }

  public void testDefaultHashedWheelTimerSize() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultHashedWheelTimerSize(), 512);
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_22_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    // removed other assertion
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    // removed other assertion
    testBooleanSystemProperty("disableUrlEncodingForBoundRequests", "defaultDisableUrlEncodingForBoundRequests", "true");
  }

  public void testDefaultUseInsecureTrustManager() {
    // removed other assertion
    testBooleanSystemProperty("useInsecureTrustManager", "defaultUseInsecureTrustManager", "false");
  }

  public void testDefaultHashedWheelTimerTickDuration() {
    // removed other assertion
    testIntegerSystemProperty("hashedWheelTimerTickDuration", "defaultHashedWheelTimerTickDuration", "100");
  }

  public void testDefaultHashedWheelTimerSize() {
    // removed other assertion
    testIntegerSystemProperty("hashedWheelTimerSize", "defaultHashedWheelTimerSize", "512");
  }

  private void testIntegerSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      // removed other assertion
    } catch (Exception e) {
      Assert.fail("Couldn't find or execute method : " + methodName, e);
}
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_24_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    // removed other assertion
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    // removed other assertion
    testBooleanSystemProperty("disableUrlEncodingForBoundRequests", "defaultDisableUrlEncodingForBoundRequests", "true");
  }

  public void testDefaultUseInsecureTrustManager() {
    // removed other assertion
    testBooleanSystemProperty("useInsecureTrustManager", "defaultUseInsecureTrustManager", "false");
  }

  public void testDefaultHashedWheelTimerTickDuration() {
    // removed other assertion
    testIntegerSystemProperty("hashedWheelTimerTickDuration", "defaultHashedWheelTimerTickDuration", "100");
  }

  public void testDefaultHashedWheelTimerSize() {
    // removed other assertion
    testIntegerSystemProperty("hashedWheelTimerSize", "defaultHashedWheelTimerSize", "512");
  }

  private void testIntegerSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      // removed other assertion
    } catch (Exception e) {
      // removed other assertion
    }
    if (previous != null)
      System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, previous);
    else
      System.clearProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
  }

  private void testBooleanSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      // removed other assertion
    } catch (Exception e) {
      Assert.fail("Couldn't find or execute method : " + methodName, e);
}
}
}

@Test
public class AsyncHttpClientDefaultsTest {

  public void testDefaultMaxTotalConnections_26_oe() {
    // removed other assertion
    testIntegerSystemProperty("maxConnections", "defaultMaxConnections", "100");
  }

  public void testDefaultMaxConnectionPerHost() {
    // removed other assertion
    testIntegerSystemProperty("maxConnectionsPerHost", "defaultMaxConnectionsPerHost", "100");
  }

  public void testDefaultConnectTimeOut() {
    // removed other assertion
    testIntegerSystemProperty("connectTimeout", "defaultConnectTimeout", "100");
  }

  public void testDefaultPooledConnectionIdleTimeout() {
    // removed other assertion
    testIntegerSystemProperty("pooledConnectionIdleTimeout", "defaultPooledConnectionIdleTimeout", "100");
  }

  public void testDefaultReadTimeout() {
    // removed other assertion
    testIntegerSystemProperty("readTimeout", "defaultReadTimeout", "100");
  }

  public void testDefaultRequestTimeout() {
    // removed other assertion
    testIntegerSystemProperty("requestTimeout", "defaultRequestTimeout", "100");
  }

  public void testDefaultConnectionTtl() {
    // removed other assertion
    testIntegerSystemProperty("connectionTtl", "defaultConnectionTtl", "100");
  }

  public void testDefaultFollowRedirect() {
    // removed other assertion
    testBooleanSystemProperty("followRedirect", "defaultFollowRedirect", "true");
  }

  public void testDefaultMaxRedirects() {
    // removed other assertion
    testIntegerSystemProperty("maxRedirects", "defaultMaxRedirects", "100");
  }

  public void testDefaultCompressionEnforced() {
    // removed other assertion
    testBooleanSystemProperty("compressionEnforced", "defaultCompressionEnforced", "true");
  }

  public void testDefaultUserAgent() {
    // removed other assertion
    testStringSystemProperty("userAgent", "defaultUserAgent", "MyAHC");
  }

  public void testDefaultUseProxySelector() {
    // removed other assertion
    testBooleanSystemProperty("useProxySelector", "defaultUseProxySelector", "true");
  }

  public void testDefaultUseProxyProperties() {
    // removed other assertion
    testBooleanSystemProperty("useProxyProperties", "defaultUseProxyProperties", "true");
  }

  public void testDefaultStrict302Handling() {
    // removed other assertion
    testBooleanSystemProperty("strict302Handling", "defaultStrict302Handling", "true");
  }

  public void testDefaultAllowPoolingConnection() {
    // removed other assertion
    testBooleanSystemProperty("keepAlive", "defaultKeepAlive", "false");
  }

  public void testDefaultMaxRequestRetry() {
    // removed other assertion
    testIntegerSystemProperty("maxRequestRetry", "defaultMaxRequestRetry", "100");
  }

  public void testDefaultDisableUrlEncodingForBoundRequests() {
    // removed other assertion
    testBooleanSystemProperty("disableUrlEncodingForBoundRequests", "defaultDisableUrlEncodingForBoundRequests", "true");
  }

  public void testDefaultUseInsecureTrustManager() {
    // removed other assertion
    testBooleanSystemProperty("useInsecureTrustManager", "defaultUseInsecureTrustManager", "false");
  }

  public void testDefaultHashedWheelTimerTickDuration() {
    // removed other assertion
    testIntegerSystemProperty("hashedWheelTimerTickDuration", "defaultHashedWheelTimerTickDuration", "100");
  }

  public void testDefaultHashedWheelTimerSize() {
    // removed other assertion
    testIntegerSystemProperty("hashedWheelTimerSize", "defaultHashedWheelTimerSize", "512");
  }

  private void testIntegerSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      // removed other assertion
    } catch (Exception e) {
      // removed other assertion
    }
    if (previous != null)
      System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, previous);
    else
      System.clearProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
  }

  private void testBooleanSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      // removed other assertion
    } catch (Exception e) {
      // removed other assertion
    }
    if (previous != null)
      System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, previous);
    else
      System.clearProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
  }

  private void testStringSystemProperty(String propertyName, String methodName, String value) {
    String previous = System.getProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName);
    System.setProperty(ASYNC_CLIENT_CONFIG_ROOT + propertyName, value);
    AsyncHttpClientConfigHelper.reloadProperties();
    try {
      Method method = AsyncHttpClientConfigDefaults.class.getMethod(methodName);
      // removed other assertion
    } catch (Exception e) {
      Assert.fail("Couldn't find or execute method : " + methodName, e);
}
}
}

}
