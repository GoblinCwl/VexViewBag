package goblincwl.vexviewbag.gui;

import goblincwl.vexviewbag.utils.VexViewBagUtils;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexText;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description 商店界面
 * @create 2020-07-05 21:09
 */
public class ShopGui extends VexGui {
    public ShopGui() {
        super(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/shop.png",
                -1,
                -1,
                304,
                200
        );

        //绿宝石卖场
        this.addComponent(new VexButton(
                "shopMenuEmerald",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/emeraldShopBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/emeraldShopBtn_.png",
                82,
                11,
                69,
                88,
                player -> VexViewAPI.openGui(player, new InShopGui("emeraldShop"))
        ));
        this.addComponent(new VexText(22, 81, VexViewBagUtils.oneRowAryList("§a§l绿宝石卖场"), 1));

        //紫晶石卖场
        this.addComponent(new VexButton(
                "shopMenuAmethyst",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/amethystShopBtn1.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/amethystShopBtn1_.png",
                153,
                11,
                69,
                88,
                player -> VexViewAPI.openGui(player, new BagGui(player))
        ));
        this.addComponent(new VexText(93, 81, VexViewBagUtils.oneRowAryList("§d§l紫晶石卖场"), 1));

        //全球市场
        this.addComponent(new VexButton(
                "shopMenuGlobal",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/globalShopBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/globalShopBtn_.png",
                224,
                11,
                69,
                88,
                player -> VexViewAPI.openGui(player, new BagGui(player))
        ));
        this.addComponent(new VexText(168, 81, VexViewBagUtils.oneRowAryList("§b§l全球市场"), 1));

        //神秘兑换
        this.addComponent(new VexButton(
                "shopMenuMystery",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/mysteryShopBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/mysteryShopBtn_.png",
                224,
                102,
                69,
                88,
                player -> VexViewAPI.openGui(player, new BagGui(player))
        ));
        this.addComponent(new VexText(239, 81, VexViewBagUtils.oneRowAryList("§9§l神秘兑换"), 1));

        //回收拍卖
        this.addComponent(new VexButton(
                "shopMenuRecovery",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/recoveryShopBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/shopMain/recoveryShopBtn_.png",
                11,
                11,
                69,
                88,
                player -> VexViewAPI.openGui(player, new BagGui(player))
        ));
        this.addComponent(new VexText(239, 172, VexViewBagUtils.oneRowAryList("§6§l回收拍卖"), 1));
    }
}
