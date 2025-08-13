package com.yourname.customchestcommand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 5) {
            sender.sendMessage("§c사용법: /chest <x> <y> <z> <item> <amount>");
            return true;
        }

        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            Material material = Material.matchMaterial(args[3].toUpperCase());
            int amount = Integer.parseInt(args[4]);

            if (material == null) {
                sender.sendMessage("§c잘못된 아이템 코드입니다.");
                return true;
            }

            Location loc;
            if (sender instanceof Player) {
                loc = new Location(((Player) sender).getWorld(), x, y, z);
            } else {
                loc = new Location(Bukkit.getWorlds().get(0), x, y, z);
            }

            Block block = loc.getBlock();
            block.setType(Material.CHEST);

            Chest chest = (Chest) block.getState();
            chest.getInventory().addItem(new ItemStack(material, amount));
            chest.update();

            sender.sendMessage("§a상자가 생성되었습니다!");
        } catch (NumberFormatException e) {
            sender.sendMessage("§c좌표와 수량은 숫자로 입력하세요.");
        }

        return true;
    }
}
