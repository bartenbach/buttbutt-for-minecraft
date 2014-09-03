package co.proxa.buttbutt.sql;

import co.proxa.buttbutt.Buttbutt;
import org.bukkit.Server;
import org.junit.Before;
import org.junit.Test;

public class QuoteGrabTableTest {

    private Server mockServer;
    private Buttbutt butt;

    @Before
    public void setUp() throws Exception {
        butt = new Buttbutt();
        SqlManager sqlManager = new SqlManager(butt);

        /* Fake our Server */
        
    }

    @Test
    public void testGetRandomQuote() throws Exception {

    }
}
