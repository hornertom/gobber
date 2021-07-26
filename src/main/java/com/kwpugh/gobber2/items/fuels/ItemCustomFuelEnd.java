package com.kwpugh.gobber2.items.fuels;

//import java.util.List;
//
//import javax.annotation.Nullable;
//
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.network.chat.Component;
//import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.TranslatableComponent;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//import net.minecraft.world.item.Item.Properties;
//
//public class ItemCustomFuelEnd extends Item
//{
//	private int burnTime;
//
//	public ItemCustomFuelEnd(Properties p_i48487_1_, String name, int burnTime)
//	{
//	    super(p_i48487_1_);
//	    this.setRegistryName(name);
//	    this.burnTime = burnTime;
//	}
//
//	@Override
//	public int getBurnTime(ItemStack itemStack)
//	{
//	    return this.burnTime;
//	}
//
//	@OnlyIn(Dist.CLIENT)
//	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
//	{
//		super.appendHoverText(stack, worldIn, tooltip, flagIn);
//		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_foo.line1").withStyle(ChatFormatting.GREEN)));
//		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_foo.line2", this.burnTime).withStyle(ChatFormatting.YELLOW)));
//	}
//}