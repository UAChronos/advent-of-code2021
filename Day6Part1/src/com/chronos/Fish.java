package com.chronos;

import java.util.HashMap;

public class Fish {
    private long descendants;
    private int daysLeft;
    private int untilBirth;

    public Fish(int lifeSpan, int nextBirthIn){
        daysLeft = lifeSpan;
        untilBirth = nextBirthIn;
    }

    public long countDescendants(HashMap<String, Long> keyMap){
        String key = "ub" + untilBirth + "dl" + daysLeft;

        if(keyMap.get(key) != null){
            descendants = keyMap.get(key);
        }
        else {
            descendants = 0;
            for (int i = daysLeft - untilBirth; i > 0; i -= 7) {
                descendants++;

                Fish newFish = new Fish(i, 9);
                descendants += newFish.countDescendants(keyMap);
            }
            keyMap.put(key, descendants);
        }

        return descendants;
    }
}
