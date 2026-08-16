package cobblegenstandalone.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import cobblegenstandalone.blockentity.CobblestoneGeneratorBlockEntity;
import cobblegenstandalone.blocks.CobblestoneGeneratorBlock;
import wily.factoryapi.FactoryAPIPlatform;
import wily.factoryapi.base.RegisterListing;

import static cobblegenstandalone.init.ModObjects.COBBLESTONE_GENERATOR;
import static cobblegenstandalone.init.Registration.BLOCK_ENTITIES;

public class BlockEntityTypes {
    public static void init() {
    }

    public static final RegisterListing.Holder<BlockEntityType<CobblestoneGeneratorBlockEntity>> COB_GENERATOR_TILE = BLOCK_ENTITIES.add(CobblestoneGeneratorBlock.COBBLESTONE_GENERATOR, () -> FactoryAPIPlatform.createBlockEntityType(CobblestoneGeneratorBlockEntity::new, COBBLESTONE_GENERATOR.get()));
}
