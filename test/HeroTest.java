import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    private HeroAircraft hero;
    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @BeforeEach
    void setUp() {
        hero=HeroAircraft.GetHeroAircraft();
    }

    @AfterEach
    void tearDown() { hero= null;}
    @DisplayName("Test getHp method")
    @org.junit.jupiter.api.Test
    void getHp(){
        int hp=hero.getHp();
        assertEquals(hp,1000);
    }

    @DisplayName("Test decreaseHp method")
    @org.junit.jupiter.api.Test
    void decreaseHp(){
        int hp1=hero.getHp();
        hero.decreaseHp(100);
        int hp2=hero.getHp();
        assertEquals(hp2,hp1-100);
    }
    @DisplayName("Test add_blood method")
    @org.junit.jupiter.api.Test
    void add_blood(){
        int hp1=hero.getHp();
        if (hp1 < 900) {
            hero.add_blood(100);
            int hp2=hero.getHp();
            assertEquals(hp2,hp1+100);
        }
        else{
            hero.add_blood(100);
            int hp3=hero.getHp();
            assertEquals(hp3,1000);
        }

    }


}
