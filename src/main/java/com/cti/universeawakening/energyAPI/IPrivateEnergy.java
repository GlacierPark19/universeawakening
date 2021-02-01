package com.cti.universeawakening.energyAPI;

import net.minecraftforge.energy.IEnergyStorage;

public interface IPrivateEnergy extends IEnergyStorage {

    /**
     * Do not use {@link #extractEnergy(int, boolean)} internally. This method
     * stops the gadgets from being used like batteries.
     */
    int extractPower(int maxExtract, boolean simulate);
}
