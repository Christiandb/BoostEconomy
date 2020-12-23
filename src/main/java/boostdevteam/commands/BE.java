package boostdevteam.commands;

import boostdevteam.boosteconomy.BoostEconomy;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BE implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("be")) {
            if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sender.sendMessage(BoostEconomy.getInstance().getConfig().getString("Messages.General.InvalidArgs").replaceAll("&", "§"));
                    if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_SPIDER_DEATH, 1.0f, 1.0f);
                        }
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if(sender.hasPermission("boosteconomy.reload")) {
                            try {
                                BoostEconomy.onReload();
                            }catch (Exception e) {
                                Bukkit.getLogger().severe("[BoostEconomy] §cError while reloading the plugin!");
                                e.printStackTrace();
                            }finally {
                                if (sender instanceof Player) {
                                    sender.sendMessage(BoostEconomy.getInstance().getConfig().getString("Messages.General.Reload").replaceAll("&", "§"));
                                    if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                        Player p = (Player) sender;
                                        p.playSound(p.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                                    }
                                }
                            }
                        }else {
                            sender.sendMessage(BoostEconomy.getInstance().getConfig().getString("Messages.General.NoPerms").replaceAll("&", "§"));
                            if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                if (sender instanceof Player) {
                                    Player p = (Player) sender;
                                    p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_SPIDER_DEATH, 1.0f, 1.0f);
                                }
                            }
                        }
                    }else if (args[0].equalsIgnoreCase("help")) {
                        if (sender.hasPermission("boosteconomy.help")) {
                            sender.sendMessage("§8§l§m+---------------------------+");
                            sender.sendMessage("§b§l/be <reload/help/debug/checkforupdates> §7The main command");
                            sender.sendMessage("§b§l/pay <player> <money> §7Send money to a player");
                            sender.sendMessage("§b§l/money [player] §7Show the money of a player");
                            sender.sendMessage("§b§l/eco <player> <set/give/take> <money> §7Commands for admin");
                            sender.sendMessage("§b§l/ecoreset <player> §7Resets the money of a player");
                            sender.sendMessage("§8§l§m+---------------------------+");
                            if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                if (sender instanceof Player) {
                                    if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                        Player player = (Player) sender;
                                        player.playSound(player.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                                    }
                                }
                            }
                        }else {
                            sender.sendMessage(BoostEconomy.getInstance().getConfig().getString("Messages.General.NoPerms").replaceAll("&", "§"));
                            if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                if (sender instanceof Player) {
                                    Player p = (Player) sender;
                                    p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_SPIDER_DEATH, 1.0f, 1.0f);
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("debug")) {
                        if (sender instanceof ConsoleCommandSender) {
                            sender.sendMessage("§8§l§m+---------------------------+");
                            sender.sendMessage("§7Version of the server: §c" + Bukkit.getBukkitVersion());
                            sender.sendMessage("§7Version of the plugin: §e" + BoostEconomy.plugin.getDescription().getVersion());
                            sender.sendMessage("§8§l§m+---------------------------+");
                        }else {
                            if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                if (sender instanceof Player) {
                                    Player p = (Player) sender;
                                    p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_SPIDER_DEATH, 1.0f, 1.0f);
                                    sender.sendMessage(BoostEconomy.getInstance().getConfig().getString("Messages.General.NoPlayer").replaceAll("&", "§"));
                                }
                            }

                        }
                    }else if (args[0].equalsIgnoreCase("checkforupdates")) {
                        if (sender.hasPermission("boosteconomy.checkforupdates")) {
                            if (sender instanceof ConsoleCommandSender) {
                                new boostdevteam.boosteconomy.UpdateChecker(BoostEconomy.plugin, 86591).getVersion(version -> {
                                    if (BoostEconomy.plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                                        Bukkit.getConsoleSender().sendMessage("[BoostEconomy] §aNo new version available!");
                                    } else {
                                        Bukkit.getConsoleSender().sendMessage("[BoostEconomy] New version available! §av" + version);
                                        Bukkit.getConsoleSender().sendMessage("[BoostEconomy] You have §cv" + BoostEconomy.plugin.getDescription().getVersion());
                                        Bukkit.getConsoleSender().sendMessage("[BoostEconomy] §eDownload it at https://www.spigotmc.org/resources/86591");
                                    }
                                });
                            } else {
                                new boostdevteam.boosteconomy.UpdateChecker(BoostEconomy.plugin, 86591).getVersion(version -> {
                                    if (BoostEconomy.plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                                        sender.sendMessage("§b§lBoostEconomy §8--> §aNo new version available!");
                                    } else {
                                        sender.sendMessage("§b§lBoostEconomy §8--> §7New version available! §av" + version);
                                        sender.sendMessage("§b§lBoostEconomy §8--> §7You have §cv" + BoostEconomy.plugin.getDescription().getVersion());
                                        sender.sendMessage("§b§lBoostEconomy §8--> §eDownload it at https://www.spigotmc.org/resources/86591");
                                    }
                                });
                            }
                            if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                if (sender instanceof Player) {
                                    if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                        Player player = (Player) sender;
                                        player.playSound(player.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                                    }
                                }
                            }
                        }else {
                            sender.sendMessage(BoostEconomy.getInstance().getConfig().getString("Messages.General.NoPerms").replaceAll("&", "§"));
                            if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                                if (sender instanceof Player) {
                                    Player p = (Player) sender;
                                    p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_SPIDER_DEATH, 1.0f, 1.0f);
                                }
                            }
                        }
                    }else {
                        sender.sendMessage(BoostEconomy.getInstance().getConfig().getString("Messages.General.InvalidArgs").replaceAll("&", "§"));
                        if (BoostEconomy.getVersion().contains("1.13") || BoostEconomy.getVersion().contains("1.14") || BoostEconomy.getVersion().contains("1.15") || BoostEconomy.getVersion().contains("1.16")) {
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_SPIDER_DEATH, 1.0f, 1.0f);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}