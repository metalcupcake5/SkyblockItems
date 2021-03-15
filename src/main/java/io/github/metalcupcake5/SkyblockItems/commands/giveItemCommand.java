package io.github.metalcupcake5.SkyblockItems.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class giveItemCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String arguments = String.join(" ", args);
            String currentItems = "\n  -Aspect of the End\n  -Explosive Bow\n  -Ender Bow\n  -Yeti Sword\n  -Leaping Sword\n  -Ember Rod\n  -Golem Sword\n  -Ink Wand\n  -Grappling Hook";
            if(args.length < 1){
                sender.sendMessage("Invalid item!\nCurrent items:"+currentItems);
                return true;
            } else if (arguments.equalsIgnoreCase("ebow") || arguments.equalsIgnoreCase("explosive") || arguments.equalsIgnoreCase("explosive bow")) {
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.BOW);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.DARK_PURPLE + "Explosive Bow");
                lores.add(ChatColor.GOLD + "Item Ability: Explosive");
                lores.add(ChatColor.GRAY + "Creates an explosion on impact!");
                lores.add(ChatColor.GRAY + "Every Monster caught in this");
                lores.add(ChatColor.GRAY + "explosion takes the full damage");
                lores.add(ChatColor.GRAY + "of the weapon!");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "EPIC BOW");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given an explosive bow");
                return true;
            }else if(arguments.equalsIgnoreCase("aote") || arguments.equalsIgnoreCase("Aspect of the End")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.BLUE + "Aspect of the End");
                lores.add(ChatColor.GOLD + "Item Ability: Instant Transmission " + ChatColor.YELLOW + ChatColor.BOLD + "RIGHT CLICK");
                lores.add(ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "8 blocks " + ChatColor.GRAY + "ahead of");
                lores.add(ChatColor.GRAY + "you and gain " + ChatColor.GREEN + "+50 speed");
                lores.add(ChatColor.GRAY + "for " + ChatColor.GREEN + "3 seconds.");
                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "50");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.BLUE + ChatColor.BOLD + "RARE SWORD");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given an Aspect of the End");
                return true;
            }else if(arguments.equalsIgnoreCase("ender bow") || arguments.equalsIgnoreCase("Ender Bow")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.BOW);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.BLUE + "Ender Bow");
                lores.add(ChatColor.GOLD + "Item Ability: Ender Warp");
                lores.add(ChatColor.GRAY + "Shoots and Ender Pearl. Upon");
                lores.add(ChatColor.GRAY + "landing you deal damage to all");
                lores.add(ChatColor.GRAY + "Monsters in a "+ChatColor.GREEN+"8.0 "+ChatColor.GRAY+"block");
                lores.add(ChatColor.GRAY + "radius for "+ChatColor.GREEN+"10% "+ChatColor.GRAY+"of their");
                lores.add(ChatColor.RED+"Health"+ChatColor.GRAY + ".");
                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "50");
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + "45s");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.BLUE + ChatColor.BOLD + "RARE BOW");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given an Ender Bow");
                return true;
            } else if(arguments.equalsIgnoreCase("yeti") || arguments.equalsIgnoreCase("Yeti Sword")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.IRON_SWORD);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.GOLD + "Yeti Sword");
                lores.add(ChatColor.GOLD + "Item Ability: Terrain Toss " + ChatColor.YELLOW + ChatColor.BOLD + "RIGHT CLICK");
                lores.add(ChatColor.GRAY + "Throws a chunk of terrain in the");
                lores.add(ChatColor.GRAY + "direction you are facing! Deals");
                lores.add(ChatColor.GRAY + "up to " + ChatColor.RED + "4000 " + ChatColor.GRAY + "damage.");
                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "250");
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + "1s");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.GOLD + ChatColor.BOLD + "LEGENDARY SWORD");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given a Yeti Sword");
                return true;
            } else if(arguments.equalsIgnoreCase("leaping") || arguments.equalsIgnoreCase("Leaping Sword")) {
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.GOLD_SWORD);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.DARK_PURPLE + "Leaping Sword");
                lores.add(ChatColor.GOLD + "Item Ability: Leap " + ChatColor.YELLOW + ChatColor.BOLD + "RIGHT CLICK");
                lores.add(ChatColor.GRAY + "Leap into the air and deal "+ ChatColor.GREEN + "350");
                lores.add(ChatColor.GRAY + "base Magic Damage to nearby");
                lores.add(ChatColor.GRAY + "enimies upon landing on the");
                lores.add(ChatColor.GRAY + "ground. Damaged enemies will");
                lores.add(ChatColor.GRAY + "also be frozen for " + ChatColor.GREEN + "1.0");
                lores.add(ChatColor.GRAY + "second.");
                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "50");
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + "1s");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "EPIC SWORD");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given a Leaping Sword");
                return true;
            } else if(arguments.equalsIgnoreCase("ember") || arguments.equalsIgnoreCase("ember rod")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.BLAZE_ROD);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.DARK_PURPLE + "Ember Rod");
                lores.add(ChatColor.GOLD + "Item Ability: Fire Blast " + ChatColor.YELLOW + ChatColor.BOLD + "RIGHT CLICK");
                lores.add(ChatColor.GRAY + "Shoot 3 Fireballs in rapid");
                lores.add(ChatColor.GRAY + "succession in front of you!");
                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "150");
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + "30s");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "EPIC SWORD");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given an Ember Rod");
                return true;
            }else if(arguments.equalsIgnoreCase("golem") || arguments.equalsIgnoreCase("golem sword")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.IRON_SWORD);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.BLUE + "Golem Sword");
                lores.add(ChatColor.GOLD + "Item Ability: Iron Punch " + ChatColor.YELLOW + ChatColor.BOLD + "RIGHT CLICK");
                lores.add(ChatColor.GRAY + "Punch the ground, damaging");
                lores.add(ChatColor.GRAY + "enemies in a hexagon around you");
                lores.add(ChatColor.GRAY + "for" + ChatColor.GREEN + " 250" + ChatColor.GRAY + " base Magic Damage");
                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "70");
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + "3s");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.BLUE + ChatColor.BOLD + "RARE SWORD");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given a Golem Sword");
                return true;
            }else if(arguments.equalsIgnoreCase("ink") || arguments.equalsIgnoreCase("ink wand")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.STICK);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.DARK_PURPLE + "Ink Wand");
                lores.add(ChatColor.GOLD + "Item Ability: Ink Bomb " + ChatColor.YELLOW + ChatColor.BOLD + "RIGHT CLICK");
                lores.add(ChatColor.GRAY + "Shoot an ink bomb in front of");
                lores.add(ChatColor.GRAY + "you dealing" + ChatColor.GREEN + " 50,000" + ChatColor.GRAY + " damage");
                lores.add(ChatColor.GRAY + "and giving blindness!");
                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "60");
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + "30s");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "EPIC SWORD");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given an Ink Wand");
                return true;
            }else if(arguments.equalsIgnoreCase("grapple") || arguments.equalsIgnoreCase("grappling hook")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.FISHING_ROD);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.GREEN + "Grappling Hook");
                lores.add(ChatColor.GRAY + "Travel around in style using");
                lores.add(ChatColor.GRAY + "this Grappling Hook");
                lores.add(ChatColor.DARK_GRAY + "2 second cooldown");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.GREEN + ChatColor.BOLD + "UNCOMMON");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given a grappling hook");
                return true;
            } else if(arguments.equalsIgnoreCase("Ember Bow")){
                Player player = (Player) sender;
                PlayerInventory inventory = player.getInventory();

                ItemStack item = new ItemStack(Material.BOW);
                ItemMeta meta = item.getItemMeta();
                List<String> lores = new ArrayList<>();

                meta.setDisplayName(ChatColor.GREEN + "Ember Bow");
                lores.add(ChatColor.GRAY + "its an ember bow");
                lores.add(ChatColor.GRAY + "ok");
                lores.add(ChatColor.GOLD + "Item Passive: Ignition");
                lores.add(ChatColor.GRAY + "Set the first target hit on");
                lores.add(ChatColor.GRAY + "fire for 2 seconds.");
                lores.add(ChatColor.GRAY + "Hitting a target that is already on fire");
                lores.add(ChatColor.GRAY + "will restore 1% of your missing health back.");
                lores.add(null);
                lores.add(ChatColor.BOLD + "" + ChatColor.GRAY + ChatColor.BOLD + "SOME RARITY");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                meta.setLore(lores);
                item.setItemMeta(meta);

                inventory.addItem(item);
                sender.sendMessage("You were given an ember bow");
                return true;
            } else{
                sender.sendMessage("Invalid item!\nCurrent items:"+currentItems);
                return true;
            }
        } else {
            sender.sendMessage("Only players can use this command!");
            return false;
        }
    }
}
