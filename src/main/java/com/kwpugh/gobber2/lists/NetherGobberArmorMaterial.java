package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class NetherGobberArmorMaterial implements ArmorMaterial
{
    private static int durabilityMultiplier = GobberConfigBuilder.NETHER_GOBBER_ARMOR_DURABILITY_MULTIPLIER.get();
    private static int enchantability = GobberConfigBuilder.NETHER_GOBBER_ARMOR_ENCHANTABILITY.get();
    private static double toughness = GobberConfigBuilder.NETHER_GOBBER_ARMOR_TOUGHNESS.get();
    private static double knochback = GobberConfigBuilder.NETHER_GOBBER_ARMOR_KNOCKBACK.get();

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNT = new int[]{7, 10, 12, 7};

    @Override
    public int getDurabilityForSlot(EquipmentSlot equipmentSlot)
    {
        return BASE_DURABILITY[equipmentSlot.getIndex()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot equipmentSlot)
    {
        return PROTECTION_AMOUNT[equipmentSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue()
    {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound()
    {
        return SoundEvents.ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.of(ItemInit.GOBBER2_INGOT_NETHER.get());
    }

    @Override
    public String getName()
    {
        return "gobber2:gobber2_nether";
    }

    @Override
    public float getToughness()
    {
        return (float) toughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return (float) knochback;
    }
}