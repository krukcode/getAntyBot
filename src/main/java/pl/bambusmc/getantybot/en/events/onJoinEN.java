package pl.bambusmc.getantybot.en.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bambusmc.getantybot.Main;
import pl.bambusmc.getantybot.System;

import java.util.UUID;

public class onJoinEN implements Listener {

    Main plugin;
    System s;

    public onJoinEN(Main m) {
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
            p.kickPlayer("§eSuccessfully verified! \n§7Rejoin the server.");
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

        p.sendMessage("§7To be verified solve this math question.");
        p.sendMessage("§7What is §e" + a + " + " + b + "§7 ?");
        p.sendMessage("§eYou have §720 §eseconds to solve!");

        Main.getMain().kickPlayerAfterWaiting(2000, p, "§cTry again!");
    }
}
