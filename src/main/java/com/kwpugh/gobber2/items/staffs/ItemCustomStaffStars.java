package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomStaffStars extends Item
{
	public ItemCustomStaffStars(Properties properties)
	{
		super(properties);
	}

    @Override
    public InteractionResult useOn(UseOnContext iuc)
    {    	
    	BlockPos torchPos;
    	BlockPos pos = iuc.getClickedPos();
    	Level world = iuc.getLevel();
    	BlockState state = iuc.getLevel().getBlockState(pos);
    	Block block = iuc.getLevel().getBlockState(pos).getBlock();
    	
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
    		if(state.isRedstoneConductor(world, pos))
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
    
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_stars.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_stars.line2").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_stars.line3").withStyle(ChatFormatting.YELLOW)));
	}
}
