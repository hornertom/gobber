package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class EndGobberToolMaterial implements Tier
{
    private static int durability = GobberConfigBuilder.END_GOBBER_TOOLS_DURABILITY.get();
    private static double miningSpeed = GobberConfigBuilder.END_GOBBER_TOOLS_MINING_SPEED.get();
    private static double attackDamage = GobberConfigBuilder.END_GOBBER_TOOLS_ATTACK_DAMAGE.get();
    private static int miningLevel = GobberConfigBuilder.END_GOBBER_TOOLS_MINING_LEVEL.get();
    private static int enchantability = GobberConfigBuilder.END_GOBBER_TOOLS_ENCHANTABILITY.get();

    @Override
    public int getUses()
    {
        return durability;
    }

    @Override
    public float getSpeed()
    {
        return (float) miningSpeed;
    }

    @Override
    public float getAttackDamageBonus()
    {
        return (float) attackDamage;
    }

    @Override
    public int getLevel()
    {
        return miningLevel;
    }

    @Override
    public int getEnchantmentValue()
    {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.of(ItemInit.GOBBER2_INGOT_END.get());
    }
}