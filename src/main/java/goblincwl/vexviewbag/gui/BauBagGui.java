package goblincwl.vexviewbag.gui;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.inv.BaublesInventoryWrapper;
import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.VexViewBagUtils;
import lain.mods.cos.api.CosArmorAPI;
import lain.mods.cos.api.inventory.CAStacksBase;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexInventoryGui;
import lk.vexview.gui.components.*;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import src.jojobadv.Entities.EntityOneStand;
import src.jojobadv.ModBase.JojoBAdvLib;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ☪wl
 * @program VexViewBag
 * @description
 * @create 2020-07-02 12:31
 */
public class BauBagGui extends VexInventoryGui {


    public BauBagGui(Player player) {

        super(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/gui/bauBagGui.png",
                -1,
                -1,
                176,
                161,
                176,
                161,
                8,
                84
        );


        EntityPlayer entityPlayer = (EntityPlayer) (Object) ((CraftPlayer) player).getHandle();

        //玩家3d模型
        this.addComponent(new VexPlayerDraw(52, 76, 30, player));

        //Baubles饰品
        IBaublesItemHandler baublesHandler = BaublesApi.getBaublesHandler(entityPlayer);
        BaublesInventoryWrapper baublesInventoryWrapper = new BaublesInventoryWrapper(baublesHandler, entityPlayer);
        this.addComponent(new VexSlot(0, 77, 8, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(0))));
        this.addComponent(new VexSlot(1, 77, 26, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(1))));
        this.addComponent(new VexSlot(2, 77, 44, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(2))));
        this.addComponent(new VexSlot(3, 77, 62, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(3))));
        this.addComponent(new VexSlot(4, 96, 8, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(4))));
        this.addComponent(new VexSlot(5, 96, 26, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(5))));
        this.addComponent(new VexSlot(6, 96, 44, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(6))));

        //护甲外饰 isSkinArmor(slot) 查询隐藏  setSkinArmor(slot,true/false); 设置隐藏
        CAStacksBase caStacks = CosArmorAPI.getCAStacks(player.getUniqueId());
        this.addComponent(new VexSlot(7, 8, 8, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(3))));
        this.addComponent(new VexSlot(8, 8, 26, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(2))));
        this.addComponent(new VexSlot(9, 8, 44, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(1))));
        this.addComponent(new VexSlot(10, 8, 62, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(0))));

        //副手
        this.addComponent(new VexSlot(11, 96, 62, player.getInventory().getItemInOffHand()));
    }
}
