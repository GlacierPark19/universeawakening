package com.cti.universeawakening.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RawLightoniumOre extends OreBlock {
    public RawLightoniumOre() {

        super(Properties.create(Material.IRON)
                .hardnessAndResistance(7.5f, 6.9f)
                .sound(SoundType.STONE)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE));

    }
}
