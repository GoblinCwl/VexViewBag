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
public class BagGui extends VexInventoryGui {


    public BagGui(Player player) {

        super(
                VexViewBag.getInstance().getConfig().getString("mainGui.url"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.x"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.y"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.w"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.h"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.xs"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.ys"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.slotLeft"),
                VexViewBag.getInstance().getConfig().getInt("mainGui.slotRight")
        );


        EntityPlayer entityPlayer = (EntityPlayer) (Object) ((CraftPlayer) player).getHandle();

        VexViewBag instance = VexViewBag.getInstance();

        //玩家头像
        ArrayList<String> playerImgText = new ArrayList<>();
        playerImgText.add("§a§l" + player.getName() + "§b的头像");
        playerImgText.add(ChatColor.GRAY + "§o(仅读取LittleSkin皮肤站的皮肤)");
        playerImgText.add("§b§m§l                           ");
        VexImage playerImg = new VexImage(
                "https://map.goblincwl.cn/tiles/faces/32x32/" + player.getName() + ".png",
                818,
                472,
                49,
                48,
                new VexHoverText(playerImgText)
        );
        this.addComponent(playerImg);

        //左侧按钮
        //会员
        this.addComponent(new VexButton(
                "vipBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/vipBtn_None.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/vipBtn_None.png",
                812,
                528,
                20,
                20,
                inPlayer -> {

                },
                new VexHoverText(Collections.singletonList("§c非会员"))
        ));

        //称号
        this.addComponent(new VexButton(
                "chBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/chBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/chBtn_.png",
                832,
                528,
                20,
                20,
                inPlayer -> {
                    VexViewBagUtils.playerOpCommand(inPlayer, "ch open");
                },
                new VexHoverText(Collections.singletonList("§a称号"))
        ));

        //替身
        this.addComponent(new VexButton(
                "standBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/standBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/standBtn_.png",
                852,
                528,
                20,
                20,
                inPlayer -> {
                    VexViewAPI.openGui(inPlayer, new StandGui(inPlayer));
                },
                new VexHoverText(Collections.singletonList("§a替身"))
        ));

        //活跃
        this.addComponent(new VexButton(
                "activeBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/infoBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/infoBtn_.png",
                812,
                548,
                20,
                20,
                inPlayer -> {

                },
                new VexHoverText(Collections.singletonList("§6活跃"))
        ));
        //任务
        this.addComponent(new VexButton(
                "taskBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/signBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/signBtn_.png",
                832,
                548,
                20,
                20,
                inPlayer -> {
                },
                new VexHoverText(Collections.singletonList("§e任务"))
        ));
        //邮箱
        this.addComponent(new VexButton(
                "mailBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/mailBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/serverMain/mailBtn_.png",
                852,
                548,
                20,
                20,
                inPlayer -> {
                    inPlayer.chat("/mb");
                },
                new VexHoverText(Collections.singletonList("§b邮箱"))
        ));

        //右侧按钮
        //传送
        this.addComponent(new VexButton(
                "tpBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/tpBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/tpBtn_.png",
                1048,
                540,
                20,
                20,
                inPlayer -> {
                    VexViewBagUtils.playerOpCommand(inPlayer, "opengui " + player.getName() + " main-gui/tpMain-gui");
                },
                new VexHoverText(Collections.singletonList("§d传送"))
        ));
        //商店
        this.addComponent(new VexButton(
                "shopBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/shopBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/shopBtn_.png",
                1068,
                540,
                20,
                20,
                inPlayer -> {
                    VexViewAPI.openGui(inPlayer, new ShopGui());
                },
                new VexHoverText(Collections.singletonList("§b商店"))
        ));
        //活动
        this.addComponent(new VexButton(
                "partyBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/partyBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/partyBtn_.png",
                1088,
                540,
                20,
                20,
                inPlayer -> {
                },
                new VexHoverText(Collections.singletonList("§e活动"))
        ));
        //工作台
        this.addComponent(new VexButton(
                "craftBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/craftBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/craftBtn_.png",
                1048,
                560,
                20,
                20,
                inPlayer -> {
                    VexViewBagUtils.playerOpCommand(inPlayer, "craft");
                },
                new VexHoverText(Collections.singletonList("§6工作台"))
        ));
        //末影箱
        this.addComponent(new VexButton(
                "endChestBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/enderChestBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/enderChestBtn_.png",
                1068,
                560,
                20,
                20,
                inPlayer -> {
                    inPlayer.chat("/ec open");
                },
                new VexHoverText(Collections.singletonList("§9末影箱"))
        ));

        //原版背包
        PlayerInventory playerInventory = player.getInventory();
        this.addComponent(new VexSlot(0, 904, 467, playerInventory.getHelmet()));
        this.addComponent(new VexSlot(1, 904, 485, playerInventory.getChestplate()));
        this.addComponent(new VexSlot(2, 904, 503, playerInventory.getLeggings()));
        this.addComponent(new VexSlot(3, 904, 521, playerInventory.getBoots()));

        //Baubles饰品
        IBaublesItemHandler baublesHandler = BaublesApi.getBaublesHandler(entityPlayer);
        BaublesInventoryWrapper baublesInventoryWrapper = new BaublesInventoryWrapper(baublesHandler, entityPlayer);
        this.addComponent(new VexSlot(4, 973, 467, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(0))));
        this.addComponent(new VexSlot(5, 973, 485, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(1))));
        this.addComponent(new VexSlot(6, 973, 503, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(2))));
        this.addComponent(new VexSlot(7, 973, 521, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(3))));
        this.addComponent(new VexSlot(8, 992, 467, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(4))));
        this.addComponent(new VexSlot(9, 992, 485, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(5))));
        this.addComponent(new VexSlot(10, 992, 503, VexViewBagUtils.convertItemNM(baublesInventoryWrapper.func_70301_a(6))));

        //护甲外饰 isSkinArmor(slot) 查询隐藏  setSkinArmor(slot,true/false); 设置隐藏
        CAStacksBase caStacks = CosArmorAPI.getCAStacks(player.getUniqueId());
        this.addComponent(new VexSlot(11, 886, 467, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(3))));
        this.addComponent(new VexSlot(12, 886, 485, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(2))));
        this.addComponent(new VexSlot(13, 886, 503, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(1))));
        this.addComponent(new VexSlot(14, 886, 521, VexViewBagUtils.convertItemNM(caStacks.getStackInSlot(0))));

        //副手
        this.addComponent(new VexSlot(15, 992, 521, playerInventory.getItemInOffHand()));

        //公告
        ArrayList<String> bcText = new ArrayList<>();
        bcText.add("这是服务器公告");
        bcText.add("服务器还在完善");
        bcText.add("欢迎多提出建议");
        this.addComponent(new VexText(1018, 472, bcText, 1));

        //阵营
        ArrayList<String> flagText = new ArrayList<>();
        flagText.add("§c无阵营");
        this.addComponent(new VexImage(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/playerMain/flag.png",
                1082,
                460,
                21,
                41,
                new VexHoverText(flagText)
        ));

        //JOJO替身
        String standImgUrl = null;
        EntityOneStand playerStand = JojoBAdvLib.getStand(entityPlayer);
        if (playerStand != null) {
            String standEngName = playerStand.getName();
            switch (standEngName) {
                case "Silver Chariot":
                case "银色战车":
                    standImgUrl = "[local]Custom/PlayerInfo/银色战车.png";
                    break;
                case "Magician's Red":
                case "魔术师之红":
                    standImgUrl = "[local]Custom/PlayerInfo/红色魔术师.png";
                    break;
                case "Star Platinum":
                case "白金之星":
                    standImgUrl = "[local]Custom/PlayerInfo/白金之星.png";
                    break;
                case "The World":
                case "世界":
                    standImgUrl = "[local]Custom/PlayerInfo/世界.png";
                    break;
                case "Gold Experience":
                case "黄金体验":
                    standImgUrl = "[local]Custom/PlayerInfo/黄金体验.png";
                    break;
                case "Crazy Diamond":
                case "疯狂钻石":
                    standImgUrl = "[local]Custom/PlayerInfo/疯狂钻石.png";
                    break;
                case "Echoes Egg":
                case "回音之卵":
                    standImgUrl = "[local]Custom/PlayerInfo/回音之卵.png";
                    break;
                case "Echoes Act1":
                case "回音Act1":
                    standImgUrl = "[local]Custom/PlayerInfo/回音Act1.png";
                    break;
                case "Killer Queen":
                case "杀手皇后":
                    standImgUrl = "[local]Custom/PlayerInfo/杀手皇后.png";
                    break;
                case "The Hand":
                case "轰炸空间":
                    standImgUrl = "[local]Custom/PlayerInfo/轰炸空间.png";
                    break;
                default:
                    break;
            }
            if (!StringUtils.isEmpty(standImgUrl)) {
                //替身背景图
                this.addComponent(new VexImage(standImgUrl, 922, 466, 49, 70));
                //玩家3D模型
                this.addComponent(new VexPlayerDraw(953, 532, 30, player));
            } else {
                //玩家3d模型
                this.addComponent(new VexPlayerDraw(947, 532, 30, player));
            }
        } else {
            //玩家3d模型
            this.addComponent(new VexPlayerDraw(947, 532, 30, player));
        }
    }
}
