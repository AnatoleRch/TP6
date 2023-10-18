package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	// S3 : on n’imprime pas leticket si le montant inséré est insuffisant
	void ticketnotprintedifnomoney() {
		machine.insertMoney(PRICE-1);
                // Les montants ont été correctement additionnés  
		assertFalse(machine.printTicket(), "le ticket est imprimé alors que montant inséré est insuffisant");              
	}

	@Test
	// S4 : on imprime le ticket si le montant inséré est suffisant
	void ticketcorrectlyprinted() {
		machine.insertMoney(PRICE);
                // Les montants ont été correctement additionnés  
		assertTrue(machine.printTicket(), "le ticket n'est pas imprimé alors que montant inséré est suffisant");              
	}

	@Test
	// S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void printticketChangesBalance() {
		machine.insertMoney(PRICE);
		machine.printTicket();
                // Les montants ont été correctement additionnés  
		assertTrue(machine.getBalance()==0, "la balance na pas été correctement décrementée");              
	}

	@Test
	// S6.1 : le montant collecté n'est pas mis à jour si on imprime pas de ticket
	void montantcollectepasenavance() {
		machine.insertMoney(PRICE);
                // Les montants ont été correctement additionnés  
		assertTrue(machine.getTotal()==0, "le total collecté a augmenté sans raisons");              
	}

	@Test
	// S6.2 : le montant collecté est mis à jour quand on imprime un ticket
	void montantcollectéqunadonimprime() {
		machine.insertMoney(PRICE);
		machine.printTicket();
                // Les montants ont été correctement additionnés  
		assertTrue(machine.getTotal()==PRICE, "le montant total n'a pas bien été incrémenté");              
	}

	@Test
	// S7 : refundrendcorrectement la monnaie
	void remboursementok() {
		machine.insertMoney(PRICE+30);
		machine.printTicket();
                // Les montants ont été correctement additionnés  
		assertTrue(machine.refund()==30, "le rembourcement n'est pas du bon montant");              
	}

	@Test
	//S8 : refund remet la balance à zéro	
	void refundsetbalanceto0() {
		machine.insertMoney(PRICE+30);
		machine.printTicket();
		machine.refund();
                // Les montants ont été correctement additionnés  
		assertTrue(machine.getBalance()==0, "la balance n'est pas remise à 0");              
	}

	@Test
	// S9 : on ne peut pas insérerun montant négatif
	void nonegativeinsertmoney() {
		try {
			machine.insertMoney(-30); // Cette ligne doit lever une exception
			// Si on arrive ici, c'est qu'on n'a pas eu d'exception -> le test doit échouer
			fail("Cet appel doit lever une exception");
		} catch (IllegalArgumentException e) {
			// Si on arrive ici c'est normal, le test est réussi
		}
	}

	@Test
	// S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	void nonegativeticketprice() {
		try {
			machine = new TicketMachine(-50); // Cette ligne doit lever une exception
			// Si on arrive ici, c'est qu'on n'a pas eu d'exception -> le test doit échouer
			fail("Cet appel doit lever une exception");
		} catch (IllegalArgumentException e) {
			// Si on arrive ici c'est normal, le test est réussi
		}
	}


}
