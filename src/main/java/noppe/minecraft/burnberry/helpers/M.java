package noppe.minecraft.burnberry.helpers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class M {
    public static Burnberry burnberry;
    public static NamespacedKey key;

    public static void setPlugin(Burnberry plugin){
        burnberry = plugin;
        key = new NamespacedKey(burnberry, "name");
    }

    public static int getTicks(){
        return burnberry.ticks;
    }

    public static Boolean matches(ItemStack item1, ItemStack item2){
        return (!M.getItemNBTName(item1).equals("empty") && M.getItemNBTName(item1).equals(M.getItemNBTName(item2)));
    }

    public static void setMetaData(Entity entity, String key, Object value){
        entity.setMetadata(key, new FixedMetadataValue(burnberry, value));
    }
    public static void setMetaData(Block block, String key, Object value){
        block.setMetadata(key, new FixedMetadataValue(burnberry, value));
    }

    public static Object getMetaData(Entity entity, String key){
        if (entity.getMetadata(key).isEmpty()){
            return null;
        }
        return entity.getMetadata(key).getFirst().value();
    }

    public static Object getMetaData(Block block, String key){
        if (block.getMetadata(key).isEmpty()){
            return null;
        }
        return block.getMetadata(key).getFirst().value();
    }

    public static void setItemNBTName(ItemStack itemstack, String name){
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, name);
        itemstack.setItemMeta(itemMeta);
    }

    public static String getItemNBTName(ItemStack itemstack){
        ItemMeta meta = itemstack.getItemMeta();
        if (meta == null || !meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)){
            return "empty";
        }
        return meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }

    public static void setItemNBTTag(ItemStack itemstack, NamespacedKey key){
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
        itemstack.setItemMeta(itemMeta);
    }

    public static boolean getItemNBTTag(ItemStack itemstack, NamespacedKey key){
        return itemstack.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.BOOLEAN);
    }

    public static void setItemName(ItemStack itemStack, String name){
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
    }

    public static String getItemName(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        return meta.getDisplayName();
    }

    public static void setLore(ItemStack item, String[] lore){
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);
    }

    public static void setLore(ItemStack item, String lore){
        String[] text = new String[1];
        text[0] = lore;
        setLore(item, text);
    }

    public static CustomEntity getWrapper(Entity entity){
        return (CustomEntity) M.getMetaData(entity, "wrapper");
    }

    public static CustomEntity getWrapper(Block block){
        return (CustomEntity) M.getMetaData(block, "wrapper");
    }

    public static CustomPlayer getPlayerFromDamageSource(EntityDeathEvent event){
        DamageSource damageSource = event.getDamageSource();
        if (damageSource != null){
            Entity entity = damageSource.getCausingEntity();
            if (entity != null){
                CustomEntity killer = M.getWrapper(entity);
                if (killer.isPlayer()){
                    return (CustomPlayer) killer;
                }
            }
        }
        return null;
    }

    public static void setInventory(Player player, Inventory inventory){
        player.getInventory().setContents(inventory.getContents());
    }

    public static void setInventory(CustomPlayer player, Inventory inventory){
        M.setInventory(player.playerWrapped, inventory);
    }

    public static Entity spawnEntity(Object origin, Location location, Class clazz){
        Entity entity = M.getWorld().spawn(location, clazz, false, null);
        M.setMetaData(entity, "origin", origin);
        return entity;
    }

    public static void playWorldSound(Sound sound){
        playWorldSound(sound, 1);
    }

    public static void playWorldSound(Sound sound, float volume){
        for (Player player: getWorld().getPlayers()){
            player.playSound(player, sound, volume, 1);
        }
    }

    public static World getWorld(){
        return burnberry.getServer().getWorld("world");
    }

    public static void print(String message){
        M.burnberry.print(message);
        System.out.println(message);
    }
}
