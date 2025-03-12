package pl.bambusmc.getantybot.en.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.bambusmc.getantybot.Main;

import java.util.UUID;

public class onChatEN implements Listener {

    Main plugin;

    public onChatEN(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onPlayerJoin(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        UUID uuid = p.getUniqueId();

        if(e.getMessage().equals(plugin.getConfig().getInt(uuid + ".captcha.solution") + "")) {
            if(plugin.getConfig().getString(uuid + ".captcha.solved") == "false") {
                e.setCancelled(false);
                p.sendMessage("§7Successfully verified!.");
                e.setCancelled(false);

                plugin.getConfig().set(uuid + ".captcha.solved", "true");
                plugin.saveConfig();
                e.setCancelled(false);
            }
        }
    }
}
