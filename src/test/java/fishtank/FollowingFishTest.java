package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingFishTest {
  private Fish followee;
  private FollowingFish follower;

  @Before
  public void setUp() {
    followee = mock(Fish.class);
    //note: this is also why we use getters and setters so much in Java,
    //we wouldn't be able to mock the field itself if it were used instead
    //of the getter.
    when(followee.getX()).thenReturn(5);
    //This syntax is introduced by a library called mockito.
    //You can use it however you want, and it will be installed when we
    //run the grader.
    //See: http://www.vogella.com/tutorials/Mockito/article.html from 4 onwards
    when(followee.getY()).thenReturn(10);

    follower = new FollowingFish(followee);
    follower.setLocation(20, 20);
  }

  @Test
  public void testApproachesFromBottomRight() {
    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 15; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }
  @Test
  public void testTopBound()
  {
    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(25);
    when(fish.getY()).thenReturn(6);
    follower = new FollowingFish(fish);
    follower.setLocation(25, 5);
    follower.update();
    System.out.println(follower.getX() + " " + follower.getY());
    assertTrue(follower.getX() == 26 && follower.getY() == 5);
  }
  @Test
  public void testBottomBound()
  {
    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(25);
    when(fish.getY()).thenReturn(41);
    follower = new FollowingFish(fish);
    follower.setLocation(25, 42);
    follower.update();
    System.out.println(follower.getX() + " " + follower.getY());
    assertTrue(follower.getX() == 26 && follower.getY() == 42);
  }
  @Test
  public void testTurnToFace()
  {
    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(25);
    when(fish.getY()).thenReturn(41);
    follower = new FollowingFish(fish);
    follower.setLocation(20, 42);
    follower.goingRight = false;
    follower.update();
    assertTrue(follower.goingRight);
  }
  @Test
  public void testCollisionWithFish()
  {
    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(25);
    when(fish.getY()).thenReturn(41);
    follower.setLocation(23, 20);
    FishTank.addEntity(23, 20, follower);
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

    follower.update();
    assertTrue(follower.getX() == 23 && follower.getY() == 20);
  }
  @Test
  public void testCollisionWithHungryFish()
  {
    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(25);
    when(fish.getY()).thenReturn(41);

    follower.setLocation(23, 20);
    FishTank.addEntity(23, 20, follower);

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

    follower.update();
    assertTrue(follower.getX() == 23 && follower.getY() == 20);
  }
  @Test
  public void testCollisionWithBubble()
  {
    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(25);
    when(fish.getY()).thenReturn(41);

    follower.setLocation(23, 20);
    FishTank.addEntity(23, 20, follower);

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

    follower.update();
    assertTrue(follower.getX() == 23 && follower.getY() == 20);
  }
  @Test
  public void testCollisionWithSeaweed()
  {
    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(25);
    when(fish.getY()).thenReturn(41);

    follower.setLocation(23, 20);
    FishTank.addEntity(23, 20, follower);

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

    follower.update();
    assertTrue(follower.getX() == 23 && follower.getY() == 20);
  }
}
