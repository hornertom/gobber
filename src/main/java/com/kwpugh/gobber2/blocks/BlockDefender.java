package com.kwpugh.gobber2.blocks;

//import java.util.List;
//
//import javax.annotation.Nullable;
//
//import com.kwpugh.gobber2.config.GobberConfigBuilder;
//
//import com.kwpugh.gobber2.init.BlockEntityInit;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.EntityBlock;
//import net.minecraft.world.level.block.RenderShape;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.network.chat.Component;
//import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.TranslatableComponent;
//import net.minecraft.world.level.BlockGetter;
//
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//public class BlockDefender extends Block implements EntityBlock
//{
//	int radius = GobberConfigBuilder.DEFENDER_RADIUS.get();
//
//	public BlockDefender(Properties properties)
//	{
//		super(properties);
//	}
//
//    @Nullable
//    @Override
//    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
//    {
//        return BlockEntityInit.BLOCK_DEFENDER.get().create(pos, state);
//    }
//
//    @Nullable
//    @Override
//	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_)
//	{
//		return ((level, blockPos, blockState, t) ->
//		{
//			BlockDefenderEntity.tick(level, blockPos, blockState, (BlockDefenderEntity) t);
//		});
//	}
//
//	@Override
//	public RenderShape getRenderShape(BlockState state)
//	{
//		return RenderShape.MODEL;
//	}
//
//	@OnlyIn(Dist.CLIENT)
//	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn)
//	{
//		super.appendHoverText(stack, worldIn, tooltip, flagIn);
//		tooltip.add((new TranslatableComponent("item.gobber2.block_defender.line2").withStyle(ChatFormatting.GREEN)));
//		tooltip.add((new TranslatableComponent("item.gobber2.block_defender.line3").withStyle(ChatFormatting.GREEN)));
//		tooltip.add((new TranslatableComponent("item.gobber2.block_defender.line4", radius).withStyle(ChatFormatting.LIGHT_PURPLE)));
//	}
//}

