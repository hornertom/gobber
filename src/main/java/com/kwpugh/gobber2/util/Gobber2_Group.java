package com.kwpugh.gobber2.util;


import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Gobber2_Group extends CreativeModeTab
{
	public Gobber2_Group() 
	{
		super("gobber2");
	}

	@Override
	public ItemStack makeIcon() 
	{
		return new ItemStack(ItemInit.GOBBER2_SWORD_NETHER.get());
	}	
}
