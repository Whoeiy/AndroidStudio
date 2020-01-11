package com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema;

public class StarbucksOrderingDbSchema {
    public static final class DrinkTable{
        public static final String TNAME = "drinks";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String TYPE = "type";
            public static final String HOT = "hot";
            public static final String PRICE = "price";
            public static final String DETAIL = "detail";
            public static final String IMAGE = "image";

        }
    }

    public static final class DrinkTypeTable{
        public static final String TNAME = "drink_types";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TYPE = "type";
        }
    }

    public static final class UserTable{
        public static final String TNAME = "users";

        public static final class Cols{
            public static final String USERNAME = "username";
            public static final String NAME = "name";
            public static final String PASSWORD = "password";
            public static final String GENDER = "gender";
            public static final String YEAR = "year";
            public static final String MONTH = "month";
            public static final String PHONE = "phone";
        }
    }
}
