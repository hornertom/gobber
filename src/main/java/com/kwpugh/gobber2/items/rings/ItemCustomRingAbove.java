package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingAbove extends Item
{
	public ItemCustomRingAbove(Properties properties)
	{
		super(properties);
	}

	int ringAboveCooldown = GobberConfigBuilder.RING_ABOVE_COOLDOWN.get();
	
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
	
		//if (world.getDimension().getType() != DimensionType.OVERWORLD)
		if(!world.dimension().equals(Level.OVERWORLD))
		{
			player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_ring_above.line5"), true);
		}
		
		player.getCooldowns().addCooldown(this, ringAboveCooldown);
		
		if (!world.isClientSide && (world.dimension().equals(Level.OVERWORLD)))
		{
			if(player.isShiftKeyDown())
			{
				//Checking from bottom of world and working upward
				double x = player.getX();
				double y = 0;
				double z = player.getZ();
				
				LevelChunk chunk = world.getChunk((int) player.getX() >> 4, (int)player.getZ() >> 4);

				while (y < world.getMaxBuildHeight())
				{
		            y++;

		            BlockPos groundPos = new BlockPos(x, y+2, z);
		            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR))
		            {
		                BlockPos legPos = new BlockPos(x, y+1, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                    BlockPos headPos = new BlockPos(x, y, z);
		                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		                    {	                    	
		                    	player.stopRiding();
		    	           		((ServerPlayer)player).connection.teleport(x, y, z, player.yRotO, player.xRotO);
		                    	
		    	           		return InteractionResultHolder.success(stack);                       
		                    }
		                }
		            }
		        }
				
			}
			else
			{	
				//Checking from top of world downward
				double x = player.getX();
				//double y = world.getMaxHeight();
				double y = 255;
				double z = player.getZ();
				
				LevelChunk chunk = world.getChunk((int) player.getX() >> 4, (int)player.getZ() >> 4);

				while (y > 0)
				{
		            y--;

		            BlockPos groundPos = new BlockPos(x, y-2, z);
		            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR))
		            {
		                BlockPos legPos = new BlockPos(x, y-1, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                    BlockPos headPos = new BlockPos(x, y, z);
		                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		                    {	                    	
		                    	player.stopRiding();
		    	           		((ServerPlayer)player).connection.teleport(x, y, z, player.yRotO, player.xRotO);
		                    	
		    	           		return InteractionResultHolder.success(stack);                       
		                    }
		                }
		            }
		        }		
			}
		}
		
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_above.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_above.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_above.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_above.line4").withStyle(ChatFormatting.RED)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring.cooldown",ringAboveCooldown).withStyle(ChatFormatting.LIGHT_PURPLE)));
	} 	
}
