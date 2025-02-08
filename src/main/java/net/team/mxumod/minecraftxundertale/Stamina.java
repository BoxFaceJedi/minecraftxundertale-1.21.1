package net.team.mxumod.minecraftxundertale;

import net.team.mxumod.minecraftxundertale.libraries.ObservableValue;

import java.util.HashMap;

public class Stamina {
    static final HashMap<String, Integer> MaxStaminaDictionary = new HashMap<>() {{
        put("Sans", 100);
        put("Chara", 100);
    }};

    private static ObservableValue<Integer> MaxStamina = new ObservableValue<>(100);
    private static ObservableValue<Integer> Stamina = new ObservableValue<>(MaxStamina.getValue());

    private static void setMaxStamina(Integer Value) {MaxStamina.setValue(Value);}
    public static void setStamina(Integer Value) {Stamina.setValue(Value);}
    public static Integer getStamina() {return Stamina.getValue();}
    public static Integer getMaxStamina(){return MaxStamina.getValue();}

    public class StaminaRefill {
        
    }
}
