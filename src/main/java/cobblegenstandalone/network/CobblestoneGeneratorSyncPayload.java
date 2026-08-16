package cobblegenstandalone.network;

import net.minecraft.core.BlockPos;
import cobblegenstandalone.CobblestoneGeneratorMod;
import cobblegenstandalone.blockentity.CobblestoneGeneratorBlockEntity;
import wily.factoryapi.base.network.CommonNetwork;

public record CobblestoneGeneratorSyncPayload(BlockPos pos, Sync sync) implements CommonNetwork.Payload {

    public enum Sync {
        NEXT_RECIPE, PREVIOUS_RECIPE;
    }

    public static final CommonNetwork.Identifier<CobblestoneGeneratorSyncPayload> ID = CommonNetwork.Identifier.create(CobblestoneGeneratorMod.createModLocation("cobblestone_generator_sync"), CobblestoneGeneratorSyncPayload::new);

    public CobblestoneGeneratorSyncPayload(CommonNetwork.PlayBuf buf) {
        this(buf.get().readBlockPos(), buf.get().readEnum(Sync.class));
    }

    @Override
    public void apply(Context context) {
        context.executor().execute(() -> {
            CobblestoneGeneratorBlockEntity be = (CobblestoneGeneratorBlockEntity) context.player().level().getBlockEntity(pos);
            if (context.player().level().isLoaded(pos)) {
                switch (sync) {
                    case NEXT_RECIPE, PREVIOUS_RECIPE -> {
                        be.changeRecipe(sync == Sync.NEXT_RECIPE);
                        be.setChanged();
                    }
                }
            }
        });
    }

    @Override
    public CommonNetwork.Identifier<? extends CommonNetwork.Payload> identifier() {
        return ID;
    }

    @Override
    public void encode(CommonNetwork.PlayBuf buf) {
        buf.get().writeBlockPos(pos);
        buf.get().writeEnum(sync);
    }
}
