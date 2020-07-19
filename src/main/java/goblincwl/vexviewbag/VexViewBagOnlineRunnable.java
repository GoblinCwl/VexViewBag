package goblincwl.vexviewbag;

import noppes.npcs.api.NpcAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description 记录玩家在线时间任务
 * @create 2020-07-19 14:44
 */
public class VexViewBagOnlineRunnable extends BukkitRunnable {
    @Override
    public void run() {
        VexViewBag vexViewBag = VexViewBag.vexViewBag;

        Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
        for (Player player : onlinePlayers) {
            try {
                //玩家数据文件
                File file = new File(vexViewBag.getDataFolder(), "/playerData/" + player.getName() + ".yml");
                if (!file.exists()) {
                    file.createNewFile();
                }
                //每分钟统计在线时间
                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

                //统计日期
                String dataDate = configuration.getString("onlineTime.dataDate");
                String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                if (now.equals(dataDate)) {
                    //当前已在线时间
                    long nowTime = configuration.getLong("onlineTime.todayTime") + 1;
                    configuration.set("onlineTime.todayTime", nowTime);
                    configuration.save(file);
                    switch ((int) nowTime) {
                        case 30:
                            VexViewBagUtils.sendMcMessage(player, "已在线§b30§r分钟", "§a菜单->活跃领取奖励！  §7§o(下一档：§a60§r)", 2);
                            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 2F, 0F);
                            vexViewBag.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mb send online30 player " + player.getName());
                            break;
                        case 60:
                            VexViewBagUtils.sendMcMessage(player, "已在线§b60§r分钟", "§a菜单->活跃领取奖励！  §7§o(下一档：§a120§r)", 2);
                            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 2F, 0F);
                            vexViewBag.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mb send online60 player " + player.getName());
                            break;
                        case 120:
                            VexViewBagUtils.sendMcMessage(player, "已在线§b120§r分钟", "§a菜单->活跃领取奖励！  §7§o(下一档：§a300§r)", 2);
                            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 2F, 0F);
                            vexViewBag.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mb send online120 player " + player.getName());
                            break;
                        case 300:
                            VexViewBagUtils.sendMcMessage(player, "已在线§b300§r分钟", "§a菜单->活跃领取奖励！  §7§o(下一档：§a500§r)", 2);
                            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 2F, 0F);
                            vexViewBag.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mb send online300 player " + player.getName());
                            break;
                        case 500:
                            VexViewBagUtils.sendMcMessage(player, "已在线§b500§r分钟", "§a菜单->活跃领取奖励！  §7§o(§c已是最终档§7§o)", 2);
                            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 2F, 0F);
                            vexViewBag.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mb send online500 player " + player.getName());
                            break;
                        default:
                            break;
                    }
                } else {
                    configuration.set("onlineTime.dataDate", now);
                    configuration.set("onlineTime.todayTime", 1);
                    configuration.save(file);
                }

            } catch (Exception e) {
                player.sendMessage(VexViewBag.messagePrefix + "§c您的在线时间统计异常，请联系管理员.");
                e.printStackTrace();
            }
        }
    }
}
