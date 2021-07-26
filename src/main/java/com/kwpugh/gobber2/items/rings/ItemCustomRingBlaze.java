package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingBlaze extends Item
{

	public ItemCustomRingBlaze(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof Player && !world.isClientSide)
		{
			Player player = (Player)entity;
			
			ItemStack equipped = player.getMainHandItem();

			if(!world.isClientSide)
			{
				if(stack == equipped)
				{
					double x = player.getX();
					double y = player.getY();
					double z = player.getZ();

					double d0 = 10.0D;
					double d1 = 5.0D;
					
					Mob hostileMob = scanForHostileMobs(world, x, y, z, d0, d1);
		
					if(hostileMob != null)
					{			
						if (!world.isClientSide)
						{	
							hostileMob.spawnAnim();
							hostileMob.remove(Entity.RemovalReason.KILLED);
							hostileMob.spawnAtLocation(Items.BLAZE_ROD, 3);
						}
					}	
				}
			}		
		}
	}
		   
	private Mob scanForHostileMobs(Level world, double xpos, double ypos, double zpos, double d0, double d1)
	{
		List<Mob> list = world.<Mob>getEntitiesOfClass(Mob.class, new AABB
				((double) xpos - d0,
				 (double) ypos - d1,
				 (double) zpos - d0,
				 (double) xpos + d0, ypos + d1,
				 (double) zpos + d0));
	
		Mob closestMob = null;
	
		for (Mob entitymob : list)
		{
			// Only select these types of mobs for killing effect
			if (entitymob instanceof Blaze)
			{
				closestMob = entitymob;
				return closestMob;
			}
		}
		return null;
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_blaze.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_blaze.line2").withStyle(ChatFormatting.YELLOW)));
	}   
}
