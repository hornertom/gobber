package com.kwpugh.gobber2.items.tools.sword;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomSwordEnd extends SwordItem
{
	public ItemCustomSwordEnd(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        if (!worldIn.isClientSide)
        {
        	//TBD
        }
        return new InteractionResultHolder<ItemStack>(InteractionResult.PASS, playerIn.getItemInHand(handIn));
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
        if(GobberConfigBuilder.END_GOBBER_TOOLS_UNBREAKABLE.get())
        {
            stack.getOrCreateTag().putBoolean("Unbreakable", true);
        }
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
}