package goblincwl.vexviewbag.gui;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.inv.BaublesInventoryWrapper;
import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.utils.VexViewBagUtils;
import lain.mods.cos.api.CosArmorAPI;
import lain.mods.cos.api.inventory.CAStacksBase;
import lk.vexview.gui.VexInventoryGui;
import lk.vexview.gui.components.*;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import src.jojobadv.Entities.EntityOneStand;
import src.jojobadv.ModBase.JojoBAdvLib;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

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
        playerImgText.add(ChatColor.AQUA + player.getName() + "的头像");
        playerImgText.add(ChatColor.GRAY + "§o仅读取LittleSkin皮肤站的皮肤");
        playerImgText.add(ChatColor.GRAY + "§b=========================");
        VexImage playerImg = new VexImage(
                "https://map.goblincwl.cn/tiles/faces/32x32/" + player.getName() + ".png",
                818,
                472,
                49,
                48,
                new VexHoverText(playerImgText)
        );
        this.addComponent(playerImg);

//        仪表盘按钮
        File fileLeft = new File(instance.getDataFolder(), "button.yml");
        YamlConfiguration buttonLeftConfig = YamlConfiguration.loadConfiguration(fileLeft);
        Set<String> buttonLeftKeys = buttonLeftConfig.getKeys(false);
        for (String key : buttonLeftKeys) {
            //要执行的指令
            String command = buttonLeftConfig.getString(key + ".command");
            //替换玩家符
            command = command.replaceAll("%player%", player.getName());
            String finalCommand = command;
            ArrayList<String> btnText = new ArrayList<>();
            btnText.add(buttonLeftConfig.getString(key + ".lore"));
            VexButton btn = new VexButton(
                    key,
                    buttonLeftConfig.getString(key + ".name"),
                    buttonLeftConfig.getString(key + ".url1"),
                    buttonLeftConfig.getString(key + ".url2"),
                    buttonLeftConfig.getInt(key + ".x"),
                    buttonLeftConfig.getInt(key + ".y"),
                    buttonLeftConfig.getInt(key + ".w"),
                    buttonLeftConfig.getInt(key + ".h"),
                    buttonLeftConfig.getBoolean(key + ".opCommand")
                            ?
                            (ButtonFunction) inPlayer -> {
                                if (StringUtils.isNotEmpty(finalCommand)) {
                                    VexViewBagUtils.playerOpCommand(inPlayer, finalCommand);
                                }
                            }
                            :
                            (ButtonFunction) inPlayer -> {
                                if (StringUtils.isNotEmpty(finalCommand)) {
                                    inPlayer.chat("/" + finalCommand);
                                }
                            },
                    new VexHoverText(btnText)
            );
            this.addComponent(btn);
        }

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
