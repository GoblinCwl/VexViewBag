package goblincwl.vexviewbag.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

    public static ItemStack convertItemNM(net.minecraft.item.ItemStack itemStack) {
        return CraftItemStack.asBukkitCopy((net.minecraft.server.v1_12_R1.ItemStack) (Object) itemStack);
    }

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

}