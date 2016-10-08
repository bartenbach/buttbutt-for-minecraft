/*package co.proxa.testbutt;

import co.proxa.buttbutt.handler.PlayerListHandler;
import co.proxa.buttbutt.listener.PlayerJoinListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PlayerJoinEvent.class)
public class PlayerJoinTest extends TestInstanceCreator {

    @Test
    public void testPlayerJoin() {
        when(mockPlayer.hasPlayedBefore()).thenReturn(false);

        PlayerJoinEvent mockEvent = mock(PlayerJoinEvent.class);
        when(mockEvent.getPlayer()).thenReturn(mockPlayer);

        mockPlayerListHandler = new PlayerListHandler(mockButt);
        when(mockButt.getPlayerListHandler()).thenReturn(mockPlayerListHandler);

        PlayerJoinListener playerJoinListener = new PlayerJoinListener(mockButt);
        playerJoinListener.onPlayerJoin(mockEvent);
    }

}*/
