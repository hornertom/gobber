package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCustomRingTeleport extends Item
{
    boolean limitedUse = GobberConfigBuilder.RING_TELEPORT_LIMITED_USE.get();

    public ItemCustomRingTeleport(Properties properties)
    {
        super(properties);
    }

    //Set the location in the ring
    public InteractionResult useOn(UseOnContext context)
    {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        Direction direction = context.getClickedFace();
        ItemStack stackRing = context.getPlayer().getMainHandItem();

        if(getPosition(stackRing) == null && player.isCrouching())
        {
            setPosition(stackRing, world, pos.relative(direction), player);
            player.sendMessage(new TranslatableComponent("item.gobber2.ring_teleport.line1").withStyle(ChatFormatting.YELLOW), null);

            return InteractionResult.SUCCESS;
        }

        if(getPosition(stackRing) != null)
        {
            player.sendMessage(new TranslatableComponent("item.gobber2.ring_teleport.line2").withStyle(ChatFormatting.YELLOW), null);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    //Use the teleport function or clear it
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);

        if(getPosition(stack) != null && !player.isCrouching())
        {
            teleport(player, world, stack);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        if(getPosition(stack) != null && player.isCrouching())
        {
            setPosition(stack, world, null, player);
            player.sendMessage(new TranslatableComponent("item.gobber2.ring_teleport.line3").withStyle(ChatFormatting.YELLOW), null);
        }

        if(limitedUse)
        {
            stack.setDamageValue(getDamage(stack) + 1);

            if(stack.getDamageValue() >= stack.getMaxDamage())
            {
                stack.shrink(1);
            }
        }

        return new InteractionResultHolder<>(InteractionResult.PASS, player.getItemInHand(hand));
    }

    //Capture player position
    public static BlockPos getPosition(ItemStack stack)
    {
        if (!stack.hasTag())
        {
            return null;
        }

        CompoundTag tags = stack.getTag();

        if (tags.contains("pos"))
        {
            return NbtUtils.readBlockPos((CompoundTag) tags.get("pos"));
        }
        return null;
    }

    //Capture player dimension
    public static String getDimension(ItemStack stack)
    {
        CompoundTag tags = stack.getTag();

        if (stack.hasTag() && tags.contains("dim"))
        {
            return stack.getTag().getString("dim");
        }
        else
        {
            return null;
        }
    }

    //Set position and dimension in the NBT
    public static void setPosition(ItemStack stack, Level world, BlockPos pos, Player player)
    {
        if(world.isClientSide) return;

        CompoundTag tags;

        if (!stack.hasTag())
        {
            tags = new CompoundTag();
        }
        else
        {
            tags = stack.getTag();
        }

        if (pos == null) // not pointing at a block will clear the NBT
        {
            tags.remove("pos");
            tags.remove("dim");
        }
        else
        {
            ResourceKey resourceKey = world.dimension();
            ResourceLocation name = resourceKey.location();
            tags.put("pos", NbtUtils.writeBlockPos(pos));
            tags.putString("dim", name.toString());
        }

        stack.setTag(tags);
    }

    //Teleport
    public void teleport(Player player, Level world, ItemStack stack)
    {
        if(world.isClientSide) return;

        BlockPos pos = getPosition(stack);
        String dim = getDimension(stack);
        ResourceKey<Level> currentDim = world.dimension();
        ServerLevel serverWorld = ((ServerLevel)world).getServer().getLevel(currentDim);  //current world the player is in
        ServerPlayer serverPlayer = (ServerPlayer) player;

        // Build world to test from stored data
        ResourceKey<Level> storedKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(dim));
        ServerLevel storedWorld = ((ServerLevel)world).getServer().getLevel(storedKey);

        // Compare current world with stored world
        if(serverWorld == storedWorld)
        {
            serverPlayer.teleportTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
        }
        else
        {
            serverPlayer.teleportTo(storedWorld, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, serverPlayer.getRespawnAngle(), serverPlayer.getRespawnAngle());
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }



    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag)
    {
        tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line9").withStyle(ChatFormatting.GREEN)));
        tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line11").withStyle(ChatFormatting.BLUE)));
        tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line12").withStyle(ChatFormatting.RED)));

        if(getPosition(stack) != null)
        {
            BlockPos pos = getPosition(stack);

            tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line13").withStyle(ChatFormatting.GREEN)));
            tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line14", getDimension(stack), pos.getX(), pos.getY(), pos.getZ()).withStyle(ChatFormatting.LIGHT_PURPLE)));
            tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line18", limitedUse).withStyle(ChatFormatting.YELLOW)));
        }
    }
}