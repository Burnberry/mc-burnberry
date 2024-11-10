package noppe.minecraft.burnberry;

import noppe.minecraft.burnberry.commands.RegisterCommands;
import noppe.minecraft.burnberry.event.CustomEventHandler;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.location.Loc;
import noppe.minecraft.burnberry.settings.DefaultGameRules;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Burnberry extends JavaPlugin implements Listener {
    CustomEventHandler eventHandler;
    RegisterCommands registerCommands;
    public int ticks;
    public Game game;

    @Override
    public void onEnable() {
        M.setPlugin(this);
        Loc.setPlugin(this);
        eventHandler = new CustomEventHandler(this);
        registerCommands = new RegisterCommands(this);
        M.print("Starting Plugin");

        ticks = 0;
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, this::onTick, 1, 1);

        DefaultGameRules.SetDefaultGameRules(M.getWorld());
        this.getServer().getPluginManager().registerEvents(this, this);
        registerCommands.registerCommands();

        game = new Game(this);
    }

    @Override
    public void onDisable() {
        M.print("Cleaning up for restart");
        game.clean();
    }

    public void print(String message){
        this.getServer().broadcastMessage(message);
    }

    public void onTick(){
        game.onTick();
    }
}
