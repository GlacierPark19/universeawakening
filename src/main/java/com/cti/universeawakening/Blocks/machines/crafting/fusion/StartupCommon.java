package com.cti.universeawakening.Blocks.machines.crafting.fusion;

import com.cti.universeawakening.universeawakening;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StartupCommon
{
    public static Block FusionCore;  // this holds the unique instance of your block
    public static BlockItem itemFusionCore; // and the corresponding item form that block

    public static TileEntityType<TileLightonianFusion> tileEntityTypeFusion;  // Holds the type of our tile entity; needed for the TileEntityData constructor
    public static ContainerType<ContainerFusion> containerTypeContainerFusion;

    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
        FusionCore = new BlockInventoryFusion().setRegistryName("fusion_block");
        blockRegisterEvent.getRegistry().register(FusionCore);
    }



    @SubscribeEvent
    public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {
        fusion_block = TileEntityType.Builder.create(TileLightonianFusion::new, FusionCore)
                .build(null);
        // you probably don't need a datafixer --> null should be fine
        tileEntityTypeMBE31.setRegistryName("minecraftbyexample:mbe31_tile_entity_type_registry_name");
        event.getRegistry().register(tileEntityTypeMBE31);
    }

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event)
    {
        containerTypeContainerFusion = IForgeContainerType.create(ContainerFusion::createContainerClientSide);
        containerTypeContainerFusion.setRegistryName("fusion_container");
        event.getRegistry().register(containerTypeContainerFusion);
    }

}

