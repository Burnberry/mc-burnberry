package noppe.minecraft.burnberry;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventPlayerJoin;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.item.Inv;
import noppe.minecraft.burnberry.location.Loc;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class Game extends CustomEventListener {
    public Burnberry burnberry;
    public List<CustomPlayer> players;

    public Game(Burnberry plugin){
        burnberry = plugin;
        players = new ArrayList<>();
        for (Player player: burnberry.getServer().getOnlinePlayers()) {
            onNewPlayer(player);
        }
    }

    public void onPlayerJoin(PlayerJoinEvent event, EventPlayerJoin ev){
        Player player = event.getPlayer();
        M.print(player.getName()+" joined!");
        this.onNewPlayer(player);
    }

    public void onNewPlayer(Player player){
        players.add(new CustomPlayer(this, player));

        player.teleport(Loc.spawn);

        if (player.getName().equals("Noppe0")) {
            player.setGameMode(GameMode.CREATIVE);
        }
        else {
            player.setGameMode(GameMode.ADVENTURE);
        }

        M.setInventory(player, Inv.lobby);
    }
}
