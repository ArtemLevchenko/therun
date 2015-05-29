package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.logic.random.GameGenerator;

/**
 *
 * @author Artem
 */
public class GameHelperEngine {

    private final static int[] PERCENT_100 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private final static int[] PERCENT_90 = new int[]{1, 1, 1, 1, 1, 1, 1, 0, 1, 1};
    private final static int[] PERCENT_80 = new int[]{1, 1, 1, 1, 0, 1, 1, 0, 1, 1};
    private final static int[] PERCENT_70 = new int[]{1, 0, 1, 1, 0, 1, 1, 0, 1, 1};
    private final static int[] PERCENT_60 = new int[]{1, 0, 1, 1, 0, 1, 0, 0, 1, 1};
    private final static int[] PERCENT_50 = new int[]{1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
    private final static int[] PERCENT_40 = new int[]{1, 0, 0, 1, 0, 1, 0, 0, 1, 0};
    private final static int[] PERCENT_30 = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 1, 0};
    private final static int[] PERCENT_20 = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 0};
    private final static int[] PERCENT_10 = new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    private final static int[] PERCENT_0 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static boolean chanseToTrue(int chance) {
        int[] massive = null;
        switch (chance) {
            case 0:
                massive = PERCENT_0;
                break;
            case 10:
                massive = PERCENT_10;
                break;
            case 20:
                massive = PERCENT_20;
                break;
            case 30:
                massive = PERCENT_30;
                break;
            case 40:
                massive = PERCENT_40;
                break;
            case 50:
                massive = PERCENT_50;
                break;
            case 60:
                massive = PERCENT_60;
                break;
            case 70:
                massive = PERCENT_70;
                break;
            case 80:
                massive = PERCENT_80;
                break;
            case 90:
                massive = PERCENT_90;
                break;
            case 100:
                massive = PERCENT_100;
                break;
            default:
                massive = PERCENT_30;
                break;
        }
        return massive[GameGenerator.randomIntegerIndex(0, 9)] == 1;
    }
}
