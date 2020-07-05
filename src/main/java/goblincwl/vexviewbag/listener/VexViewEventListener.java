package goblincwl.vexviewbag.listener;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.inv.BaublesInventoryWrapper;
import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.gui.BagGui;
import goblincwl.vexviewbag.utils.VexViewBagUtils;
import lain.mods.cos.api.CosArmorAPI;
import lain.mods.cos.api.inventory.CAStacksBase;
import lk.vexview.api.VexViewAPI;
import lk.vexview.event.KeyBoardPressEvent;
import lk.vexview.event.VexSlotInteractEvent;
import lk.vexview.gui.VexInventoryGui;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


/**
 * @author ☪wl
 * @program VexViewBag
 * @description VexView事件监听类
 * @create 2020-07-01 16:48
 */
public final class VexViewEventListener implements Listener {

    int bb = 1;

    @EventHandler
    public void onKey(KeyBoardPressEvent event) throws IllegalAccessException, NoSuchFieldException {
        //按的是E(18) R(19)，并且有GUI打开，并且按键结束（放开，非按住）
        if (event.getKey() == 19 && event.getEventKeyState()) {
            if (event.getType().toString().equals("GUI")) {
                //如果在GUI里
                //关闭界面
                event.getPlayer().closeInventory();
            } else {
                //打开bag
                VexViewAPI.openGui(event.getPlayer(), new BagGui(event.getPlayer()));
            }
        }
    }

