package pl.bambusmc.getantybot.en.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.bambusmc.getantybot.Main;

import java.util.UUID;

public class onMoveEN implements Listener {

    Main plugin;

    public onMoveEN(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        UUID uuid = p.getUniqueId();

        if(plugin.getConfig().getString(uuid + ".captcha.solved") == "false") {
            e.setCancelled(true);
        }
    }
}
