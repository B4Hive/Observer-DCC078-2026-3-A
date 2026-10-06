package b4hive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Observable;
import org.junit.jupiter.api.Test;

@SuppressWarnings ("deprecation")
public class AppTest {

	@Test
	void shouldReturnTheSameAttendantInstance() {
		Attendant first = Attendant.getInstance();
		Attendant second = Attendant.getInstance();

		assertSame(first, second);
	}

	@Test
	void shouldManageTheCurrentClient() {
		Attendant attendant = Attendant.getInstance();
		Client client = new Client();

		assertNull(attendant.getClient());

		attendant.setClient(client);

		assertSame(client, attendant.getClient());
	}

	@Test
	void shouldGenerateDistinctClientIds() {
		Client first = new Client();
		Client second = new Client();

		assertNotSame(first, second);
		assertEquals(first.getId() + 1, second.getId());
	}

	@Test
	void shouldNotifyClientsWithTheLastCalledNumber() {
		Attendant attendant = Attendant.getInstance();
		Client client = new Client();

		attendant.callClient();

		assertEquals(attendant.getLastCalled(), client.getLastCalled());
	}

	@Test
	public void shouldRejectNonIntegerNotificationArguments() {
		Client client = new Client();
		Observable observable = new Observable();

		RuntimeException exception = assertThrows(
				RuntimeException.class,
				() -> client.update(observable, "invalid"));

		assertEquals("O argumento passado para o método update não é do tipo Integer.", exception.getMessage());
	}

	@Test
	void shouldRejectNotificationsFromNonAttendantsWhenClientIdMatches() {
		Client client = new Client();
		Observable observable = new Observable();

		RuntimeException exception = assertThrows(
				RuntimeException.class,
				() -> client.update(observable, client.getId()));

		assertEquals(
				"O objeto Observable passado para o método update não é do tipo Attendant.",
				exception.getMessage());
	}

}
