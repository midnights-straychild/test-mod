package com.example.testmod.items;

import com.example.testmod.TestMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;

import net.minecraft.util.EnumHand;

public class TutorialDust extends ItemBoneMeal {
    public static final String registryName = "tutorial_dust";

    public TutorialDust() {
        super(EnumDyeColor.MAGENTA, new Item
            .Properties()
            .maxStackSize(64)
            .group(TestMod.creativeTab));
        this.setRegistryName(TutorialDust.registryName);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (target.isCreatureType(EnumCreatureType.CREATURE, false)) {
            EntityAnimal animal = (EntityAnimal) target;

            animal.setInLove(player);

            stack.setCount(stack.getCount() - 1);

            player.inventory.markDirty();
        }

        return true;
    }
}
