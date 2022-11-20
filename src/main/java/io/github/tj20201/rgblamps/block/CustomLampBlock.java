package io.github.tj20201.rgblamps.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 Custom lamp block made for RGBLamps mod.
 Contains a custom COLOR property which defines the colour of the lamp. No arguments passed through on creation.
**/
@SuppressWarnings("deprecation")
public class CustomLampBlock extends Block {
    /**
     Integer defaulting to 16 which ranges from 1-16 which defines the colour of the Block.
     (1 = RED, 2 = GREEN, 3 = PURPLE, 4 = CYAN, 5 = LIGHT GRAY,
     6 = GRAY, 7 = PINK, 8 = LIME, 9 = YELLOW, 10 = LIGHT BLUE,
     11 = MAGENTA, 12 = ORANGE, 13 = BLACK, 14 = BLUE, 15 = BROWN,
     16 = WHITE)
    **/
    public static final IntProperty COLOR = IntProperty.of("color", 1, 16);
    /**
     Boolean defaulting to False which defines whether the block is enabled and emitting light or not.
    **/
    public static final BooleanProperty LIT;

    public CustomLampBlock() {
        super(FabricBlockSettings.of(Material.REDSTONE_LAMP).strength(1.0f).luminance((state) -> state.get(CustomLampBlock.LIT) ? 15 : 0));
        this.setDefaultState(getStateManager().getDefaultState()
                .with(COLOR, 16)
                .with(LIT, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(COLOR);
        builder.add(LIT);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean bl = state.get(LIT);
            if (bl != world.isReceivingRedstonePower(pos)) {
                if (bl) {
                    world.createAndScheduleBlockTick(pos, this, 4);
                } else {
                    world.setBlockState(pos, state.cycle(LIT), 2);
                }
            }

        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(LIT), 2);
        }

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack handItem = player.getMainHandStack();
        if (handItem.isOf(Items.RED_DYE))        { world.setBlockState(pos, state.with(COLOR, 1)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.GREEN_DYE))      { world.setBlockState(pos, state.with(COLOR, 2)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.PURPLE_DYE))     { world.setBlockState(pos, state.with(COLOR, 3)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.CYAN_DYE))       { world.setBlockState(pos, state.with(COLOR, 4)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.LIGHT_GRAY_DYE)) { world.setBlockState(pos, state.with(COLOR, 5)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.GRAY_DYE))       { world.setBlockState(pos, state.with(COLOR, 6)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.PINK_DYE))       { world.setBlockState(pos, state.with(COLOR, 7)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.LIME_DYE))       { world.setBlockState(pos, state.with(COLOR, 8)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.YELLOW_DYE))     { world.setBlockState(pos, state.with(COLOR, 9)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.LIGHT_BLUE_DYE)) { world.setBlockState(pos, state.with(COLOR, 10)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.MAGENTA_DYE))    { world.setBlockState(pos, state.with(COLOR, 11)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.ORANGE_DYE))     { world.setBlockState(pos, state.with(COLOR, 12)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.BLACK_DYE))      { world.setBlockState(pos, state.with(COLOR, 13)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.BLUE_DYE))       { world.setBlockState(pos, state.with(COLOR, 14)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.BROWN_DYE))      { world.setBlockState(pos, state.with(COLOR, 15)); player.getMainHandStack().decrement(1);}
        if (handItem.isOf(Items.WHITE_DYE))      { world.setBlockState(pos, state.with(COLOR, 16)); player.getMainHandStack().decrement(1);}
        return ActionResult.SUCCESS;
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}
