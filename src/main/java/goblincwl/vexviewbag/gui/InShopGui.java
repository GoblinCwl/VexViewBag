package goblincwl.vexviewbag.gui;

import com.tripleying.qwq.MailBox.API.MailBoxAPI;
import com.tripleying.qwq.MailBox.Mail.BaseFileMail;
import com.tripleying.qwq.MailBox.Mail.PlayerFileMail;
import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.VexViewBagPlayer;
import goblincwl.vexviewbag.VexViewBagUtils;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Score;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description 商店界面
 * @create 2020-07-05 21:09
 */
public class InShopGui extends VexGui {
    public InShopGui(String ymlName, String emailSender, String emailTitle) {
        super(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/inShop.png",
                -1,
                -1,
                318,
                203
        );

        YamlConfiguration shopConfig = (YamlConfiguration) VexViewBag.configurationMap.get(ymlName + ".yml");
        Set<String> keys = shopConfig.getKeys(false);

        int row = 1;
        int colum = 1;

        for (int i = 0; i < keys.size(); i++) {
            //商品
            ItemStack itemStack = shopConfig.getItemStack(i + ".item");
            //两种价格
            String[] money1 = shopConfig.getString(i + ".money1").split(":");
            String[] money2 = shopConfig.getString(i + ".money2").split(":");

            int imgX = 9 + (colum - 1) * 60;
            int imgY = 9 + (row - 1) * 46;

            //打底图片
            this.addComponent(new VexImage(
                    "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/shopItem.png",
                    imgX,
                    imgY,
                    60,
                    46
            ));

            //物品槽
            this.addComponent(new VexSlot(
                    i,
                    imgX + 7,
                    imgY + 7,
                    itemStack
            ));

            //数量输入框
            VexTextField vexInput = new VexTextField(
                    imgX + 4,
                    imgY + 29,
                    15,
                    10,
                    2,
                    i,
                    "1"
            );
            this.addComponent(vexInput);

            //货币1
            Map<String, String> moneyTyp1Map = moneyTypePick(money1[0]);
            String money1Img = moneyTyp1Map.get("moneyImg");
            String money1Prefix = moneyTyp1Map.get("moneyPrefix");
            String money1Hov = moneyTyp1Map.get("moneyHov");
            if (!money1Img.equals("0")) {
                //图标
                this.addComponent(new VexImage(
                        money1Img,
                        imgX + 29,
                        imgY + 6,
                        8,
                        8,
                        new VexHoverText(Collections.singletonList(money1Hov))
                ));
                this.addComponent(new VexText(
                        imgX + 40,
                        imgY + 5,
                        Collections.singletonList(money1Prefix + money1[1]),
                        1
                ));
            }

            //货币2
            Map<String, String> moneyTyp2Map = moneyTypePick(money2[0]);
            String money2Img = moneyTyp2Map.get("moneyImg");
            String money2Prefix = moneyTyp2Map.get("moneyPrefix");
            String money2Hov = moneyTyp2Map.get("moneyHov");
            if (!money2Img.equals("0")) {
                //图标
                this.addComponent(new VexImage(
                        money2Img,
                        imgX + 29,
                        imgY + 16,
                        8,
                        8,
                        new VexHoverText(Collections.singletonList(money2Hov))
                ));
                this.addComponent(new VexText(
                        imgX + 40,
                        imgY + 15,
                        Collections.singletonList(money2Prefix + money2[1]),
                        1
                ));
            }

            VexButton vexBuyBtn = new VexButton(
                    i,
                    "购买",
                    "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/buyButton.png",
                    "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/buyButton_.png",
                    imgX + 22,
                    imgY + 28,
                    33,
                    12,
                    player -> {
                        ArrayList<String[]> moneyList = new ArrayList<>(Arrays.asList(money1, money2));
                        int buyAmount = Integer.parseInt(vexInput.getTypedText());
                        if (checkMoney(player, moneyList, buyAmount)) {
                            if (consumeMoney(player, moneyList, buyAmount)) {
                                player.sendMessage(VexViewBag.messagePrefix + "§a购买成功!");
                                String emailContext;
                                if ("0".equals(money2[0])) {
                                    emailContext = "§e尊敬的§b" + player.getName() + "§e， §a这是购买的物品. §c您一共消费了: " + money1Hov + "：" + Double.parseDouble(money1[1]) * buyAmount + " §e欢迎下次光临";
                                } else {
                                    emailContext = "§e尊敬的§b" + player.getName() + "§e， §a这是购买的物品. §c您一共消费了: " + money1Hov + "：" + Double.parseDouble(money1[1]) + " " + money2Hov + "：" + Double.parseDouble(money2[1]) + " §e欢迎下次光临";
                                }
                                BaseFileMail mail = MailBoxAPI.createBaseFileMail("player", emailSender, emailTitle, emailContext, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                                itemStack.setAmount(buyAmount);
                                mail.setItemList(Collections.singletonList(itemStack));
                                PlayerFileMail playerFileMail = (PlayerFileMail) mail;
                                playerFileMail.setRecipient(Collections.singletonList(player.getName()));
                                playerFileMail.Send(Bukkit.getConsoleSender(), null);
                            }
                        } else {
                            player.sendMessage(VexViewBag.messagePrefix + "§c购买失败，余额不足!");
                        }
                    }
            );

            this.addComponent(vexBuyBtn);

            colum++;
            if (colum > 5) {
                colum = 1;
                row++;
            }
        }

    }

    /**
     * 筛选货币类型
     *
     * @param moneyType 货币类型代码
     * @return com.google.gson.JsonObject
     * @create 2020/7/5 22:04
     * @author ☪wl
     */
    private Map<String, String> moneyTypePick(String moneyType) {
        Map<String, String> dataMap = new HashMap<>();

        String moneyImg;
        String moneyPrefix;
        String moneyHov;

        switch (moneyType) {
            case "1":
                //绿宝石
                moneyImg = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/emerald.png";
                moneyPrefix = "§a";
                moneyHov = moneyPrefix + "绿宝石";
                break;
            case "2":
                moneyImg = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/amethyst.png";
                moneyPrefix = "§d";
                moneyHov = moneyPrefix + "紫晶石";
                break;
            case "3":
                moneyImg = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/huoyuezhi.png";
                moneyPrefix = "§6";
                moneyHov = moneyPrefix + "活跃值";
                break;
            case "4":
                moneyImg = "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/jingyingfencheng.png";
                moneyPrefix = "§b";
                moneyHov = moneyPrefix + "精英粉尘";
                break;
            default:
                moneyImg = "0";
                moneyPrefix = "";
                moneyHov = "";
                break;
        }
        dataMap.put("moneyImg", moneyImg);
        dataMap.put("moneyPrefix", moneyPrefix);
        dataMap.put("moneyHov", moneyHov);

        return dataMap;
    }

    private Boolean checkMoney(Player player, List<String[]> moneyList, Integer amount) {
        for (String[] money : moneyList) {
            double moneyCount = Double.parseDouble(money[1]) * amount;
            switch (money[0]) {
                case "1":
                    Economy economy = VexViewBag.economy;
                    double balance = economy.getBalance(player);
                    if (balance < moneyCount) {
                        return false;
                    }
                    break;
                case "2":
                    PlayerPointsAPI playerPointsAPI = VexViewBag.playerPointsAPI;
                    int points = playerPointsAPI.look(player.getUniqueId());
                    if (points < moneyCount) {
                        return false;
                    }
                    break;
                case "3":
                    try {
                        //玩家数据文件
                        //玩家数据
                        VexViewBagPlayer vexViewBagPlayer = VexViewBag.mySqlManager.selectData(player.getUniqueId().toString());
                        if (vexViewBagPlayer == null) {
                            vexViewBagPlayer = VexViewBag.mySqlManager.insertData(player.getUniqueId().toString());
                        }
                        long activePoint = vexViewBagPlayer.getActivePoint();
                        if (activePoint < moneyCount) {
                            return false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    if (!player.getInventory().contains(Material.getMaterial("CUSTOMMC_ITEM9"), (int) moneyCount)) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private Boolean consumeMoney(Player player, List<String[]> moneyList, Integer amount) {
        for (String[] money : moneyList) {
            double moneyCount = Double.parseDouble(money[1]) * amount;
            switch (money[0]) {
                case "1":
                    Economy economy = VexViewBag.economy;
                    economy.withdrawPlayer(player, moneyCount);
                    break;
                case "2":
                    PlayerPointsAPI playerPointsAPI = VexViewBag.playerPointsAPI;
                    playerPointsAPI.take(player.getUniqueId(), (int) moneyCount);
                    break;
                case "3":
                    //玩家数据文件
                    try {
                        //玩家数据
                        VexViewBagPlayer vexViewBagPlayer = VexViewBag.mySqlManager.selectData(player.getUniqueId().toString());
                        if (vexViewBagPlayer == null) {
                            vexViewBagPlayer = VexViewBag.mySqlManager.insertData(player.getUniqueId().toString());
                        }
                        long activePoint = vexViewBagPlayer.getActivePoint();
                        vexViewBagPlayer.setActivePoint((long) (activePoint - moneyCount));
                        VexViewBag.mySqlManager.updateActivePoint(vexViewBagPlayer.getPlayerUUID(), vexViewBagPlayer.getActivePoint());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    VexViewBagUtils.consumePlayerItem(player, new ItemStack(Material.getMaterial("CUSTOMMC_ITEM9")), (int) moneyCount);
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
