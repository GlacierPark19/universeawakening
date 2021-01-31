package com.cti.universeawakening;
import com.cti.universeawakening.Blocks.BlockItemBase;
import com.cti.universeawakening.Blocks.CopperBlock;
import com.cti.universeawakening.Blocks.CopperOre;
import com.cti.universeawakening.Items.DeliciousSandwich;
import com.cti.universeawakening.Items.ItemBase;
import com.cti.universeawakening.Items.ItemModRecord;

import com.cti.universeawakening.Tools.LightoniumTier;
import com.cti.universeawakening.Tools.ModItemTier;
//import net.minecraft.block;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryManager;
//import net.minecraft.resources.VanillaPack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;
//import net.minecraft.block.material.Material;
import net.minecraftforge.energy.IEnergyStorage;



public class RegistryHandler {
    // create DeferredRegister object

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, universeawakening.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, universeawakening.MODID);

    public static void init() {
        // attach DeferredRegister to the event bus
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // register item
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", ItemBase::new);
    public static final RegistryObject<DeliciousSandwich> DELICIOUS_SANDWICH = ITEMS.register("delicious_sandwich", DeliciousSandwich::new);
    //Reccords and other goodies
    //public static final Item recordCraft1 = new ItemModRecord(1, .ModSounds.ctiRecord1, .unstackable());

    public static final RegistryObject<Item> LIGHTONIUM_INGOT = ITEMS.register("lightonium_ingot", ItemBase::new);

    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", ItemBase::new);



//TOOLS

    public static final RegistryObject<SwordItem> COPPER_SWORD = ITEMS.register("copper_sword", () ->
            new SwordItem(ModItemTier.COPPER, 3, -2.5f, new Item.Properties().group(universeawakening.TAB))
            );

    public static final RegistryObject<SwordItem> LIGHTONIAN_SWORD = ITEMS.register("lightonian_sword", () ->
            new SwordItem(LightoniumTier.LIGHTONIUM, 98100, -2.5f, new Item.Properties().group(universeawakening.TAB)));
    ;

//BlOCKS

    public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore", CopperOre::new);

    public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS.register("copper_block", CopperBlock::new);

//BLOCK-ITEMS
public static final RegistryObject<Item> COPPER_BLOCK_ITEM = ITEMS.register("copper_block",() -> new BlockItemBase(COPPER_BLOCK.get()));
public static final RegistryObject<Item> COPPER_ORE_ITEM = ITEMS.register("copper_ore", () -> new BlockItemBase(COPPER_ORE.get()));

}







                     //   )


        //    );

