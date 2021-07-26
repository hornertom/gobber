package com.kwpugh.gobber2.items.rings;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingExplorer extends Item
{
	public ItemCustomRingExplorer(Properties properties)
	{
		super(properties);
	}
	
	int ringExplorerCooldown = GobberConfigBuilder.RING_EXPLORER_COOLDOWN.get();
	
    int min = GobberConfigBuilder.RING_EXPLORER_MIN_RANGE.get();
    int max = GobberConfigBuilder.RING_EXPLORER_MAX_RANGE.get();
    
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		player.getCooldowns().addCooldown(this, ringExplorerCooldown);
		
		if (!world.dimension().equals(Level.OVERWORLD))
		{
			player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_ring_above.line5"), true);
			return InteractionResultHolder.success(stack);	
		}
		
		if (!world.isClientSide)
		{
			//ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
			ServerLevel serverWorld = (ServerLevel)world;		
			BlockPos worldSpawn = serverWorld.getSharedSpawnPos();
		
			Random rand = new Random();
	
	        int x = (int) Math.round(worldSpawn.getX()) + rand.nextInt(max + min) - min;
	        //int y = world.getMaxHeight();
	        int y = 255;
	        int z = (int) Math.round(worldSpawn.getZ()) + rand.nextInt(max + min) - min;
	
	        LevelChunk chunk = world.getChunk(x >> 4, z >> 4);
	        Biome biome = world.getBiome(new BlockPos(x, y, z));
	
	        if ( (biome.getBiomeCategory().getName().equals("ocean")) || 
	        	(biome.getBiomeCategory().getName().equals("river")) || 
	        	(biome.getBiomeCategory().getName().equals("beach"))  )
	        {	
	        	if(world.isClientSide)  //test for server
	        	{
	        		player.getCooldowns().removeCooldown(this);
	        	}
	        	
	        	return use(world, player, hand);
	        }
	
	        while (y > 0) {
	            y--;
	            BlockPos groundPos = new BlockPos(x, y-2, z);
	            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR) && 
	            		(!chunk.getBlockState(groundPos).getBlock().equals(Blocks.BEDROCK) &&
	            		(!chunk.getBlockState(groundPos).getBlock().equals(Blocks.WATER) &&
	            				y-2 != 1))    )
	            {
	                BlockPos legPos = new BlockPos(x, y-1, z);
	                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
	                {
	                    BlockPos headPos = new BlockPos(x, y, z);
	                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
	                    {
	                    	player.stopRiding();
	    	           		((ServerPlayer)player).connection.teleport(x, y, z, player.yRotO, player.xRotO);
	    	           		
	    	           		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
	    	           		
	    	           		return InteractionResultHolder.success(stack);
	                    }
	                }
	            }
	        }		        
	        player.displayClientMessage(new TranslatableComponent("Sorry, no spot found, try again after cooldown"), true);
	        return InteractionResultHolder.success(stack);		 
        }

        return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_explorer.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_explorer.line2",min, max).withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_explorer.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring.cooldown",ringExplorerCooldown).withStyle(ChatFormatting.LIGHT_PURPLE)));
	}  
}
