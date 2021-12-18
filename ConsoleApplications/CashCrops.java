package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * The farming game "Stardew Valley" has the player growing
 * and cultivating crops and then selling them for profit.
 * This simulation takes the in-game values for crops and
 * outputs the most money the player can make over a
 * defined number of days.
 *
 * CAUTION: This simulation only outputs the money made by
 * mono-producing one crop and replanting them the day that
 * they are picked (see maxHarvests). If the player decides
 * to mix plants, then the player can change the number of
 * days to simulate growing that plant then selling it
 * however many times a season.
 *
 * All the plants have their own growth rates, buying price,
 * and selling price.
 *
 * @author Jacob Swineford
 */
public class CashCrops {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of crops you plan to plant: ");
        int crops = in.nextInt();
        System.out.print("Enter the number of days: ");
        int days = in.nextInt();

        String str = "Here are the mono-profits for %d days - " +
                "\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s" +
                "\n\n%s\n\n%s\n\n%s\n\n%s%n";
        System.out.printf(str, days, cauliflower(crops, days), potato(crops, days),
                garlic(crops, days), kale(crops, days), parsnip(crops, days),
                strawberry(crops, days), blueJazz(crops, days),
                coffeeBeans(crops, days), greenBeans(crops, days), tulip(crops, days),
                rhubarb(crops, days), blueberry(crops, days), corn(crops, days));
    }

    /**
     * Calculates the integer amount of money made by
     * Cauliflower and returns the required money to
     * start.
     *
     * This plant grows in the spring.
     */
    private static String cauliflower(int crops, int NUM_DAYS) {
        int maturity = 12 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 80;
        int sPrice = 175;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Cauliflower Net gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }
    /**
     * Calculates the integer amount of money made by
     * Potato and returns the required money to start.
     *
     * There is a chance that there will be multiple
     * potatoes upon harvest, but this returns a flat value
     * based on the minimum amount of potatoes.
     *
     * This plant grows in the spring.
     */
    private static String potato(int crops, int NUM_DAYS) {
        int maturity = 6 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 50;
        int sPrice = 80;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Flat Potato Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * garlic and returns the money required to start.
     *
     * This plant grows in the spring.
     */
    private static String garlic(int crops, int NUM_DAYS) {
        int maturity = 4 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 40;
        int sPrice = 60;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Garlic Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * kale and returns the required money to start.
     *
     * This plant grows in the spring.
     */
    private static String kale(int crops, int NUM_DAYS) {
        int maturity = 6 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 70;
        int sPrice = 110;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Kale Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * parsnip and returns the money required to start.
     *
     * This plant grows in the spring.
     */
    private static String parsnip(int crops, int NUM_DAYS) {
        int maturity = 4 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 20;
        int sPrice = 35;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Parsnip Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * strawberry. it takes 8 days to mature and grows
     * more strawberries every 4 days. Also returns the
     * money need to start.
     *
     * There is a chance that there will be multiple
     * strawberries upon harvest, but this returns a flat value
     * based on the minimum amount of strawberries.
     *
     * this crop grows in the spring.
     */
    private static String strawberry(int crops, int NUM_DAYS) {
        int maturity = 8 + 1;
        int reGrow = 0;
        int maxHarvests = 0;
        int bPrice = 100;
        int sPrice = 120;
        int iPrice = bPrice * crops;
        while (maturity + reGrow <= NUM_DAYS) {
            reGrow += 4;
            maxHarvests++;
        }
        int netGains = (maxHarvests * sPrice * crops) -
                (bPrice * crops);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Flat Strawberry Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * Blue Jazz and returns the money required to start.
     *
     * This plant grows in the spring.
     */
    private static String blueJazz(int crops, int NUM_DAYS) {
        int maturity = 7 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 30;
        int sPrice = 50;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Blue Jazz Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * Coffee Bean bushes. it takes 10 days to mature and grows
     * 4 or more Coffee Beans every 2 days. Also returns the
     * money needed to start.
     *
     * There is a chance that there will be more than 4
     * coffee beans upon harvest, but this returns a flat value
     * based on the minimum amount of coffee beans.
     *
     * This plant grows in the spring and summer.
     */
    private static String coffeeBeans(int crops, int NUM_DAYS) {
        int maturity = 10 + 1;
        int reGrow = 0;
        int maxHarvests = 0;
        int perHarvest = 4;
        int bPrice = 2500;
        int sPrice = 15;
        int iPrice = bPrice * crops;
        while (maturity + reGrow <= NUM_DAYS) {
            reGrow += 2;
            maxHarvests++;
        }
        int netGains = (maxHarvests * sPrice * crops * perHarvest) -
                (bPrice * crops);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Flat Coffee Bean Bush Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * green beans. it takes 10 days to mature and grows
     * more strawberries every 3 days. Also returns the
     * money need to start.
     *
     * this crop grows in the spring.
     */
    private static String greenBeans(int crops, int NUM_DAYS) {
        int maturity = 10 + 1;
        int reGrow = 0;
        int maxHarvests = 0;
        int bPrice = 60;
        int sPrice = 40;
        int iPrice = bPrice * crops;
        while (maturity + reGrow <= NUM_DAYS) {
            reGrow += 3;
            maxHarvests++;
        }
        int netGains = (maxHarvests * sPrice * crops) -
                (bPrice * crops);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Green Bean Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * rhubarb and returns the money required to start.
     *
     * this crop grows in the spring.
     */
    private static String rhubarb(int crops, int NUM_DAYS) {
        int maturity = 13 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 100;
        int sPrice = 120;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Rhubarb Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * tulip and returns the money required to start.
     *
     * This crop grows in the spring.
     */
    private static String tulip(int crops, int NUM_DAYS) {
        int maturity = 6 + 1;
        int maxHarvests = NUM_DAYS / maturity;
        int bPrice = 20;
        int sPrice = 30;
        int iPrice = bPrice * crops;
        int netGains = (maxHarvests * sPrice * crops) -
                (maxHarvests * bPrice);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Tulip Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * blueberry bushes. it takes 10 days to mature and grows
     * 4 or more blueberries every 2 days. Also returns the
     * money needed to start.
     *
     * There is a chance that there will be more than 3
     * blueberries upon harvest, but this returns a flat value
     * based on the minimum amount of blueberries.
     *
     * This plant grows in the summer.
     */
    private static String blueberry(int crops, int NUM_DAYS) {
        int maturity = 13 + 1;
        int reGrow = 0;
        int maxHarvests = 0;
        int perHarvest = 4;
        int bPrice = 80;
        int sPrice = 50;
        int iPrice = bPrice * crops;
        while (maturity + reGrow <= NUM_DAYS) {
            reGrow += 4;
            maxHarvests++;
        }
        int netGains = (maxHarvests * sPrice * crops * perHarvest) -
                (bPrice * crops);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Flat blueberry Bush Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * corn stocks. it takes 10 days to mature and grows
     * more corn every 4 days. Also returns the
     * money needed to start.
     *
     * this crop grows in the summer and fall.
     */
    private static String corn(int crops, int NUM_DAYS) {
        int maturity = 14 + 1;
        int reGrow = 0;
        int maxHarvests = 0;
        int bPrice = 150;
        int sPrice = 50;
        int iPrice = bPrice * crops;
        while (maturity + reGrow <= NUM_DAYS) {
            reGrow += 4;
            maxHarvests++;
        }
        int netGains = (maxHarvests * sPrice * crops) -
                (bPrice * crops);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Corn Stock Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }

    /**
     * Calculates the integer amount of money made by
     * hops. it takes 11 days to mature and grows
     * more hops every day. Also returns the
     * money needed to start.
     *
     * this crop grows in the summer.
     */
    private static String hops(int crops, int NUM_DAYS) {
        int maturity = 11 + 1;
        int reGrow = 0;
        int maxHarvests = 0;
        int bPrice = 60;
        int sPrice = 25;
        int iPrice = bPrice * crops;
        while (maturity + reGrow <= NUM_DAYS) {
            reGrow += 1;
            maxHarvests++;
        }
        int netGains = (maxHarvests * sPrice * crops) -
                (bPrice * crops);
        if (NUM_DAYS < maturity) {
            netGains = -iPrice;
        }
        String str = "Hops Net Gains: $" + netGains;
        String str2 = "Starting Price: $" + iPrice;
        return str + "\n" + str2;
    }


}
