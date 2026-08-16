package cobblegenstandalone.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import cobblegenstandalone.init.ModObjects;
import wily.factoryapi.util.DynamicUtil;

public record CobblestoneGeneratorRecipe(ResourceLocation id, ItemStack result, int duration) implements Recipe<Container> {
    public static final CobblestoneGeneratorRecipe.Serializer SERIALIZER = new CobblestoneGeneratorRecipe.Serializer();

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return id();
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return true;
    }

    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess access) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<CobblestoneGeneratorRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<CobblestoneGeneratorRecipe> getType() {
        return ModObjects.ROCK_GENERATING_RECIPE.get();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return result;
    }

    public static class Serializer implements RecipeSerializer<CobblestoneGeneratorRecipe> {

        @Override
        public CobblestoneGeneratorRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            return new CobblestoneGeneratorRecipe(resourceLocation, DynamicUtil.ITEM_CODEC.parse(JsonOps.INSTANCE, jsonObject.get("result")).result().get(), GsonHelper.getAsInt(jsonObject, "duration", 80));
        }

        @Override
        public CobblestoneGeneratorRecipe fromNetwork(ResourceLocation location, FriendlyByteBuf friendlyByteBuf) {
            return new CobblestoneGeneratorRecipe(location, friendlyByteBuf.readItem(), friendlyByteBuf.readInt());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CobblestoneGeneratorRecipe recipe) {
            buffer.writeItem(recipe.result);
            buffer.writeInt(recipe.duration);
        }
    }
}
