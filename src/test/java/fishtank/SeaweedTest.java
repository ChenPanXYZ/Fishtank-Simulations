package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeaweedTest {
    private Seaweed seaweed;
    @Before
    public void setUp()
    {
        seaweed = new Seaweed(8);
        FishTank.clear();
    }
    @Test
    public void testSeaweedRecover()
    {
        int i;
        seaweed.setLocation(3, 5);
        seaweed.beingEaten(4);
        assertEquals(4, seaweed.getLength());
        for (i =0; i<199; i++)
        {
            seaweed.update();
        }
        assertEquals(4, seaweed.getLength());
        seaweed.update();
        assertEquals(8, seaweed.getLength());
        FishTank.clear();
    }
    @Test
    public void testFishEatSeaweed()
    {
        Fish fish = new Fish();
        int eat_correct_num = 0;
        for(int i =0; i < 1000; i++)
        {
            seaweed.setLocation(22, 25);
            fish.setLocation(23, 20);
            fish.setGoingRight(false);
            FishTank.addEntity(23, 20, fish);
            FishTank.addEntity(22, 25, seaweed);
            fish.update();
            if (seaweed.getLength() == 5)
            {
                eat_correct_num += 1;
            }
            seaweed.recover();
            FishTank.clear();
        }
        assertEquals(eat_correct_num, 1000);
    }
    @Test
    public void testEatSeaweed()
    {
        HungryFish hungryFish = new HungryFish();
        int eat_correct_num = 0;
        for(int i =0; i < 1000; i++)
        {
            Seaweed seaweed = new Seaweed(9);
            seaweed.setLocation(22, 25);
            hungryFish.setLocation(23, 20);
            hungryFish.setGoingRight(false);
            FishTank.addEntity(23, 20, hungryFish);
            FishTank.addEntity(22, 25, seaweed);
            hungryFish.update();
            if (seaweed.getLength() == 5)
            {
                eat_correct_num += 1;
            }
            seaweed.recover();
            FishTank.clear();
        }
        assertEquals(eat_correct_num, 1000);
    }
}
