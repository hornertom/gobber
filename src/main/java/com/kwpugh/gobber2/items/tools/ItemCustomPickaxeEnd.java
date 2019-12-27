package com.kwpugh.gobber2.items.tools;

import java.util.List;

import com.kwpugh.gobber2.lists.ItemList;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomPickaxeEnd extends PickaxeItem
{
	public ItemCustomPickaxeEnd(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		if(!world.isRemote)
		{
		    if(player.isCrouching())
		    {
		        EnableUtil.changeEnabled(player, hand);
		        player.sendMessage(new StringTextComponent("Night vision ability active: " + EnableUtil.isEnabled(stack)));
		    }
		    
		    if(EnableUtil.isEnabled(stack))
			{
			 	player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, (int) 2400, (int) 0));		 			 	
			}	
		    else
		    {
		    	player.removeActivePotionEffect(Effects.NIGHT_VISION);
		    }
		    return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
		}
		return super.onItemRightClick(world, player, hand);
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		stack.setDamage(0);  //no damage
        
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
       if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F)
       {
          stack.damageItem(0, entityLiving, (p_220038_0_) -> {
             p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
          });
       }
       return true;
    }
    
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.gobber2_ingot_nether;
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);		
		list.add(new StringTextComponent(TextFormatting.BLUE + "An unbreakable pickaxe"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click for Night Vision"));
		list.add(new StringTextComponent(TextFormatting.RED + "Night vision ability active: " + EnableUtil.isEnabled(stack)));
		list.add(new StringTextComponent(TextFormatting.GOLD + "Sneak right-click to toggle ability on/off"));
	} 
}
