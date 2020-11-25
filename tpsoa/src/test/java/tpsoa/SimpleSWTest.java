package tpsoa;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import tpsoa.SimpleSW;

public class SimpleSWTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(SimpleSW.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("simpleSW").request().get(String.class);

        assertEquals("affichage simpleSW!", responseMsg);
    }
}
