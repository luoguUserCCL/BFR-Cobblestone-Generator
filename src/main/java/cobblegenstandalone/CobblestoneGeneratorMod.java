package cobblegenstandalone;

import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cobblegenstandalone.init.ModObjects;
import cobblegenstandalone.init.Registration;
import cobblegenstandalone.network.CobblestoneGeneratorSyncPayload;
import wily.factoryapi.FactoryAPI;
import wily.factoryapi.FactoryAPIPlatform;
import wily.factoryapi.FactoryEvent;

import java.util.function.Supplier;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.api.distmarker.Dist;

@Mod(CobblestoneGeneratorMod.MOD_ID)
public class CobblestoneGeneratorMod {

    public static final String MOD_ID = "cobblegenstandalone";
    public static final Supplier<String> VERSION = FactoryAPIPlatform.getModInfo(MOD_ID)::getVersion;
    public static final Supplier<String> MC_VERSION = SharedConstants.getCurrentVersion()::getName;

    public static final Logger LOGGER = LogManager.getLogger();

    public CobblestoneGeneratorMod() {
        init();
        if (FMLEnvironment.dist == Dist.CLIENT)
            CobblestoneGeneratorModClient.init();
    }

    public static void init() {
        FactoryEvent.registerPayload(registry -> {
            registry.register(true, CobblestoneGeneratorSyncPayload.ID);
        });
        Registration.init();
    }

    public static ResourceLocation createModLocation(String path) {
        return FactoryAPI.createLocation(MOD_ID, path);
    }
}
