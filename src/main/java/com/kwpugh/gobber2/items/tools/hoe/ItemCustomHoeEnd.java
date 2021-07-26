package com.kwpugh.gobber2.items.tools.hoe;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ItemCustomHoeEnd extends HoeItem
{
	public ItemCustomHoeEnd(Tier p_41336_, int p_41337_, float p_41338_, Properties p_41339_)
	{
		super(p_41336_, p_41337_, p_41338_, p_41339_);
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
}
