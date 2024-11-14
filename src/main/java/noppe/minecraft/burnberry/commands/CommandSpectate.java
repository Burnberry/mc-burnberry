package noppe.minecraft.burnberry.commands;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpectate implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            p.setGameMode(GameMode.SPECTATOR);
        }
        return true;
    }
}
