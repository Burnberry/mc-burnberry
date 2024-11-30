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
//        game = new DefenseGame(this);
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
        CustomPlayer customPlayer = this.onNewPlayer(player);
        if (game != null){
            game.onNewPlayer(customPlayer);
        }
    }

    public CustomPlayer onNewPlayer(Player player){
        CustomPlayer cPlayer = new CustomPlayer(this, player);
        players.add(cPlayer);

        player.teleport(Loc.spawn);

        if (player.getName().equals("Noppe0")) {
            player.setGameMode(GameMode.CREATIVE);
        }
        else {
            player.setGameMode(GameMode.ADVENTURE);
        }

        if (game == null){
            M.setInventory(player, Inv.lobby);
        } else {
            M.setInventory(player, Inv.game);
        }
        return cPlayer;
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

    public void startGame(){
        stopGame();
        game = new DefenseGame(this);
        for (CustomPlayer player: players){
            M.setInventory(player, Inv.game);
        }
    }

    public void stopGame(){
        clean();
        game = null;
        for (CustomPlayer player: players){
            M.setInventory(player, Inv.lobby);
            if (player.playerWrapped.getGameMode() == GameMode.SPECTATOR){
                player.playerWrapped.setGameMode(GameMode.ADVENTURE);
            }
        }
    }
}
