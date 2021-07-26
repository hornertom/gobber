package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.util.EnableUtil;
import com.kwpugh.gobber2.util.MagnetRangeUtil;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingAttraction extends Item
{
	public ItemCustomRingAttraction(Properties properties)
	{
		super(properties);
	}
	
	int ringAttractionBlocking = GobberConfigBuilder.RING_ATTRACTION_BLOCK_DISTANCE.get();
	boolean ringAttractionMode = GobberConfigBuilder.RING_ATTRACTION_MODE.get();
	
	static int range;
	
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{
		if(entity instanceof Player && !world.isClientSide && EnableUtil.isEnabled(stack))
		{
			Player player = (Player)entity;
			
			boolean init = MagnetRangeUtil.getCurrentlySet(stack);
			
			// Set default range or read range from NBT if it exists
			if(!init)
			{
				range = 8;
			}
			else
			{
				range = MagnetRangeUtil.getCurrentRange(stack);
			}			

			double x = player.getX();
			double y = player.getY();
			double z = player.getZ();

			// Check for a particular block that stops the attraction
			BlockPos playerPos = new BlockPos(player.position());
			for (BlockPos targetPos : BlockPos.betweenClosed(playerPos.offset(-range, -ringAttractionBlocking, -range), playerPos.offset(range, ringAttractionBlocking, range)))
			{
				BlockState blockstate = world.getBlockState(targetPos);
				
				if ((blockstate.getBlock() == Blocks.COAL_BLOCK))
				{
					return;
				}				
			}
			
			//Scan for and collect items
			List<ItemEntity> items = entity.level.getEntitiesOfClass(ItemEntity.class, new AABB(x - range, y - range, z - range, x + range, y + range, z + range));
			for(ItemEntity e: items)
			{
				if(!player.isShiftKeyDown() && !e.getPersistentData().getBoolean("PreventRemoteMovement"))
				{
					if(ringAttractionMode)
					{
						e.playerTouch(player);
					}
					else
					{
						double factor = 0.035;
						e.push((x - e.getX()) * factor, (y - e.getY()+1.25) * factor, (z - e.getZ()) * factor);	
					}
				}
			}

			//Scan for and collect XP Orbs
			List<ExperienceOrb> xp = entity.level.getEntitiesOfClass(ExperienceOrb.class, new AABB(x - range, y - range, z - range, x + range, y + range, z + range));
			for(ExperienceOrb orb: xp)
			{
				if(!player.isShiftKeyDown())
				{ 
					if(ringAttractionMode)
					{
						orb.playerTouch(player);
					}
					else
					{
						double factor = 0.035;
						orb.push((x - orb.getX()) * factor, (y - orb.getY()+1.25) * factor, (z - orb.getZ()) * factor);	
					}
				}
			}
		}
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		ItemStack stack = player.getItemInHand(hand);	
		
		if((!world.isClientSide) && (!player.isShiftKeyDown()))
        {
            EnableUtil.changeEnabled(player, hand);
            player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line2", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.GREEN), true);
            return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, player.getItemInHand(hand));
        }		
		
        if((!world.isClientSide) && (player.isShiftKeyDown()))
        {       	
        	switch(range)
			{
				case 8:
					range = 10;
					MagnetRangeUtil.setCurrentRange(stack, range);
					player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRangeUtil.getCurrentRange(stack)).withStyle(ChatFormatting.GREEN)), true);
					break;
				case 10:
					range = 12;
					MagnetRangeUtil.setCurrentRange(stack, range);
					player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRangeUtil.getCurrentRange(stack)).withStyle(ChatFormatting.GREEN)), true);
					break;
				case 12:
					range = 14;
					MagnetRangeUtil.setCurrentRange(stack, range);
					player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRangeUtil.getCurrentRange(stack)).withStyle(ChatFormatting.GREEN)), true);
					break;
				case 14:
					range = 4;
					MagnetRangeUtil.setCurrentRange(stack, range);
					player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRangeUtil.getCurrentRange(stack)).withStyle(ChatFormatting.GREEN)), true);
					break;			
				case 4:
					range = 8;
					MagnetRangeUtil.setCurrentRange(stack, range);
					player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRangeUtil.getCurrentRange(stack)).withStyle(ChatFormatting.GREEN)), true);
					break;		
				default:
					break;
			}
        }
        
        return super.use(world, player, hand);
    }
	
	@Override
	public boolean isFoil(ItemStack stack)
	{
		return EnableUtil.isEnabled(stack);
	}
	  
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{	
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line2", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line4").withStyle(ChatFormatting.RED)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line6").withStyle(ChatFormatting.BLUE)));
		
		if(EnableUtil.isEnabled(stack))  // Will still show range 0 on first use until range is changed
		{
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line5",MagnetRangeUtil.getCurrentRange(stack)).withStyle(ChatFormatting.LIGHT_PURPLE)));	
		}	
	}     
}
