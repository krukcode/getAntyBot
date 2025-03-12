package pl.bambusmc.getantybot.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static String pluginPrefix = "NO PREFIX DEFINED!";
    public static void sendMessage (CommandSender sender, String text) { sender.sendMessage(fix(text)); }
    public static void sendToConsole (String text) { Bukkit.getConsoleSender().sendMessage(fix(pluginPrefix + " " + text)); }

    public static void setPrefix(String pluginName) {
        pluginPrefix = "&7[" + pluginName + "&7]&r";
    }

    public static String fix(String text) {
        return fixGradient(ChatColor.translateAlternateColorCodes('&', text).replaceAll(">>", "»").replaceAll("<<", "«"));
    }

    public static String smallCaps(String text) {
        return text
                .replaceAll("a", "ᴀ")
                .replaceAll("b", "ʙ")
                .replaceAll("c", "ᴄ")
                .replaceAll("d", "ᴅ")
                .replaceAll("e", "ᴇ")
                .replaceAll("f", "ꜰ")
                .replaceAll("g", "ɢ")
                .replaceAll("h", "ʜ")
                .replaceAll("i", "ɪ")
                .replaceAll("j", "ᴊ")
                .replaceAll("k", "ᴋ")
                .replaceAll("l", "ʟ")
                .replaceAll("m", "ᴍ")
                .replaceAll("n", "ɴ")
                .replaceAll("o", "ᴏ")
                .replaceAll("p", "ᴘ")
                .replaceAll("r", "ʀ")
                .replaceAll("s", "ѕ")
                .replaceAll("t", "ᴛ")
                .replaceAll("u", "ᴜ")
                .replaceAll("w", "ᴡ")
                .replaceAll("y", "ʏ")
                .replaceAll("z", "ᴢ")
                .replaceAll("A", "ᴀ")
                .replaceAll("B", "ʙ")
                .replaceAll("C", "ᴄ")
                .replaceAll("D", "ᴅ")
                .replaceAll("E", "ᴇ")
                .replaceAll("F", "ꜰ")
                .replaceAll("G", "ɢ")
                .replaceAll("H", "ʜ")
                .replaceAll("I", "ɪ")
                .replaceAll("J", "ᴊ")
                .replaceAll("K", "ᴋ")
                .replaceAll("L", "ʟ")
                .replaceAll("M", "ᴍ")
                .replaceAll("N", "ɴ")
                .replaceAll("O", "ᴏ")
                .replaceAll("P", "ᴘ")
                .replaceAll("Q", "ǫ")
                .replaceAll("R", "ʀ")
                .replaceAll("S", "ѕ")
                .replaceAll("T", "ᴛ")
                .replaceAll("U", "ᴜ")
                .replaceAll("V", "ᴠ")
                .replaceAll("W", "ᴡ")
                .replaceAll("X", "х")
                .replaceAll("Y", "ʏ")
                .replaceAll("Z", "ᴢ");

    }

    public static List<String> fix(List<String> strings) {
        for(int i = 0; i < strings.size(); ++i) {
            strings.set(i, fix(strings.get(i)));
        }

        return strings;
    }
    public static String fixGradient(String string) {
        Pattern pattern = Pattern.compile("&(#[a-fA-F0-9]{6})");
        for (Matcher matcher = pattern.matcher(string); matcher.find(); matcher = pattern.matcher(string)) {
            String color = string.substring(matcher.start() + 1, matcher.end());
            string = string.replace("&" + color, net.md_5.bungee.api.ChatColor.of(color) + "");
        }
        string = ChatColor.translateAlternateColorCodes('&', string);
        return string;
    }
}
