package achievementpopups;

import achievementpopups.patches.CreditsScreenRenderPatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

public class AchievementTracker {
  private AchievementTracker() {

  }
  private static List<AbstractGameEffect> achievementsToRender = new ArrayList<>();
  private static LocalDateTime lastTimeCreditAchievementWasRendered = LocalDateTime.MIN;

  public static void addAchievementToRenderQueue(Achievement achievement) {
    if (shouldRenderAchievement(achievement)) {
      achievementsToRender.add(achievement.getPopupVfx());
      achievementsToRender.add(achievement.getBadgeVfx());
      achievementsToRender.add(achievement.getTextVfx());
    }
  }

  public static void renderAllQueuedAchievements(SpriteBatch spriteBatch) {
    achievementsToRender.forEach(it -> it.render(spriteBatch));
    achievementsToRender.forEach(AbstractGameEffect::update);
    achievementsToRender.removeIf(it -> it.isDone);
  }


  private static boolean shouldRenderAchievement(Achievement achievement) {
    if (CreditsScreenRenderPatch.ACHIEVEMENT.equals(achievement.getName())) {
      // Don't show the achievement if you showed it in the last minute (testing purposes)
      if (LocalDateTime.now().minus(Duration.ofMinutes(1L)).isBefore(lastTimeCreditAchievementWasRendered)) {
        return false;
      }
      lastTimeCreditAchievementWasRendered = LocalDateTime.now();
      return true;
    }
    return true;
  }
}
