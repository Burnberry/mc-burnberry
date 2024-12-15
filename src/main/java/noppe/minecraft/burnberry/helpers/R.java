package noppe.minecraft.burnberry.helpers;

import java.util.concurrent.ThreadLocalRandom;

public class R {
    public static int randomInt(int a, int b){
        return ThreadLocalRandom.current().nextInt(a, b);
    }

    public static int randomInt(int b){
        return randomInt(0, b);
    }
}
