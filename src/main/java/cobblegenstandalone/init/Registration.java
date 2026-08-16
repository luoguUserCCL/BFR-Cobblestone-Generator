package cobblegenstandalone.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;
import cobblegenstandalone.CobblestoneGeneratorMod;
import cobblegenstandalone.blocks.BFRBlock;
import wily.factoryapi.FactoryAPIPlatform;
import wily.factoryapi.base.RegisterListing;

import java.util.List;
import java.util.stream.Stream;

public class Registration {
    public static final RegisterListing<BlockEntityType<?>> BLOCK_ENTITIES = FactoryAPIPlatform.createRegister(CobblestoneGeneratorMod.MOD_ID, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    public static final RegisterListing<MenuType<?>> CONTAINERS = FactoryAPIPlatform.createRegister(CobblestoneGeneratorMod.MOD_ID, BuiltInRegistries.MENU);
    static final RegisterListing<RecipeSerializer<?>> RECIPES_SERIALIZERS = FactoryAPIPlatform.createRegister(CobblestoneGeneratorMod.MOD_ID, BuiltInRegistries.RECIPE_SERIALIZER);
    static final RegisterListing<RecipeType<?>> RECIPES = FactoryAPIPlatform.createRegister(CobblestoneGeneratorMod.MOD_ID, BuiltInRegistries.RECIPE_TYPE);
    public static final RegisterListing<CreativeModeTab> TABS = FactoryAPIPlatform.createRegister(CobblestoneGeneratorMod.MOD_ID, BuiltInRegistries.CREATIVE_MODE_TAB);

    public static void init() {
        ModObjects.init();
        BlockEntityTypes.init();
        BLOCK_ENTITIES.register();
        CONTAINERS.register();
        RECIPES_SERIALIZERS.register();
        RECIPES.register();
        TABS.register();
    }

    public static <T extends Block> RegisterListing.Holder<T> registerBlockItem(RegisterListing.Holder<T> holder, RegisterListing<Item> items) {
        items.add(holder.getId().getPath(), () -> new BlockItem(holder.get(), FactoryAPIPlatform.setupBlockItemProperties(new Item.Properties(), holder)) {
            @Override
            public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
                if (getBlock() instanceof BFRBlock b) b.appendHoverText(itemStack, tooltip::add, tooltipFlag);
                super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
            }
        });
        return holder;
    }

    public static Stream<RegisterListing.Holder<Item>> getItems() {
        return ModObjects.ITEMS.stream();
    }
}
