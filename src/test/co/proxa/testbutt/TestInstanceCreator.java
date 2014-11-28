package co.proxa.testbutt;

import co.proxa.buttbutt.Buttbutt;
import co.proxa.buttbutt.handler.PlayerListHandler;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.powermock.core.MockGateway;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(Buttbutt.class)
@RunWith(PowerMockRunner.class)
public class TestInstanceCreator {

    protected static FileConfiguration config;
    protected BukkitScheduler mockScheduler;
    protected PlayerListHandler mockPlayerListHandler;
    protected Server mockServer;
    protected Buttbutt mockButt;
    protected Player mockPlayer;

    @BeforeClass
    public static void testClassSetup() {
        File configFile = new File(new File("").getAbsolutePath() + "resource/config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        MockGateway.MOCK_STANDARD_METHODS = false;
    }

    @Before
    public void setUp() throws Exception {
        /* Set up our mock server and scheduler */
        mockServer = mock(Server.class);
        mockScheduler = mock(BukkitScheduler.class);
        when(mockServer.getScheduler()).thenReturn(mockScheduler);

        /* mock our main plugin class and config */
        mockButt = mock(Buttbutt.class);
        when(mockButt.getConfig()).thenReturn(config);

        /* mock a player */
        mockPlayer = mock(Player.class);
        when(mockPlayer.getName()).thenReturn("MockPlayer");

        /* mock other necessary classes */
        mockPlayerListHandler = mock(PlayerListHandler.class);
    }


}
