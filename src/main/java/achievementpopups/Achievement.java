package achievementpopups;

import achievementpopups.util.TexLoader;
import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Achievement {
  private static final float MOVE_DURATION = .7f;
  private static final float SHOW_DURATION = 6f;
  private static final float BADGE_TOP_OFFSET = 14;
  private static final float BADGE_LEFT_OFFSET = 14;
  private static final float TEXT_TOP_OFFSET = 54;
  private static final float TEXT_LEFT_OFFSET = 88;

  private final String name;
  private final AbstractGameEffect popupVfx;
  private final AbstractGameEffect badgeVfx;
  private final AbstractGameEffect textVfx;

  public Achievement(String name, String achievementPopupTextureLocation, String achievementBadgeTextureLocation, String achievementTextTextureLocation) {
    this.name = name;

    Texture achievementPopup = TexLoader.getTexture(achievementPopupTextureLocation);
    float popupW = achievementPopup.getWidth();
    float popupH = achievementPopup.getHeight();
    popupVfx = pasteSomethingOnThePopup(popupW, popupH, achievementPopup, 0, 0);


    Texture achievementBadge = TexLoader.getTexture(achievementBadgeTextureLocation);
    badgeVfx = pasteSomethingOnThePopup(popupW, popupH, achievementBadge, BADGE_LEFT_OFFSET, BADGE_TOP_OFFSET);

    Texture achievementText = TexLoader.getTexture(achievementTextTextureLocation);
    textVfx = pasteSomethingOnThePopup(popupW, popupH, achievementText, TEXT_LEFT_OFFSET, TEXT_TOP_OFFSET);
  }

  private AbstractGameEffect pasteSomethingOnThePopup(float popupW, float popupH, Texture texture,
      float popupLeftOffset, float popupTopOffset) {
    float elementW = texture.getWidth();
    float elementH = texture.getHeight();
    return new VfxBuilder(texture, Settings.WIDTH - popupW + popupLeftOffset + elementW / 2,
        0 - popupTopOffset - elementH / 2, MOVE_DURATION)
        .setScale(1 / Settings.scale)
        .moveY(0 - popupTopOffset - elementH / 2, 0 + popupH - popupTopOffset - elementH / 2)
        .andThen(SHOW_DURATION)
        .andThen(MOVE_DURATION)
        .moveY(0 + popupH - popupTopOffset - elementH / 2, 0 - popupTopOffset - elementH / 2)
        .build();
  }

  public String getName() {
    return name;
  }

  public AbstractGameEffect getPopupVfx() {
    return popupVfx;
  }

  public AbstractGameEffect getBadgeVfx() {
    return badgeVfx;
  }

  public AbstractGameEffect getTextVfx() {
    return textVfx;
  }
}
