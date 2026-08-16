package cobblegenstandalone.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import cobblegenstandalone.blockentity.InventoryBlockEntity;
import wily.factoryapi.base.network.OpenExtraMenuPayload;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class BFRBlock extends Block {
    public BFRBlock(Properties properties) {
        super(properties.strength(3f));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> customDrops = super.getDrops(state, builder);
        if (!customDrops.isEmpty())
            return customDrops;
        return Collections.singletonList(new ItemStack(this));
    }

    protected boolean openBlockEntityMenu(Level world, BlockPos pos, Player player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof MenuProvider menu) {
            OpenExtraMenuPayload.openMenuWithPos((ServerPlayer) player, menu, blockEntity.getBlockPos());
            return true;
        }
        return false;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean bl) {
        if (state.getBlock() != oldState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof InventoryBlockEntity inventory) {
                onRemoveInventoryBlockEntity(level, pos, inventory, true);
            }
        }
        super.onRemove(state, level, pos, oldState, bl);
    }

    public void onRemoveInventoryBlockEntity(Level level, BlockPos pos, InventoryBlockEntity inventory, boolean dropContents) {
        inventory.onRemoved(dropContents);
        level.updateNeighbourForOutputSignal(pos, this);
    }

    public void appendHoverText(ItemStack stack, Consumer<Component> tooltip, TooltipFlag flagIn) {
    }
}
