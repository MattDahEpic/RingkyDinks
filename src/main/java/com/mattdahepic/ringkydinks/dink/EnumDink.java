package com.mattdahepic.ringkydinks.dink;

public enum EnumDink {
    TEMPLATE(false,false,null),
    FLIGHT(false,true, EnumRing.TIER3),
    LAVAWALK(false,false, EnumRing.TIER1),
    WATERWALK(false,false, EnumRing.TIER1),
    ANTIPOTION(false,true, EnumRing.TIER2),
    EXTINGUISHER(false,false, EnumRing.TIER2),
    MAGNET(false,true, EnumRing.TIER2),
    WATERBREATHING(false,true, EnumRing.TIER2),
    NIGHTVISION(false,true, EnumRing.TIER2),
    SATURATION(false,true, EnumRing.TIER2),
    SPEED(false,true, EnumRing.TIER2),
    //CHEST(true,false,EnumRing.TIER1),
    ENDERCHEST(true,false, EnumRing.TIER1),
    CRAFTINGTABLE(true,false, EnumRing.TIER1),
    MOBDERPEARL(true,false, EnumRing.TIER2);

    public final boolean hasUseAbility;
    public final boolean constantItemConsumption;
    public final EnumRing ring;
    EnumDink (boolean useAbility, boolean constantItemConsumption, EnumRing ring) {
        this.constantItemConsumption = constantItemConsumption;
        this.hasUseAbility = useAbility;
        this.ring = ring;
    }
    public String getType () {
        return this.name().toLowerCase();
    }
    @Override public String toString () {
        return this.getType();
    }

    public enum EnumRing {
        TIER1(0),
        TIER2(1),
        TIER3(2);

        public final int meta;

        EnumRing(int meta) {
            this.meta = meta;
        }
    }
}
