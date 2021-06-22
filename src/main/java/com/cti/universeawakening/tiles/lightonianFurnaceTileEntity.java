package com.cti.universeawakening.tiles;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public class lightonianFurnaceTileEntity extends AbstractLightonianFurnaceTileEntity {

    public LightonianFurnaceTileEntity() {
        super(lightonianFurnaceTileEntity.LIGHTONIAN_FURNACE, RecipeTypes.SMELTING, IRecipeType.SMELTING);
    }

    @Override
    @Nonnull
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("tile.universeawakening.lightonian_furnace");
    }

    @Override
    @Nonnull
    protected Container createMenu(int id, @Nonnull PlayerInventory player) {
        return new FurnaceContainer(id, player, this, this.furnaceData);
    }

}{
}
