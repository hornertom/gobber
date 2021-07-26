package com.kwpugh.gobber2.items.rings;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingMiner extends Item
{
	public ItemCustomRingMiner(Properties properties)
	{
		super(properties);
	}

	public static final int BREAK_DELAY = 1;

	int ringMinerCooldown = GobberConfigBuilder.RING_MINER_COOLDOWN.get();
	boolean reverseRingMiner = GobberConfigBuilder.REVERSE_RING_MINER.get();
	boolean delayedBreakMode = GobberConfigBuilder.DELAY_BREAK_MODE.get();

	boolean shiftKeyPressed = false;

	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);

        ItemStack equippedMain = player.getMainHandItem();

        if(equippedMain == stack)   //Only works in the main hand
        {
        	shiftKeyPressed = player.isShiftKeyDown();

        	player.getCooldowns().addCooldown(this, ringMinerCooldown);

        	if(!world.isClientSide)
			{
				Block block;
				List<BlockPos> poslist = new ArrayList<BlockPos>();

				for (int x = 5; x >= -5; x--)
				{
					for (int y = 4; y >= 0; y--)
					{
						for (int z = 5; z >= -5; z--)
						{
							BlockPos pos = player.blockPosition().offset(x, y, z);
							BlockState state = world.getBlockState(pos);
							block = state.getBlock();
							String blockForgeTags = block.getTags().toString();

							if (block == Blocks.STONE ||
									state.is(BlockTags.BASE_STONE_OVERWORLD) ||   // base_stone_overworld
									state.is(BlockTags.BASE_STONE_NETHER) ||   // base_stone_nether
									blockForgeTags.contains("forge:stone") ||
									blockForgeTags.contains("forge:sandstone") ||
									blockForgeTags.contains("forge:sand") ||
									blockForgeTags.contains("forge:dirt") ||
									blockForgeTags.contains("forge:gravel") ||
									block instanceof GravelBlock ||
									block instanceof SandBlock ||
									block == Blocks.DIRT ||
									block == Blocks.DIRT_PATH ||
									block == Blocks.SAND  ||
									block == Blocks.RED_SAND  ||
									block == Blocks.SANDSTONE ||
									block == Blocks.RED_SANDSTONE ||
									block == Blocks.GRAVEL ||
									block == Blocks.GRASS_BLOCK ||
									block == Blocks.COARSE_DIRT ||
									block == Blocks.PODZOL ||
									block == Blocks.MYCELIUM ||
									block == Blocks.GRANITE ||
									block == Blocks.ANDESITE ||
									block == Blocks.DIORITE  ||
									block == Blocks.SOUL_SAND ||
									block == Blocks.SOUL_SOIL ||
									block == Blocks.MOSSY_COBBLESTONE ||
									block == Blocks.MOSSY_COBBLESTONE_SLAB ||
									block == Blocks.MOSSY_COBBLESTONE_STAIRS ||
									block == Blocks.MOSSY_STONE_BRICKS ||
									block == Blocks.MOSSY_STONE_BRICK_STAIRS ||
									block == Blocks.MOSSY_STONE_BRICK_SLAB ||
									block == Blocks.STONE_BRICKS ||
									block == Blocks.STONE_BRICK_STAIRS ||
									block == Blocks.STONE_BRICK_SLAB ||
									block == Blocks.CRACKED_STONE_BRICKS ||
									block == Blocks.INFESTED_CRACKED_STONE_BRICKS ||
									block == Blocks.INFESTED_CHISELED_STONE_BRICKS||
									block == Blocks.INFESTED_COBBLESTONE ||
									block == Blocks.INFESTED_MOSSY_STONE_BRICKS ||
									block == Blocks.END_STONE ||
									block == Blocks.BASALT ||
									block == Blocks.BLACKSTONE ||
									block == Blocks.MAGMA_BLOCK ||
									block == Blocks.NETHER_WART_BLOCK ||
									block == Blocks.WARPED_WART_BLOCK ||
									block == Blocks.NETHERRACK)
							{
								poslist.add(player.blockPosition().offset(x, y, z));
							}
						}
					}
				}

				if(delayedBreakMode)
				{
					//Test code for block break delay
					if (!poslist.isEmpty())
					{
						//List<BlockPos> reversedPosList = reverseList(poslist);
						MinecraftForge.EVENT_BUS.register(new Object()
			            {
			                int delay = BREAK_DELAY;
			                int i = 0;

			                @SubscribeEvent
			                public void onTick(TickEvent.WorldTickEvent event)
			                {
			                    if (delay-- > 0) return;
			                    delay = BREAK_DELAY;
			                    if (i < poslist.size())
			                    {
			                        BlockPos breakPos = poslist.get(i);
			                        
			                    	if(shiftKeyPressed)    //NOTE: shift key needs to be held down through the delayed block breaking to get drops
									{
										world.destroyBlock(breakPos, !reverseRingMiner);
									}
									else
									{
										world.destroyBlock(breakPos, reverseRingMiner);
									}

			                        i++;
			                    }
			                    else
			                    {
			                        MinecraftForge.EVENT_BUS.unregister(this);
			                    }
			                }
			            });
					}
				}
				else
				{
					//Traditional method of block break, all at once
					if (!poslist.isEmpty())
					{
						for (int i = 0; i <= poslist.size() - 1; i++)
						{
							BlockPos targetpos = poslist.get(i);
							block = world.getBlockState(targetpos).getBlock();

							if(player.isShiftKeyDown())
							{
								world.destroyBlock(targetpos, !reverseRingMiner);
							}
							else
							{
								world.destroyBlock(targetpos, reverseRingMiner);
							}
						}
					}
				}
			}
        }
        return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.line1").withStyle(ChatFormatting.GREEN)));

		if(reverseRingMiner)
		{
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.line4").withStyle(ChatFormatting.YELLOW)));
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.line5").withStyle(ChatFormatting.YELLOW)));
		}
		else
		{
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.line2").withStyle(ChatFormatting.YELLOW)));
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.line3").withStyle(ChatFormatting.YELLOW)));
		}

		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.line6").withStyle(ChatFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring.cooldown", ringMinerCooldown).withStyle(ChatFormatting.LIGHT_PURPLE)));

		if(delayedBreakMode)
		{
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.mode.line2").withStyle(ChatFormatting.LIGHT_PURPLE)));
		}
		else
		{
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_miner.mode.line1").withStyle(ChatFormatting.LIGHT_PURPLE)));
		}
	}
}