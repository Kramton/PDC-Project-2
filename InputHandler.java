
package main;

import java.util.Random;
import javax.swing.SwingUtilities;

/**
 *
 * @author cqm0237
 */
public class InputHandler {

    //player attacks
    public void pCombat(PlayerPanel player, MonsterPanel monster) {
        
        Random rand = new Random();
        String out = "";
        out += "<html>You are attacking " + monster.getName() + "!<br>";
        double luckyStrike = rand.nextDouble();
        int multiplier = rand.nextInt(2) + 2;
        
        if(luckyStrike >=0.7){
            out += "You attack crits, gaining " + multiplier + " times the power!<br>";
            out += monster.getName() + " takes " + player.getAttack()*multiplier + " damage<br>";
            monster.setHealth(monster.getHealth() - player.getAttack()*multiplier); 
        }
        else{
            out += monster.getName() + " takes " + player.getAttack() + " damage<br>";
            monster.setHealth(monster.getHealth() - player.getAttack());
        }
        
        
        if (monster.getHealth() > 0) {
            out += monster.getName() + " has " + monster.getHealth() + " HP left";
        } 
        else {
            out += monster.getName() + " has 0 HP left</html>";
        }
        
        final String pCombatLog = out;
        
        SwingUtilities.invokeLater(() -> GamePanel.gameChatLog.setText(pCombatLog));
        
        monster.updateStatusLabel();
        
        try {
            Thread.sleep(1000); // Simulate player attacking monster
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        if(monster.isDead()){
            try {
                    Thread.sleep(2000); // Simulate monster dying animation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    //monster attacks
    public void mCombat(PlayerPanel player, MonsterPanel monster) {
        String out = "";
        
        out += "<html>" + monster.getName() + "'s turn! <br>";
        out += monster.getName() + " attacks!<br>";
        player.changeDefense(monster.getAttack() * -1);//change defense by negative

        if (player.getHealth() > 0) {
            out += "You have " + player.getHealth() + " HP and " + player.getDefense() + " DEF left</html>";
        } 
        else {
            out += "You have 0 HP left</html>";
        }
        
        final String pCombatLog = out;
        SwingUtilities.invokeLater(() -> GamePanel.gameChatLog.setText(pCombatLog));
        
        player.updateStatusLabel();
    }

    /*
    //use item
    public void useItem(PlayerPanel player, int itemIndex) {
        itemIndex--;

        if (player.getItems()[itemIndex] == null) {
            System.out.println("You have no item in that slot!");
        } //if the item is a potion, add health by the stat of the potion
        else if (player.getItems()[itemIndex].getName().equals("Potion")) {
            player.changeHealth(player.getItems()[itemIndex].getStat());
            System.out.println("Your health has increased by " + player.getItems()[itemIndex].getStat());
            player.setItems(itemIndex, null);
        } //if the item is a sword, add attack by the stat of the sword
        else if (player.getItems()[itemIndex].getName().equals("Sword")) {
            player.changeAttack(player.getItems()[itemIndex].getStat());
            System.out.println("Your attack has increased by " + player.getItems()[itemIndex].getStat());
            player.setItems(itemIndex, null);
        } //if the item is a shield, add defense by the stat of the shield
        else if (player.getItems()[itemIndex].getName().equals("Shield")) {
            player.changeDefense(player.getItems()[itemIndex].getStat());
            System.out.println("Your defense has increased by " + player.getItems()[itemIndex].getStat());
            player.setItems(itemIndex, null);
        }
    }*/
    
    
    public void help() {
        GamePanel.gameChatLog.setText("<html>=============================== HELP =============================="
                        + "<br> + Defeat the monster in each room to advance to the next level!"
                        + "<br> + After clearing the room, you will receive a random item."
                        + "<br> + Defeat the Boss at room 5 to beat the game!"
                        + "<br> + Use swords to increase your damage!"
                        + "<br> + Use shields to increase your defense!"
                        + "<br> + Use potions to heal yourself!"
                        + "<br> + Don't forget to save game!"
                        + "<br> ===================================================================</html>");
    }
}
