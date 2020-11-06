package util;

public enum GenericConstants {

    NEW_PARTIAL_USER_DATA("newPartialUserData");

    private final String message;

    GenericConstants(String s) {
        message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}