    @EventHandler
    public void onInvSlotClick(VexSlotInteractEvent event) {
        //玩家对象
        Player player = event.getPlayer();
        //玩家已打开的gui
        VexInventoryGui vexGui = (VexInventoryGui) VexViewAPI.getPlayerCurrentGui(player).getVexGui();

        //插件配置文件
        FileConfiguration config = VexViewBag.getInstance().getConfig();

        //debug标记
        boolean debugMode = config.getBoolean("slotClickDebug");
        //本次点击槽位序号
        int slotId = event.getId();
        //玩家背包
        PlayerInventory playerInventory = player.getInventory();
        //Baubles饰品
        EntityPlayer entityPlayer = (EntityPlayer) (Object) ((CraftPlayer) player).getHandle();
        IBaublesItemHandler baublesHandler = BaublesApi.getBaublesHandler(entityPlayer);
        BaublesInventoryWrapper baublesInventoryWrapper = new BaublesInventoryWrapper(baublesHandler, entityPlayer);
        //CosArmor护甲外饰
        CAStacksBase caStacks = CosArmorAPI.getCAStacks(player.getUniqueId());
        //准星物品
        ItemStack crossItem = vexGui.getCrossItem();
        //槽位物品
        ItemStack eventItem = event.getItem();
        if (bb == 1) {
            //========第一次事件触发========
            bb++;

            //事件触发不在玩家背包内
            if (!event.isInventory()) {

                if (debugMode) {
                    player.sendMessage("第一次-------");
                    player.sendMessage("槽位物品-" + event.getItem());
                    player.sendMessage("准星物品-" + vexGui.getCrossItem());
                }

                switch (slotId) {
                    case 0:
                        //校验头盔
                        if (crossItem != null && !VexViewBagUtils.isMatchType(crossItem, "HELMET")) {
                            event.setCancelled(true);
                        }
                        return;
                    case 1:
                    case 12:
                        if (crossItem != null && !VexViewBagUtils.isMatchType(crossItem, "CHESTPLATE")) {
                            event.setCancelled(true);
                        }
                        return;
                    case 2:
                    case 13:
                        if (crossItem != null && !VexViewBagUtils.isMatchType(crossItem, "LEGGINGS")) {
                            event.setCancelled(true);
                        }
                        return;
                    case 3:
                    case 14:
                        if (crossItem != null && !VexViewBagUtils.isMatchType(crossItem, "BOOTS")) {
                            event.setCancelled(true);
                        }
                        return;
                    case 4:
                        if (!baublesInventoryWrapper.func_94041_b(0, VexViewBagUtils.convertItem(crossItem)) && crossItem != null) {
                            event.setCancelled(true);
                        }
                        return;
                    case 5:
                        if (!baublesInventoryWrapper.func_94041_b(1, VexViewBagUtils.convertItem(crossItem)) && crossItem != null) {
                            event.setCancelled(true);
                        }
                        return;
                    case 6:
                        if (!baublesInventoryWrapper.func_94041_b(2, VexViewBagUtils.convertItem(crossItem)) && crossItem != null) {
                            event.setCancelled(true);
                        }
                        return;
                    case 7:
                        if (!baublesInventoryWrapper.func_94041_b(3, VexViewBagUtils.convertItem(crossItem)) && crossItem != null) {
                            event.setCancelled(true);
                        }
                        return;
                    case 8:
                        if (!baublesInventoryWrapper.func_94041_b(4, VexViewBagUtils.convertItem(crossItem)) && crossItem != null) {
                            event.setCancelled(true);
                        }
                        return;
                    case 9:
                        if (!baublesInventoryWrapper.func_94041_b(5, VexViewBagUtils.convertItem(crossItem)) && crossItem != null) {
                            event.setCancelled(true);
                        }
                        return;
                    case 10:
                        if (!baublesInventoryWrapper.func_94041_b(6, VexViewBagUtils.convertItem(crossItem)) && crossItem != null) {
                            event.setCancelled(true);
                        }
                        return;
                    case 11:
                        if (crossItem != null && !VexViewBagUtils.isMatchType(crossItem, "HELMET") && !VexViewBagUtils.isMatchType(crossItem, "SKULL") && !VexViewBagUtils.isMatchType(crossItem, "TROPHY")) {
                            event.setCancelled(true);
                        }
                        return;
                    case 15:
                }
            }
        } else {
            //========第二次事件触发========
            bb = 1;
            if (!event.isInventory()) {
                if (debugMode) {
                    player.sendMessage("第二次=======");
                    player.sendMessage("槽位物品-" + event.getItem());
                    player.sendMessage("准星物品-" + vexGui.getCrossItem());
                }

                switch (slotId) {
                    case 0:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "HELMET")) {
                            playerInventory.setHelmet(eventItem);
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 1:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "CHESTPLATE")) {
                            playerInventory.setChestplate(eventItem);
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 2:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "LEGGINGS")) {
                            playerInventory.setLeggings(eventItem);
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 3:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "BOOTS")) {
                            playerInventory.setBoots(eventItem);
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 4:
                        if (baublesInventoryWrapper.func_94041_b(0, VexViewBagUtils.convertItem(eventItem)) || eventItem == null) {
                            baublesInventoryWrapper.func_70299_a(0, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 5:
                        if (baublesInventoryWrapper.func_94041_b(1, VexViewBagUtils.convertItem(eventItem)) || eventItem == null) {
                            baublesInventoryWrapper.func_70299_a(1, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 6:
                        if (baublesInventoryWrapper.func_94041_b(2, VexViewBagUtils.convertItem(eventItem)) || eventItem == null) {
                            baublesInventoryWrapper.func_70299_a(2, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 7:
                        if (baublesInventoryWrapper.func_94041_b(3, VexViewBagUtils.convertItem(eventItem)) || eventItem == null) {
                            baublesInventoryWrapper.func_70299_a(3, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 8:
                        if (baublesInventoryWrapper.func_94041_b(4, VexViewBagUtils.convertItem(eventItem)) || eventItem == null) {
                            baublesInventoryWrapper.func_70299_a(4, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 9:
                        if (baublesInventoryWrapper.func_94041_b(5, VexViewBagUtils.convertItem(eventItem)) || eventItem == null) {
                            baublesInventoryWrapper.func_70299_a(5, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 10:
                        if (baublesInventoryWrapper.func_94041_b(6, VexViewBagUtils.convertItem(eventItem)) || eventItem == null) {
                            baublesInventoryWrapper.func_70299_a(6, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 11:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "HELMET") || VexViewBagUtils.isMatchType(eventItem, "SKULL") || VexViewBagUtils.isMatchType(eventItem, "TROPHY")) {
                            caStacks.setStackInSlot(3, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 12:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "CHESTPLATE")) {
                            caStacks.setStackInSlot(2, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 13:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "LEGGINGS")) {
                            caStacks.setStackInSlot(1, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 14:
                        if (eventItem == null || VexViewBagUtils.isMatchType(eventItem, "BOOTS")) {
                            caStacks.setStackInSlot(0, VexViewBagUtils.convertItem(eventItem));
                        } else {
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case 15:
                        playerInventory.setItemInOffHand(eventItem);
                        break;
                }
            }
        }

    }


}
