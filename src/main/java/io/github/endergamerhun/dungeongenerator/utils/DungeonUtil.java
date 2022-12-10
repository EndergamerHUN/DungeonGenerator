package io.github.endergamerhun.dungeongenerator.utils;

import java.io.File;

public class DungeonUtil {

    public static String[] getDungeonsAsString() {
        return new File(Util.getRoot(),"dungeons").list((dir, name) -> dir.isDirectory());
    }
}
