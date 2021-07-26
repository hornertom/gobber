package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public enum ToolMaterialList implements Tier
{
	GOBBER2(1.0f, 9.0f, 3800, 4, 20, ItemInit.GOBBER2_INGOT.get()),
	GOBBER2_NETHER(1.0f, 12.0f, 5200, 5, 25, ItemInit.GOBBER2_INGOT_NETHER.get()),
	GOBBER2_END(1.0f, 14.0f, 8000, 5, 30, ItemInit.GOBBER2_INGOT_END.get());
	
	private float attackDamage, efficiency;
	private int durability, harvestLevel, enchantability;
	private Item repairMaterial;
	
	private ToolMaterialList(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial) 
	{
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public float getAttackDamageBonus() 
	{
		return this.attackDamage;
	}

	@Override
	public float getSpeed() 
	{
		return this.efficiency;
	}

	@Override
	public int getEnchantmentValue() 
	{
		return this.enchantability;
	}

	@Override
	public int getLevel()
	{
		return this.harvestLevel;
	}

	@Override
	public int getUses() 
	{
		return this.durability;
	}

	@Override
	public Ingredient getRepairIngredient() 
	{
		return Ingredient.of(this.repairMaterial);
	}
}
