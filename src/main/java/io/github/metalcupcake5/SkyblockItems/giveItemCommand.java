package io.github.metalcupcake5.SkyblockItems;

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
        //int level = parseInt(args[1]);

        //Speedy Sword
        //ItemStack item = new ItemStack(Material.STONE_SWORD);

        //AOTE
        /*ItemStack item = new ItemStack(Material.DIAMOND_SWORD);

        ItemMeta meta = item.getItemMeta();
        List<String> lores = new ArrayList<String>();
        //Speedy Sword

        //meta.setDisplayName(ChatColor.GOLD+"Speedy Sword");
        //lores.add(ChatColor.WHITE+"On switching to this sword,");
        //lores.add(ChatColor.WHITE+"receive an extra boost of speed.");

        meta.setLore(lores);
        item.setItemMeta(meta);*/
        //item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        if (sender instanceof Player) {
            String arguments = String.join(" ", args);
            String currentItems = "\n  -Aspect of the End\n  -Explosive Bow\n  -Ender Bow\n  -Yeti Sword\n  -Leaping Sword";
            if(args.length < 1){
                sender.sendMessage("Invalid item!\nCurrent items:"+currentItems);
                return true;
            }else if (arguments.equalsIgnoreCase("ebow") || arguments.equalsIgnoreCase("explosive") || arguments.equalsIgnoreCase("explosive bow") || arguments.equalsIgnoreCase("Explosive Bow")) {
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
            }else if(arguments.equalsIgnoreCase("aote") || arguments.equalsIgnoreCase("AOTE") || arguments.equalsIgnoreCase("Aspect of the End") || arguments.equalsIgnoreCase("aspect of the end")){
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
            }else if(arguments.equalsIgnoreCase("ender bow") || arguments.equalsIgnoreCase("Ender Bow") || arguments.equalsIgnoreCase("ender Bow") || arguments.equalsIgnoreCase("Ender bow")){
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
            }else if(arguments.equalsIgnoreCase("yeti") || arguments.equalsIgnoreCase("Yeti Sword") || arguments.equalsIgnoreCase("yeti sword") || arguments.equalsIgnoreCase("Yeti sword")){
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
            } else if(arguments.equalsIgnoreCase("leaping") || arguments.equalsIgnoreCase("Leaping Sword") || arguments.equalsIgnoreCase("leaping sword") || arguments.equalsIgnoreCase("Leaping sword")){
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
            }else{
                sender.sendMessage("Invalid item!\nCurrent items:"+currentItems);
                return true;
            }
            /*Player player = (Player) sender;
            PlayerInventory inventory = player.getInventory();
            inventory.addItem(item);
            sender.sendMessage("Given a "+item.getType());
            return true;*/
        } else {
            sender.sendMessage("Only players can use this command!");
            return false;
        }
    }
}
