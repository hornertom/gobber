package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.util.RayTraceUtil;

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
import net.minecraft.world.phys.HitResult;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingBlink extends Item
{
	public ItemCustomRingBlink(Properties properties)
	{
		super(properties);
	}

	int blinkCooldown = GobberConfigBuilder.RING_BLINK_COOLDOWN.get();
	  
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		player.getCooldowns().addCooldown(this, blinkCooldown);
		 
		if (!world.isClientSide)
		{						
			HitResult pos = RayTraceUtil.getNearestPositionWithAir(world, player, 100);
            if(pos != null && (pos.getType() == HitResult.Type.BLOCK || player.xRotO >= -5))
            {
            	int side = pos.getType().ordinal();
                if(side != -1)
                {
                    double x = pos.getLocation().x-(side == 4 ? 0.5 : 0)+(side == 5 ? 0.5 : 0);
                    double y = pos.getLocation().y-(side == 0 ? 2.0 : 0)+(side == 1 ? 0.5 : 0);
                    double z = pos.getLocation().z-(side == 2 ? 0.5 : 0)+(side == 3 ? 0.5 : 0);
	           		
	           		player.stopRiding();
	           		((ServerPlayer)player).connection.teleport(x, y, z, player.yRotO, player.xRotO);

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);

                    return InteractionResultHolder.success(stack);
                }
            }		 
        }

        return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_blink.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_blink.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_blink.line3").withStyle(ChatFormatting.RED)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring.cooldown",blinkCooldown).withStyle(ChatFormatting.LIGHT_PURPLE)));
	}   
}
