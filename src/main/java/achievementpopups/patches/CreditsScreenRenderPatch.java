package achievementpopups.patches;

import achievementpopups.Achievement;
import achievementpopups.AchievementTracker;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.credits.CreditsScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import javassist.CtBehavior;

@SpirePatch(clz = MainMenuScreen.class, method = "render")
public class CreditsScreenRenderPatch {
  public static final String ACHIEVEMENT = "LookingAtCredits";

  @SpireInsertPatch(locator = Locator.class)
  public static void patchMethod(MainMenuScreen __instance) {
    AchievementTracker.addAchievementToRenderQueue(
        new Achievement(
            ACHIEVEMENT,
            "achievementpopupsResources/images/popups/achievementpopup.png",
            "achievementpopupsResources/images/popups/achievementbadge.png",
            "achievementpopupsResources/images/popups/achievementtext2.png"
        ));
  }

  private static class Locator extends SpireInsertLocator {
    @Override
    public int[] Locate(CtBehavior ctBehavior) throws Exception {
      Matcher matcher = new Matcher.MethodCallMatcher(CreditsScreen.class, "render");
      return LineFinder.findInOrder(ctBehavior, matcher);
    }
  }
}
