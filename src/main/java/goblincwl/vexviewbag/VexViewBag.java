package goblincwl.vexviewbag;

import goblincwl.vexviewbag.mysql.MySqlManager;
import lk.vexview.api.VexViewAPI;
import net.milkbowl.vault.economy.Economy;
import noppes.npcs.api.NpcAPI;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;


public final class VexViewBag extends JavaPlugin {

    public static VexViewBag vexViewBag;
    public static Economy economy;
    public static PlayerPointsAPI playerPointsAPI;
    public static String messagePrefix;
    public static NpcAPI npcAPI;
    public static MySqlManager mySqlManager;

    public static Map<String, Object> configurationMap;

    @Override
    public void onEnable() {
        ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
        consoleSender.sendMessage(ChatColor.AQUA + "VexViewBag插件启动中...");


        try {
            if (!Bukkit.getPluginManager().isPluginEnabled("VexView")) {
                throw new RuntimeException();
            }

            if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
                throw new RuntimeException();
            }

            if (!Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
                throw new RuntimeException();
            }

            //静态主类
            vexViewBag = this;
            //Vault Economy注入
            economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
            //PlayerPoints注入
            Plugin playerPoints = getServer().getPluginManager().getPlugin("PlayerPoints");
            playerPointsAPI = ((PlayerPoints) playerPoints).getAPI();
            //CustomNpcs
            npcAPI = NpcAPI.Instance();

            String version = VexViewAPI.getVexView().getVersion();
            consoleSender.sendMessage("已找到VexView前置，版本：" + version);

            //注册指令
            Bukkit.getPluginCommand("vvb").setExecutor(new VexViewBagCommandHandler());
            //注册事件
            Bukkit.getPluginManager().registerEvents(new VexViewBagEventListener(), this);
            //注册PlaceHolder变量
            new VexViewBagPlaceHoldedr(this).register();

            //生成默认配置
            saveDefaultConfig();
            //加载其他配置文件
            configurationMap = VexViewBagUtils.loadAllConfig();
            //mysql注入
            mySqlManager = MySqlManager.get();
            mySqlManager.enableMySql();

            //消息提示头
            messagePrefix = getConfig().getString("messagePrefix");

            //开始记录在线时间
            VexViewBagOnlineRunnable vexViewBagOnlineRunnable = new VexViewBagOnlineRunnable();
            vexViewBagOnlineRunnable.runTaskTimer(this, 1200L, 1200L);

            consoleSender.sendMessage(ChatColor.AQUA + "VexViewBag插件启动完成！");
        } catch (Exception e) {
            consoleSender.sendMessage(ChatColor.RED + "VexViewBag插件启动失败！");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("VexViewBag插件已关闭。");
    }

    public static VexViewBag getInstance() {
        return vexViewBag;
    }
}
