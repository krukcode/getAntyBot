package pl.bambusmc.getantybot;

public class System {

    public static int getRandom() {
        int max = 9;
        int min = 1;
        int range = max - min + 1;
        
        int rand = (int)(Math.random() * range) + min;
        
        return rand;
    }

}
