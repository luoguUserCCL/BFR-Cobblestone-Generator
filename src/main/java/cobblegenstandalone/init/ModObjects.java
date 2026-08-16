package cobblegenstandalone.init;

import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import cobblegenstandalone.blocks.CobblestoneGeneratorBlock;
import cobblegenstandalone.inventory.CobblestoneGeneratorMenu;
import cobblegenstandalone.recipes.CobblestoneGeneratorRecipe;
import wily.factoryapi.FactoryAPIPlatform;
import wily.factoryapi.base.FactoryExtraMenuSupplier;
import wily.factoryapi.base.RegisterListing;

import static cobblegenstandalone.CobblestoneGeneratorMod.MOD_ID;
import static cobblegenstandalone.init.Registration.*;

public class ModObjects {
    public static final RegisterListing<Block> BLOCKS = FactoryAPIPlatform.createRegister(MOD_ID, BuiltInRegistries.BLOCK);
    public static final RegisterListing<Item> ITEMS = FactoryAPIPlatform.createRegister(MOD_ID, BuiltInRegistries.ITEM);

    public static void init() {
        BLOCKS.register();
        ITEMS.register();
    }

    public static final RegisterListing.Holder<CreativeModeTab> ITEM_GROUP = TABS.add("cobblegen_tab", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup." + MOD_ID + ".tab")).icon(() -> ModObjects.COBBLESTONE_GENERATOR.get().asItem().getDefaultInstance()).displayItems(((itemDisplayParameters, output) -> ITEMS.forEach(h -> output.accept(h.get().getDefaultInstance())))).build());

    public static final RegisterListing.Holder<RecipeSerializer<CobblestoneGeneratorRecipe>> COB_GENERATION_SERIALIZER = RECIPES_SERIALIZERS.add("rock_generating", () -> CobblestoneGeneratorRecipe.SERIALIZER);

    public static final RegisterListing.Holder<RecipeType<CobblestoneGeneratorRecipe>> ROCK_GENERATING_RECIPE = RECIPES.add("rock_generating", () -> new RecipeType<>() {
    });

    public static final RegisterListing.Holder<CobblestoneGeneratorBlock> COBBLESTONE_GENERATOR = registerBlockItem(BLOCKS.add(CobblestoneGeneratorBlock.COBBLESTONE_GENERATOR, () -> new CobblestoneGeneratorBlock(FactoryAPIPlatform.setupBlockProperties(propertiesOf(Blocks.BLACKSTONE), ModObjects.COBBLESTONE_GENERATOR))), ITEMS);

    public static final RegisterListing.Holder<MenuType<CobblestoneGeneratorMenu>> COB_GENERATOR_CONTAINER = CONTAINERS.add(CobblestoneGeneratorBlock.COBBLESTONE_GENERATOR, () -> FactoryExtraMenuSupplier.createMenuType((windowId, inv, data) -> new CobblestoneGeneratorMenu(windowId, inv.player.level(), data.get().readBlockPos(), inv)));

    public static BlockBehaviour.Properties propertiesOf(Block block) {
        return BlockBehaviour.Properties.copy(block);
    }
}
