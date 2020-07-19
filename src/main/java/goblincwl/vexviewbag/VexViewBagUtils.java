package goblincwl.vexviewbag;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.entity.IPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ☪wl
 * @program VexViewBag
 * @description 全局静态工具类
 * @create 2020-07-01 16:53
 */
public class VexViewBagUtils {

    /**
     * 检测物品type是否包含指定字符串
     *
     * @param bukkitItem  bukkitItemStack
     * @param matchString 匹配的String
     * @return java.lang.Boolean
     * @create 2020/7/2 9:47
     * @author ☪wl
     */
    public static Boolean isMatchType(ItemStack bukkitItem, String matchString) {
        String type = bukkitItem.getType().toString();
        return type.contains(matchString);
    }

    /**
     * 转换BukkitItemStack为net.minecraft.item.ItemStack
     *
     * @param itemStack bukkitItemStack
     * @return net.minecraft.item.ItemStack
     * @create 2020/7/2 9:48
     * @author ☪wl
     */
    public static net.minecraft.item.ItemStack convertItem(ItemStack itemStack) {
        return (net.minecraft.item.ItemStack) (Object) CraftItemStack.asNMSCopy(itemStack);
    }

    /**
     * 转换net.minecraft.item.ItemStack为BukkitItemStack
     *
     * @param itemStack net.minecraft.item.ItemStack
     * @return org.bukkit.inventory.ItemStack
     * @create 2020/7/19 1:25
     * @author ☪wl
     */
    public static ItemStack convertItemNM(net.minecraft.item.ItemStack itemStack) {
        return CraftItemStack.asBukkitCopy((net.minecraft.server.v1_12_R1.ItemStack) (Object) itemStack);
    }

    /**
     * 玩家无视权限执行指令
     *
     * @param player  玩家对象
     * @param command 指令(需要带/)
     * @return void
     * @create 2020/7/19 1:24
     * @author ☪wl
     */
    public static void playerOpCommand(Player player, String command) {
        boolean isOp = player.isOp();
        try {
            player.setOp(true);
            Bukkit.dispatchCommand(player, command);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            player.setOp(isOp);
        }
    }

    /**
     * 消耗玩家物品
     *
     * @param player    玩家
     * @param itemStack 物品
     * @param amount    数量
     * @return boolean
     * @create 2020/7/19 1:27
     * @author ☪wl
     */
    public static boolean consumePlayerItem(Player player, ItemStack itemStack, Integer amount) {
        HashMap<Integer, ? extends ItemStack> itemStackMap = player.getInventory().all(itemStack.getType());
        for (ItemStack nowItemStack : itemStackMap.values()) {
            if (nowItemStack.getAmount() >= amount) {
                nowItemStack.setAmount(nowItemStack.getAmount() - amount);
                return true;
            } else {
                amount = amount - nowItemStack.getAmount();
                nowItemStack.setAmount(0);
            }
        }
        return true;
    }

    /**
     * 发送特殊消息
     *
     * @param player  玩家
     * @param title   标题
     * @param message 内容
     * @param type    类型(0,1,2)
     * @return void
     * @create 2020/7/19 15:18
     * @author ☪wl
     */
    public static void sendMcMessage(Player player, String title, String message, Integer type) {
        if (player != null) {
            EntityPlayer entityPlayer = (EntityPlayer) (Object) ((CraftPlayer) player).getHandle();
            IPlayer<EntityPlayerMP> iPlayer = (IPlayer<EntityPlayerMP>) VexViewBag.npcAPI.getIEntity(entityPlayer);
            iPlayer.sendNotification(title, message, type);
        }
    }

    /**
     * 加载配置文件到map
     *
     * @return void
     * @create 2020/7/19 17:47
     * @author ☪wl
     */
    public static Map<String, Object> loadAllConfig() {
        Map<String, Object> configurationMap = new HashMap<>();
        File fileDirectory = new File(VexViewBag.vexViewBag.getDataFolder().toURI());
        if (fileDirectory.exists()) {
            for (File file : fileDirectory.listFiles()) {
                if (!file.isDirectory()) {
                    configurationMap.put(file.getName(), YamlConfiguration.loadConfiguration(file));
                }
            }
        }
        return configurationMap;
    }

}