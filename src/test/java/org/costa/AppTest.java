package org.costa;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
	private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static final String USAGE_MESSAGE = "Usage: java -jar message-digest-generator-1.0-SNAPSHOT.jar <algorithm> <password>"
			+ System.lineSeparator() + "Available algorithms: MD2 MD5 SHA-1 SHA-224 SHA-256 SHA-384 SHA-512"
			+ System.lineSeparator();

	@Before
	public void before() throws FileNotFoundException {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void after() {
		outContent.reset();
		System.setOut(null);
	}

	@Test
	public void testWithNoArguments() throws NoSuchAlgorithmException {
		App.main(new String[] {});
		assertEquals(USAGE_MESSAGE, outContent.toString());
	}

	@Test
	public void testWithJustOneArgument() throws NoSuchAlgorithmException {
		App.main(new String[] { "arg1" });
		assertEquals(USAGE_MESSAGE, outContent.toString());
	}

	@Test
	public void testWithMoreThanTwoArguments() throws NoSuchAlgorithmException {
		App.main(new String[] { "arg1", "arg2", "arg3" });
		assertEquals(USAGE_MESSAGE, outContent.toString());
	}

	@Test
	public void testWithInvalidAlgorithmArgument() throws NoSuchAlgorithmException {
		App.main(new String[] { "arg1", "arg2" });
		assertEquals(USAGE_MESSAGE, outContent.toString());
	}

	@Test
	public void testWithMD2Algorithm() throws NoSuchAlgorithmException {
		App.main(new String[] { "MD2", "random" });
		assertEquals("da831b8542a0eb4de41845e84750dcd8" + System.lineSeparator(), outContent.toString());
	}

	@Test
	public void testWithMD5Algorithm() throws NoSuchAlgorithmException {
		App.main(new String[] { "MD5", "random" });
		assertEquals("7ddf32e17a6ac5ce04a8ecbf782ca509" + System.lineSeparator(), outContent.toString());
	}

	@Test
	public void testWithSHA1Algorithm() throws NoSuchAlgorithmException {
		App.main(new String[] { "SHA-1", "random" });
		assertEquals("a415ab5cc17c8c093c015ccdb7e552aee7911aa4" + System.lineSeparator(), outContent.toString());
	}

	@Test
	public void testWithSHA224Algorithm() throws NoSuchAlgorithmException {
		App.main(new String[] { "SHA-224", "random" });
		assertEquals("84ca7e556e458c2a6193dcba3986704a240d6966a8e4a6476d810615" + System.lineSeparator(),
				outContent.toString());
	}

	@Test
	public void testWithSHA256Algorithm() throws NoSuchAlgorithmException {
		App.main(new String[] { "SHA-256", "random" });
		assertEquals("a441b15fe9a3cf56661190a0b93b9dec7d04127288cc87250967cf3b52894d11" + System.lineSeparator(),
				outContent.toString());
	}

	@Test
	public void testWithSHA384Algorithm() throws NoSuchAlgorithmException {
		App.main(new String[] { "SSH-384", "random" });
		assertEquals(USAGE_MESSAGE, outContent.toString());
	}

	@Test
	public void testWithSHA512Algorithm() throws NoSuchAlgorithmException {
		App.main(new String[] { "SHA-512", "random" });
		assertEquals(
				"811a90e1c8e86c7b4c0eef5b2c0bf0ec1b19c4b1b5a242e6455be93787cb473cb7bc9b0fdeb960d00d5c6881c2094dd63c5c900ce9057255e2a4e271fc25fef1"
						+ System.lineSeparator(),
				outContent.toString());
	}
}
