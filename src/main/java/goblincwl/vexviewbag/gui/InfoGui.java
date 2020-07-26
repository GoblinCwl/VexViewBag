package goblincwl.vexviewbag.gui;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.inv.BaublesInventoryWrapper;
import goblincwl.vexviewbag.VexViewBagUtils;
import lain.mods.cos.api.CosArmorAPI;
import lain.mods.cos.api.inventory.CAStacksBase;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexImage;
import lk.vexview.gui.components.VexPlayerDraw;
import lk.vexview.gui.components.VexSlot;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import src.jojobadv.Entities.EntityOneStand;
import src.jojobadv.ModBase.JojoBAdvLib;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description 玩家信息界面
 * @create 2020-07-05 21:09
 */
public class InfoGui extends VexGui {
    public InfoGui(Player player) {
        super(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/gui/infoGui.png",
                -1,
                -1,
                176,
                160
        );

        EntityPlayer entityPlayer = (EntityPlayer) (Object) ((CraftPlayer) player).getHandle();

        //原版背包
        PlayerInventory playerInventory = player.getInventory();
        this.addComponent(new VexSlot(0, 26, 7, playerInventory.getHelmet()));
        this.addComponent(new VexSlot(1, 26, 25, playerInventory.getChestplate()));
        this.addComponent(new VexSlot(2, 26, 43, playerInventory.getLeggings()));
        this.addComponent(new VexSlot(3, 26, 61, playerInventory.getBoots()));

        //Baubles饰品
        IBaublesItemHandler baublesHandler = BaublesApi.getBaublesHandler(entityPlayer);
        BaublesInventoryWrapper baublesInventoryWrapper = new BaublesInventoryWrapper(baublesHandler, entityPlayer);
        this.addComponent(new VexSlot(4, 95, 7, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(0))));
        this.addComponent(new VexSlot(5, 95, 25, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(1))));
        this.addComponent(new VexSlot(6, 95, 43, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(2))));
        this.addComponent(new VexSlot(7, 95, 61, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(3))));
        this.addComponent(new VexSlot(8, 114, 7, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(4))));
        this.addComponent(new VexSlot(9, 114, 25, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(5))));
        this.addComponent(new VexSlot(10, 114, 43, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(6))));

        //护甲外饰 isSkinArmor(slot) 查询隐藏  setSkinArmor(slot,true/false); 设置隐藏
        CAStacksBase caStacks = CosArmorAPI.getCAStacks(player.getUniqueId());
        this.addComponent(new VexSlot(11, 8, 7, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(3))));
        this.addComponent(new VexSlot(12, 8, 25, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(2))));
        this.addComponent(new VexSlot(13, 8, 43, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(1))));
        this.addComponent(new VexSlot(14, 8, 61, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(0))));

        //副手
        this.addComponent(new VexSlot(15, 114, 61, playerInventory.getItemInOffHand()));

        //JOJO替身
        String standImgUrl = null;
        EntityOneStand playerStand = JojoBAdvLib.getStand(entityPlayer);
        if (playerStand != null) {
            String standEngName = playerStand.getName();
            switch (standEngName) {
                case "Silver Chariot":
                case "银色战车":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Silver_Chariot.png";
                    break;
                case "Magician's Red":
                case "魔术师之红":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Magician's_Red.png";
                    break;
                case "Star Platinum":
                case "白金之星":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Star_Platinum.png";
                    break;
                case "The World":
                case "世界":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/The_World.png";
                    break;
                case "Gold Experience":
                case "黄金体验":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Gold_Experience.png";
                    break;
                case "Crazy Diamond":
                case "疯狂钻石":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Crazy_Diamond.png";
                    break;
                case "Echoes Egg":
                case "回音之卵":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Echoes_Egg.png";
                    break;
                case "Echoes Act1":
                case "回音Act1":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Echoes_Act1.png";
                    break;
                case "Killer Queen":
                case "杀手皇后":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/Killer_Queen.png";
                    break;
                case "The Hand":
                case "轰炸空间":
                    standImgUrl = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standPng/The_Hand.png";
                    break;
                default:
                    break;
            }
            if (!StringUtils.isEmpty(standImgUrl)) {
                //替身背景图
                this.addComponent(new VexImage(standImgUrl, 44, 7, 49, 70));
                //玩家3D模型
                this.addComponent(new VexPlayerDraw(76, 74, 30, player));
            } else {
                //玩家3d模型
                this.addComponent(new VexPlayerDraw(70, 74, 30, player));
            }
        } else {
            //玩家3d模型
            this.addComponent(new VexPlayerDraw(70, 74, 30, player));
        }
    }
}
