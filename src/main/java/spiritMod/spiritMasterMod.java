package spiritMod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import spiritMod.spiritMasterCharacter;

import basemod.*;
import basemod.interfaces.*;
import spiritMod.patches.SpiritmasterEnum;

@SpireInitializer
public class spiritMasterMod implements PostInitializeSubscriber,
EditStringsSubscriber, 
EditRelicsSubscriber,
EditCharactersSubscriber
{  
	public static final Logger logger = LogManager.getLogger(spiritMasterMod.class.getName());
	
	
	private static final String MODNAME = "Spirit Master";
    private static final String AUTHOR = "Beta Chess";
    private static final String DESCRIPTION = "Playable Spirit Master Character";
    
    
    private static final String SpiritPortrait = "charstuff/YohaneBG.png";
    private static final String SpiritButton = "charstuff/YohaneButton.png";
    
    
    
    // !!! creating constructor 
	public spiritMasterMod() {
    	BaseMod.subscribe(this);
    }
    
    
    // !!! Initialize mod
    public static void initialize() {
    	logger.info("------------------------- AscensionPlus initiation -------------------------");
    	
    	
    	@SuppressWarnings("unused")
		spiritMasterMod spiritMod = new spiritMasterMod();
    	
    	
    	logger.info("----------------------------------------------------------------------------");
    }
     
    // !!! Creating Character
    @Override
	public void receiveEditCharacters() {
		logger.info("begin editing characters");
		
		logger.info("add " + SpiritmasterEnum.SPIRITMASTER.toString());
		BaseMod.addCharacter(new spiritMasterCharacter(CardCrawlGame.playerName, SpiritmasterEnum.SPIRITMASTER),
				SpiritButton,
				SpiritPortrait,
				SpiritmasterEnum.SPIRITMASTER);
		
		logger.info("done editing characters");
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
}