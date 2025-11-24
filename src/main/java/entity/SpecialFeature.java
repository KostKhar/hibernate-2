package entity;


public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String dbValue;

    SpecialFeature(String dbValue) {
        this.dbValue = dbValue;
    }

    public static SpecialFeature fromDbValue(String dbValue) {
        if (dbValue == null) return null;

        for (SpecialFeature feature : values()) {
            if (feature.dbValue.equals(dbValue)) {
                return feature;
            }
        }
        throw new IllegalArgumentException("Unknown feature: " + dbValue);
    }

    public String getDbValue() {
        return dbValue;
    }
}
