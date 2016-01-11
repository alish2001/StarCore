package com.starnetmc.Core.Modules.Heartbeat;

/**
 * Created by Ali on 1/10/2016 at 6:39 PM.
 */

public enum Beat {

    TICK(1L), SECOND(20L),SHORT(100L), MINUTE(1200L), HOUR(72000);

    long value;
    long last;

    Beat(long value) {
        this.value = value;
        this.last = System.currentTimeMillis();
    }

    public boolean elapsed() {
        if (elapsed(this.last, this.value)) {
            this.last = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public static boolean elapsed(long from, long required) {
        return System.currentTimeMillis() - from > required;
    }

}
