package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomStaffEnsnarement extends Item
{
	public ItemCustomStaffEnsnarement(Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity)
	{		
		if (entity instanceof Player || !entity.canChangeDimensions() || !entity.isAlive())
    
			return false;

        if((stack.getOrCreateTagElement("mob_data").isEmpty()) &&
        		(entity instanceof Animal ||	
        				entity instanceof Horse ||
        				entity instanceof Donkey ||
        				entity instanceof Llama ||
        				entity instanceof Mule ||
        				entity instanceof AbstractGolem ||
        				entity instanceof Monster ||
        				entity instanceof Villager ||
        				entity instanceof Phantom))
			{
        		CompoundTag tag = entity.serializeNBT();
		
        		if (!player.level.isClientSide)
        		{
        			entity.remove(Entity.RemovalReason.KILLED);
        			stack.getTag().put("mob_data", tag);
        			stack.getTag().putString("name", entity.getDisplayName().getString());
        		}

        		return true;
			}
        
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public InteractionResult useOn(UseOnContext iuc)
	{
		CompoundTag tag = iuc.getItemInHand().getOrCreateTagElement("mob_data");
		if (!tag.isEmpty())
		{
			BlockPos pos = iuc.getClickedPos().relative(iuc.getClickedFace());
			if (!iuc.getLevel().isClientSide)
			{
				Entity entity = EntityType.loadEntityRecursive(tag, iuc.getLevel(), a -> a);
				if (entity != null)
				{
					entity.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
					((ServerLevel) iuc.getLevel()).addFreshEntity(entity);
					iuc.getItemInHand().getTag().remove("mob_data");

					if (iuc.getItemInHand().hurt(1, iuc.getLevel().random, (ServerPlayer) iuc.getPlayer()))
					{
						iuc.getItemInHand().shrink(1);
					}
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.FAIL;
	}

	@Override
	public CompoundTag getShareTag(ItemStack stack)
	{
		CompoundTag tag = super.getShareTag(stack);
		CompoundTag mob = new CompoundTag();

		if (tag == null)
		{
			return null;
		}

		tag = tag.copy();

		if (tag.getCompound("mob_data").contains("id"))
		{
			mob.putString("id", tag.getCompound("mob_data").getString("id"));
		}

		tag.put("mob_data", mob);

		return tag;
	}

	@Override
	public boolean isFoil(ItemStack stack)
	{
		return stack.hasTag() && !stack.getOrCreateTagElement("mob_data").isEmpty();
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_ensnarement.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_ensnarement.line2").withStyle(ChatFormatting.GREEN)));

		if (stack.hasTag())
		{
			CompoundTag tag = stack.getOrCreateTagElement("mob_data");

			if (tag.isEmpty())
			{
				tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_ensnarement.line3").withStyle(ChatFormatting.GREEN)));
			}
			else
			{
				tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_ensnarement.line4", stack.getTag().getString("name")).withStyle(ChatFormatting.GREEN)));
			}
		}
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_ensnarement.line5").withStyle(ChatFormatting.AQUA)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_ensnarement.line6").withStyle(ChatFormatting.YELLOW)));
	}
}