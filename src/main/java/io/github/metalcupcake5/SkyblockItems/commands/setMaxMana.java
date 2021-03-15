package io.github.metalcupcake5.SkyblockItems.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.metalcupcake5.SkyblockItems.SkyblockItems.setPlayerMaxMana;
import static java.lang.Integer.parseInt;

public class setMaxMana implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(args.length < 1){
                    sender.sendMessage("You must provide a mana amount!");
                    return true;
                }
                Integer mana = parseInt(args[0]);
                setPlayerMaxMana(player.getUniqueId(), mana);
            } else {
                sender.sendMessage("You must be a player to run this command!");
                return false;
            }
            return true;
        } else if (args.length == 2) {
            String username = args[0];
            Player player = Bukkit.getServer().getPlayer(username);
            if (player == null) {
                sender.sendMessage("That person is not online!");
                return true;
            }
            setPlayerMaxMana(player.getUniqueId(), parseInt(args[1]));
            return true;
        }else{
            sender.sendMessage("Correct usage: /setMaxMana <username> <mana> OR /setMaxMana <mana>");
            return true;
        }
    }
}
