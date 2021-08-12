package com.kwpugh.gobber2.items.tools.paxel;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.init.TagInit;
import com.kwpugh.gobber2.items.toolbaseclasses.PaxelBase;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;


public class ItemCustomPaxelStars extends PaxelBase
{
	public ItemCustomPaxelStars(float attackDamageIn, float attackSpeedIn, Tier tier, Tag<Block> mineabl,
								Properties builder)
	{
		super(attackDamageIn, attackSpeedIn, tier, TagInit.PAXEL_MINEABLE, builder);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state)
	{
		if (state.is(BlockTags.MINEABLE_WITH_AXE)) return speed;
		if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) return speed;
		if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) return speed;
		return super.getDestroySpeed(stack, state);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ToolAction toolAction)
	{
		boolean actionTest = false;
		if(ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction) ||
				ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction))
		{
			actionTest = true;
		}

		return actionTest;
	}

	@Override
	public InteractionResult useOn(UseOnContext iuc)
	{
		BlockPos torchPos;
		BlockPos pos = iuc.getClickedPos();
		Level world = iuc.getLevel();
		BlockPos blockpos = iuc.getClickedPos();
		Player player = iuc.getPlayer();
		ItemStack stack = player.getMainHandItem();
		BlockState blockstate = world.getBlockState(blockpos);

		if(!EnableUtil.isEnabled(stack))
		{
			// Log-stripping logic
			Level level = iuc.getLevel();
			Optional<BlockState> optional = Optional.ofNullable(blockstate.getToolModifiedState(level, blockpos, player, iuc.getItemInHand(), ToolActions.AXE_STRIP));
			Optional<BlockState> optional1 = Optional.ofNullable(blockstate.getToolModifiedState(level, blockpos, player, iuc.getItemInHand(), ToolActions.AXE_SCRAPE));
			Optional<BlockState> optional2 = Optional.ofNullable(blockstate.getToolModifiedState(level, blockpos, player, iuc.getItemInHand(), ToolActions.AXE_WAX_OFF));
			ItemStack itemstack = iuc.getItemInHand();
			Optional<BlockState> optional3 = Optional.empty();
			if (optional.isPresent())
			{
				level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
				optional3 = optional;
			}
			else if (optional1.isPresent())
			{
				level.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.levelEvent(player, 3005, blockpos, 0);
				optional3 = optional1;
			}
			else if (optional2.isPresent())
			{
				level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.levelEvent(player, 3004, blockpos, 0);
				optional3 = optional2;
			}

			if (optional3.isPresent())
			{
				if (player instanceof ServerPlayer)
				{
					CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
				}

				level.setBlock(blockpos, optional3.get(), 11);

				if (player != null)
				{
					itemstack.hurtAndBreak(1, player, (p_150686_) -> {
						p_150686_.broadcastBreakEvent(iuc.getHand());
					});
				}

				return InteractionResult.sidedSuccess(level.isClientSide);
			}
			else
			{
				// shovel logic
				if (iuc.getClickedFace() == Direction.DOWN)
				{
					return InteractionResult.PASS;
				}
				else
				{
					BlockState blockstate1 = blockstate.getToolModifiedState(level, blockpos, player, iuc.getItemInHand(), ToolActions.SHOVEL_FLATTEN);
					BlockState blockstate2 = null;
					if (blockstate1 != null && level.isEmptyBlock(blockpos.above()))
					{
						level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
						blockstate2 = blockstate1;
					}
					else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT))
					{
						if (!level.isClientSide())
						{
							level.levelEvent((Player)null, 1009, blockpos, 0);
						}

						CampfireBlock.dowse(iuc.getPlayer(), level, blockpos, blockstate);
						blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
					}

					if (blockstate2 != null)
					{
						if (!level.isClientSide)
						{
							level.setBlock(blockpos, blockstate2, 11);
							if (player != null)
							{
								iuc.getItemInHand().hurtAndBreak(1, player, (p_43122_) ->
								{
									p_43122_.broadcastBreakEvent(iuc.getHand());
								});
							}
						}

						return InteractionResult.sidedSuccess(level.isClientSide);
					}
				}
			}

			stack.setDamageValue(0);  //no damage

			return InteractionResult.SUCCESS;
		}

		if(iuc.getLevel().getBlockState(pos).getBlock() == Blocks.TORCH
				|| iuc.getLevel().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
		{
			return InteractionResult.FAIL;
		}

		Boolean isWallTorch = false;
		switch(iuc.getClickedFace())
		{
			case DOWN:
				return InteractionResult.FAIL;
			case UP:
				torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
				break;
			case NORTH:
				torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
				isWallTorch = true;
				break;
			case SOUTH:
				torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
				isWallTorch = true;
				break;
			case WEST:
				torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
				isWallTorch = true;
				break;
			case EAST:
				torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
				isWallTorch = true;
				break;
			default:
				return InteractionResult.FAIL;
		}

		if(iuc.getLevel().getBlockState(torchPos).isAir() || iuc.getLevel().getBlockState(torchPos).getFluidState().isSource())
		{
			if(blockstate.isRedstoneConductor(world, pos))
			{
				if (isWallTorch)
				{
					iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.WALL_TORCH.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, iuc.getClickedFace()));
					iuc.getLevel().playSound(null, iuc.getPlayer().blockPosition(), SoundEvents.WOOD_PLACE, SoundSource.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
				}
				else
				{
					iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.TORCH.defaultBlockState());
					iuc.getLevel().playSound(null, iuc.getPlayer().blockPosition(), SoundEvents.WOOD_PLACE, SoundSource.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
				}
			}

			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);

		if(!world.isClientSide && player.isShiftKeyDown())
		{
			EnableUtil.changeEnabled(player, hand);
			player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED), true);
			return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, player.getItemInHand(hand));
		}
		return super.use(world, player, hand);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		stack.setDamageValue(0);  //no damage

		return true;
	}

	public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
	{
		if (!worldIn.isClientSide && (double)state.getDestroySpeed(worldIn, pos) != 0.0D)
		{
			stack.setDamageValue(0);
		}
		return true;
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn)
	{
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line5").withStyle(ChatFormatting.LIGHT_PURPLE)));
	}
}










