package exileMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModCardTags;
import exileMod.TheExileMod;
import exileMod.patches.AbstractCardEnum;

public class Strike_Purple extends AbstractExileCard {

	public static final String ID = "Exhile:strike_p";
	public static final String NAME = "Strike";
    public static final String DESCRIPTION = "Deal !D! damage.";
    public static final String IMG_PATH = "Images/cards/Strike.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int UPGRADE_BONUS = 3;
    
    
    public Strike_Purple() {
    	super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, AbstractCardEnum.EXILEPURPLE, RARITY, TARGET);
    	
    	this.baseDamage = 6;
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new Strike_Purple();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
        com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
}
