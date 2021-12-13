package com.kwpugh.gobber2.items.tools.excavator;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.kwpugh.gobber2.items.toolbaseclasses.ExcavatorUtil;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomExcavatorEnd extends ShovelItem
{
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.GRASS_BLOCK,
			Blocks.DIRT_PATH,
			Blocks.DIRT,
			Blocks.COARSE_DIRT,
			Blocks.RED_SAND,
			Blocks.SAND,
			Blocks.PODZOL,
			Blocks.GRAVEL,
			Blocks.SOUL_SAND,
			Blocks.CLAY);

	public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.DIRT);

	public ItemCustomExcavatorEnd(Tier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override   //Used to override taking damage
	public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entityLiving)
	{
		if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F)
		{
			ExcavatorUtil.attemptBreakNeighbors(world, pos, (Player) entityLiving, BlockTags.MINEABLE_WITH_SHOVEL, EFFECTIVE_MATERIALS);

			stack.hurtAndBreak(0, entityLiving, (p_220038_0_) -> {
				p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			});
		}

		return true;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		stack.setDamageValue(0);  //no damage

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

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_excavator.line1").withStyle(ChatFormatting.GREEN)));
	}
}