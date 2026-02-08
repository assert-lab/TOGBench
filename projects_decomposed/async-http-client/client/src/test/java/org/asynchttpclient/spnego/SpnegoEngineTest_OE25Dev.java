package org.asynchttpclient.spnego;

import org.apache.commons.io.FileUtils;
import org.apache.kerby.kerberos.kerb.server.SimpleKdcServer;
import org.asynchttpclient.AbstractBasicTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpnegoEngineTest_OE25Dev extends AbstractBasicTest {
  private static SimpleKdcServer kerbyServer;

  private static String basedir;
  private static String alice;
  private static String bob;
  private static File aliceKeytab;
  private static File bobKeytab;
  private static File loginConfig;

  @BeforeClass
  public static void startServers() throws Exception {
    basedir = System.getProperty("basedir");
    if (basedir == null) {
      basedir = new File(".").getCanonicalPath();
    }

    // System.setProperty("sun.security.krb5.debug", "true");
    System.setProperty("java.security.krb5.conf",
        new File(basedir + File.separator + "target" + File.separator + "krb5.conf").getCanonicalPath());
    loginConfig = new File(basedir + File.separator + "target" + File.separator + "kerberos.jaas");
    System.setProperty("java.security.auth.login.config", loginConfig.getCanonicalPath());

    kerbyServer = new SimpleKdcServer();

    kerbyServer.setKdcRealm("service.ws.apache.org");
    kerbyServer.setAllowUdp(false);
    kerbyServer.setWorkDir(new File(basedir, "target"));

    //kerbyServer.setInnerKdcImpl(new NettyKdcServerImpl(kerbyServer.getKdcSetting()));

    kerbyServer.init();

    // Create principals
    alice = "alice@service.ws.apache.org";
    bob = "bob/service.ws.apache.org@service.ws.apache.org";

    kerbyServer.createPrincipal(alice, "alice");
    kerbyServer.createPrincipal(bob, "bob");

    aliceKeytab = new File(basedir + File.separator + "target" + File.separator + "alice.keytab");
    bobKeytab = new File(basedir + File.separator + "target" + File.separator + "bob.keytab");
    kerbyServer.exportPrincipal(alice, aliceKeytab);
    kerbyServer.exportPrincipal(bob, bobKeytab);

    kerbyServer.start();

    FileUtils.copyInputStreamToFile(SpnegoEngine.class.getResourceAsStream("/kerberos.jaas"), loginConfig);
  }

  @Test(expectedExceptions = SpnegoEngineException.class)
  public void testSpnegoGenerateTokenWithUsernamePasswordFail() throws Exception {
    SpnegoEngine spnegoEngine = new SpnegoEngine("alice",
        "wrong password",
        "bob",
        "service.ws.apache.org",
        false,
        null,
        "alice",
        null);
    spnegoEngine.generateToken("localhost");
  }

  @AfterClass
  public static void cleanup() throws Exception {
    if (kerbyServer != null) {
      kerbyServer.stop();
    }
    FileUtils.deleteQuietly(aliceKeytab);
    FileUtils.deleteQuietly(bobKeytab);
    FileUtils.deleteQuietly(loginConfig);
  }

  @Test
  public void testSpnegoGenerateTokenWithUsernamePassword_1_oe() throws Exception {
    SpnegoEngine spnegoEngine = new SpnegoEngine("alice",
        "alice",
        "bob",
        "service.ws.apache.org",
        false,
        null,
        "alice",
        null);
    String token = spnegoEngine.generateToken("localhost");
    Assert.assertNotNull(token);
  }

  @Test
  public void testSpnegoGenerateTokenWithUsernamePassword_2_oe() throws Exception {
    SpnegoEngine spnegoEngine = new SpnegoEngine("alice",
        "alice",
        "bob",
        "service.ws.apache.org",
        false,
        null,
        "alice",
        null);
    String token = spnegoEngine.generateToken("localhost");
    // removed other assertion
    Assert.assertTrue(token.startsWith("YII"));
  }

  @Test
  public void testSpnegoGenerateTokenWithCustomLoginConfig_1_oe() throws Exception {
    Map<String, String> loginConfig = new HashMap<>();
    loginConfig.put("useKeyTab", "true");
    loginConfig.put("storeKey", "true");
    loginConfig.put("refreshKrb5Config", "true");
    loginConfig.put("keyTab", aliceKeytab.getCanonicalPath());
    loginConfig.put("principal", alice);
    loginConfig.put("debug", String.valueOf(true));
    SpnegoEngine spnegoEngine = new SpnegoEngine(null,
        null,
        "bob",
        "service.ws.apache.org",
        false,
        loginConfig,
        null,
        null);

    String token = spnegoEngine.generateToken("localhost");
    Assert.assertNotNull(token);
  }

  @Test
  public void testSpnegoGenerateTokenWithCustomLoginConfig_2_oe() throws Exception {
    Map<String, String> loginConfig = new HashMap<>();
    loginConfig.put("useKeyTab", "true");
    loginConfig.put("storeKey", "true");
    loginConfig.put("refreshKrb5Config", "true");
    loginConfig.put("keyTab", aliceKeytab.getCanonicalPath());
    loginConfig.put("principal", alice);
    loginConfig.put("debug", String.valueOf(true));
    SpnegoEngine spnegoEngine = new SpnegoEngine(null,
        null,
        "bob",
        "service.ws.apache.org",
        false,
        loginConfig,
        null,
        null);

    String token = spnegoEngine.generateToken("localhost");
    // removed other assertion
    Assert.assertTrue(token.startsWith("YII"));
  }

  @Test
  public void testGetCompleteServicePrincipalName_1_oe() throws Exception {
    {
      SpnegoEngine spnegoEngine = new SpnegoEngine(null,
          null,
          "bob",
          "service.ws.apache.org",
          false,
          null,
          null,
          null);
      Assert.assertEquals("bob@service.ws.apache.org", spnegoEngine.getCompleteServicePrincipalName("localhost"));
  }
  }

  @Test
  public void testGetCompleteServicePrincipalName_3_oe() throws Exception {
    {
      SpnegoEngine spnegoEngine = new SpnegoEngine(null,
          null,
          "bob",
          "service.ws.apache.org",
          false,
          null,
          null,
          null);
      // removed other assertion
    }
    {
      SpnegoEngine spnegoEngine = new SpnegoEngine(null,
          null,
          null,
          "service.ws.apache.org",
          true,
          null,
          null,
          null);
      // removed other assertion
      Assert.assertTrue(spnegoEngine.getCompleteServicePrincipalName("localhost").startsWith("HTTP@"));
  }
  }

  @Test
  public void testGetCompleteServicePrincipalName_4_oe() throws Exception {
    {
      SpnegoEngine spnegoEngine = new SpnegoEngine(null,
          null,
          "bob",
          "service.ws.apache.org",
          false,
          null,
          null,
          null);
      // removed other assertion
    }
    {
      SpnegoEngine spnegoEngine = new SpnegoEngine(null,
          null,
          null,
          "service.ws.apache.org",
          true,
          null,
          null,
          null);
      // removed other assertion
      // removed other assertion
    }
    {
      SpnegoEngine spnegoEngine = new SpnegoEngine(null,
          null,
          null,
          "service.ws.apache.org",
          false,
          null,
          null,
          null);
      Assert.assertEquals("HTTP@localhost", spnegoEngine.getCompleteServicePrincipalName("localhost"));
  }
  }

}
