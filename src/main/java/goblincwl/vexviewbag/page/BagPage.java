package goblincwl.vexviewbag.page;

import goblincwl.vexviewbag.VexViewBagUtils;
import goblincwl.vexviewbag.gui.BauBagGui;
import goblincwl.vexviewbag.gui.InfoGui;
import goblincwl.vexviewbag.gui.ShopGui;
import goblincwl.vexviewbag.gui.StandGui;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexHoverText;
import lk.vexview.gui.components.VexImage;
import lk.vexview.gui.components.VexText;
import lk.vexview.newinv.VexPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description 侧边栏页数
 * @create 2020-07-25 14:27
 */
public class BagPage {

    List<VexText> textList;
    List<VexImage> imageList;
    List<VexButton> buttonList;

    public BagPage() {
        textList = new ArrayList<>();
        imageList = new ArrayList<>();
        buttonList = new ArrayList<>();

        //头像框
        this.imageList.add(new VexImage(
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/other/headIframe.png",
                6,
                6,
                60,
                60));

        //头像
        this.buttonList.add(new VexButton(
                "headBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/other/Steve.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/other/Steve_.png",
                12,
                12,
                49,
                48,
                inPlayer -> {
                    VexViewAPI.openGui(inPlayer, new InfoGui(inPlayer));
                }
        ));

        //左侧按钮
        //饰品
        this.buttonList.add(new VexButton(
                "bauBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/bauBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/bauBtn_.png",
                6,
                68,
                20,
                20,
                inPlayer -> {
                    VexViewAPI.openGui(inPlayer, new BauBagGui(inPlayer));
                }
        ));

        //称号
        this.buttonList.add(new VexButton(
                "chBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/chBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/chBtn_.png",
                26,
                68,
                20,
                20,
                inPlayer -> {
                    VexViewBagUtils.playerOpCommand(inPlayer, "ch open");
                }
        ));

        //替身
        this.buttonList.add(new VexButton(
                "standBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/standBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/standBtn_.png",
                46,
                68,
                20,
                20,
                inPlayer -> {
                    VexViewAPI.openGui(inPlayer, new StandGui(inPlayer));
                }
        ));

        //活跃
        this.buttonList.add(new VexButton(
                "_activeBtn1_",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/infoBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/infoBtn_.png",
                6,
                88,
                20,
                20,
                inPlayer -> {
                    VexViewBagUtils.playerOpCommand(inPlayer, "fg open");
                }
        ));
        //任务
        this.buttonList.add(new VexButton(
                "tasksBtn_",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/signBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/signBtn_.png",
                26,
                88,
                20,
                20,
                inPlayer -> {
                    inPlayer.sendMessage("暂未开放");
                }
        ));
        //邮箱
        this.buttonList.add(new VexButton(
                "mailBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/mailBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/mailBtn_.png",
                46,
                88,
                20,
                20,
                inPlayer -> {
                    inPlayer.chat("/mb");
                }
        ));

        //右侧按钮
        //传送
        this.buttonList.add(new VexButton(
                "tpBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/tpBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/tpBtn_.png",
                70,
                3,
                20,
                20,
                inPlayer -> {
                    VexViewBagUtils.playerOpCommand(inPlayer, "opengui " + inPlayer.getName() + " main-gui/tpMain-gui");
                },
                new VexHoverText(Collections.singletonList("§d传送"))
        ));
        //商店
        this.buttonList.add(new VexButton(
                "shopBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/shopBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/shopBtn_.png",
                70,
                22,
                20,
                20,
                inPlayer -> {
                    VexViewAPI.openGui(inPlayer, new ShopGui());
                },
                new VexHoverText(Collections.singletonList("§b商店"))
        ));
        //活动
        this.buttonList.add(new VexButton(
                "partyBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/partyBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/partyBtn_.png",
                70,
                41,
                20,
                20,
                inPlayer -> {
                },
                new VexHoverText(Collections.singletonList("§e活动"))
        ));
        //工作台
        this.buttonList.add(new VexButton(
                "craftBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/craftBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/craftBtn_.png",
                70,
                60,
                20,
                20,
                inPlayer -> {
                    VexViewBagUtils.playerOpCommand(inPlayer, "craft");
                },
                new VexHoverText(Collections.singletonList("§6工作台"))
        ));
        //末影箱
        this.buttonList.add(new VexButton(
                "endChestBtn",
                "",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/enderChestBtn.png",
                "https://dragonstwilight.oss-cn-beijing.aliyuncs.com/VexViewPic/button/enderChestBtn_.png",
                70,
                79,
                20,
                20,
                inPlayer -> {
                    inPlayer.chat("/ec open");
                },
                new VexHoverText(Collections.singletonList("§9末影箱"))
        ));
    }

    public VexPage getPage() {
        return new VexPage(this.textList, this.imageList, this.buttonList);
    }
}
