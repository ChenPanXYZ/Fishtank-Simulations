package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {
    private Bubble bubble;
    @Before
    public void setUp() {
        bubble = new Bubble();
        FishTank.clear();
    }
    @Test
    public void testGoLeftRightProp()
    {
        int goLeft_num = 0;
        int goRight_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            bubble.setLocation(23, 20);
            int original_x = bubble.getX();
            bubble.update();
            if ((original_x - bubble.getX()) == 1)
            {
                goLeft_num += 1;
            }
            else if ((bubble.getX() - original_x) == 1)
            {
                goRight_num += 1;
            }
            FishTank.clear();
        }
        assertTrue(goLeft_num >= 280 && goLeft_num <= 380 && goRight_num >= 280 && goRight_num <= 380);
    }
    @Test
    public void testCollisionWithFish()
    {
        bubble.setLocation(23, 20);
        FishTank.addEntity(23, 20, bubble);

        Fish upFish = new Fish();
        upFish.setLocation(23, 19);
        FishTank.addEntity(23, 19, upFish);

        Fish rightUpFish = new Fish();
        rightUpFish.setLocation(24, 19);
        FishTank.addEntity(24, 19, rightUpFish);

        Fish leftUpFish = new Fish();
        leftUpFish.setLocation(22, 19);
        FishTank.addEntity(22, 19, leftUpFish);

        bubble.update();
        assertTrue(bubble.getX() == 23 && bubble.getY() == 20);
    }
    @Test
    public void testCollisionWithHungryFish()
    {
        bubble.setLocation(23, 20);
        FishTank.addEntity(23, 20, bubble);

        HungryFish upHungryFish = new HungryFish();
        upHungryFish.setLocation(23, 19);
        FishTank.addEntity(23, 19, upHungryFish);

        HungryFish rightUpHungryFish = new HungryFish();
        rightUpHungryFish.setLocation(24, 19);
        FishTank.addEntity(24, 19, rightUpHungryFish);

        HungryFish leftUpHungryFish = new HungryFish();
        leftUpHungryFish.setLocation(22, 19);
        FishTank.addEntity(22, 19, leftUpHungryFish);

        bubble.update();
        assertTrue(bubble.getX() == 23 && bubble.getY() == 20);
    }
    @Test
    public void testCollisionWithBubble()
    {
        bubble.setLocation(23, 20);
        FishTank.addEntity(23, 20, bubble);

        Bubble upBubble = new Bubble();
        upBubble.setLocation(23, 19);
        FishTank.addEntity(23, 19, upBubble);

        Bubble rightUpBubble = new Bubble();
        rightUpBubble.setLocation(24, 19);
        FishTank.addEntity(24, 19, rightUpBubble);

        Bubble leftUpBubble = new Bubble();
        leftUpBubble.setLocation(22, 19);
        FishTank.addEntity(22, 19, leftUpBubble);

        bubble.update();
        assertTrue(bubble.getX() == 23 && bubble.getY() == 20);
    }
    @Test
    public void testCollisionWithSeaweed()
    {
        bubble.setLocation(23, 20);
        FishTank.addEntity(23, 20, bubble);

        Seaweed upSeaweed = new Seaweed(8);
        upSeaweed.setLocation(23, 19);
        FishTank.addEntity(23, 19, upSeaweed);

        Seaweed rightUpSeaweed = new Seaweed(8);
        rightUpSeaweed.setLocation(24, 19);
        FishTank.addEntity(24, 19, rightUpSeaweed);

        Seaweed leftUpSeaweed = new Seaweed(8);
        leftUpSeaweed.setLocation(22, 19);
        FishTank.addEntity(22, 19, leftUpSeaweed);

        bubble.update();
        assertTrue(bubble.getX() == 23 && bubble.getY() == 20);
    }
    @Test
    public void testDeleteFromTop()
    {
        bubble.setLocation(20, 2);
        FishTank.addEntity(20, 2, bubble);
        bubble.update();
        assertFalse(bubble.exists());
    }
    @Test
    public void testDeleteFromLeft()
    {
        int deleteFromLeft_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            Bubble leftBubble = new Bubble();
            leftBubble.setLocation(5, 10);
            FishTank.addEntity(5, 10, bubble);
            leftBubble.update();
            if (!leftBubble.exists())
            {
                deleteFromLeft_num += 1;
            }
            FishTank.clear();
        }
        assertTrue(deleteFromLeft_num >= 280 && deleteFromLeft_num <= 380);
    }
    @Test
    public void testDeleteFromRight()
    {
        int deleteFromRight_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            Bubble rightBubble = new Bubble();
            rightBubble.setLocation(5, 10);
            FishTank.addEntity(5, 10, bubble);
            rightBubble.update();
            if (!rightBubble.exists())
            {
                deleteFromRight_num += 1;
            }
            FishTank.clear();
        }
        assertTrue(deleteFromRight_num >= 280 && deleteFromRight_num <= 380);
    }
}
