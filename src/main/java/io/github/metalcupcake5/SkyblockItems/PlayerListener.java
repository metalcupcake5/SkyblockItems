package io.github.metalcupcake5.SkyblockItems;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.github.metalcupcake5.SkyblockItems.SkyblockItems.*;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class PlayerListener implements Listener{
    public PlayerListener(SkyblockItems plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private final CooldownManager cooldownManager = new CooldownManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        setPlayerMana(player.getUniqueId(), 100);
        setPlayerMaxMana(player.getUniqueId(), 100);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if(playerMana.get(uuid) == null){
            return;
        }
        playerMana.remove(uuid);
        playerMaxMana.remove(uuid);
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        Material.CHEST,
                        Material.ENDER_CHEST,
                        Material.STONE_BUTTON,
                        Material.WOOD_BUTTON,
                        Material.WOOD_DOOR,
                        Material.WOODEN_DOOR,
                        Material.ACACIA_DOOR,
                        Material.BIRCH_DOOR,
                        Material.DARK_OAK_DOOR,
                        Material.JUNGLE_DOOR,
                        Material.SPRUCE_DOOR,
                        Material.TRAP_DOOR,
                        Material.FENCE_GATE,
                        Material.DARK_OAK_FENCE_GATE,
                        Material.BIRCH_FENCE_GATE,
                        Material.SPRUCE_FENCE_GATE,
                        Material.JUNGLE_FENCE_GATE,
                        Material.ACACIA_FENCE_GATE
                        ));
        /*if(clickedBlock.getType() == Material.CHEST){
            return;
        }*/
        if(clickedBlock != null && materials.contains(clickedBlock.getType())){
            return;
        }
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (!item.hasItemMeta()) {
                return;
            }
            ItemMeta meta = item.getItemMeta();
            if (!meta.hasDisplayName()) {
                return;
            }
            String name = meta.getDisplayName();
            if (name.contains(ChatColor.BLUE + "Aspect of the End")) {
                Integer cost = aote_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                Block b = player.getTargetBlock((Set<Material>) null, 8);
                Location location = b.getLocation();
                location.setPitch(player.getLocation().getPitch());
                location.setYaw(player.getLocation().getYaw());
                player.teleport(location);
                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Instant Transmission" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost+" mana)");
            }
            if (name.contains(ChatColor.GOLD + "Yeti Sword")) {
                Integer cost = yeti_sword_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId()+"-YETI");
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) <= CooldownManager.YETI_COOLDOWN){
                    long time =  CooldownManager.YETI_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft)*-1;
                    player.sendMessage(ChatColor.RED + "This ability is currently on cooldown for "+time+" more seconds.");
                    return;
                }
                Block b = player.getTargetBlock((Set<Material>) null, 32);
                World world = player.getWorld();
                Location startBlock = player.getLocation();

                List<Location> locationList = new ArrayList<>();
                List<Location> endList = new ArrayList<>();

                //Get all blocks around player
                for (int length = -2; length < 2; length++) {
                    for (int height = -2; height < 2; height++) {
                        Location loc = startBlock.clone().add(length, 0, height);
                        Location end = b.getLocation().clone().add(length, 0, height);
                        locationList.add(loc);
                        endList.add(end);
                    }
                }

                Byte blockData = 0x0;
                locationList.forEach(location -> {
                    Location material = location.clone().subtract(0,1,0);
                    Location origin = location.clone().add(0,1,0);
                    int pos = locationList.indexOf(location);
                    Location endPos = endList.get(pos);

                    FallingBlock block = world.spawnFallingBlock(origin, material.getBlock().getType(), blockData);
                    block.setDropItem(false);
                    block.setMetadata("yeti", new FixedMetadataValue(getPlugin(SkyblockItems.class), true));
                    block.setVelocity(calculateVelocityBlock(origin.toVector(), endPos.toVector(), 3));
                });
                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Terrain Toss" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");
                cooldownManager.setCooldown(player.getUniqueId()+"-YETI", System.currentTimeMillis());
            }else if (name.contains(ChatColor.DARK_PURPLE + "Leaping Sword")){
                Integer cost = leaping_sword_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId()+"-LEAPING");
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) <= CooldownManager.LEAPING_COOLDOWN){
                    long time =  CooldownManager.LEAPING_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft)*-1;
                    player.sendMessage(ChatColor.RED + "This ability is currently on cooldown for "+time+" more seconds.");
                    return;
                }

                Block b = player.getTargetBlock((Set<Material>) null, 10);
                World world = player.getWorld();
                Location startBlock = player.getLocation();
                Location target = b.getLocation();

                Entity e = world.spawnFallingBlock(startBlock, Material.PISTON_MOVING_PIECE, (byte) 0);
                e.setMetadata("leaping", new FixedMetadataValue(getPlugin(SkyblockItems.class), true));
                e.setPassenger(player);
                e.setVelocity(calculateVelocityBlock(startBlock.toVector(), target.toVector(), 5));
                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Leap" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");
                cooldownManager.setCooldown(player.getUniqueId()+"-LEAPING", System.currentTimeMillis());

            }
        }
        if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
            if (!item.hasItemMeta()) {
                return;
            }
            ItemMeta meta = item.getItemMeta();
            if (!meta.hasDisplayName()) {
                return;
            }
            String check = ChatColor.BLUE + "Ender Bow";
            String name = meta.getDisplayName();
            if (name.contains(check)) {
                Integer cost = ender_bow_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }
                long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId()+"-ENDER_BOW");
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) <= CooldownManager.ENDER_BOW_COOLDOWN){
                    long time = CooldownManager.ENDER_BOW_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft)*-1;
                    player.sendMessage(ChatColor.RED + "This ability is currently on cooldown for "+time+" more seconds.");
                    return;
                }
                Projectile projectile = player.launchProjectile(EnderPearl.class);
                projectile.setMetadata("enderbow", new FixedMetadataValue(getPlugin(SkyblockItems.class), true));
                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Ender Warp" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");
                cooldownManager.setCooldown(player.getUniqueId()+"-ENDER_BOW", System.currentTimeMillis());
            }

        }
    }
}
