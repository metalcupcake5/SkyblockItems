package io.github.metalcupcake5.SkyblockItems.commands;

import io.github.metalcupcake5.SkyblockItems.SkyblockItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Set;

public class configCommand implements CommandExecutor {

    private final SkyblockItems plugin;

    public configCommand(SkyblockItems plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            sender.sendMessage("hello");
        }else{
            Set<String> keys = this.plugin.getConfig().getKeys(false);
            String list = String.join("\n", keys);
            sender.sendMessage("Valid keys:\n" + list);
        }
        return true;
    }
}
