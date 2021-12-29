package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class GobberArmorMaterial implements ArmorMaterial
{
    private static int durabilityMultiplier = GobberConfigBuilder.GOBBER_ARMOR_DURABILITY_MULTIPLIER.get();
    private static int enchantability = GobberConfigBuilder.GOBBER_ARMOR_ENCHANTABILITY.get();
    private static double toughness = GobberConfigBuilder.GOBBER_ARMOR_TOUGHNESS.get();
    private static double knochback = GobberConfigBuilder.GOBBER_ARMOR_KNOCKBACK.get();
    private static int gobberHead = GobberConfigBuilder.GOBBER_HEAD_PROTECTION.get();
    private static int gobberChest = GobberConfigBuilder.GOBBER_CHEST_PROTECTION.get();
    private static int gobberLeggings = GobberConfigBuilder.GOBBER_LEGGINGS_PROTECTION.get();
    private static int gobberBoots = GobberConfigBuilder.GOBBER_BOOTS_PROTECTION.get();

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNT = new int[]{gobberHead, gobberLeggings, gobberChest, gobberBoots};

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
        return Ingredient.of(ItemInit.GOBBER2_INGOT.get());
    }

    @Override
    public String getName()
    {
        return "gobber2:gobber2";
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
