package sweet.wilderness.huh;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class Timer implements ServerTickEvents.EndTick{
    private long ticksUntilSomething;
    private Runnable code;

    @Override
    public void onEndTick(MinecraftServer server) {
        if (--this.ticksUntilSomething == 0L)
            code.run();

    }

    public void SetTimer(long ticksUntilSomething, Runnable code){
        this.ticksUntilSomething = ticksUntilSomething;
        this.code = code;
    }
}
