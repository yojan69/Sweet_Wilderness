package sweet.wilderness.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static sweet.wilderness.SweetWilderness.LOGGER;
import static sweet.wilderness.SweetWilderness.MOD_ID;

public class ModSounds {
    public static final SoundEvent MUSIC_DISC_ECOSYSTEM = registerSoundEvent("music_disc_ecosystem");
    public static final SoundEvent MUSIC_DISC_ROOTED_SWAMP = registerSoundEvent("music_disc_rooted_swamp");
    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, net.minecraft.sound.SoundEvent.of(id));
    }

    public static void registerSounds(){
        LOGGER.info("Registered Sounds for Sweet Wilderness :D");
    }
}
