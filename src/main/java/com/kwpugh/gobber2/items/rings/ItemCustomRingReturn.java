package com.kwpugh.gobber2.items.rings;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingReturn extends Item
{
	private static int duration = 25;
	//private boolean oneTimeUse = GeneralModConfig.ONE_TIME_USE.get();
	
    public ItemCustomRingReturn(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity)
    {
        if(!world.isClientSide())
        {
            ServerPlayer player = (ServerPlayer) entity;
           
            if(world.dimension().equals(Level.OVERWORLD)) //if dimension is Overworld
            {          	  
                if(player.getRespawnPosition() != null) //player bed location not null
                {                	
                	BlockPos bedLoc = player.getRespawnPosition(); //get player bed position
                	
                	if (player.isPassenger())
                	{
                		player.stopRiding();
                	}
                	
                    setPositionAndUpdate(entity, world, bedLoc);
                    player.displayClientMessage(new TranslatableComponent("item.gobber2.ring_return.line1").withStyle(ChatFormatting.GREEN), true); 
                }
                else
                {
                	 player.displayClientMessage(new TranslatableComponent("item.gobber2.ring_return.line2").withStyle(ChatFormatting.GREEN), true);
                     return stack;
                }
            }
            else
            {
				player.displayClientMessage((new TranslatableComponent("item.gobber2.ring_return.line4").withStyle(ChatFormatting.GREEN)), true);
            }
        }
        
        return stack;        
    }    
  
    @Override
    public int getUseDuration(ItemStack stack)
    {
        return duration;
    }

    public void setPositionAndUpdate(LivingEntity entity, Level world, BlockPos bedLoc)
    {
        entity.teleportTo(bedLoc.getX() + 0.5F, bedLoc.getY() + 0.6F, bedLoc.getZ() + 0.5F);
        entity.fallDistance = 0;
    }
    
    @Override
    public UseAnim getUseAnimation (ItemStack stack)
    {
        return UseAnim.BOW;
    } 
 
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.ring_return.line3").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.ring_return.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.ring_return.line4").withStyle(ChatFormatting.LIGHT_PURPLE)));
	} 
}