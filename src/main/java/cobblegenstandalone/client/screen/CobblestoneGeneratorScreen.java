package cobblegenstandalone.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import cobblegenstandalone.CobblestoneGeneratorMod;
import cobblegenstandalone.inventory.CobblestoneGeneratorMenu;
import cobblegenstandalone.network.CobblestoneGeneratorSyncPayload;
import wily.factoryapi.FactoryAPIClient;
import wily.factoryapi.base.client.FactoryGuiGraphics;
import wily.factoryapi.base.client.drawable.FactoryDrawableButton;
import wily.factoryapi.base.network.CommonNetwork;
import wily.factoryapi.util.FluidInstance;
import wily.factoryapi.util.FluidRenderUtil;

public class CobblestoneGeneratorScreen extends AbstractBasicScreen<CobblestoneGeneratorMenu> {
    public static final ResourceLocation GUI = CobblestoneGeneratorMod.createModLocation("textures/container/cobblestone_generator_gui.png");
    Inventory playerInv;
    Component name;

    public static final FluidInstance waterInstance = FluidInstance.create(Fluids.WATER);
    public static final FluidInstance lavaInstance = FluidInstance.create(Fluids.LAVA);

    protected FactoryDrawableButton changeRecipeButton;

    public CobblestoneGeneratorScreen(CobblestoneGeneratorMenu t, Inventory inv, Component name) {
        super(t, inv, name);
        playerInv = inv;
        this.name = name;
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 7 + imageWidth / 2 - font.width(name.getString()) / 2;
        addNestedRenderable(changeRecipeButton = new FactoryDrawableButton(leftPos + 81, topPos + 25, BetterFurnacesDrawables.BUTTON).onPress((b, i) -> CommonNetwork.sendToServer(new CobblestoneGeneratorSyncPayload(this.getMenu().getPos(), i == 0 ? CobblestoneGeneratorSyncPayload.Sync.NEXT_RECIPE : CobblestoneGeneratorSyncPayload.Sync.PREVIOUS_RECIPE))).grave(0.3F));
    }

    public static void renderStretchedFluid(GuiGraphics graphics, int x, int y, int width, int height, FluidInstance fluid, boolean hasColor) {
        if (hasColor) FactoryGuiGraphics.of(graphics).setColor(FluidRenderUtil.getFixedColor(fluid));
        FactoryGuiGraphics.of(graphics).blit(x, y, 0, width, height, FactoryAPIClient.getFluidStillTexture(fluid.getFluid()));
        if (hasColor) FactoryGuiGraphics.of(graphics).clearColor();
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        FactoryGuiGraphics.of(graphics).blit(GUI, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
        graphics.pose().pushPose();
        graphics.pose().translate(leftPos + 88, topPos + 32, 0);
        graphics.pose().scale(0.75F, 0.75F, 0.75F);
        graphics.pose().translate(-8, -8, 0);
        ItemStack result = getMenu().be.getResult();
        graphics.renderItem(result, 0, 0);
        graphics.pose().popPose();
        changeRecipeButton.clearTooltips().tooltip(result.getHoverName());
        int i;
        i = this.getMenu().getCobTimeScaled(16);
        if (i > 0) {
            renderStretchedFluid(graphics, leftPos + 58, topPos + 44, 17, 12, lavaInstance, false);
            renderStretchedFluid(graphics, leftPos + 101, topPos + 44, 17, 12, waterInstance, true);
            FactoryGuiGraphics.of(graphics).blit(GUI, leftPos + 58, topPos + 44, 176, 24, i + 1, 12);
            FactoryGuiGraphics.of(graphics).blit(GUI, leftPos + 117 - i, topPos + 44, 192 - i, 36, 17, 12);
        }
        FactoryGuiGraphics.of(graphics).blit(GUI, leftPos + 58, topPos + 44, 176, 0, 17, 12);
        FactoryGuiGraphics.of(graphics).blit(GUI, leftPos + 101, topPos + 44, 176, 12, 17, 12);
    }
}
