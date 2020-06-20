package gameplay;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import ui.UI;
import weapon.Pistol;
import weapon.PlasmaCannon;

/**
 * This acts as our remote control and the Client.
 * @author Moumouni Noma
 *
 */
public class InvokerBuilder 
{
	public static void main(String[] args) throws ExistingWorldException 
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		Invoker remoteControl = new Invoker(); 
		UI ui = new UI(e);
		LifeForm target = new Human("Bob", 10, 10);
		LifeForm attacker = new Alien("Jim", 10);
		attacker.changeDirection(LifeForm.NORTH);
		e.addLifeForm(target, 0, 0);
		ui.updateGrid();
		e.addLifeForm(attacker, 1, 0);
		remoteControl.setLifeForm(attacker);
		ui.updateGrid();
		
		PlasmaCannon pc = new PlasmaCannon(10, 10, 10, 10);
		e.addWeapon1(pc, 1, 0);
		ui.updateGrid();
		Pistol p = new Pistol(10, 10, 10, 10);
		e.addWeapon2(p, 1, 0);
		ui.updateGrid();

		Command rc = new ReloadCommand();
		Command atc = new AttackCommand();
		Command aqc = new AcquireCommand();
		Command dc = new DropCommand();
		Command mc = new MoveCommand();
		Command nc = new TurnCommand(LifeForm.NORTH);
		Command sc = new TurnCommand(LifeForm.SOUTH);
		Command ec = new TurnCommand(LifeForm.EAST);
		Command wc = new TurnCommand(LifeForm.WEST);

		remoteControl.setCommand(rc, Command.RELOAD); 
		remoteControl.setCommand(atc, Command.ATTACK); 
		remoteControl.setCommand(aqc, Command.ACQUIRE); 
		remoteControl.setCommand(dc, Command.DROP);
		remoteControl.setCommand(mc, Command.MOVE);
		remoteControl.setCommand(nc, Command.NORTH); 
		remoteControl.setCommand(sc, Command.SOUTH);
		remoteControl.setCommand(ec, Command.EAST);
		remoteControl.setCommand(wc, Command.WEST);
		ui.updateGrid();
		System.out.println(remoteControl);
	
	}

}
