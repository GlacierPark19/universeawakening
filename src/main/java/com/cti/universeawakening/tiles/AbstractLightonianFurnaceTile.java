package com.cti.universeawakening.tiles;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public abstract class AbstractBrickFurnaceTileEntity extends AbstractFurnaceTileEntity {

    protected final IRecipeType<? extends AbstractCookingRecipe> specificRecipeType;

    public AbstractBrickFurnaceTileEntity(TileEntityType<?> tileTypeIn,
                                          IRecipeType<? extends AbstractCookingRecipe> specificRecipeTypeIn,
                                          IRecipeType<? extends AbstractCookingRecipe> vanillaRecipeTypeIn) {
        super(tileTypeIn, vanillaRecipeTypeIn);
        this.specificRecipeType = specificRecipeTypeIn;
    }

    /* FOLLOWING Code helps the copied code below. */

    public static final int BURN_TIME = 0;
    public static final int RECIPES_USED = 1;
    public static final int COOK_TIME = 2;
    public static final int COOK_TIME_TOTAL = 3;

    /* FOLLOWING Code is copied from "Shadows-of-Fire/FastFurnace" mod to enhance performance */

    public static final int INPUT = 0;
    public static final int FUEL = 1;
    public static final int OUTPUT = 2;

    protected AbstractCookingRecipe curRecipe;
    protected ItemStack failedMatch = ItemStack.EMPTY;

    private boolean isBurning() {
        return this.furnaceData.get(BURN_TIME) > 0; //changed because of private variable
    }

    @Override
    public void tick() {
        boolean wasBurning = this.isBurning();
        boolean dirty = false;
        if (this.isBurning()) {
            this.furnaceData.set(BURN_TIME, this.furnaceData.get(BURN_TIME) - 1); //changed because of private variable
        }

        if (this.world != null && !this.world.isRemote) {
            ItemStack fuel = this.items.get(FUEL);
            if (this.isBurning() || !fuel.isEmpty() && !this.items.get(INPUT).isEmpty()) {
                AbstractCookingRecipe irecipe = getRecipe();
                boolean valid = this.canSmelt(irecipe);
                if (!this.isBurning() && valid) {
                    this.furnaceData.set(BURN_TIME, this.getBurnTime(fuel)); //changed because of private variable
                    this.furnaceData.set(RECIPES_USED, this.furnaceData.get(BURN_TIME)); //changed because of private variable
                    if (this.isBurning()) {
                        dirty = true;
                        if (fuel.hasContainerItem()) this.items.set(1, fuel.getContainerItem());
                        else if (!fuel.isEmpty()) {
                            fuel.shrink(1);
                            if (fuel.isEmpty()) {
                                this.items.set(1, fuel.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isBurning() && valid) {
                    this.furnaceData.set(COOK_TIME, this.furnaceData.get(COOK_TIME) + 1); //changed because of private variable
                    if (this.furnaceData.get(COOK_TIME) == this.furnaceData.get(COOK_TIME_TOTAL)) { //changed because of private variable
                        this.furnaceData.set(COOK_TIME, 0); //changed because of private variable
                        this.furnaceData.set(COOK_TIME_TOTAL, this.getCookTime()); //changed because of private variable
                        this.smeltItem(irecipe);
                        dirty = true;
                    }
                } else {
                    this.furnaceData.set(COOK_TIME, 0); //changed because of private variable
                }
            } else if (!this.isBurning() && this.furnaceData.get(COOK_TIME) > 0) { //changed because of private variable
                this.furnaceData.set(COOK_TIME, MathHelper.clamp(this.furnaceData.get(COOK_TIME) - 2, 0, this.furnaceData.get(COOK_TIME_TOTAL))); //changed because of private variable
            }

            if (wasBurning != this.isBurning()) {
                dirty = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.isBurning()), 3);
            }
        }

        if (dirty) {
            this.markDirty();
        }

    }

    @Override
    protected boolean canSmelt(@Nullable IRecipe<?> recipe) {
        if (!this.items.get(0).isEmpty() && recipe != null) {
            ItemStack recipeOutput = recipe.getRecipeOutput();
            if (!recipeOutput.isEmpty()) {
                ItemStack output = this.items.get(OUTPUT);
                if (output.isEmpty()) return true;
                else if (!output.isItemEqual(recipeOutput)) return false;
                else return output.getCount() + recipeOutput.getCount() <= output.getMaxStackSize();
            }
        }
        return false;
    }

    private void smeltItem(@Nullable IRecipe<?> recipe) {
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemstack = this.items.get(0);
            ItemStack itemstack1 = recipe.getRecipeOutput();
            ItemStack itemstack2 = this.items.get(2);
            if (itemstack2.isEmpty()) {
                this.items.set(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (this.world != null && !this.world.isRemote) {
                this.setRecipeUsed(recipe);
            }

            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(1).isEmpty() && this.items.get(1).getItem() == Items.BUCKET) {
                this.items.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
        }
    }

    @Override
    protected int getCookTime() {
        AbstractCookingRecipe rec = getRecipe();
        if (rec == null) {
            return 200;
        } else if (this.specificRecipeType.getClass().isInstance(rec.getType())) {
            return rec.getCookTime();
        }
        return (int) (rec.getCookTime() * ServerConfig.COOK_TIME_FACTOR.get());
    }

    protected AbstractCookingRecipe getRecipe() {
        ItemStack input = this.getStackInSlot(INPUT);
        if (input.isEmpty() || input == failedMatch) {
            return null;
        }
        if (this.world != null && curRecipe != null && curRecipe.matches(this, world)) {
            return curRecipe;
        } else {
            AbstractCookingRecipe rec = null;
            if (this.world != null) {
                rec = this.world.getRecipeManager().getRecipe(this.specificRecipeType, this, this.world).orElse(null);
                if (rec == null && ServerConfig.VANILLA_RECIPES_ENABLED.get()) {
                    rec = this.world.getRecipeManager().getRecipes(this.recipeType, this, this.world)
                            .stream().filter(abstractCookingRecipe -> ServerConfig.isRecipeNotBlacklisted(abstractCookingRecipe.getId())).findFirst().orElse(null);
                }
            }
            if (rec == null) {
                failedMatch = input;
            } else {
                failedMatch = ItemStack.EMPTY;
            }
            return curRecipe = rec;
        }
    }

}
