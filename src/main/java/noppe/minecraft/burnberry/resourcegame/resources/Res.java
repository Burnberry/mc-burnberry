package noppe.minecraft.burnberry.resourcegame.resources;

public enum Res {
    WOOD("Wood"),
    STONE("Stone"),
    IRON("Iron");

    private String stringValue;
    void Strings(final String s) { stringValue = s; }

    Res(String stringValue) {
        this.stringValue = stringValue;
    }
    public String toString() { return stringValue; }
}