package xyz.rajik.collections;

import lombok.Data;
import xyz.rajik.graphics.Ball;
import xyz.rajik.graphics.Bonus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Bonuses implements Iterable<Bonus> {
    List<Bonus> bonuses;
    public static boolean isX2Active = false;
    public static boolean isIncreaseSpeedActive = false;
    public static boolean isDecreaseSpeedActive = false;
    public static boolean isDisableCollisionActive = false;

    private static double x2Time = 0;
    private static double incTime = 0;
    private static double decTime = 0;
    private static double disTime = 0;

    public Bonuses() {
        bonuses = new ArrayList<>();
    }

    public static void setIsX2Active() {
        Bonuses.isX2Active = true;
        x2Time = 30;
    }

    public static void setIsIncreaseSpeedActive() {
        Bonuses.isIncreaseSpeedActive = true;
        incTime = 20;
    }

    public static void setIsDecreaseSpeedActive() {
        Bonuses.isDecreaseSpeedActive = true;
        decTime = 20;
    }

    public static void setIsDisableCollisionActive() {
        Bonuses.isDisableCollisionActive = true;
        disTime = 10;
    }

    @Override

    public Iterator<Bonus> iterator() {
        return bonuses.iterator();
    }

    public static void update(double dt) {
        x2Time -= dt;
        incTime -= dt;
        decTime -= dt;
        disTime -= dt;
        if (x2Time <= 0) isX2Active = false;
        if (incTime <= 0) isIncreaseSpeedActive = false;
        if (decTime <= 0) isDecreaseSpeedActive = false;
        if (disTime <= 0) isDisableCollisionActive = false;
    }

    public static void unset() {
        isX2Active = false;
        isIncreaseSpeedActive = false;
        isDecreaseSpeedActive = false;
        isDisableCollisionActive = false;
        x2Time = 0;
        incTime = 0;
        decTime = 0;
        disTime = 0;
    }
}
