package noppe.minecraft.burnberry;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventEntityDeath;
import noppe.minecraft.burnberry.event.events.EventPlayerJoin;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.item.Inv;
import noppe.minecraft.burnberry.location.Loc;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class Lobby extends CustomEventListener {
    public Burnberry burnberry;
    public List<CustomPlayer> players;
    public DefenseGame game;

    public Lobby(Burnberry plugin){
        burnberry = plugin;
        game = new DefenseGame(this);
        players = new ArrayList<>();
        for (Player player: burnberry.getServer().getOnlinePlayers()) {
            onNewPlayer(player);
        }
    }

    public void clean(){
        if (game != null){
            game.clean();
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

    public void onTick(){
//        for (CustomPlayer player: players){
//            player.onTick();
//        }
        if (game != null){
            game.onTick();
        }
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev) {
        if (ev.player != null && game != null){
            game.onEntityDeath(event, ev);
        }
    }
}
