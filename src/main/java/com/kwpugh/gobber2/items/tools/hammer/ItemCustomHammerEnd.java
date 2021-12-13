package com.kwpugh.gobber2.items.tools.hammer;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.kwpugh.gobber2.items.toolbaseclasses.HammerUtil;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomHammerEnd extends PickaxeItem
{
	public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.STONE, Material.METAL, Material.GLASS, Material.ICE, Material.ICE_SOLID, Material.HEAVY_METAL);

	public ItemCustomHammerEnd(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
	 */
	public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entityLiving)
	{
		if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F)
		{
			HammerUtil.attemptBreakNeighbors(world, pos, (Player) entityLiving, EFFECTIVE_MATERIALS);

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

	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{
		//Nothing right now
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		if(!world.isClientSide && player.isShiftKeyDown())
		{
			return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, player.getItemInHand(hand));
		}

		return super.use(world, player, hand);
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
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_hammer.line1").withStyle(ChatFormatting.GREEN)));
	}
}
