package exileMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModCardTags;
import exileMod.TheExileMod;
import exileMod.patches.AbstractCardEnum;

public class Psyche extends AbstractExileCard {

	public static final String ID = "Exhile:psyche";
	public static final String NAME = "Psyche";
    public static final String DESCRIPTION = "Deal !D! damage to all enemies. N Gain 2 Potency for one turn.";
    public static final String IMG_PATH = "Images/cards/Psyche.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 1;
    private static final int UPGRADE_BONUS = 3;
    
    
    public Psyche() {
    	super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, AbstractCardEnum.EXILEPURPLE, RARITY, TARGET);
    	
    	this.baseDamage = 6;
        this.tags.add(TheExileMod.ENHANCING); 
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new Psyche();
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
