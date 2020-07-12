package goblincwl.vexviewbag.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.utils.VexViewBagUtils;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.OpenedVexGui;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description 商店界面
 * @create 2020-07-05 21:09
 */
public class InShopGui extends VexGui {
    public InShopGui(String ymlName) {
        super(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/inShop.png",
                -1,
                -1,
                318,
                203
        );

        VexViewBag instance = VexViewBag.getInstance();

        File shopConfigFile = new File(instance.getDataFolder(), ymlName + ".yml");
        YamlConfiguration shopConfig = YamlConfiguration.loadConfiguration(shopConfigFile);
        Set<String> keys = shopConfig.getKeys(false);

        int row = 1;
        int colum = 1;

        VexButton[] buttonArray = new VexButton[keys.size()];
        VexTextField[] inputArray = new VexTextField[keys.size()];

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
            inputArray[i] = vexInput;

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
                        new VexHoverText(VexViewBagUtils.oneRowAryList(money1Hov))
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
                        new VexHoverText(VexViewBagUtils.oneRowAryList(money2Hov))
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
                    12
            );

            this.addComponent(vexBuyBtn);
            buttonArray[i] = vexBuyBtn;

            colum++;
            if (colum > 5) {
                colum = 1;
                row++;
            }
        }

    }

    //购买物品的方法
/*    private void buyItem(ItemStack bukkitItem, String money1Type, String money2Type, Double money1, Double money2, Player player, Integer count, String shopMailSender, String shopMailTopic) {
        OpenedVexGui playerCurrentGui = VexViewAPI.getPlayerCurrentGui(player);

        money1 = money1 * count;
        money2 = money2 * count;
        var chekcMoney1 = chekcMoney(money1Type, money1, player, currentGui);
        if (chekcMoney1 == = 0) {
            var chekcMoney2 = chekcMoney(money2Type, money2, player, currentGui);
            if (chekcMoney2 == = 0) {
                //消耗货币
                var money1Name = consoumeMoney(money1Type, player, money1, API);
                var money2Name = consoumeMoney(money2Type, player, money2, API);
                //发送邮件
                var itemList = new ArrayList();
                bukkitItem.setAmount(count);
                itemList.add(bukkitItem);
                if (money2Type == 0) {
                    sendMail('player', shopMailSender, shopMailTopic, '§e尊敬的§b' + player.getName() + '§e， §a这是购买的物品. §c您一共消费了: ' + money1Name + '：' + money1 + ' §e欢迎下次光临', player, itemList);
                } else {
                    sendMail('player', shopMailSender, shopMailTopic, '§e尊敬的§b' + player.getName() + '§e， §a这是购买的物品. §c您一共消费了: ' + money1Name + '：' + money1 + ' ' + money2Name + '：' + money2 + ' §e欢迎下次光临', player, itemList);
                }

                //弹框提示
                showAlert(currentGui, "§a§l购买成功", "§6§l已发送至邮箱", player);
            } else {
                return;
            }
        } else {
            return;
        }
    }*/


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
}
