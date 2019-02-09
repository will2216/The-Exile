package exileMod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.*;
import basemod.interfaces.*;
import exileMod.ExileCharacter;
import exileMod.cards.*;
import exileMod.patches.*;

@SpireInitializer
public class TheExileMod implements PostInitializeSubscriber,
EditStringsSubscriber, 
EditRelicsSubscriber,
EditCharactersSubscriber,
EditCardsSubscriber
{  
	public static final Logger logger = LogManager.getLogger(TheExileMod.class.getName());
	
	
	private static final String MODNAME = "Spirit Master";
    private static final String AUTHOR = "Beta Chess";
    private static final String DESCRIPTION = "Playable exhiled character";
    private static final Color PURPLE = CardHelper.getColor(136.0f, 0.0f, 186.0f);
    
    private static final String ATTACK_PURPLE = "Images/512/bg_attack_purple.png";
    private static final String SKILL_PURPLE = "Images/512/bg_skill_purple.png";
    private static final String POWER_PURPLE = "Images/512/bg_power_purple.png";
    private static final String ENERGY_ORB_PURPLE = "Images/512/card_purple_orb.png";

    private static final String ATTACK_PURPLE_PORTRAIT = "Images/1024/bg_attack_purple.png";
    private static final String SKILL_PURPLE_PORTRAIT = "Images/1024/bg_skill_purple.png";
    private static final String POWER_PURPLE_PORTRAIT = "Images/1024/bg_power_purple.png";
    private static final String ENERGY_ORB_PURPLE_PORTRAIT = "Images/1024/card_purple_orb.png";
    
    private static final String SpiritPortrait = "charstuff/YohaneBG.png";
    private static final String SpiritButton = "charstuff/YohaneButton.png";
    
    
    // !!! creating constructor 
	public TheExileMod() {
    	BaseMod.subscribe(this);
    	BaseMod.addColor(AbstractCardEnum.EXILEPURPLE,
                PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE,
                ATTACK_PURPLE, SKILL_PURPLE, POWER_PURPLE, ENERGY_ORB_PURPLE,
                ATTACK_PURPLE_PORTRAIT, SKILL_PURPLE_PORTRAIT, POWER_PURPLE_PORTRAIT,
                ENERGY_ORB_PURPLE_PORTRAIT);
    }
    
    
    // !!! Initialize mod
    public static void initialize() {
    	logger.info("------------------------- AscensionPlus initiation -------------------------");
    	
    	
    	@SuppressWarnings("unused")
		TheExileMod spiritMod = new TheExileMod();
    	
    	
    	logger.info("----------------------------------------------------------------------------");
    }
     
    
    
    // !!! creating mod badge and settings
    @Override
    public void receivePostInitialize() {
    	
    	// Mod badge
    	logger.info("Creating mod badge");
    	
    	ModPanel settingsPanel = new ModPanel();
        
    	Texture badgeTexture = new Texture("img/AscensionBadge.png");
    	

    	BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
    	logger.info("Mod badge created"); 
    }
    
    
    // !!! Creating Character
    @Override
	public void receiveEditCharacters() {
		logger.info("begin editing characters");
		
		logger.info("add " + ExileEnum.EXHILE.toString());
		ExileCharacter exhilecharacter = new ExileCharacter(CardCrawlGame.playerName, ExileEnum.EXHILE);
				
		BaseMod.addCharacter(exhilecharacter, SpiritButton, SpiritPortrait, ExileEnum.EXHILE);
		
		logger.info("done editing characters");
	}
    
    
    // !!! adding cards
	public void receiveEditCards() {

		BaseMod.addCard(new Strike_Purple());
		BaseMod.addCard(new Psyche());
		
		UnlockTracker.unlockCard(Strike_Purple.ID);
		UnlockTracker.unlockCard(Psyche.ID);
		
	}
    

    // !!! adding relics
    public void receiveEditRelics() {
    	logger.info("Editing relics");
    	
    	logger.info("Done editing relics");
    }

    
    // !!! loading strings for cards and relics
    @Override
    public void receiveEditStrings() {
    	logger.info("Editing strings");
    	
    	//BaseMod.loadCustomStrings(RelicStrings.class, loadJson("localization/eng/AscensionRelicStrings.json"));
    	
    	logger.info("Done editing strings");
    }
    
    
    // !!! json loader
    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
    
    // !!! resource getter
    public static final String getResourcePath(String resource) {
        return "Images/" + resource;
    }
    
    @SpireEnum
    public static AbstractCard.CardTags DEMON;
    @SpireEnum
    public static AbstractCard.CardTags LESSER;
    @SpireEnum
    public static AbstractCard.CardTags STANDARD;
    @SpireEnum
    public static AbstractCard.CardTags HIGHER;
    @SpireEnum
    public static AbstractCard.CardTags ENHANCING;
}