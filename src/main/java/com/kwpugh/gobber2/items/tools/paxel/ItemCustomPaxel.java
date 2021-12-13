package com.kwpugh.gobber2.items.tools.paxel;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.init.TagInit;
import com.kwpugh.gobber2.items.toolbaseclasses.PaxelBase;

import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;

public class ItemCustomPaxel extends PaxelBase
{
	public ItemCustomPaxel(float attackDamageIn, float attackSpeedIn, Tier tier, Tag<Block> effectiveBlocksIn,
						   Properties builder)
	{
		super(attackDamageIn, attackSpeedIn, tier, TagInit.PAXEL_MINEABLE, builder);
	}

	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_INGOT.get();
	}
}
