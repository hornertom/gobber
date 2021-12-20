package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ToolMaterialTiers
{
    private static int gobberDurability = GobberConfigBuilder.GOBBER_TOOLS_DURABILITY.get();
    private static double gobberMiningSpeed = GobberConfigBuilder.GOBBER_TOOLS_MINING_SPEED.get();
    private static double gobberAttackDamage = GobberConfigBuilder.GOBBER_TOOLS_ATTACK_DAMAGE.get();
    private static int gobberMiningLevel = 4;
    private static int gobberEnchantability = GobberConfigBuilder.GOBBER_TOOLS_ENCHANTABILITY.get();

    private static int netherDurability = GobberConfigBuilder.NETHER_GOBBER_TOOLS_DURABILITY.get();
    private static double netherMiningSpeed = GobberConfigBuilder.NETHER_GOBBER_TOOLS_MINING_SPEED.get();
    private static double netherAttackDamage = GobberConfigBuilder.NETHER_GOBBER_TOOLS_ATTACK_DAMAGE.get();
    private static int netherMiningLevel = 5;
    private static int netherEnchantability = GobberConfigBuilder.NETHER_GOBBER_TOOLS_ENCHANTABILITY.get();

    private static int endDurability = GobberConfigBuilder.END_GOBBER_TOOLS_DURABILITY.get();
    private static double endMiningSpeed = GobberConfigBuilder.END_GOBBER_TOOLS_MINING_SPEED.get();
    private static double endAttackDamage = GobberConfigBuilder.END_GOBBER_TOOLS_ATTACK_DAMAGE.get();
    private static int endMiningLevel = 5;
    private static int endEnchantability = GobberConfigBuilder.END_GOBBER_TOOLS_ENCHANTABILITY.get();


    public static final Tier OVERWORLD_GOBBER = new ForgeTier(gobberMiningLevel, gobberDurability, (float) gobberMiningSpeed, (float) gobberAttackDamage, gobberEnchantability,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.GOBBER2_INGOT.get()));

    public static final Tier NETHER_GOBBER = new ForgeTier(netherMiningLevel, netherDurability, (float) netherMiningSpeed, (float) netherAttackDamage, netherEnchantability,
            Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ItemInit.GOBBER2_INGOT_NETHER.get()));

    public static final Tier END_GOBBER = new ForgeTier(endMiningLevel, endDurability, (float) endMiningSpeed, (float) endAttackDamage, endEnchantability,
            Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ItemInit.GOBBER2_INGOT_END.get()));
}
