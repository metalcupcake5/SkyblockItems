package io.github.metalcupcake5.SkyblockItems;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.List;

public class EntityListener implements Listener {

    private final SkyblockItems plugin;

    public EntityListener(SkyblockItems plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void projectileHitEvent(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        if(projectile.hasMetadata("ebow")){
            Location location = projectile.getLocation();
            World world = projectile.getWorld();
            world.createExplosion(location.getBlockX(), location.getBlockY(), location.getBlockZ(), 3, false, false);
            projectile.remove();
        }
        if(projectile.hasMetadata("enderbow")){
            List<Entity> entityList = projectile.getNearbyEntities(8,8,8);
            entityList.forEach(entity -> {
                if(entity instanceof LivingEntity){
                    double health = ((LivingEntity) entity).getHealth();
                    ((LivingEntity) entity).damage(health/10);
                }
            });
        }
    }

    @EventHandler
    public void shootBow(EntityShootBowEvent event){
        ItemStack bow = event.getBow();
        if(!bow.hasItemMeta()){
            return;
        }
        ItemMeta meta = bow.getItemMeta();
        if(!meta.hasDisplayName()){
            return;
        }
        if(meta.getDisplayName().contains(ChatColor.DARK_PURPLE+"Explosive Bow")){
            Entity projectile = event.getProjectile();
            projectile.setMetadata("ebow", new FixedMetadataValue(SkyblockItems.getPlugin(SkyblockItems.class), true));
        }
    }

    @EventHandler
    public void changeBlock(EntityChangeBlockEvent event){
        Entity fallingBlock = event.getEntity();
        if (event.getEntityType() == EntityType.FALLING_BLOCK && fallingBlock.hasMetadata("yeti")) {
            Block block = event.getBlock();
            block.setType(Material.AIR);
            event.setCancelled(true);
            List<Entity> entityList = fallingBlock.getNearbyEntities(3,3,3);
            fallingBlock.getWorld().playEffect(fallingBlock.getLocation(), Effect.EXPLOSION_HUGE, 0);
            fallingBlock.setVelocity(new Vector(0,0,0));

            List<Entity> fallingBlockList = fallingBlock.getNearbyEntities(7,7,7);
            fallingBlockList.forEach(entity -> {
                if(entity.getType() == EntityType.FALLING_BLOCK && entity.hasMetadata("yeti")){
                    entity.remove();
                }
            });
            entityList.forEach(entity -> {
                if(entity instanceof LivingEntity){
                    double health = ((LivingEntity) entity).getHealth();
                    ((LivingEntity) entity).damage(4000);
                }
            });
            fallingBlock.remove();

        }else if(event.getEntityType() == EntityType.FALLING_BLOCK && fallingBlock.hasMetadata("leaping")) {
            Entity passenger = fallingBlock.getPassenger();
            passenger.eject();
            //this.getLogger
            Player player = (Player) passenger;
            player.getWorld().playEffect(fallingBlock.getLocation(), Effect.LAVA_POP, 0);
            System.out.println("hi");
        }
    }
}
