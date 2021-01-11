package achievementpopups.relics;

import achievementpopups.TheTodo;

import static achievementpopups.AchievementPopupsMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheTodo.Enums.TODO_COLOR);
    }
}
