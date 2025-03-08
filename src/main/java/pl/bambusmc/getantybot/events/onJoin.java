package pl.bambusmc.getantybot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bambusmc.getantybot.Main;
import pl.bambusmc.getantybot.System;

import java.util.UUID;

public class onJoin implements Listener {

    Main plugin;
    pl.bambusmc.getantybot.System s;

    public onJoin(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        String ip = p.getAddress().getHostName();
        UUID uuid = p.getUniqueId();

        plugin.getConfig().set(uuid + ".ip", ip);
        plugin.saveConfig();

        if(!p.hasPlayedBefore()) {
            p.kickPlayer("§eZostałeś zweryfikowany! \n§7Wejdź ponownie na serwer.");
        }

        plugin.getConfig().set(uuid + ".captcha.a", System.getRandom());
        plugin.getConfig().set(uuid + ".captcha.b", System.getRandom());
        plugin.getConfig().set(uuid + ".captcha.solved", "false");
        plugin.saveConfig();

        int a = plugin.getConfig().getInt(uuid + ".captcha.a");
        int b = plugin.getConfig().getInt(uuid + ".captcha.b");
        int solution = a + b;

        plugin.getConfig().set(uuid + ".captcha.solution", solution);
        plugin.saveConfig();

        p.sendMessage("§7By zostać zweryfikowanym rozwiąż działanie.");
        p.sendMessage("§7Ile to §e" + a + " + " + b + "§7 ?");
        p.sendMessage("§eMasz §720 §esekund na odpowiedź!");

        Main.getMain().kickPlayerAfterWaiting(2000, p, "§cCzas na odpowiedź minął!");
    }
}
