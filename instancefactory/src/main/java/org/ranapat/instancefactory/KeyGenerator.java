package org.ranapat.instancefactory;

class KeyGenerator {
    private KeyGenerator() {
        //
    }

    public static String generate(final Class _class, final Class[] types, final Object... values) {
        final int typesLength = types.length;
        final int valuesLength = values.length;

        if (typesLength != valuesLength) {
            return null;
        }
        for (int i = 0; i < typesLength; ++i) {
            final Class type = types[i];
            final Object value = values[i];

            if (!type.isInstance(value)) {
                return null;
            }
        }

        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(_class.getCanonicalName());

        if (types.length > 0) {
            stringBuilder.append("-");

            for (int i = 0; i < typesLength; ++i) {
                final Class type = types[i];
                final Object value = values[i];

                stringBuilder.append(type.getCanonicalName());
                stringBuilder.append(":");
                stringBuilder.append(value);

                if (i != typesLength - 1) {
                    stringBuilder.append(",");
                }
            }
        }

        return stringBuilder.toString();
    }
}
