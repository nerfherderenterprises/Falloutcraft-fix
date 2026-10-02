/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.AttachFace
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package fr.tathan.falloutcraft.common.blocks;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VaultButtons
extends FaceAttachedHorizontalDirectionalBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final int PRESSED_DEPTH = 1;
    private static final int UNPRESSED_DEPTH = 2;
    protected static final int HALF_AABB_HEIGHT = 2;
    protected static final int HALF_AABB_WIDTH = 3;
    protected static final VoxelShape CEILING_AABB_X = Block.box((double)6.0, (double)14.0, (double)5.0, (double)10.0, (double)16.0, (double)11.0);
    protected static final VoxelShape CEILING_AABB_Z = Block.box((double)5.0, (double)14.0, (double)6.0, (double)11.0, (double)16.0, (double)10.0);
    protected static final VoxelShape FLOOR_AABB_X = Block.box((double)6.0, (double)0.0, (double)5.0, (double)10.0, (double)2.0, (double)11.0);
    protected static final VoxelShape FLOOR_AABB_Z = Block.box((double)5.0, (double)0.0, (double)6.0, (double)11.0, (double)2.0, (double)10.0);
    protected static final VoxelShape NORTH_AABB = Block.box((double)5.0, (double)6.0, (double)14.0, (double)11.0, (double)10.0, (double)16.0);
    protected static final VoxelShape SOUTH_AABB = Block.box((double)5.0, (double)6.0, (double)0.0, (double)11.0, (double)10.0, (double)2.0);
    protected static final VoxelShape WEST_AABB = Block.box((double)14.0, (double)6.0, (double)5.0, (double)16.0, (double)10.0, (double)11.0);
    protected static final VoxelShape EAST_AABB = Block.box((double)0.0, (double)6.0, (double)5.0, (double)2.0, (double)10.0, (double)11.0);
    protected static final VoxelShape PRESSED_CEILING_AABB_X = Block.box((double)6.0, (double)15.0, (double)5.0, (double)10.0, (double)16.0, (double)11.0);
    protected static final VoxelShape PRESSED_CEILING_AABB_Z = Block.box((double)5.0, (double)15.0, (double)6.0, (double)11.0, (double)16.0, (double)10.0);
    protected static final VoxelShape PRESSED_FLOOR_AABB_X = Block.box((double)6.0, (double)0.0, (double)5.0, (double)10.0, (double)1.0, (double)11.0);
    protected static final VoxelShape PRESSED_FLOOR_AABB_Z = Block.box((double)5.0, (double)0.0, (double)6.0, (double)11.0, (double)1.0, (double)10.0);
    protected static final VoxelShape PRESSED_NORTH_AABB = Block.box((double)5.0, (double)6.0, (double)15.0, (double)11.0, (double)10.0, (double)16.0);
    protected static final VoxelShape PRESSED_SOUTH_AABB = Block.box((double)5.0, (double)6.0, (double)0.0, (double)11.0, (double)10.0, (double)1.0);
    protected static final VoxelShape PRESSED_WEST_AABB = Block.box((double)15.0, (double)6.0, (double)5.0, (double)16.0, (double)10.0, (double)11.0);
    protected static final VoxelShape PRESSED_EAST_AABB = Block.box((double)0.0, (double)6.0, (double)5.0, (double)1.0, (double)10.0, (double)11.0);
    private final boolean sensitive;

    public VaultButtons(boolean pSensitive, BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)FACING, (Comparable)Direction.NORTH)).setValue((Property)POWERED, (Comparable)Boolean.valueOf(false))).setValue((Property)FACE, (Comparable)AttachFace.WALL));
        this.sensitive = pSensitive;
    }

    private int getPressDuration() {
        return this.sensitive ? 30 : 20;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = (Direction)pState.getValue((Property)FACING);
        boolean flag = (Boolean)pState.getValue((Property)POWERED);
        switch ((AttachFace)pState.getValue((Property)FACE)) {
            case FLOOR: {
                if (direction.getAxis() == Direction.Axis.X) {
                    return flag ? PRESSED_FLOOR_AABB_X : FLOOR_AABB_X;
                }
                return flag ? PRESSED_FLOOR_AABB_Z : FLOOR_AABB_Z;
            }
            case WALL: {
                switch (direction) {
                    case EAST: {
                        return flag ? PRESSED_EAST_AABB : EAST_AABB;
                    }
                    case WEST: {
                        return flag ? PRESSED_WEST_AABB : WEST_AABB;
                    }
                    case SOUTH: {
                        return flag ? PRESSED_SOUTH_AABB : SOUTH_AABB;
                    }
                }
                return flag ? PRESSED_NORTH_AABB : NORTH_AABB;
            }
        }
        if (direction.getAxis() == Direction.Axis.X) {
            return flag ? PRESSED_CEILING_AABB_X : CEILING_AABB_X;
        }
        return flag ? PRESSED_CEILING_AABB_Z : CEILING_AABB_Z;
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (((Boolean)pState.getValue((Property)POWERED)).booleanValue()) {
            return InteractionResult.CONSUME;
        }
        this.press(pState, pLevel, pPos);
        pLevel.gameEvent((Entity)pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        return InteractionResult.sidedSuccess((boolean)pLevel.isClientSide);
    }

    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, (BlockState)pState.setValue((Property)POWERED, (Comparable)Boolean.valueOf(true)), 3);
        this.updateNeighbours(pState, pLevel, pPos);
        pLevel.scheduleTick(pPos, (Block)this, this.getPressDuration());
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pIsMoving && !pState.is(pNewState.getBlock())) {
            if (((Boolean)pState.getValue((Property)POWERED)).booleanValue()) {
                this.updateNeighbours(pState, pLevel, pPos);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return (Boolean)pBlockState.getValue((Property)POWERED) != false ? 15 : 0;
    }

    public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return (Boolean)pBlockState.getValue((Property)POWERED) != false && VaultButtons.getConnectedDirection((BlockState)pBlockState) == pSide ? 15 : 0;
    }

    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (((Boolean)pState.getValue((Property)POWERED)).booleanValue()) {
            if (this.sensitive) {
                this.checkPressed(pState, (Level)pLevel, pPos);
            } else {
                pLevel.setBlock(pPos, (BlockState)pState.setValue((Property)POWERED, (Comparable)Boolean.valueOf(false)), 3);
                this.updateNeighbours(pState, (Level)pLevel, pPos);
                pLevel.gameEvent((Entity)null, GameEvent.BLOCK_DEACTIVATE, pPos);
            }
        }
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide && this.sensitive && !((Boolean)pState.getValue((Property)POWERED)).booleanValue()) {
            this.checkPressed(pState, pLevel, pPos);
        }
    }

    private void checkPressed(BlockState pState, Level pLevel, BlockPos pPos) {
        boolean flag1;
        List list = pLevel.getEntitiesOfClass(AbstractArrow.class, pState.getShape((BlockGetter)pLevel, pPos).bounds().move(pPos));
        boolean flag = !list.isEmpty();
        if (flag != (flag1 = ((Boolean)pState.getValue((Property)POWERED)).booleanValue())) {
            pLevel.setBlock(pPos, (BlockState)pState.setValue((Property)POWERED, (Comparable)Boolean.valueOf(flag)), 3);
            this.updateNeighbours(pState, pLevel, pPos);
            pLevel.gameEvent((Entity)list.stream().findFirst().orElse(null), flag ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pPos);
        }
        if (flag) {
            pLevel.scheduleTick(new BlockPos((Vec3i)pPos), (Block)this, this.getPressDuration());
        }
    }

    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, (Block)this);
        pLevel.updateNeighborsAt(pPos.relative(VaultButtons.getConnectedDirection((BlockState)pState).getOpposite()), (Block)this);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{FACING, POWERED, FACE});
    }
}

