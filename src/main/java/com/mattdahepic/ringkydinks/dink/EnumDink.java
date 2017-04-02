package com.mattdahepic.ringkydinks.dink;

import com.mattdahepic.ringkydinks.dink.ability.*;

public enum EnumDink {
    TEMPLATE(null,null),
    FLIGHT(EnumRing.TIER3,new DinkAbilityFlight()),
    LAVAWALK(EnumRing.TIER1,new DinkAbilityLavaWalk()),
    ANTIPOTION(EnumRing.TIER2,new DinkAbilityAntiPotion()),
    EXTINGUISHER(EnumRing.TIER2,new DinkAbilityExtinguisher()),
    MAGNET(EnumRing.TIER2,new DinkAbilityMagnet()),
    WATERBREATHING(EnumRing.TIER2,new DinkAbilityWaterbreathing()),
    NIGHTVISION(EnumRing.TIER2,new DinkAbilityNightVision()),
    SATURATION(EnumRing.TIER2,new DinkAbilitySaturation()),
    SPEED(EnumRing.TIER2,new DinkAbilitySpeed()),
    //CHEST(true,false,EnumRing.TIER1),
    ENDERCHEST(EnumRing.TIER1,new DinkAbilityEnderchest()),
    CRAFTINGTABLE(EnumRing.TIER1,new DinkAbilityCraftingTable()),
    MOBDERPEARL(EnumRing.TIER2,new DinkAbilityMobderpearl()),
    UPHILLSTEPASSIST(EnumRing.TIER2,new DinkAbilityUphillStepAssist()),
    REGENERATION(EnumRing.TIER3,new DinkAbilityRegeneration());

    public final EnumRing ring;
    public final IDinkAbility ability;
    EnumDink (EnumRing ring, IDinkAbility ability) {
        this.ring = ring;
        this.ability = ability;
    }
    public String getType () {
        return this.name().toLowerCase();
    }
    @Override public String toString () {
        return this.getType();
    }

    public enum EnumRing {
        TIER1,
        TIER2,
        TIER3;

        public final int meta;

        EnumRing() {
            this.meta = ordinal();
        }
    }
}
