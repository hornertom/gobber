package com.kwpugh.gobber2.blocks;

//import java.util.List;
//
//import javax.annotation.Nullable;
//
//import com.kwpugh.gobber2.config.GobberConfigBuilder;
//import com.kwpugh.gobber2.init.BlockEntityInit;
//
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.RenderShape;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.network.chat.Component;
//import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.TranslatableComponent;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//public class BlockMaturator extends Block
//{
//	int radius = GobberConfigBuilder.MATURATOR_RADIUS.get();
//
//	public BlockMaturator(Properties properties)
//	{
//		super(properties);
//	}
//
//	@Override
//	public boolean hasTileEntity(final BlockState state)
//	{
//		return true;
//	}
//
//	@Nullable
//	@Override
//	public BlockEntity createTileEntity(final BlockState state, final BlockGetter world)
//	{
//		// Always use TileEntityType#create to allow registry overrides to work.
//		return BlockEntityInit.BLOCK_MATURATOR.get().create();
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
//		tooltip.add((new TranslatableComponent("item.gobber2.block_maturator.line2").withStyle(ChatFormatting.GREEN)));
//		tooltip.add((new TranslatableComponent("item.gobber2.block_maturator.line3", radius).withStyle(ChatFormatting.LIGHT_PURPLE)));
//	}
//}