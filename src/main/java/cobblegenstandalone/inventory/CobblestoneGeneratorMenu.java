package cobblegenstandalone.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import cobblegenstandalone.blockentity.CobblestoneGeneratorBlockEntity;
import cobblegenstandalone.init.ModObjects;

public class CobblestoneGeneratorMenu extends AbstractInventoryMenu<CobblestoneGeneratorBlockEntity> {
    public CobblestoneGeneratorMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory) {
        this(windowId, world, pos, playerInventory, new SimpleContainerData(3));
    }

    public CobblestoneGeneratorMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory, ContainerData fields) {
        super(ModObjects.COB_GENERATOR_CONTAINER.get(), windowId, world, pos, playerInventory, fields);
        checkContainerDataCount(this.data, 3);
    }

    public int getCobTimeScaled(int pixels) {
        int i = this.data.get(0);
        int j = this.data.get(2);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
