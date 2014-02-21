package co.proxa.buttbutt.file;

import org.bukkit.plugin.java.JavaPlugin;

public class FileManager {

    private JavaPlugin butt;

    public FileManager(JavaPlugin butt) {
        this.butt = butt;
    }

    public void createFiles() {
        boolean filesExist = butt.getDataFolder().exists();
        if (!filesExist) {
            butt.getLogger().info("*************************************************************************");
            butt.getLogger().info("**************************************************************************");
                if (!butt.getDataFolder().mkdirs()) {
                    butt.getLogger().severe("ERROR: Couldn't create plugins/buttbutt directory!");
                } else {
                    butt.getConfig().options().copyDefaults(true);
                    butt.saveConfig();
                    butt.getLogger().info("Created plugins/buttbutt directory, and new plugins/buttbutt/config.yml");
                    butt.getLogger().info("Please configure your SQL database settings in the config.yml");
                }
            butt.getLogger().info("**************************************************************************");
            butt.getLogger().info("*************************************************************************");
            butt.getServer().shutdown();
        }
        butt.getConfig().options().copyDefaults(true);
        butt.saveConfig();
    }

}
