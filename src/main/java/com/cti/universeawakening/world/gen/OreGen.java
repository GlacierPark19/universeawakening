//package com.cti.universeawakening.world.gen;


import com.cti.universeawakening.RegistryHandler;
import com.cti.universeawakening.universeawakening;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeature;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;


public static void setupGen() {
    for (Map.Entry<RegistryKey<Biome>, Biome> biome : WorldGenRegistries.field_243657_i.func_239659_c_() /* Collection of Biome Entries */) {
        if (!biome.getValue().getCategory().equals(Biome.Category.NETHER) && !biome.getValue().getCategory().equals(Biome.Category.THEEND)) {
            addFeatureToBiome(
                biome.getValue(),
                GenerationStage.Decoration.UNDERGROUND_ORES,
                WorldGenRegistries.field_243653_e.getOrDefault(ModBlocks.ADAMANTINE_ORE.getId())
            );
        }
    }
}

public static void addFeatureToBiome(Biome biome, GenerationStage.Decoration decoration, ConfiguredFeature<?, ?> configuredFeature) {
    List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = new ArrayList<>(
        biome.func_242440_e().func_242498_c() /* List of Configured Features */
    );

    while (biomeFeatures.size() <= decoration.ordinal()) {
        biomeFeatures.add(Lists.newArrayList());
    }
    
    List<Supplier<ConfiguredFeature<?, ?>>> features = new ArrayList<>(biomeFeatures.get(decoration.ordinal()));
    features.add(() -> configuredFeature);
    biomeFeatures.set(decoration.ordinal(), features);

    /* Change field_242484_f that contains the Configured Features of the Biome*/
    ObfuscationReflectionHelper.setPrivateValue(BiomeGenerationSettings.class, biome.func_242440_e(), biomeFeatures, "field_242484_f");
}}

