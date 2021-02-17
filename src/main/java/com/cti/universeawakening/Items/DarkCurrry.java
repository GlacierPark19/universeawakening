package com.cti.universeawakening.Items;

import com.cti.universeawakening.universeawakening;
import net.minecraft.item.Food;
import net.minecraft.item.Item;


public class DarkCurrry extends Item {
    public DarkCurrry() {
        super(new Properties()


             .group(universeawakening.TAB)
                .food(new Food.Builder()
                    .hunger(214747)
                        .saturation(200f)

                        .build()



        )
        );











    }


}
