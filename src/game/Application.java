package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.blacksmith.BlackSmith;
import game.factorymonster.*;
import game.item.*;
import game.item.weapon.broadsword.BroadSword;
import game.ground.*;
import game.ground.burialgroundgate.BurialGroundGate;
import game.ground.puddle.Puddle;
import game.item.weapon.gianthammer.GiantHammer;
import game.item.weapon.greatknife.GreatKnife;
import game.playerenemy.ForestWatcher;
import game.playerenemy.WanderingUndead;
import game.traveller.Traveller;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Pits());

        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######..........................",
                "..........................#_____#..........................",
                "........~~................#_____#..........................",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~.......+.............~~~~.......................",
                "....~~~~~...................+.............###..##..........",
                "~~~~~~~...................................#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~~.................................#######..........");

        List<String> burialGround = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        List<String> ancientWoods = Arrays.asList(
                "....+++..............................+++++++++....~~~....~~~",
                "+...+++..............................++++++++.....~~~.....~~",
                "++...............#######..............++++.........~~.......",
                "++...............#_____#...........................~~~......",
                "+................#_____#............................~~......",
                ".................###_###............~...............~~.....~",
                "...............................~.+++~~..............~~....~~",
                ".....................~........~~+++++...............~~~...~~",
                "....................~~~.........++++............~~~~~~~...~~",
                "....................~~~~.~~~~..........~........~~~~~~.....~",
                "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
                "+++++..............~~~~~~~~~~~........~~~........~~~~~......"
        );

        List<String> bossRoom = Arrays.asList(
                "~~~~.......+++......~+++++..............",
                "~~~~.......+++.......+++++..............",
                "~~~++......+++........++++..............",
                "~~~++......++...........+..............+",
                "~~~~~~...........+.......~~~++........++",
                "~~~~~~..........++++....~~~~++++......++",
                "~~~~~~...........+++++++~~~~.++++.....++",
                "~~~~~..............++++++~~...+++.....++",
                "......................+++......++.....++",
                ".......................+~~............++",
                ".......................~~~~...........++",
                "........................~~++...........+",
                ".....++++...............+++++...........",
                ".....++++~..............+++++...........",
                "......+++~~.............++++...........~",
                ".......++..++++.......................~~",
                "...........+++++......................~~",
                "...........++++++.....................~~",
                "..........~~+++++......................~",
                ".........~~~~++++..................~~..~"
        );

        List<String> overgrownSanctuary = Arrays.asList(
                "++++.....++++........++++~~~~~.......~~~..........",
                "++++......++.........++++~~~~.........~...........",
                "+++..................+++++~~.......+++............",
                "....................++++++......++++++............",
                "...................++++........++++++~~...........",
                "...................+++.........+++..~~~...........",
                "..................+++..........++...~~~...........",
                "~~~...........................~~~..~~~~...........",
                "~~~~............+++..........~~~~~~~~~~...........",
                "~~~~............+++.........~~~~~~~~~~~~..........",
                "++~..............+++.......+~~........~~..........",
                "+++..............+++......+++..........~~.........",
                "+++..............+++......+++..........~~.........",
                "~~~..............+++......+++..........~~~........",
                "~~~~.............+++......+++..........~~~........"
        );

        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap gameMap1 = new GameMap(groundFactory, burialGround);
        GameMap gameMap2 = new GameMap(groundFactory, ancientWoods);
        GameMap gameMap3 = new GameMap(groundFactory, bossRoom);
        GameMap gameMap4 = new GameMap(groundFactory, overgrownSanctuary);
        world.addGameMap(gameMap);
        world.addGameMap(gameMap1);
        world.addGameMap(gameMap2);
        world.addGameMap(gameMap3);
        world.addGameMap(gameMap4);

        WeatherEngine weatherEngine = new WeatherEngine();


        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        // Map0
        // Add WanderingUndead
        gameMap.at(23, 10).addActor(new WanderingUndead("Wandering Undead", 't', 100,50,30,"bites")); //23 10
        // Add Player
        Player player = new Player(gameMap.at(29, 6));
        world.addPlayer(player, gameMap3.at(5, 5));
        // Add Forest Watcher
        ForestWatcher forestWatcher = new ForestWatcher(player,weatherEngine, gameMap1, gameMap2);
        // Add BroadSword
        gameMap.at(31,5).addItem(new BroadSword());
        gameMap.at(31,5).addItem(new Heal());
        gameMap.at(31,5).addItem(new Flask());
        gameMap.at(31,5).addItem(new GreatKnife());
        gameMap.at(31,5).addItem(new GiantHammer());
        gameMap.at(31,5).addItem(new Runes(2000));
        gameMap.at(31,5).addItem(new BloodBerry());

        // Add GraveYard
        gameMap.at(29,10).setGround(new GraveYard(new WanderingUndeadFactory()));
        // Add Gate
        gameMap.at(28,12).setGround(new BurialGroundGate(gameMap1));
        //Add BlackSmith
        gameMap.at(28,5).addActor(new BlackSmith(gameMap3,forestWatcher));
        gameMap.at(27,5).addActor(new Traveller(gameMap3,forestWatcher));

        //Map1 Burial Ground
        // Add gate
        gameMap1.at(gameMap1.getXRange().min(),gameMap1.getYRange().min()).setGround(new BurialGroundGate(gameMap));
        // Add graveYard
        gameMap1.at(1,3).setGround(new GraveYard(new HollowSoldierFactory()));
        // Add gate go to the Ancient wood
        gameMap1.at(2,1).setGround(new BurialGroundGate(gameMap2));
        // Add key
        gameMap1.at(3,1).addItem(new BurialGroundKey());

        // Map2 Ancient Wood
        // Add Hut
        gameMap2.at(10,5).setGround(new Hut(new ForestKeeperFactory(player)));
        // Add Bush
        gameMap2.at(12,8).setGround(new Bush(new RedWolfFactory(player)));
        // Add BloodBerry
        gameMap2.at(21,3).addItem(new BloodBerry());
        gameMap2.at(21,3).addItem(new Runes(3000));

        // Add traveller
        gameMap2.at(19,3).addActor(new Traveller(gameMap3,forestWatcher));
        // Add gate
        gameMap2.at(19,7).setGround(new BurialGroundGate(gameMap3));
        // Add key
        gameMap2.at(19,7).addItem(new BurialGroundKey());
        // Add gate
        gameMap2.at(5,7).setGround(new BurialGroundGate(gameMap1));
        // Add key
        gameMap2.at(5,7).addItem(new BurialGroundKey());


        // Map3 the Boss Room
        gameMap3.at(5,6).addItem(new GreatKnife());
        gameMap3.at(10,5).setGround(new Hut(new ForestKeeperFactory(weatherEngine)));
        gameMap3.at(12,7).setGround(new Bush(new RedWolfFactory(weatherEngine)));
        //Add Actor
        gameMap3.at(5,7).addActor(new ForestWatcher(player,weatherEngine, gameMap2, gameMap4));
        gameMap3.at(4,6).addItem(new BroadSword());
        gameMap2.at(5,5).addItem(new GiantHammer());
        gameMap2.at(5,5).addItem(new GreatKnife());
        gameMap3.at(5,7).setGround(new BurialGroundGate(gameMap1)); // temporary test gate
        gameMap3.at(5,8).addItem(new Runes(500));
        gameMap3.at(5,4).addItem(new BurialGroundKey());

        // Map4 Overgrown Sanctuary
        // gameMap4.at(10,5).setGround(new DoubleHut(new HollowSoldierFactory(), new EldentreeGuardianFactory(player)));
        MonsterFactory[] monsterFactoryArray = {new HollowSoldierFactory(), new EldentreeGuardianFactory(player)};
        gameMap4.at(10,5).setGround(new DoubleHut(monsterFactoryArray));
        MonsterFactory[] monsterFactoryArray1 = {new RedWolfFactory(), new LivingBranchFactory()};
        gameMap4.at(15,5).setGround(new DoubleBush(monsterFactoryArray1));

        world.run();
    }
}
