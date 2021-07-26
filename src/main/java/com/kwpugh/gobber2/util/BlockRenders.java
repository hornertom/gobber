package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.init.BlockInit;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

public class BlockRenders
{
	public static void defineRenders()
	{
		RenderType cutoutMipped = RenderType.cutoutMipped();
		RenderType translucent = RenderType.translucent();

		ItemBlockRenderTypes.setRenderLayer(BlockInit.GOBBER2_GLASS.get(), translucent);	
		ItemBlockRenderTypes.setRenderLayer(BlockInit.GOBBER2_GLASS_NETHER.get(), translucent);	
		ItemBlockRenderTypes.setRenderLayer(BlockInit.GOBBER2_GLASS_END.get(), translucent);
		ItemBlockRenderTypes.setRenderLayer(BlockInit.CLEAR_GLASS.get(), translucent);
	}	
}