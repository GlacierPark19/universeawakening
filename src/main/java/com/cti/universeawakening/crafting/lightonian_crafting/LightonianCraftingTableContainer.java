package com.cti.universeawakening.crafting.lightonian_crafting;

import com.cti.universeawakening.RegistryHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

public class LightonianCraftingTableContainer extends WorkbenchContainer {
    private final IWorldPosCallable field_217070_e;

    public LightonianCraftingTableContainer(int id, PlayerInventory playerInventory, IWorldPosCallable p_i50090_3_) {
        super(id, playerInventory, p_i50090_3_);
        this.field_217070_e = p_i50090_3_;
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(this.field_217070_e, player, RegistryHandler.LIGHTONIAN_CRAFTING_TABLE.get());
    }

}
