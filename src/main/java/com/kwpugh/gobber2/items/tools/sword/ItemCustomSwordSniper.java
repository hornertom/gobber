package com.kwpugh.gobber2.items.tools.sword;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
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

public class ItemCustomSwordSniper extends SwordItem
{
	public ItemCustomSwordSniper(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	int swordSniperCooldown = GobberConfigBuilder.SNIPER_SWORD_COOLDOWN.get();
	boolean enableDragonStar = GobberConfigBuilder.ENABLE_DRAGON_STAR_OFFHAND.get();
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		ItemStack offHand = player.getOffhandItem();
		Item stack2 = offHand.getItem().asItem();
		
		// Set sword cooldown if no dragon star in offhand	
		if(      !(stack2 == ItemInit.DRAGON_STAR.get())      )
		{
			player.getCooldowns().addCooldown(this, swordSniperCooldown);
		}
		
		// Set cooldown if special function is false in config
		if(!enableDragonStar)
		{
			player.getCooldowns().addCooldown(this, swordSniperCooldown);
		}
				
		if(!world.isClientSide)
		{
		    if(player.isShiftKeyDown())
		    {
		        EnableUtil.changeEnabled(player, hand);
		        player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_sword_sniper.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED), true);		        
		    }
		    
		    if(EnableUtil.isEnabled(stack))
			{
	            ArrowItem itemarrow = (ArrowItem)Items.ARROW;
	            AbstractArrow entityarrow = itemarrow.createArrow(world, new ItemStack(Items.ARROW), player);
	            float arrowVelocity = 60.0F;
	            entityarrow.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, arrowVelocity, 1.0F);
	            entityarrow.setBaseDamage(1);
	            world.addFreshEntity(entityarrow);
	            entityarrow.pickup = AbstractArrow.Pickup.DISALLOWED;
			}	
		    else
		    {
		    	//TBD
		    }
		    return new InteractionResultHolder<ItemStack>(InteractionResult.PASS, player.getItemInHand(hand));
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
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_sword_sniper.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_sword_sniper.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_sword_sniper.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_sword_sniper.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_sword_sniper.line5").withStyle(ChatFormatting.LIGHT_PURPLE)));
	}  
}