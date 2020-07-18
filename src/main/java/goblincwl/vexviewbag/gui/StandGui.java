package goblincwl.vexviewbag.gui;

import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.utils.VexViewBagUtils;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import src.jojobadv.Entities.EntityOneStand;
import src.jojobadv.Events.JojoBAdvPlayerCapabilities;
import src.jojobadv.ModBase.JojoBAdvLib;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author ☪wl
 * @program VexViewBag
 * @description 替身属性界面
 * @create 2020-07-03 9:10
 */
public class StandGui extends VexGui {

    public StandGui(Player player) throws NoSuchFieldException, IllegalAccessException {
        super(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standGui1.png",
                -1,
                -1,
                201,
                121
        );


        EntityPlayer entityPlayer = (EntityPlayer) (Object) ((CraftPlayer) player).getHandle();
        //获取玩家替身capsnbt数据
        JojoBAdvPlayerCapabilities jojoCap = getJojoCapInstance(entityPlayer);
        if (jojoCap != null) {
            //替身属性值
            int[] standStatArray = jojoCap.getStandStatArray();
            int playerStandLvl = jojoCap.getPlayerStandLvl();

            //JOJO替身
            String standImgUrl = null;
            String standName = "§7§l未觉醒替身";
            EntityOneStand playerStand = JojoBAdvLib.getStand(entityPlayer);
            if (playerStand != null) {
                String standEngName = playerStand.getName();
                switch (standEngName) {
                    case "Silver Chariot":
                    case "银色战车":
                        standName = "§7§l银色战车";
                        standImgUrl = "[local]Custom/PlayerInfo/银色战车.png";
                        break;
                    case "Magician's Red":
                    case "魔术师之红":
                        standName = "§c§l红色魔术师";
                        standImgUrl = "[local]Custom/PlayerInfo/红色魔术师.png";
                        break;
                    case "Star Platinum":
                    case "白金之星":
                        standName = "§b§l白金之星";
                        standImgUrl = "[local]Custom/PlayerInfo/白金之星.png";
                        break;
                    case "The World":
                    case "世界":
                        standName = "§e§l世界";
                        standImgUrl = "[local]Custom/PlayerInfo/世界.png";
                        break;
                    case "Gold Experience":
                    case "黄金体验":
                        standName = "§6§l黄金体验";
                        standImgUrl = "[local]Custom/PlayerInfo/黄金体验.png";
                        break;
                    case "Crazy Diamond":
                    case "疯狂钻石":
                        standName = "§d§l疯狂钻石";
                        standImgUrl = "[local]Custom/PlayerInfo/疯狂钻石.png";
                        break;
                    case "Echoes Egg":
                    case "回音之卵":
                        standName = "§a§l回音之卵";
                        standImgUrl = "[local]Custom/PlayerInfo/回音之卵.png";
                        break;
                    case "Echoes Act1":
                    case "回音Act1":
                        standName = "§a§l回音Act1";
                        standImgUrl = "[local]Custom/PlayerInfo/回音Act1.png";
                        break;
                    case "Killer Queen":
                    case "杀手皇后":
                        standName = "§d§l杀手皇后";
                        standImgUrl = "[local]Custom/PlayerInfo/杀手皇后.png";
                        break;
                    case "The Hand":
                    case "轰炸空间":
                        standName = "§1§l轰炸空间";
                        standImgUrl = "[local]Custom/PlayerInfo/轰炸空间.png";
                        break;
                    default:
                        standName = "未觉醒替身";
                        break;
                }

                if (!"未觉醒替身".equals(standName)) {
                    //替身图片
                    this.addComponent(new VexImage(standImgUrl, 8, 8, 70, 104));

                    //玩家3D渲染图
                    this.addComponent(new VexPlayerDraw(54, 106, 45, player));

                    ArrayList<String> standPowerNumAry;
                    standPowerNumAry = new ArrayList<>();
                    standPowerNumAry.add("§c§l破坏力            " + standStatArray[0]);
                    this.addComponent(new VexText(98, 31, standPowerNumAry, 1));
                    String finalStandName = standName;
                    this.addComponent(new VexButton(
                            "standMenuPohuai",
                            "+",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn.png",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn_.png",
                            86,
                            31,
                            10,
                            10,
                            player12 -> {
                                try {
                                    standAddPoint(0, player12, finalStandName, "§c§l破坏力");
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }));

                    standPowerNumAry = new ArrayList<>();
                    standPowerNumAry.add("§b§l速度值            " + standStatArray[1]);
                    this.addComponent(new VexText(98, 44, standPowerNumAry, 1));
                    this.addComponent(new VexButton(
                            "standMenuSudu",
                            "+",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn.png",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn_.png",
                            86,
                            44,
                            10,
                            10,
                            player12 -> {
                                try {
                                    standAddPoint(1, player12, finalStandName, "§b§l速度值");
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }));

                    standPowerNumAry = new ArrayList<>();
                    standPowerNumAry.add("§6§l射程值            " + standStatArray[2]);
                    this.addComponent(new VexText(98, 57, standPowerNumAry, 1));
                    this.addComponent(new VexButton(
                            "standMenuShecheng",
                            "+",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn.png",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn_.png",
                            86,
                            57,
                            10,
                            10,
                            player12 -> {
                                try {
                                    standAddPoint(2, player12, finalStandName, "§6§l射程值");
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }));

                    standPowerNumAry = new ArrayList<>();
                    standPowerNumAry.add("§a§l持续力            " + standStatArray[3]);
                    this.addComponent(new VexText(98, 70, standPowerNumAry, 1));
                    this.addComponent(new VexButton(
                            "standMenuChixu",
                            "+",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn.png",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn_.png",
                            86,
                            70,
                            10,
                            10,
                            player12 -> {
                                try {
                                    standAddPoint(3, player12, finalStandName, "§a§l持续力");
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }));

                    standPowerNumAry = new ArrayList<>();
                    standPowerNumAry.add("§d§l精密度            " + standStatArray[4]);
                    this.addComponent(new VexText(98, 83, standPowerNumAry, 1));
                    this.addComponent(new VexButton(
                            "standMenuJingmi",
                            "+",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn.png",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standAddPointBtn_.png",
                            86,
                            83,
                            10,
                            10,
                            player12 -> {
                                try {
                                    standAddPoint(4, player12, finalStandName, "§d§l精密度");
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }));

                    //成长之力
                    standPowerNumAry = new ArrayList<>();
                    standPowerNumAry.add("§e§l+" + standStatArray[5]);
                    ArrayList<String> czHovList = new ArrayList<>();
                    czHovList.add("§e成长之力");
                    czHovList.add("§7§o消耗其加点");
                    this.addComponent(new VexText(90, 101, standPowerNumAry, 1.2, new VexHoverText(czHovList), 1));

                    //升级按钮
                    VexButton standLevelUpBtn = new VexButton(
                            "standMenuLevelUp",
                            "§9§l升级",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standLevelUpBtn.png",
                            "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/standMain/standLevelUpBtn_.png",
                            114,
                            99,
                            78,
                            15,
                            player1 -> {
                                JojoBAdvPlayerCapabilities jojoCapInstance;
                                try {
                                    jojoCapInstance = getJojoCapInstance((EntityPlayer) (Object) ((CraftPlayer) player1).getHandle());
                                    int playerStandLvl1 = jojoCapInstance.getPlayerStandLvl();
                                    if (playerStandLvl1 >= 100) {
                                        player1.sendMessage(VexViewBag.messagePrefix + ChatColor.RED + "你的" + finalStandName + ChatColor.RED + "已经无法再继续提升了！");

                                    } else {
                                        if (!player1.getInventory().contains(Material.getMaterial("CUSTOMMC_ITEM202"), playerStandLvl1)) {
                                            player1.sendMessage("§c[§7系统§c]§c你需要§e" + playerStandLvl1 + "个§c精神之瓶才可以升级替身!");
                                        } else {
                                            VexViewBagUtils.consumePlayerItem(player1, new ItemStack(Material.getMaterial("CUSTOMMC_ITEM202")), playerStandLvl1);
                                            //增加等级+1
                                            jojoCapInstance.setPlayerStandLvl(playerStandLvl + 1);
                                            //成长之力+1
                                            int[] standStatArray1 = jojoCapInstance.getStandStatArray();
                                            standStatArray1[5] = standStatArray1[5] + 1;
                                            jojoCapInstance.setStandStatArray(standStatArray1);
                                            player1.sendMessage("§a[§7系统§a]§6替身等级§a+1§6,§d成长之力§a+1,当前等级§5[§b" + (playerStandLvl1 + 1) + "§5].");
                                            VexViewAPI.openGui(player1, new StandGui(player1));
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                    this.addComponent(standLevelUpBtn);
                }
            }
            //替身等级
            ArrayList<String> standLevelAry = new ArrayList<>();
            standLevelAry.add(standName.substring(0, 2) + "Lv:" + playerStandLvl);
            this.addComponent(new VexText(91, 15, standLevelAry, 1));

            //替身名称
            ArrayList<String> standNameAry = new ArrayList<>();
            standNameAry.add(standName);
            int standNameX;
            switch (standName.length() - 4) {
                case 2:
                    standNameX = 150;
                    break;
                case 5:
                    standNameX = 115;
                    break;
                default:
                    standNameX = 128;
                    break;
            }
            this.addComponent(new VexText(standNameX, 13, standNameAry, 1.2));
        }
    }

    /**
     * 获取JOJOCapnbt
     *
     * @param entityPlayer 玩家对象(n.m.xx)
     * @return src.jojobadv.Events.JojoBAdvPlayerCapabilities
     * @create 2020/7/3 10:35
     * @author ☪wl
     */
    private JojoBAdvPlayerCapabilities getJojoCapInstance(EntityPlayer entityPlayer) throws NoSuchFieldException, IllegalAccessException {
        Class<?> netMinecraftEntityClass = entityPlayer.getClass().getSuperclass().getSuperclass();
        Field capabilities = netMinecraftEntityClass.getField("capabilities");
        CapabilityDispatcher capabilityDispatcher = (CapabilityDispatcher) capabilities.get(entityPlayer);
        Field caps = capabilityDispatcher.getClass().getDeclaredField("caps");
        caps.setAccessible(true);
        ICapabilityProvider[] capabilityProviders = (ICapabilityProvider[]) caps.get(capabilityDispatcher);
        ICapabilityProvider iCapabilityProvider = null;
        for (ICapabilityProvider capabilityProvider : capabilityProviders) {
            //捕获JOJO的数据
            if (capabilityProvider.getClass().getName().equals("src.jojobadv.Events.JojoBAdvProvider")) {
                iCapabilityProvider = capabilityProvider;
                break;
            }
        }
        if (iCapabilityProvider != null) {
            Field instanceFiled = iCapabilityProvider.getClass().getDeclaredField("instance");
            instanceFiled.setAccessible(true);
            return (JojoBAdvPlayerCapabilities) instanceFiled.get(iCapabilityProvider);
        } else {
            return null;
        }
    }

    /**
     * 添加替身属性点
     *
     * @param index     属性点数组下标
     * @param player    玩家
     * @param standName 替身名称
     * @param pointName 属性点名称
     * @return void
     * @create 2020/7/3 10:44
     * @author ☪wl
     */
    private void standAddPoint(Integer index, Player player, String standName, String pointName) throws NoSuchFieldException, IllegalAccessException {
        JojoBAdvPlayerCapabilities jojoCapInstance = getJojoCapInstance((EntityPlayer) (Object) ((CraftPlayer) player).getHandle());
        assert jojoCapInstance != null;
        int[] standStatArray = jojoCapInstance.getStandStatArray();
        if (standStatArray[5] < 1) {
            player.sendMessage(VexViewBag.messagePrefix + ChatColor.RED + "你至少需要1点" + ChatColor.YELLOW + "成长之力" + ChatColor.RED + "才可以加点!");
        } else {
            if (standStatArray[index] >= 99) {
                player.sendMessage(VexViewBag.messagePrefix + standName + ChatColor.YELLOW + "的" + pointName + ChatColor.YELLOW + "已经达到瓶颈了!");
                return;
            }
            standStatArray[index] = standStatArray[index] + 1;
            standStatArray[5] = standStatArray[5] - 1;
            jojoCapInstance.setStandStatArray(standStatArray);
            player.sendMessage(VexViewBag.messagePrefix + standName + pointName + ChatColor.BLUE + "+1.");
            //加完点后重新打开gui
            VexViewAPI.openGui(player, new StandGui(player));
        }
    }
}
