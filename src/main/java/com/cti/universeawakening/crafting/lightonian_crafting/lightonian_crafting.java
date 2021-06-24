package com.cti.universeawakening.crafting.lightonian_crafting;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class lightonian_crafting extends Block {

    private static final ITextComponent LightonianCraftingTableContainer = new TranslationTextComponent("container.crafting");

    public lightonian_crafting() {
        super(Block.Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .harvestLevel(0)
                .hardnessAndResistance(5f,25f)
                .harvestTool(ToolType.PICKAXE));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            player.openContainer(state.getContainer(worldIn, pos));
            player.addStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos)
    {
        return new SimpleNamedContainerProvider((id, inventory, player) -> {
            return new LightonianCraftingTableContainer(id, inventory, IWorldPosCallable.of(world, pos));
        }, LightonianCraftingTableContainer);
    }
}
