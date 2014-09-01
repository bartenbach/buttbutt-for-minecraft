package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.Buttbutt;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class PlayerListHandler {

    private Buttbutt butt;

    public PlayerListHandler(Buttbutt butt) {
        this.butt = butt;
    }

    public void sendTabListPacket(Player player) {
        PacketContainer buttPacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        buttPacket.getStrings().write(0, ChatColor.GOLD + "buttbutt");
        buttPacket.getBooleans().write(0, true);
        buttPacket.getIntegers().write(0, 10);
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, buttPacket);
        } catch (InvocationTargetException ex) {
            System.out.println("butbutt: Couldn't sent packet");
        }
    }

    public void initPacketListener(){
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(butt, PacketType.Status.Server.OUT_SERVER_INFO) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        event.getPacket().getServerPings().getValues().get(0).setPlayersOnline(butt.getServer().getOnlinePlayers().length+1);
                        event.getPacket().getServerPings().getValues().get(0).setPlayersMaximum(butt.getServer().getMaxPlayers());
                        event.getPacket().getServerPings().getValues().get(0).setMotD(butt.getServer().getMotd());
                    }
                });
    }

    public void sendPlayerListPacket(Player player) {
        
    }

}
