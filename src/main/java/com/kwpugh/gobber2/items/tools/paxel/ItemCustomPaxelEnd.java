package com.kwpugh.gobber2.items.tools.paxel;

import java.util.Set;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.init.TagInit;
import com.kwpugh.gobber2.items.toolbaseclasses.PaxelBase;

import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomPaxelEnd extends PaxelBase
{
	public ItemCustomPaxelEnd(float attackDamageIn, float attackSpeedIn, Tier tier, Tag<Block> effectiveBlocksIn,
			Properties builder)
	{
		super(attackDamageIn, attackSpeedIn, tier, TagInit.PAXEL_MINEABLE, builder);
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
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_INGOT_END.get();
	}
}
