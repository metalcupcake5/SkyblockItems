package io.github.metalcupcake5.SkyblockItems.commands;

import io.github.metalcupcake5.SkyblockItems.SkyblockItems;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.metalcupcake5.SkyblockItems.SkyblockItems.setPlayerMana;

public class setMana implements CommandExecutor {

    private final SkyblockItems plugin;

    public setMana(SkyblockItems plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(args.length < 1){
                    sender.sendMessage("You must provide a mana amount!");
                    return true;
                }
                try{
                    int mana = Integer.parseInt(args[0]);
                    setPlayerMana(player.getUniqueId(), mana);
                    sender.sendMessage("Set your mana to " + args[0]);
                    return true;
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid mana amount!");
                    return true;
                }
            } else {
                sender.sendMessage("You must be a player to run this command without a user argument!");
                return false;
            }
        } else if (args.length == 2) {
            String username = args[0];
            Player player = Bukkit.getServer().getPlayer(username);
            if (player == null) {
                sender.sendMessage("That person is not online!");
                return true;
            }
            try{
                int mana = Integer.parseInt(args[1]);
                setPlayerMana(player.getUniqueId(), mana);
                sender.sendMessage("Set " + username + "'s mana to " + args[1]);
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid mana amount!");
                return true;
            }
        }else{
            sender.sendMessage("Correct usage: /setMaxMana <username> <mana> OR /setMaxMana <mana>");
            return true;
        }
    }
}
