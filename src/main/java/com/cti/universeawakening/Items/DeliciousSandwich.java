package com.cti.universeawakening.Items;

import com.cti.universeawakening.universeawakening;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class DeliciousSandwich extends Item {
    public DeliciousSandwich() {
        super(new Item.Properties()


             .group(universeawakening.TAB)
                .food(new Food.Builder()
                    .hunger(2048)
                        .saturation(2048f)
                        //.effect(new EffectInstance(Effects.REGENERATION, 2400, 2)),

                        .build()



        )
        );







    }
}
