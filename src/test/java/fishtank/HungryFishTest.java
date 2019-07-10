package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HungryFishTest {

    /* Note: FishTest is in the package FishTank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto grading your tests, so make sure
       to follow this naming convention!
     */
    private HungryFish hungryfish;

    @Before
    public void setUp() {
        hungryfish = new HungryFish();
        FishTank.clear();
    }

    @Test
    public void testFishAlwaysInBound()
    {
        boolean stayInBound = true;
        for(int i = 0; i< 500; i++)
        {
            hungryfish.setLocation(3, 5);
            hungryfish.update();
            if (hungryfish.getX() < 3 || hungryfish.getY() < 5)
            {
                stayInBound = false;
                break;
            }
        }
        assertTrue(stayInBound);
        for(int i = 0; i< 500; i++)
        {
            hungryfish.setLocation(102, 5);
            hungryfish.update();
            if (hungryfish.getX() > 102 || hungryfish.getY() < 5)
            {
                stayInBound = false;
                break;
            }
        }
        assertTrue(stayInBound);
        for(int i = 0; i< 500; i++)
        {
            hungryfish.setLocation(102, 42);
            hungryfish.update();
            if (hungryfish.getX() > 102 || hungryfish.getY() > 42)
            {
                stayInBound = false;
                break;
            }
        }
        assertTrue(stayInBound);
    }
    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
        // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            hungryfish.setLocation(5, 10);
            hungryfish.goingRight =
                    false; //notice: I can edit package private attributes!
            hungryfish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(hungryfish.getX(), hungryfish.getY() - 1);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue(bubbleMade);
    }
    @Test
    public void testFishBubblesProp() {
        int bubble_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            hungryfish.setLocation(23, 20);
            hungryfish.update();
            if (FishTank.getEntity(hungryfish.getX(), hungryfish.getY() - 1) instanceof Bubble)
            {
                bubble_num += 1;
            }
            FishTank.clear();
        }
        assertTrue(bubble_num >= 50 && bubble_num <= 150);
    }
    @Test
    public void testHungryFishTurnAroundProp(){
        int turnAround_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            hungryfish.setLocation(23, 20);
            boolean original_direction =(hungryfish.goingRight);
            hungryfish.update();
            if (original_direction != hungryfish.goingRight)
            {
                turnAround_num += 1;
            }
        }
        assertTrue(turnAround_num >= 50 && turnAround_num <= 150);
    }
    @Test
    public void testFishMoveUpDownProp(){
        int moveUp_num = 0;
        int moveDown_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            hungryfish.setLocation(23, 20);
            int original_y = hungryfish.getY();
            hungryfish.update();
            if ((original_y - hungryfish.getY()) == 1)
            {
                moveUp_num += 1;
            }
            else if ((hungryfish.getY() - original_y) == 1)
            {
                moveDown_num += 1;
            }
            FishTank.clear();
        }
        assertTrue(moveDown_num >= 50 && moveDown_num <= 150 && moveUp_num >= 50 && moveUp_num <= 150);
    }
    @Test
    public void testHungryFishGoRight(){
        hungryfish.setLocation(23, 20);
        hungryfish.setGoingRight(true);
        int original_x = hungryfish.getX();
        hungryfish.update();
        assertEquals(1, (hungryfish.getX() - original_x));
    }
    @Test
    public void testHungryFishGoLeft(){
        hungryfish.setLocation(23, 20);
        hungryfish.setGoingRight(false);
        int original_x = hungryfish.getX();
        hungryfish.update();
        assertEquals(1, (original_x - hungryfish.getX()));
    }
    @Test
    public void testCollisionWithFish()
    {
        hungryfish.setLocation(23, 20);
        FishTank.addEntity(23, 20, hungryfish);

        Fish leftFish = new Fish();
        leftFish.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftFish);

        Fish rightFish = new Fish();
        rightFish.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightFish);

        Fish upFish = new Fish();
        upFish.setLocation(23, 19);
        FishTank.addEntity(23, 19, upFish);

        Fish bottomFish = new Fish();
        bottomFish.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomFish);

        hungryfish.update();
        assertTrue(hungryfish.getX() == 23 && hungryfish.getY() == 20);
    }
    @Test
    public void testCollisionWithHungryFish()
    {
        hungryfish.setLocation(23, 20);
        FishTank.addEntity(23, 20, hungryfish);

        HungryFish leftHungryFish = new HungryFish();
        leftHungryFish.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftHungryFish);

        HungryFish rightHungryFish = new HungryFish();
        rightHungryFish.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightHungryFish);

        HungryFish upHungryFish = new HungryFish();
        upHungryFish.setLocation(23, 19);
        FishTank.addEntity(23, 19, upHungryFish);

        HungryFish bottomHungryFish = new HungryFish();
        bottomHungryFish.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomHungryFish);

        hungryfish.update();
        assertTrue(hungryfish.getX() == 23 && hungryfish.getY() == 20);
    }
    @Test
    public void testCollisionWithBubble()
    {
        hungryfish.setLocation(23, 20);
        FishTank.addEntity(23, 20, hungryfish);

        Bubble leftBubble = new Bubble();
        leftBubble.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftBubble);

        Bubble rightBubble = new Bubble();
        rightBubble.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightBubble);

        Bubble upBubble = new Bubble();
        upBubble.setLocation(23, 19);
        FishTank.addEntity(23, 19, upBubble);

        Bubble bottomBubble = new Bubble();
        bottomBubble.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomBubble);

        hungryfish.update();
        assertTrue(hungryfish.getX() == 23 && hungryfish.getY() == 20);
    }
    @Test
    public void testCollisionWithSeaweed()
    {
        hungryfish.setLocation(23, 20);
        FishTank.addEntity(23, 20, hungryfish);

        Seaweed leftSeaweed = new Seaweed(4);
        leftSeaweed.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftSeaweed);

        Seaweed rightSeaweed = new Seaweed(6);
        rightSeaweed.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightSeaweed);

        Seaweed upSeaweed = new Seaweed(7);
        upSeaweed.setLocation(23, 19);
        FishTank.addEntity(23, 19, upSeaweed);

        Seaweed bottomSeaweed = new Seaweed(9);
        bottomSeaweed.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomSeaweed);

        hungryfish.update();
        assertTrue(hungryfish.getX() == 23 && hungryfish.getY() == 20);
    }

    @Test
    public void testEatSeaweed()
    {
        int eat_correct_num = 0;
        for(int i =0; i < 1000; i++)
        {
            Seaweed seaweed = new Seaweed(9);
            seaweed.setLocation(22, 25);
            hungryfish.setLocation(23, 20);
            hungryfish.setGoingRight(false);
            FishTank.addEntity(23, 20, hungryfish);
            FishTank.addEntity(22, 25, seaweed);
            hungryfish.update();
            if (seaweed.getLength() == 5)
            {
                eat_correct_num += 1;
            }
            FishTank.clear();
        }
        assertEquals(1000, eat_correct_num);
    }
}
