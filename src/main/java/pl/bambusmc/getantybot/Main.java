package pl.bambusmc.getantybot;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bambusmc.getantybot.Utils.Util;
import pl.bambusmc.getantybot.en.events.onChatEN;
import pl.bambusmc.getantybot.en.events.onJoinEN;
import pl.bambusmc.getantybot.en.events.onMoveEN;
import pl.bambusmc.getantybot.pl.events.onChatPL;
import pl.bambusmc.getantybot.pl.events.onJoinPL;
import pl.bambusmc.getantybot.pl.events.onMovePL;

import java.util.Date;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main main;

    @Override
    public void onEnable() {
        main = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Util.Setup(main, "a");

        Util.sendToConsole("\n\n&b _  __ ____   _   _  _  __  ____   ___   ____   _____              _     _   _  _____  ___  ____    ___   _____ \n" +
                "| |/ /|  _ \\ | | | || |/ / / ___| / _ \\ |  _ \\ | ____|            / \\   | \\ | ||_   _||_ _|| __ )  / _ \\ |_   _|\n" +
                "| ' / | |_) || | | || ' / | |    | | | || | | ||  _|    _____    / _ \\  |  \\| |  | |   | | |  _ \\ | | | |  | |  \n" +
                "| . \\ |  _ < | |_| || . \\ | |___ | |_| || |_| || |___  |_____|  / ___ \\ | |\\  |  | |   | | | |_) || |_| |  | |  \n" +
                "|_|\\_\\|_| \\_\\ \\___/ |_|\\_\\ \\____| \\___/ |____/ |_____|         /_/   \\_\\|_| \\_|  |_|  |___||____/  \\___/   |_|  \n" +
                "                                                                                                                \n\n&eVersion: " + getDescription().getVersion() + "&7\n\n&aLoading plugin...");

        if (Objects.equals(getConfig().getString("language"), "PL")) {
            Util.sendToConsole("&aLoading PL events...");
            getServer().getPluginManager().registerEvents(new onJoinPL(this), this);
            getServer().getPluginManager().registerEvents(new onChatPL(this), this);
            getServer().getPluginManager().registerEvents(new onMovePL(this), this);
            Util.sendToConsole("&aPL events loaded!");
        } else if (Objects.equals(getConfig().getString("language"), "EN")) {
            Util.sendToConsole("&aLoading EN events...");
            getServer().getPluginManager().registerEvents(new onJoinEN(this), this);
            getServer().getPluginManager().registerEvents(new onChatEN(this), this);
            getServer().getPluginManager().registerEvents(new onMoveEN(this), this);
            Util.sendToConsole("&aEN events loaded!");
        } else {
            Util.sendToConsole("&cError... &7Language not supported! &cDisabling plugin!");
            getPluginLoader().disablePlugin(this);
            return;
        }

        Util.sendToConsole("&aPlugin loaded!\n&ePlugin download: &7https://www.spigotmc.org/resources/getantybot.123124/");


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
