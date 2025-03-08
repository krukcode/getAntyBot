package pl.bambusmc.getantybot;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bambusmc.getantybot.events.onChat;
import pl.bambusmc.getantybot.events.onJoin;
import pl.bambusmc.getantybot.events.onMove;

import java.util.Date;

public final class Main extends JavaPlugin {

    public static Main main;

    @Override
    public void onEnable() {


        getConfig().options().copyDefaults(true);
        saveConfig();

        main = this;

        getServer().getPluginManager().registerEvents(new onJoin(this), this);
        getServer().getPluginManager().registerEvents(new onChat(this), this);
        getServer().getPluginManager().registerEvents(new onMove(this), this);
    }

    public static Main getMain() {
        return main;
    }

    public void kickPlayerAfterWaiting(Integer milliseconds, Player p, String reason) {
        long start = new Date().getTime();

        for (int i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds){
                p.kickPlayer(reason);
                break;
            }
        }
    }

    public int millisToSeconds(int mt) {
      int seconds = (int) Math.floor(mt/1000) % 60;
        return seconds;
    }
}
