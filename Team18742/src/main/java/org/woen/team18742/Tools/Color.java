package org.woen.team18742.Tools;

import androidx.annotation.NonNull;

public class Color {
    public int R, G, B;

    public Color(int r, int g, int b){
        R = r;
        G = g;
        B = b;
    }

    @NonNull
    @Override
    public String toString() {
        if (R > 255 || G > 255 || B > 255)
            throw new RuntimeException("color more 255");

        if (R < 0 || G < 0 || B < 0)
            throw new RuntimeException("color less 0");

        String rString = Integer.toString(R, 16).toUpperCase(), gString = Integer.toString(G, 16).toUpperCase(), bString = Integer.toString(B, 16).toUpperCase();

        if(rString.length() == 1)
            rString = "0" + rString;

        if(gString.length() == 1)
            gString = "0" + gString;

        if(bString.length() == 1)
            bString = "0" + bString;

        return "#" + rString + gString + bString;
    }
}
