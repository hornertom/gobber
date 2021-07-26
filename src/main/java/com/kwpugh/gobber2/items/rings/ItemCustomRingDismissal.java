package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingDismissal extends Item
{
	public ItemCustomRingDismissal(Properties properties)
	{
		super(properties);
	}
	double velocity = GobberConfigBuilder.RING_DISMISSAL_VELOCITY.get();
	double lift = GobberConfigBuilder.RING_DISMISSAL_LIFT.get();
	double range = GobberConfigBuilder.RING_DISMISSAL_RANGE.get();
	
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
					Vec3 look = player.getLookAngle().normalize();	
					double lookX = look.x();
					double lookY = look.y();
					double lookZ = look.z();
										
					double x = player.getX();
					double y = player.getY();
					double z = player.getZ();

					double d0 = range;
					double d1 = 4.0D;

					Mob hostileMob = scanForHostileMobs(world, x, y, z, d0, d1);

					if(hostileMob != null)
					{
						hostileMob.push(lookX * velocity, lookY * lift, lookZ * velocity);
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
			MobCategory isCreature = entitymob.getClassification(true);

			// Exclude some of the harder mobs
			if (isCreature == MobCategory.CREATURE ||
					entitymob instanceof Evoker ||
					entitymob instanceof Guardian ||
					entitymob instanceof Vex ||
					entitymob instanceof Vindicator ||
					entitymob instanceof WitherBoss ||
					entitymob instanceof EnderDragon)
			{
				continue;
			}
			else
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
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_dismissal.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_dismissal.line2").withStyle(ChatFormatting.YELLOW)));
	}
}
