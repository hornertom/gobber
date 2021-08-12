package com.kwpugh.gobber2.init;

//import com.kwpugh.gobber2.Gobber2;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.tags.Tag;
//import net.minecraft.world.item.*;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.common.ForgeTier;
//import net.minecraftforge.common.TierSortingRegistry;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import java.util.List;
//
//@Mod.EventBusSubscriber(modid = Gobber2.modid, bus = Mod.EventBusSubscriber.Bus.FORGE)
//public class TestTier
//{
//    public static final Tag.Named<Block> MY_TOOL_TAG = BlockTags.createOptional(new ResourceLocation("gobber2:minable/my_tool"));
//    public static final Tag.Named<Block> MY_TIER_TAG = BlockTags.createOptional(new ResourceLocation("gobber2:needs_my_tier_tool"));
//    public static final Tier MY_TIER = TierSortingRegistry.registerTier(
//            new ForgeTier(5, 5000, 10, 100, 0, MY_TIER_TAG, () -> Ingredient.of(Items.BEDROCK)),
//            new ResourceLocation("gobber2:my_tier"),
//            List.of(Tiers.DIAMOND), List.of());
//
//    @SubscribeEvent
//    public static void registerItems(RegistryEvent.Register<Item> event)
//    {
//        event.getRegistry().register(new DiggerItem(1, 1, MY_TIER, MY_TOOL_TAG, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS))
//        {
//            @Override
//            public float getDestroySpeed(ItemStack stack, BlockState state)
//            {
//                if (state.is(BlockTags.MINEABLE_WITH_AXE)) return speed;
//                if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) return speed;
//                return super.getDestroySpeed(stack, state);
//            }
//
//            @Override
//            public boolean isCorrectToolForDrops(ItemStack stack, BlockState state)
//            {
//                if (state.is(BlockTags.MINEABLE_WITH_PICKAXE))
//                    return TierSortingRegistry.isCorrectTierForDrops(Tiers.WOOD, state);
//                if (state.is(BlockTags.MINEABLE_WITH_AXE))
//                    return TierSortingRegistry.isCorrectTierForDrops(MY_TIER, state);
//                if (state.is(MY_TOOL_TAG))
//                    return TierSortingRegistry.isCorrectTierForDrops(MY_TIER, state);
//                return false;
//            }
//        }.setRegistryName("test_item"));
//    }
//}