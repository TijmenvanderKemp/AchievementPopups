package achievementpopups.cards;

import achievementpopups.Achievement;
import achievementpopups.AchievementTracker;
import achievementpopups.util.TexLoader;
import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static achievementpopups.AchievementPopupsMod.makeID;

public class Strike extends AbstractTodoCard {

  public final static String ID = makeID("Strike");

  private static final int DAMAGE = 6;
  private static final int UPG_DAMAGE = 3;

  public Strike() {
    super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    baseDamage = DAMAGE;
    tags.add(CardTags.STRIKE);
    tags.add(CardTags.STARTER_STRIKE);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AchievementTracker.addAchievementToRenderQueue(
        new Achievement(
            "Strike!",
            "achievementpopupsResources/images/popups/achievementpopup.png",
            "achievementpopupsResources/images/popups/achievementbadge.png",
            "achievementpopupsResources/images/popups/achievementtext.png"
            ));
    dmg(m, AbstractGameAction.AttackEffect.NONE);
  }

  public void upp() {
    upgradeDamage(UPG_DAMAGE);
  }
}