//public class ItemCustomPaxelStars extends PaxelBase
//{
//	public ItemCustomPaxelStars(float attackDamageIn, float attackSpeedIn, Tier tier, Tag<Block> mineabl,
//					 Properties builder)
//	{
//		super(attackDamageIn, attackSpeedIn, tier, TagInit.PAXEL_MINEABLE,
//				builder.addToolType(net.minecraftforge.common.ToolType.AXE, tier.getLevel())
//						.addToolType(net.minecraftforge.common.ToolType.PICKAXE, tier.getLevel())
//						.addToolType(net.minecraftforge.common.ToolType.SHOVEL, tier.getLevel()));
//	}
//
//	public boolean isCorrectToolForDrops(BlockState blockIn) {
//		int i = this.getTier().getLevel();
//		return i >= blockIn.getHarvestLevel();
//	}
//
//	public float getDestroySpeed(ItemStack stack, BlockState state) {
//		Material material = state.getMaterial();
//		return material != Material.METAL && material != Material.HEAVY_METAL && material != Material.STONE
//				&& material != Material.WOOD && material != Material.PLANT ? super.getDestroySpeed(stack, state)
//				: this.speed;
//	}
//
//    @Override
//    public InteractionResult useOn(UseOnContext iuc)
//    {
//    	BlockPos torchPos;
//    	BlockPos pos = iuc.getClickedPos();
//    	Level world = iuc.getLevel();
//    	BlockPos blockpos = iuc.getClickedPos();
//    	Player player = iuc.getPlayer();
//    	ItemStack stack = player.getMainHandItem();
//    	BlockState blockstate = world.getBlockState(blockpos);
//
//    	if(!EnableUtil.isEnabled(stack))
//    	{
//			// Log-stripping logic
//			Level level = iuc.getLevel();
//			Optional<BlockState> optional = Optional.ofNullable(blockstate.getToolModifiedState(level, blockpos, player, iuc.getItemInHand(),  net.minecraftforge.common.ToolType.AXE));
//			Optional<BlockState> optional1 = WeatheringCopper.getPrevious(blockstate);
//			Optional<BlockState> optional2 = Optional.ofNullable(HoneycombItem.WAX_OFF_BY_BLOCK.get().get(blockstate.getBlock())).map((p_150694_) -> {
//				return p_150694_.withPropertiesOf(blockstate);
//			});
//			ItemStack itemstack = iuc.getItemInHand();
//			Optional<BlockState> optional3 = Optional.empty();
//			if (optional.isPresent())
//			{
//				level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
//				optional3 = optional;
//			}
//			else if (optional1.isPresent())
//			{
//				level.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
//				level.levelEvent(player, 3005, blockpos, 0);
//				optional3 = optional1;
//			}
//			else if (optional2.isPresent())
//			{
//				level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
//				level.levelEvent(player, 3004, blockpos, 0);
//				optional3 = optional2;
//			}
//
//			if (optional3.isPresent())
//			{
//				if (player instanceof ServerPlayer)
//				{
//					CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
//				}
//
//				level.setBlock(blockpos, optional3.get(), 11);
//
//				if (player != null)
//				{
//					itemstack.hurtAndBreak(1, player, (p_150686_) -> {
//						p_150686_.broadcastBreakEvent(iuc.getHand());
//					});
//				}
//
//				return InteractionResult.sidedSuccess(level.isClientSide);
//			}
//			else
//			{
//				// shovel logic
//				if (iuc.getClickedFace() == Direction.DOWN)
//				{
//					return InteractionResult.PASS;
//				}
//				else
//				{
//					BlockState blockstate1 = blockstate.getToolModifiedState(level, blockpos, player, iuc.getItemInHand(), net.minecraftforge.common.ToolType.SHOVEL);
//					BlockState blockstate2 = null;
//					if (blockstate1 != null && level.isEmptyBlock(blockpos.above()))
//					{
//						level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
//						blockstate2 = blockstate1;
//					}
//					else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT))
//					{
//						if (!level.isClientSide())
//						{
//							level.levelEvent((Player)null, 1009, blockpos, 0);
//						}
//
//						CampfireBlock.dowse(iuc.getPlayer(), level, blockpos, blockstate);
//						blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
//					}
//
//					if (blockstate2 != null)
//					{
//						if (!level.isClientSide)
//						{
//							level.setBlock(blockpos, blockstate2, 11);
//							if (player != null)
//							{
//								iuc.getItemInHand().hurtAndBreak(1, player, (p_43122_) ->
//								{
//									p_43122_.broadcastBreakEvent(iuc.getHand());
//								});
//							}
//						}
//
//						return InteractionResult.sidedSuccess(level.isClientSide);
//					}
//				}
//			}
//
//            stack.setDamageValue(0);  //no damage
//
//            return InteractionResult.SUCCESS;
//    	}
//
//    	if(iuc.getLevel().getBlockState(pos).getBlock() == Blocks.TORCH
//				|| iuc.getLevel().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
//		{
//			return InteractionResult.FAIL;
//		}
//
//    	Boolean isWallTorch = false;
//    	switch(iuc.getClickedFace())
//    	{
//    	case DOWN:
//    		return InteractionResult.FAIL;
//    	case UP:
//    		torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
//    		break;
//    	case NORTH:
//    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
//    		isWallTorch = true;
//    		break;
//    	case SOUTH:
//    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
//    		isWallTorch = true;
//    		break;
//    	case WEST:
//    		torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
//    		isWallTorch = true;
//    		break;
//    	case EAST:
//    		torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
//    		isWallTorch = true;
//    		break;
//    	default:
//    		return InteractionResult.FAIL;
//    	}
//
//    	if(iuc.getLevel().getBlockState(torchPos).isAir() || iuc.getLevel().getBlockState(torchPos).getFluidState().isSource())
//    	{
//    		if(blockstate.isRedstoneConductor(world, pos))
//    		{
//    			if (isWallTorch)
//        		{
//        			iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.WALL_TORCH.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, iuc.getClickedFace()));
//        			iuc.getLevel().playSound(null, iuc.getPlayer().blockPosition(), SoundEvents.WOOD_PLACE, SoundSource.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
//        		}
//        		else
//        		{
//        			iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.TORCH.defaultBlockState());
//        			iuc.getLevel().playSound(null, iuc.getPlayer().blockPosition(), SoundEvents.WOOD_PLACE, SoundSource.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
//        		}
//    		}
//
//    		return InteractionResult.SUCCESS;
//    	}
//    	return InteractionResult.FAIL;
//    }
//
//	@Override
//    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
//    {
//		ItemStack stack = player.getItemInHand(hand);
//
//        if(!world.isClientSide && player.isShiftKeyDown())
//        {
//            EnableUtil.changeEnabled(player, hand);
//            player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED), true);
//            return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, player.getItemInHand(hand));
//        }
//        return super.use(world, player, hand);
//    }
//
//	@Override
//    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
//    {
//		stack.setDamageValue(0);  //no damage
//
//        return true;
//    }
//
//    public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
//    {
//        if (!worldIn.isClientSide && (double)state.getDestroySpeed(worldIn, pos) != 0.0D)
//        {
//            stack.setDamageValue(0);
//        }
//        return true;
//    }
//
//	@Override
//	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn)
//	{
//		stack.getOrCreateTag().putBoolean("Unbreakable", true);
//	}
//
//	@Override
//	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
//	{
//		return true;
//	}
//
//	@Override
//	@OnlyIn(Dist.CLIENT)
//	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
//	{
//		super.appendHoverText(stack, worldIn, tooltip, flagIn);
//		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line2").withStyle(ChatFormatting.YELLOW)));
//		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line3").withStyle(ChatFormatting.YELLOW)));
//		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED)));
//		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line5").withStyle(ChatFormatting.LIGHT_PURPLE)));
//	}
//}
