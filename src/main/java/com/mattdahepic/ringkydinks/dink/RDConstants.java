package com.mattdahepic.ringkydinks.dink;

public class RDConstants {
    public static EnumDink getDinkByID (int id) {
        for (EnumDink d : EnumDink.values()) {
            if (d.id == id) return d;
        }
        throw new IndexOutOfBoundsException("Invalid dink id.");
    }

    public enum EnumDink {
        TEMPLATE(0,"template",null),
        FLIGHT(1,"flight",DinkLevel.TIER3),
        LAVAWALK(2,"lavawalk",DinkLevel.TIER1),
        WATERWALK(3,"waterwalk",DinkLevel.TIER1),
        ANTIPOTION(4,"antipotion",DinkLevel.TIER2),
        EXTINGUISHER(5,"extinguisher",DinkLevel.TIER2),
        MAGNET(6,"magnet",DinkLevel.TIER2),
        WATERBREATHING(7,"waterbreathing",DinkLevel.TIER2),
        NIGHTVISION(8,"nightvision",DinkLevel.TIER2);
        //CHEST(9,"chest",DinkLevel.TIER1),
        //ENDERCHEST(10,"enderchest",DinkLevel.TIER1);

        public final int id;
        public final String name;
        public final DinkLevel level;
        EnumDink (int id, String name,DinkLevel level) {
            this.id = id;
            this.name = name;
            this.level = level;
        }
    }
    public enum DinkLevel {
        TIER1(0),
        TIER2(1),
        TIER3(2);

        public final int meta;
        DinkLevel (int meta) {
            this.meta = meta;
        }
    }
}
