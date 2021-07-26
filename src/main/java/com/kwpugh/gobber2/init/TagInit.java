package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class TagInit
{
    public static final Tag<Block> PAXEL_MINEABLE = getBlockTagWrapper("paxel_mineable");

    public static Tag<Block> getBlockTagWrapper(String path)
    {
        return BlockTags.bind(Gobber2.modid + ":" + path);
    }
}