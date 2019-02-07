package spiritMod;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import spiritMod.patches.*;

import basemod.animations.SpriterAnimation;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.characters.CustomCharSelectInfo;

import com.megacrit.cardcrawl.helpers.*;


public class spiritMasterCharacter extends AbstractPlayerWithMinions {
	public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
	private static final Color GREY = CardHelper.getColor(131.0f, 156.0f, 165.0f);
	
	public static final String[] orbTextures = {
			"Images/orb/layer1.png", 
			"Images/orb/layer2.png", 
			"Images/orb/layer3.png", 
			"Images/orb/layer4.png", 
			"Images/orb/layer5.png", 
			"Images/orb/layer6.png", 
			"Images/orb/layer1d.png", 
			"Images/orb/layer2d.png", 
			"Images/orb/layer3d.png", 
			"Images/orb/layer4d.png", 
			"Images/orb/layer5d.png"
	};
	
	
	public CustomCharSelectInfo getInfo() {
        return (CustomCharSelectInfo) getLoadout ();
    }
	
	public spiritMasterCharacter(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, "Images/orb/vfx.png", new SpriterAnimation("charassets/animation.scml"));
		
		this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
		
		initializeClass(null, "charassets/shoulder2.png", "charassets/shoulder.png", "charassets/corpse.png",
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
	}
	
	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		retVal.add("Strike_R");
		return retVal;
	}

	@Override
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		//retVal.add("Yohane:AngelWings");
		//UnlockTracker.markRelicAsSeen("Yohane:AngelWings");
		return retVal;
	}

    public CharSelectInfo getLoadout() {

        CharSelectInfo info = new CustomCharSelectInfo (
                "Spirit Master",
                "A master of the spirit arts. ",
                60, //currentHP
                60, //maxHP
                0,  //maxOrbs
                2,  //maxMinions
                99, //gold
                5,  //cardDraw
                this,
                getStartingRelics(),
                getStartingDeck(),
                false);
        return info;
    }

    @Override
	public String getTitle(PlayerClass playerClass) {
		return "the Never Forgotten";
	}

	@Override
	public AbstractCard.CardColor getCardColor() {
		return AbstractCardEnum.SPIRITBLUE;
	}

	@Override
	public Color getCardRenderColor() {
		return GREY;
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		return new Strike_Red();
	}

	@Override
	public Color getCardTrailColor() {
		return GREY;
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return 5;
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontRed;
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playA("ATTACK_FIRE", MathUtils.random(-0.2f, 0.2f));
		CardCrawlGame.sound.playA("ATTACK_FAST", MathUtils.random(-0.2f, 0.2f));
		CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);

	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_FIRE";
	}

	@Override
	public String getLocalizedCharacterName() {
		return "Yohane";
	}

	@Override
	public AbstractPlayer newInstance() {
		return new spiritMasterCharacter(this.name, SpiritmasterEnum.SPIRITMASTER);
	}

	@Override
	public TextureAtlas.AtlasRegion getOrb() {
		return AbstractCard.orb_red;
	}

	@Override
	public String getSpireHeartText() {
		return "NL You gather all your Fallen Energy...";
	}

	@Override
	public Color getSlashAttackColor() {
		return Color.BLACK;
	}

	@Override
	public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
		return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY};
	}

	@Override
	public String getVampireText() {
		return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us~ ~weeb,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
	}
	
}
