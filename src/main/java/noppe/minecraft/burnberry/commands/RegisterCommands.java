package noppe.minecraft.burnberry.commands;

import noppe.minecraft.burnberry.Burnberry;

public class RegisterCommands {
    Burnberry burnberry;

    public RegisterCommands(Burnberry plugin){
        burnberry = plugin;
    }

    public void registerCommands(){
        burnberry.getCommand("template").setExecutor(new CommandTemplate());
        burnberry.getCommand("test").setExecutor(new CommandTest());
        burnberry.getCommand("spectate").setExecutor(new CommandSpectate());
    }
}
