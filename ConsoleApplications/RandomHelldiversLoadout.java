package Misc.ConsoleApplications;

import javafx.scene.text.Font;

import java.util.Random;

/**
 * @author Jacob Swineford
 */
public class RandomHelldiversLoadout {

    public static void main(String[] args) {
        String[] weapons = {
                "AR-19 'Liberator'",
                "AR-20L 'Justice'",
                "AR-22C 'Patriot'",
                "SG-225 'Breaker'",
                "SG-8 'Punisher'",
                "DBS-2 'Double Freedom'",
                "SMG-45 'Defender'",
                "MP-98 'Knight' SMG",
                "RX-1 Rail Gun",
                "M2016 'Constitution'",
                "LAS-5 'Scythe'",
                "LAS-13 'Trident'",
                "AC-3 Arc Thrower",
                "MG-105 'Stalwart'",
                "SMG-34 'Ninja'",
                "LHO-63 'Camper'",
                "AR-14D 'Paragon'",
                "CR-9 ''Suppressor",
                "PLAS-1 'Scorcher'",
                "LAS-16 'Sickle'",
                "LAS-12 'Tanto'",
                "AC-5 Arc Shotgun"
        };
        String[] stratagems = {
                "Resupply",
                "MG-94 Machine Gun",
                "MGX-42 Machine Gun",
                "LAS-98 Laser Cannon",
                "'Obliterator' Grenade Launcher",
                "FLAM-40 'Incinerator'",
                "RL-112 Recoilless Rifle",
                "EAT-17",
                "Resupply Pack",
                "LIFT-850 Jump Pack",
                "SH-20 Shield Generator Pack",
                "REP-80",
                "EXO-44 'Walker' Exosuit",
                "M5 APC",
                "M5-32 HAV",
                "AC-22 'Dum-Dum'",
                "M-25 'Rumbler'",
                "TOX-13 'Avenger'",
                "MLS-4X 'Commando'",
                "AD-334 'Guard Dog'",
                "AD-289 'Angel'",
                "SH-32 Directional Kinetic Shield",
                "REC-6 'Demolisher'",
                "EXO-48 'Obsidian'",
                "EXO-51 'Lumberer'",
                "MC-109 'Hammer' Motorcycle",
                "TD-110 'Bastion'",
                "Distractor Beacon",
                "A/MG-|| Minigun Turret",
                "A/RX-34 Railcannon Turret",
                "A/GL-8 Launcher Turret",
                "A/AC-6 Tesla Tower",
                "Airdropped Anti-Personnel Mines",
                "'Thunderer' Smoke Round",
                "Airdropped Stun Mines",
                "'Humblebee' UAV drone",
                "At-47 Anti-tank Emplacement",
                "Anti-Personnel Barrier",
                "Static Field Conductors",
                "Airstrike",
                "'Vindicator' Dive Bomb",
                "Strafing Run",
                "Incendiary Bombs",
                "Heaving Strafing Run",
                "'Thunderer' Barrage",
                "Orbital Laser Strike",
                "'Shredder' Missile Strike",
                "Railcannon Strike",
                "Close Air Support",
                "Missile Barrage",
                "'Sledge' Precision Artillery"
        }; // special stratagems not included

        String[] perks = {
                "Laser Aim Module",
                "MD-99 Autoinjector",
                "Cardio Accelerator",
                "Heavy Armor",
                "Incendiary Grenades",
                "Stun Grenades",
                "Smoke Grenades",
                "Stratagem Priority",
                "Displacement Field",
                "Strong Arm",
                "P-6 'Gunslinger'",
                "FLAM-24 'Pyro'",
                "PLAS-3 'Singe'",
                "All Terrain Boots",
                "Precision Call-in"
        };

        Random rand = new Random();
        System.out.println("Perk: " + perks[rand.nextInt(perks.length)] + '\n');
        System.out.println("Weapon: " + weapons[rand.nextInt(weapons.length)] + '\n');
        System.out.println("Stratagems -> \n");
        System.out.println(stratagems[rand.nextInt(stratagems.length)]);
        System.out.println(stratagems[rand.nextInt(stratagems.length)]);
        System.out.println(stratagems[rand.nextInt(stratagems.length)]);
        System.out.println(stratagems[rand.nextInt(stratagems.length)]);

        System.out.println(Font.getFamilies());
    }
}
