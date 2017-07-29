package com.lyrch.librebrew.model;

import android.provider.BaseColumns;

/**
 * Created by jaredwilkerson on 12/15/16.
 */
public class HopReaderContract {
    private HopReaderContract() {}

    public static class HopEntry implements BaseColumns {
        public static final String TABLE_NAME = "hops";
        public static final String HOP_NAME = "name";
        public static final String LIST_VERSION = "version";
        public static final String MAX_ALPHA_ACID = "max_alpha_acid";
        public static final String MIN_ALPAH_ACID = "min_alpha_acid";
    }
}
