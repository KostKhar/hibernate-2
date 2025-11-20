package entity;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String dbValue;

    Rating(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static Rating fromDbValue(String dbValue) {
        if (dbValue == null) return null;

        for (Rating rating : values()) {
            if (rating.dbValue.equals(dbValue)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown rating: " + dbValue);
    }
}
