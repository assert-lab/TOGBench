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


public class AsyncHttpClientDefaultsTest_OE25Dev {

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

  public void testDefaultMaxTotalConnections_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxConnections(), -1);
  }

  public void testDefaultMaxConnectionPerHost_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxConnectionsPerHost(), -1);
  }

  public void testDefaultConnectTimeOut_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultConnectTimeout(), 5 * 1000);
  }

  public void testDefaultPooledConnectionIdleTimeout_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultPooledConnectionIdleTimeout(), 60 * 1000);
  }

  public void testDefaultReadTimeout_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultReadTimeout(), 60 * 1000);
  }

  public void testDefaultRequestTimeout_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultRequestTimeout(), 60 * 1000);
  }

  public void testDefaultConnectionTtl_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultConnectionTtl(), -1);
  }

  public void testDefaultFollowRedirect_1_oe() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultFollowRedirect());
  }

  public void testDefaultMaxRedirects_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxRedirects(), 5);
  }

  public void testDefaultCompressionEnforced_1_oe() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultCompressionEnforced());
  }

  public void testDefaultUserAgent_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultUserAgent(), "AHC/2.1");
  }

  public void testDefaultUseProxySelector_1_oe() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxySelector());
  }

  public void testDefaultUseProxyProperties_1_oe() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxyProperties());
  }

  public void testDefaultStrict302Handling_1_oe() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultStrict302Handling());
  }

  public void testDefaultAllowPoolingConnection_1_oe() {
    Assert.assertTrue(AsyncHttpClientConfigDefaults.defaultKeepAlive());
  }

  public void testDefaultMaxRequestRetry_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultMaxRequestRetry(), 5);
  }

  public void testDefaultDisableUrlEncodingForBoundRequests_1_oe() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultDisableUrlEncodingForBoundRequests());
  }

  public void testDefaultUseInsecureTrustManager_1_oe() {
    Assert.assertFalse(AsyncHttpClientConfigDefaults.defaultUseInsecureTrustManager());
  }

  public void testDefaultHashedWheelTimerTickDuration_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultHashedWheelTimerTickDuration(), 100);
  }

  public void testDefaultHashedWheelTimerSize_1_oe() {
    Assert.assertEquals(AsyncHttpClientConfigDefaults.defaultHashedWheelTimerSize(), 512);
  }

}
